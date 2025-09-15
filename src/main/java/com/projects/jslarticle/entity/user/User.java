package com.projects.jslarticle.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 탁영복
 * @version 1.0.1
 * @description User Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @updated 2025-09-15
 * @change 2025-08-27 - Entity 최초 생성 및 기분 구조 작성 (1.0.0) 2025-09-15 - Dto 대응 Builder 추가 (1.0.1)
 * @since 2025-08-27
 */
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"nickname", "tag"}),
                @UniqueConstraint(columnNames = {"email"})
        }
)
@Entity
@Getter
@NoArgsConstructor
public class User {

    @Builder
    public User(String email, String password, String nickname, String tag) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.tag = tag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 256)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String tag;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false, length = 255)
    private String profileImageUrl;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
