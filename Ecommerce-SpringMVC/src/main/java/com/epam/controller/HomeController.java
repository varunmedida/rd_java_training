package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "login";
	}
	@GetMapping(value = "/login")
	public String homelogin() {
		return "login";
	}
}
