package com.projects.jslarticle.entity.content;

import com.projects.jslarticle.entity.admin.Admin;
import com.projects.jslarticle.entity.user.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description Report Entity 입니다. 추가 Entity 제작후 수정필요 합니다.
 * @since 2025-08-29
 */
@Table(name = "report")
@Entity
@Getter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "content_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_report_content_id")
    )
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_report_user_id")
    )
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "admin_id",
            foreignKey = @ForeignKey(name = "fk_report_admin_id")
    )
    private Admin admin;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(length = 255)
    private String imageUrl;

    @Column(length = 255)
    private String result;

    private Boolean isProcessed;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
