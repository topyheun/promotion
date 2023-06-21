package topy.promotion.modules.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -899719982L;

    public static final QUser user = new QUser("user");

    public final topy.promotion.modules.common.QBaseTimeEntity _super = new topy.promotion.modules.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<topy.promotion.modules.promotion.domain.Participation, topy.promotion.modules.promotion.domain.QParticipation> participations = this.<topy.promotion.modules.promotion.domain.Participation, topy.promotion.modules.promotion.domain.QParticipation>createList("participations", topy.promotion.modules.promotion.domain.Participation.class, topy.promotion.modules.promotion.domain.QParticipation.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

