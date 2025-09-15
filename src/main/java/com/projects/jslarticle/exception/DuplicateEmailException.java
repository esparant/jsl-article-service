package com.projects.jslarticle.exception;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description 이메일 중복 Exception 파일입니다.
 * @since 2025-09-15
 */
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
