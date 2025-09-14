package com.projects.jslarticle.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController {

	@GetMapping("select")
	void select() {}
	
	@GetMapping("login")
	void login(@AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			boolean isUser = userDetails.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"));
			
			if (isUser) {
				System.out.println("이사람은 유저다!");
			}
		}
	}

	@GetMapping("findpassword")
	void findPassword() {}

}
