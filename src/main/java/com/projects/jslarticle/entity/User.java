package com.projects.jslarticle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description User 엔티티입니다. 추가 엔티티 제작후 수정필요 합니다.
 * @since 2025-08-27
 */
@Entity
@Getter
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_nickname_tag",
                        columnNames = {"nickname", "tag"}
                )
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 256)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String tag;

    @Column(nullable = false)
    private Long point;

    @Column(nullable = false, length = 255)
    private String profileImageUrl;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
