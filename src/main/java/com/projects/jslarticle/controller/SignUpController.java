package com.projects.jslarticle.controller;

import com.projects.jslarticle.dto.SignUpDto;
import com.projects.jslarticle.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description SignUoController 컨트롤러입니다.
 * @since 2025-09-16
 */
@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signUp() {
        return "account/signup";
    }

    @PostMapping("/signup")
    public String signUp(SignUpDto signUpDto) {

        UserDetails userDetails = userService.signUp(signUpDto);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        // TODO - 세션추가

        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/email-auth")
    public String emailAuth(HttpSession session, String email) {
        String code = String.valueOf((int) (Math.random() * 900000) + 100000); // 6자리 숫자

        session.setAttribute("code", code);

        return null;
    }
}
