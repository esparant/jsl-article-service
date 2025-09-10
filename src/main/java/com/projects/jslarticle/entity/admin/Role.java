package com.projects.jslarticle.entity.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Role Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-28
 */
@Table(
        name = "role",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role_name"})
)
@Entity
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
