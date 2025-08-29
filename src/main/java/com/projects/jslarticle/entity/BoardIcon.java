package com.projects.jslarticle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description BoardIcon Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-27
 */
@Entity
@Getter
public class BoardIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "boardIcon")
    private Board board;

    private String currentIconUrl;

    private String previousIconUrl;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
