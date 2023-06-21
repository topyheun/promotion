package topy.promotion.modules.promotion.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReward is a Querydsl query type for Reward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReward extends EntityPathBase<Reward> {

    private static final long serialVersionUID = 542564178L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReward reward = new QReward("reward");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QPromotion promotion;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final EnumPath<Rank> rank = createEnum("rank", Rank.class);

    public QReward(String variable) {
        this(Reward.class, forVariable(variable), INITS);
    }

    public QReward(Path<? extends Reward> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReward(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReward(PathMetadata metadata, PathInits inits) {
        this(Reward.class, metadata, inits);
    }

    public QReward(Class<? extends Reward> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.promotion = inits.isInitialized("promotion") ? new QPromotion(forProperty("promotion")) : null;
    }

}

