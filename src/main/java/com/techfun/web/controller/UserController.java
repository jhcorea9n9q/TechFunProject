package com.techfun.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.techfun.domain.SNSUser;
import com.techfun.service.UserDBAccessSample;
import com.techfun.web.form.ProfileForm;
import com.techfun.web.form.UserForm;

@Controller
@SessionAttributes({"scopedTarget.sNSUser","userForm","profForm"})
public class UserController {
	
	@Autowired
	private SNSUser userInfo;
	
	@Autowired
	private UserDBAccessSample UDS;
	
	@ModelAttribute("userForm")
	public UserForm setUserForm() {
		return new UserForm();
	}
	
	@ModelAttribute("profForm")
	public ProfileForm setProfileForm() {
		return new ProfileForm();
	}
	
	@RequestMapping(value = "/trylogin", params = "loginBtn", method = RequestMethod.POST)
	public String tryLogin(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult result) {
		
		SNSUser domain = new SNSUser();
		BeanUtils.copyProperties(userForm, domain);
		
		SNSUser sns_user = UDS.accountCheck(domain);
		BeanUtils.copyProperties(sns_user, userInfo);
		
		if(!userForm.getEmail().equals("") && !userForm.getPassword().equals("")) {
			if(userInfo == null) {
				result.reject("userLoginFail");
			}
		}
		if(result.hasErrors()) {
			return "user/login";
		}
		if(userInfo.getProfYn().equals("N")) {
			return "user/profile/profileIn";
		}
			
		return "contents/main";
	}
	
	@RequestMapping(value = "/trylogin", params = "backBtn", method = RequestMethod.POST)
	public String loginBack(SessionStatus sess) {
		sess.setComplete();
		return "home";
	}

}
