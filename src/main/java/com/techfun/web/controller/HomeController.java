package com.techfun.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.techfun.domain.SNSUser;
import com.techfun.service.UserDBAccessSample;
import com.techfun.web.form.ProfileForm;
import com.techfun.web.form.SignInForm;
import com.techfun.web.form.UserForm;

@Controller
@SessionAttributes({"scopedTarget.sNSUser", "signForm", "userForm", "profForm"})
public class HomeController {
	
	@Autowired
	private SNSUser userInfo;
	
	@Autowired
	private UserDBAccessSample UDS;
	
	@ModelAttribute("signForm")
	public SignInForm setSignInForm() {
		return new SignInForm();
	}
	
	@ModelAttribute("userForm")
	public UserForm setUserForm() {
		return new UserForm();
	}
	
	@ModelAttribute("profForm")
	public ProfileForm setProfileForm() {
		return new ProfileForm();
	}
	
	@RequestMapping("/println")
	public String home(Model model) {
		if(userInfo.getEmail()!=null) {
			if(userInfo.getProfYn().equals("Y")) {
				model.addAttribute("nickname", UDS.getProfileData(userInfo).getNicknm());
			}else {
				model.addAttribute("userInfo", userInfo.getEmail());
			}
		}
		return "home";
	}
	
	@RequestMapping(value = "/home-choice", params = "signInBtn", method = RequestMethod.POST)
	public String homeSign() {
		return "user/signIn";
	}
	
	@RequestMapping(value = "/home-choice", params = "loginBtn", method = RequestMethod.POST)
	public String homeLogin() {
		return "user/login";
	}
	
	@RequestMapping(value = "/home-choice", params = "mainBtn", method = RequestMethod.POST)
	public String homeMain(Model model) {
		if(userInfo.getEmail()!=null) {
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("profileInfo", UDS.getProfileData(userInfo));
		}
		return "contents/main";
	}
	
	@RequestMapping(value = "/home-choice", params = "logoutBtn", method = RequestMethod.POST)
	public String homeLogout(SessionStatus sess) {
		sess.setComplete();
		SNSUser domain = new SNSUser();
		BeanUtils.copyProperties(domain, userInfo);
		return "redirect:/println";
	}
	
	@RequestMapping("/login-main")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus sess) {
		sess.setComplete();
		SNSUser domain = new SNSUser();
		BeanUtils.copyProperties(domain, userInfo);
		return "redirect:/println";
	}

}
