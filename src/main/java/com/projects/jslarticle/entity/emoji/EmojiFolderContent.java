package com.projects.jslarticle.entity.emoji;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description EmojiFolderContent Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-09-03
 */
@Table(
        name = "emoji_folder_content",
        uniqueConstraints = @UniqueConstraint(columnNames = {"emoji_id", "emoji_folder_id"})
)
@Entity
@Getter
public class EmojiFolderContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emoji_id", nullable = false)
    private Emoji emoji;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emoji_folder_id", nullable = false)
    private EmojiFolder emojiFolder;
}
