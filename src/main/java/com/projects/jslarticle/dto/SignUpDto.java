package com.projects.jslarticle.dto;

import lombok.Builder;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description SignUpDto Record 파일입니다.
 * @since 2025-09-15
 */
@Builder
public record SignUpDto(String email,
                        String nickname,
                        String password) {
}
