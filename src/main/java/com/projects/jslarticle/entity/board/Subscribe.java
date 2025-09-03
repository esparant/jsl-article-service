package com.projects.jslarticle.entity.board;

import com.projects.jslarticle.entity.user.User;
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
 * @description Subscribe Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-09-02
 */
@Table(
        name = "role",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_subscribe_user_id_board_id", columnNames = {"user_id", "board_id"})
        }
)
@Entity
@Getter
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_subscribe_user_id")
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "board_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_subscribe_board_id")
    )
    private Board board;

    @Column(nullable = false)
    private Integer sortOrder;

    @Column(nullable = false)
    private Boolean notification;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
