package com.projects.jslarticle.entity.emoji;

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
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description EmojiUser Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-09-03
 */
@Table(name = "emoji_user")
@Entity
@Getter
public class EmojiUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "emoji_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_emoji_user_emoji_id")
    )
    private Emoji emoji;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_emoji_user_user_id")
    )
    private User users;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer sortOrder;
}
