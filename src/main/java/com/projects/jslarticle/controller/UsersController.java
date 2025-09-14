package com.projects.jslarticle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UsersController {
	
	@GetMapping("info_about_me_edit")
	void infoAboutMeEdit() {}
	
	@GetMapping("info_about_me")
	void infoAboutMe() {}

	@GetMapping("info_about_you")
	void infoAboutYou() {}
}
