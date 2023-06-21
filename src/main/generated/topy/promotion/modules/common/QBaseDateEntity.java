package topy.promotion.modules.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseDateEntity is a Querydsl query type for BaseDateEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseDateEntity extends EntityPathBase<BaseDateEntity> {

    private static final long serialVersionUID = 1248890889L;

    public static final QBaseDateEntity baseDateEntity = new QBaseDateEntity("baseDateEntity");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public QBaseDateEntity(String variable) {
        super(BaseDateEntity.class, forVariable(variable));
    }

    public QBaseDateEntity(Path<? extends BaseDateEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseDateEntity(PathMetadata metadata) {
        super(BaseDateEntity.class, metadata);
    }

}

