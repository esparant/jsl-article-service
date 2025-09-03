package com.projects.jslarticle.entity.content;

import com.projects.jslarticle.entity.emoji.Emoji;
import com.projects.jslarticle.entity.article.Article;
import com.projects.jslarticle.entity.article.Comment;
import com.projects.jslarticle.entity.board.Board;
import com.projects.jslarticle.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;


/**
 * @author 탁영복
 * @version 1.0.0
 * @description Content Entity 입니다. 상속을 할 수 있는 부모 Entity 입니다. 상속 받는 Entity 는 See 태그를 참고해주세요.
 * @see Comment
 * @see Article
 * @see Board
 * @see Emoji
 * @since 2025-08-29
 */
@Table(name = "cotent")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_content_user_id")
    )
    private User user;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(nullable = false)
    private Boolean isVisible;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
