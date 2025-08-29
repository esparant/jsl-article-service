package com.projects.jslarticle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Comment Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-28
 */
@Table(name = "comment")
@Entity
@Getter
public class Comment extends Content {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "article_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_comment_article_id")
    )
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",
            nullable = true,
            foreignKey = @ForeignKey(name = "fk_comment_parent_id")
    )
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children;

    @Column(nullable = false, length = 255)
    private String content;
}
