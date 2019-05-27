package com.techfun.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.techfun.domain.SNSUser;
import com.techfun.service.UserDBAccessSample;
import com.techfun.web.form.SignInForm;
import com.techfun.web.form.UserForm;

@Controller
@SessionAttributes({"signForm", "userForm"})
public class SignInController {
	
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
	
	@RequestMapping(value = "/sign-in", params = "confBtn", method = RequestMethod.POST)
	public String signConf(@Validated @ModelAttribute("signForm") SignInForm signForm, BindingResult result) {
		
		if(!signForm.getEmail().equals("")) {
			SNSUser domain = new SNSUser();
			BeanUtils.copyProperties(signForm, domain);
			
			if(UDS.emailHasSame(domain)) {
				result.rejectValue("email", "alreadyHasSameEmail");
			}
		}
		
		if(!signForm.getPassword().equals("")) {
			if(signForm.getPassword().length() < 4) {
				result.rejectValue("password", "passwordTooShort");
			}else {
				if(!signForm.getPassword().equals(signForm.getPwd_c())) {
					result.rejectValue("pwd_c", "passwordCheckUndone");
				}
			}
		}
		
		if(result.hasErrors()) {
			return "user/signIn";
		}
		
		return "user/signInConf";
	}
	
	@RequestMapping(value = "/sign-in", params = "backBtn", method = RequestMethod.POST)
	public String signBack(SessionStatus sess) {
		sess.setComplete();
		return "home";
	}
	
	@RequestMapping(value = "/sign-end", params = "endBtn", method = RequestMethod.POST)
	public String signEnd(@ModelAttribute("signForm") SignInForm signForm,
			Model model, @RequestParam(value = "sign_c", required = false) String checkboxValue) {
		
		if(checkboxValue == null) {
			model.addAttribute("checkError", true);
			return "user/signInConf";
		}
		
		SNSUser domain = new SNSUser();
		BeanUtils.copyProperties(signForm, domain);
		UDS.insertSign(domain);
		return "redirect:/sign-end?finish";
	}
	
	@RequestMapping(value = "/sign-end", params = "finish")
	public String signFinish(@ModelAttribute("signForm") SignInForm signForm) {
		return "user/signInEnd";
	}
	
	
	@RequestMapping(value = "/sign-end", params = "backBtn", method = RequestMethod.POST)
	public String signInBack(@ModelAttribute("signForm") SignInForm signForm) {
		return "user/signIn";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String signLogin(SessionStatus sess) {
		sess.setComplete();
		return "user/login";
	}

}
