package topy.promotion.infra.aop;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.stream.IntStream;

public class DynamicValueParser {

    private DynamicValueParser() {
    }

    public static Object getDynamicValue(String[] parameterNames, Object[] args, String key) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        IntStream.range(0, parameterNames.length)
                .forEach(index -> context.setVariable(parameterNames[index], args[index]));

        return parser.parseExpression(key).getValue(context, Object.class);
    }
}
