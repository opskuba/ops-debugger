package com.opskuba.debugger.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opskuba.debugger.service.UserService;

@RestController
public class UserController extends BasicController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = { RequestMethod.PUT })
	public String register(String json) {

		return "";
	}
	
	@RequestMapping(value = "/user", method = { RequestMethod.GET })
	public String user(String json) {

		return "";
	}

}
