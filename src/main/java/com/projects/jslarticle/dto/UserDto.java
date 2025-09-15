package com.projects.jslarticle.dto;

import lombok.Builder;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description UserDto Record 파일입니다.
 * @since 2025-09-15
 */
@Builder
public record UserDto(Long id,
                      String email,
                      String nickname,
                      String tag,
                      Integer point,
                      String profileImageUrl) {
}