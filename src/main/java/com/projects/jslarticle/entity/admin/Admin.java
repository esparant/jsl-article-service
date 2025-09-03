package com.projects.jslarticle.entity.admin;

import com.projects.jslarticle.entity.board.Board;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Admin Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-29
 */
@Table(name = "admin")
@Entity
@Getter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "board_id",
            foreignKey = @ForeignKey(name = "fk_admin_board_id")
    )
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_admin_user_id")
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "role_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_admin_role_id")
    )
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "admin_config_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_admin_admin_config_id")
    )
    private AdminConfig adminConfig;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
