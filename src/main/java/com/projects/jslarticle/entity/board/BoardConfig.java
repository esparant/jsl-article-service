package com.projects.jslarticle.entity.board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description BoardConfig Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-27
 */
@Table(
        name = "board_config",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_board_config_board_id",
                        columnNames = {"board_id"}
                )
        }
)
@Entity
@Getter
public class BoardConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "board_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_board_config_board_id")
    )
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "board_icon_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_board_config_board_icon_id")
    )
    private BoardIcon boardIcon;

    @Column(nullable = false)
    private Integer popLeastLike;

    @Column(nullable = false)
    private Boolean dislikeAvailable;

    @Column(nullable = false)
    private Boolean dislikeInfluence;

    private LocalDateTime updatedAt;
}
