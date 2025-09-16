package com.projects.jslarticle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@GetMapping("list")
	void list() {}
	
	@GetMapping("write")
	void write() {}
	
	@GetMapping("detail")
	void detail() {}
}
