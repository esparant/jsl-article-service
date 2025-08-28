package com.projects.jslarticle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0
 * @description User 엔티티입니다. 추가 엔티티 제작후 수정필요 합니다.
 * @since 2025-08-27
 */
@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String tag;

    private Long point;

    private String profileImageUrl;

    private Boolean isDeleted;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
