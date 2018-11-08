package com.cfs.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAccessController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
}
