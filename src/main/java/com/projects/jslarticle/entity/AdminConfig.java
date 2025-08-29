package com.projects.jslarticle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description AdminConfig Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-29
 */
@Table(name = "admin_config")
@Entity
@Getter
public class AdminConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "owner_admin_id",
            foreignKey = @ForeignKey(name = "fk_admin_config_owner_admin_id")
    )
    private Admin ownerAdmin;

    @OneToOne(mappedBy = "adminConfig")
    private Admin admin;


    @Column(nullable = false)
    private Integer availableBanDays;

    @Column(nullable = false)
    private Integer availableBanUserCount;

    @Column(nullable = false)
    private Boolean availableAdminAddition;

    @Column(nullable = false)
    private Boolean availableAdminDismissal;

    @Column(nullable = false)
    private Boolean availableCategoryModification;

    @Column(nullable = false)
    private Boolean availableCategoryCreation;
}
