package com.projects.jslarticle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Role Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-28
 */
@Table(name = "role")
@Entity
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String roleName;
}
