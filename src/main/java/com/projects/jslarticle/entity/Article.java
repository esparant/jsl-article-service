package com.projects.jslarticle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0
 * @description Article 엔티티입니다. 추가 엔티티 제작후 수정필요 합니다.
 * @since 2025-08-27
 */
@Entity
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_category_id")
    private ArticleCategory articleCategory;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean isNotice;

    private String title;

    private String content;

    private String imageUrl;

    private Integer views;

    private Integer likeCount;

    private Integer dislikeCount;

    private Boolean isDeleted;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
