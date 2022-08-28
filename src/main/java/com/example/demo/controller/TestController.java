package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TestController {
	@RequestMapping(value= {"/helloWorld"},method= {RequestMethod.GET})
	public String helloWorld() {
		return "<h1>Hello World!</h1>";
	}
	@RequestMapping(value= {"/userLogin"},method = {RequestMethod.GET})
	public String userLogin() {
		return "<h1>User SignIn</h1>";
	}
	@RequestMapping(value= {"/adminLogin"},method = {RequestMethod.GET})
	public String adminLogin() {
		return "<h1>Admin Welcome!</h1>";
	}
}
