package com.projects.jslarticle.entity.board;

import com.projects.jslarticle.entity.article.ArticleCategory;
import com.projects.jslarticle.entity.content.Content;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Board Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-27
 */
@Table(
        name = "board",
        uniqueConstraints = @UniqueConstraint(columnNames = {"board_config_id"})
)
@Entity
@Getter
@PrimaryKeyJoinColumn(name = "id")
public class Board extends Content {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_config_id", nullable = false)
    private BoardConfig boardConfig;

    @OneToMany(mappedBy = "board")
    private List<ArticleCategory> articleCategoryList;

    @Column(nullable = false, length = 50)
    private String boardName;

    @Column(nullable = false, length = 255)
    private String description;
}
