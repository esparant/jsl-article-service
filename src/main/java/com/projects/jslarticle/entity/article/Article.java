package com.projects.jslarticle.entity.article;

import com.projects.jslarticle.entity.content.Content;
import com.projects.jslarticle.entity.admin.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Article Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-27
 */
@Table(name = "article")
@Entity
@Getter
public class Article extends Content {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "article_category_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_article_article_category_id")
    )

    private ArticleCategory articleCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "role_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_article_role_id")
    )
    private Role role;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @Column(nullable = false)
    private Boolean isNotice;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(length = 255)
    private String imageUrl;

    @Column(nullable = false)
    private Integer views;

    @Column(nullable = false)
    private Integer likeCount;

    @Column(nullable = false)
    private Integer dislikeCount;
}
