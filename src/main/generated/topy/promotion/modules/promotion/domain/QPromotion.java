package topy.promotion.modules.promotion.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPromotion is a Querydsl query type for Promotion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPromotion extends EntityPathBase<Promotion> {

    private static final long serialVersionUID = -394570336L;

    public static final QPromotion promotion = new QPromotion("promotion");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Participation, QParticipation> participations = this.<Participation, QParticipation>createList("participations", Participation.class, QParticipation.class, PathInits.DIRECT2);

    public final ListPath<Reward, QReward> rewards = this.<Reward, QReward>createList("rewards", Reward.class, QReward.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final EnumPath<PromotionStatus> status = createEnum("status", PromotionStatus.class);

    public final StringPath title = createString("title");

    public QPromotion(String variable) {
        super(Promotion.class, forVariable(variable));
    }

    public QPromotion(Path<? extends Promotion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPromotion(PathMetadata metadata) {
        super(Promotion.class, metadata);
    }

}

