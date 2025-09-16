package com.projects.jslarticle.service;

import com.projects.jslarticle.dto.SignUpDto;
import com.projects.jslarticle.entity.user.User;
import com.projects.jslarticle.repository.UserRepository;
import com.projects.jslarticle.springsecurity.CustomUserDetails;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * @param signUpDto - Controller 에서 signUpDto 를 받습니다.
     * @return userDto - 최종적으로 등록후 유저한테 반환할때 userDto 로 매핑후 반환
     * @desciption - 회원가입 기능
     */
    public UserDetails signUp(SignUpDto signUpDto) {

        return new CustomUserDetails(
                userRepository.save(
                        User.builder()
                                .email(signUpDto.email())
                                .nickname(signUpDto.nickname())
                                // 비밀번호 암호화
                                .password(passwordEncoder.encode(signUpDto.password()))
                                .tag("#" + UUID.randomUUID().toString().substring(0, 8))
                                .build()
                ));
    }
}
