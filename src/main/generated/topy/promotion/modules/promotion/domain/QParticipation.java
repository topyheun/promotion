package topy.promotion.modules.promotion.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParticipation is a Querydsl query type for Participation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParticipation extends EntityPathBase<Participation> {

    private static final long serialVersionUID = 1965105086L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParticipation participation = new QParticipation("participation");

    public final topy.promotion.modules.common.QBaseDateEntity _super = new topy.promotion.modules.common.QBaseDateEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPromotion promotion;

    public final topy.promotion.modules.user.QUser user;

    public QParticipation(String variable) {
        this(Participation.class, forVariable(variable), INITS);
    }

    public QParticipation(Path<? extends Participation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParticipation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParticipation(PathMetadata metadata, PathInits inits) {
        this(Participation.class, metadata, inits);
    }

    public QParticipation(Class<? extends Participation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.promotion = inits.isInitialized("promotion") ? new QPromotion(forProperty("promotion")) : null;
        this.user = inits.isInitialized("user") ? new topy.promotion.modules.user.QUser(forProperty("user")) : null;
    }

}

