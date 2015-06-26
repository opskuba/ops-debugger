package com.opskuba.debugger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class DebuggerController {
	
	@ResponseBody
	@RequestMapping("/")
	String debugger() {
		return "make a quiet debugger...";
	}

}
