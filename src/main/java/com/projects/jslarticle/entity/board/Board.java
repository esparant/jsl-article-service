package com.projects.jslarticle.entity.board;

import com.projects.jslarticle.entity.article.ArticleCategory;
import com.projects.jslarticle.entity.content.Content;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Board Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-27
 */
@Table(name = "board")
@Entity
@Getter
@PrimaryKeyJoinColumn(
        name = "id",
        foreignKey = @ForeignKey(name = "fk_board_content_id")
)
public class Board extends Content {

    @OneToOne(mappedBy = "board")
    private BoardConfig boardConfig;

    @OneToMany(mappedBy = "board")
    private List<ArticleCategory> articleCategoryList;

    @Column(nullable = false, length = 50)
    private String boardName;

    @Column(nullable = false, length = 255)
    private String description;
}
