package com.opskuba.debugger.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController extends BasicController{

	
	@RequestMapping("/login")
	public String login(String username, String password) {
//		String remoteAddr = request.getRemoteAddr();
		
		return "dashboard";
	}
	
	
}
