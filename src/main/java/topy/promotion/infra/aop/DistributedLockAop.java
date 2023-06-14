package topy.promotion.infra.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAop {

    private final RedissonClient redissonClient;
    private final AopTransactionExecutor aopTransactionExecutor;

    private static final String REDISSON_LOCK_PREFIX = "LOCK:";

    @Around("@annotation(topy.promotion.infra.aop.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key = buildLockKey(distributedLock, joinPoint);

        RLock rLock = redissonClient.getLock(key);
        boolean lockAcquired = false;

        try {
            lockAcquired = rLock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());

            if (lockAcquired) {
                return aopTransactionExecutor.proceed(joinPoint);
            } else {
                return false;
            }
        } finally {
            if (lockAcquired) {
                rLock.unlock();
            }
        }
    }

    private String buildLockKey(DistributedLock distributedLock, ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object dynamicKey = DynamicValueParser.getDynamicValue(
                methodSignature.getParameterNames(),
                joinPoint.getArgs(),
                distributedLock.key()
        );
        return REDISSON_LOCK_PREFIX + dynamicKey;
    }
}
