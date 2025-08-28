package com.projects.jslarticle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author 탁영복
 * @since 2025-08-27
 * @version 1.0
 * @description Board 엔티티입니다. 추가 엔티티 제작후 수정필요 합니다.
 */
@Entity
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "board")
    private BoardConfig boardConfig;

    @OneToMany(mappedBy = "board")
    private List<ArticleCategory> articleCategoryList;

    private String boardName;

    private String boardDescription;

    private Boolean isDeleted;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
