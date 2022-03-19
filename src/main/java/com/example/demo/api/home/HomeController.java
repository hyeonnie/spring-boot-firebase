package com.example.demo.api.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class HomeController {

	@GetMapping
	public String home() {
		return "Hello, world!";
	}
	
}
