package topy.promotion.modules.promotion.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWinner is a Querydsl query type for Winner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWinner extends EntityPathBase<Winner> {

    private static final long serialVersionUID = 689148002L;

    public static final QWinner winner = new QWinner("winner");

    public final topy.promotion.modules.common.QBaseDateEntity _super = new topy.promotion.modules.common.QBaseDateEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath participatedPromotionTitle = createString("participatedPromotionTitle");

    public final StringPath winnerName = createString("winnerName");

    public final StringPath winnerRank = createString("winnerRank");

    public final StringPath winnerReward = createString("winnerReward");

    public QWinner(String variable) {
        super(Winner.class, forVariable(variable));
    }

    public QWinner(Path<? extends Winner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWinner(PathMetadata metadata) {
        super(Winner.class, metadata);
    }

}

