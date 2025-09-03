package com.projects.jslarticle.entity.emoji;

import com.projects.jslarticle.entity.content.Content;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Emoji Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-09-03
 */
@Table(name = "emoji")
@Entity
@Getter
@PrimaryKeyJoinColumn(
        name = "id",
        foreignKey = @ForeignKey(name = "fk_emoji_content_id")
)
public class Emoji extends Content {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 255)
    private String imageUrl;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private Integer point;
}
