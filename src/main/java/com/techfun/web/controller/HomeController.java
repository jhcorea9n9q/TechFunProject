package com.techfun.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techfun.domain.SNSUser;

@Controller
@SessionAttributes("scopedTarget.sNSUser")
public class HomeController {
	
	@Autowired
	private SNSUser userInfo;
	
	public String home() {
		return "home";
	}

}
