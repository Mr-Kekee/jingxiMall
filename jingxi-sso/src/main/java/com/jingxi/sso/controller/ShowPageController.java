package com.jingxi.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowPageController {
	
	@RequestMapping("/{page}")
	public String toPage (@PathVariable String page) {
		return page;
	}
}
