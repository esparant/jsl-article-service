package com.projects.jslarticle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author 탁영복
 * @since 2025-08-27
 * @version 1.0
 * @description User 엔티티입니다. 추가 엔티티 제작후 수정필요 합니다.
 */
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
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

    private boolean isDeleted;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
