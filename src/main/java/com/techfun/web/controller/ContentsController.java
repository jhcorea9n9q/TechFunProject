package com.techfun.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techfun.domain.SNSUser;
import com.techfun.service.UserDBAccessSample;

@Controller
@SessionAttributes({"scopedTarget.sNSUser"})
public class ContentsController {
	
	@Autowired
	private SNSUser userInfo;
	
	@Autowired
	private UserDBAccessSample UDS;

}
