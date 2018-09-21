package com.cfs.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAccessController {



	@RequestMapping("/login")
	public String login() {
		return "login";
	}

//	@GetMapping("/403")
//	public String accessDenied() {
//		return "403";
//	}
}
