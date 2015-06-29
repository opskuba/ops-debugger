package com.opskuba.debugger.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DebuggerController extends BasicController{
	
	@RequestMapping("/")
	public String debugger() {
		return "debugger";
	}
	
	@RequestMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard() {
		return "/debugger/dashboard";
	}

}
