package com.projects.jslarticle.service;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.projects.jslarticle.dto.SignUpDto;
import com.projects.jslarticle.dto.UserDto;
import com.projects.jslarticle.entity.user.User;
import com.projects.jslarticle.exception.DuplicateEmailException;
import com.projects.jslarticle.mapper.UserMapper;
import com.projects.jslarticle.repository.UserRepository;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description UserService 단위 테스트입니다.
 * @since 2025-09-15
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("UserService 단위 테스트")
class UserServiceUnitTest {

    // Mock 주입 시작
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;
    // Mock 주입 끝


    @Test
    @DisplayName("signUp 기능이 정상적으로 작동한다.")
    void signUp_단위테스트_성공() {
        // given
        SignUpDto signUpDto = SignUpDto.builder()
                .email("email")
                .nickname("nickname")
                .build();

        User user = User.builder().build();

        String tag = UUID.randomUUID().toString().substring(0, 8);
        UserDto userDto = UserDto.builder()
                .email(signUpDto.email())
                .nickname(signUpDto.nickname())
                .tag("#" + tag)
                .build();

        // when
        // MockStub 설정 시작
        when(userMapper.toUserDto(user)).thenReturn(userDto);
        when(userMapper.toUserDto(user)).thenReturn(userDto);
        when(userRepository.save(any(User.class))).thenReturn(user);
        // MockStub 설정 끝

        UserDto savedUser = userService.save(signUpDto);

        // then
        // 검증 시작
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(savedUser.email(), signUpDto.email());
        Assertions.assertEquals(savedUser.nickname(), signUpDto.nickname());
        Assertions.assertEquals(savedUser.tag(), "#" + tag);
        // 검증 끝
    }

    @Test
    @DisplayName("이미 존재하는 이메일을 입력받을시 예외가 발생한다.")
    void signUp_중복예외_발생() {
        // given
        SignUpDto signUpDto = SignUpDto.builder()
                .email("email")
                .build();

        //when
        when(userRepository.save(argThat(user -> user.getEmail().equals("email"))))
                .thenThrow(new DuplicateEmailException());

        // then
        Assertions.assertThrows(DuplicateEmailException.class, () -> userService.save(signUpDto));
    }
}