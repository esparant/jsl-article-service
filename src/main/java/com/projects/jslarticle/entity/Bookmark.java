package com.projects.jslarticle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Bookmark Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-29
 */
@Table(
        name = "bookmark",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_bookmark_user_id_article_id", columnNames = {"user_id", "artilce_id"})
        }
)
@Entity
@Getter
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_bookmark_user_id")
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "article_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_bookmark_article_id")
    )
    private Article article;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
