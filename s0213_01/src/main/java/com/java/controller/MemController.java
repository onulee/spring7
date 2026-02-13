package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemController {

	@GetMapping("/member/j_join")
	public String j_join() {
		return "member/j_join";
	}
}
