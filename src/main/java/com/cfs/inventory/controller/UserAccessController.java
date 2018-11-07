package com.cfs.inventory.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAccessController {

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String exception = ((Exception)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage();
		System.out.println(exception);
		return "login";
	}

//	@GetMapping("/403")
//	public String accessDenied() {
//		return "403";
//	}
}
