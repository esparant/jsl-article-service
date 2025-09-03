package com.projects.jslarticle.entity;

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
 * @description EmojiFolder Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-09-03
 */
@Table(
        name = "emoji_folder",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_emoji_folder_user_id_folder_name",
                        columnNames = {"user_id", "folder_name"}
                )
        }
)
@Entity
@Getter
public class EmojiFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_emoji_folder_user_id")
    )
    private User user;

    @Column(nullable = false)
    private String folderName;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private Boolean isPublic;

    @Column(nullable = false)
    private Integer sortOrder;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
