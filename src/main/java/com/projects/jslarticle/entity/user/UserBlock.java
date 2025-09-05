package com.projects.jslarticle.entity.user;

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
 * @description UserBlock Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-29
 */
@Table(
        name = "user_block",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_block_user_id_blocked_user_id",
                        columnNames = {"user_id", "blocked_user_id"})
        }
)
@Entity
@Getter
public class UserBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_block_user_id")
    )
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "blocked_user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_blocked_user_id")
    )
    private Users blockedUsers;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
