package com.projects.jslarticle.service;

import com.projects.jslarticle.dto.SignUpDto;
import com.projects.jslarticle.dto.UserDto;
import com.projects.jslarticle.entity.user.User;
import com.projects.jslarticle.exception.DuplicateEmailException;
import com.projects.jslarticle.mapper.UserMapper;
import com.projects.jslarticle.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description UserService 파일입니다.
 * @since 2025-09-15
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto save(SignUpDto signUpDto) {

        // 이메일 중복시 예외를 던집니다.
        userRepository.findByEmail(signUpDto.email())
                .ifPresent(u -> {
                    throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
                });

        // SingUpDto -> User 수동 매핑
        User user = User.builder()
                .email(signUpDto.email())
                .nickname(signUpDto.nickname())
                // 비밀번호 암호화
                .password(passwordEncoder.encode(signUpDto.password()))
                .tag("#" + UUID.randomUUID().toString().substring(0, 8))
                .build();

        // Repository 에 User 저장
        User savedUser = userRepository.save(user);

        // UserMapper 를 통해 UserDto 로 변경후 반환
        return userMapper.toUserDto(savedUser);
    }
}
