package com.projects.jslarticle.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController {

    @GetMapping("/sign-up")
    public String signUp(HttpSession session) {

        session.setAttribute("signUpEmail", null);
        session.setAttribute("signUpFlag", false);
        session.setAttribute("signUpEmail", null);

        return "account/signup";
    }

    
}
