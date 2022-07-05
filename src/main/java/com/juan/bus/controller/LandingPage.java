package com.juan.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPage {
	@GetMapping("/")
	public String main() {
		return "index";
	}
}
