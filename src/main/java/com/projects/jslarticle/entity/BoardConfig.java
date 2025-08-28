package com.projects.jslarticle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author 탁영복
 * @since 2025-08-27
 * @version 1.0
 * @description BoardConfig 엔티티입니다. 추가 엔티티 제작후 수정필요 합니다.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BoardConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne
    @JoinColumn(name = "board_icon_id")
    private BoardIcon boardIcon;

    private int bestLeast;

    private boolean dislikeAvailable;

    private boolean dislikeInfluence;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
