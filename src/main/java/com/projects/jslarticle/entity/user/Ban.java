package com.projects.jslarticle.entity.user;

import com.projects.jslarticle.entity.admin.Admin;
import com.projects.jslarticle.entity.board.Board;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Ban Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-09-02
 */
@Table(name = "ban")
@Entity
@Getter
public class Ban {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "admin_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_ban_admin_id")
    )
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "board_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_ban_board_id")
    )
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_ban_user_id")
    )
    private User users;

    private String content;

    private Boolean isBlocked;
}
