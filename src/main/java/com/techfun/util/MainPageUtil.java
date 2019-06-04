package com.techfun.util;

import org.springframework.ui.Model;

import com.techfun.domain.Profile;
import com.techfun.domain.SNSUser;

public class MainPageUtil {
	
	public static Model modelMaker(Model model, 
			SNSUser loginUserInfo,
			SNSUser pageUserInfo,
			Profile profileInfo,
			boolean isLogin) {
		if(loginUserInfo!=null) {
			model.addAttribute("loginUserInfo", loginUserInfo);
		}
		model.addAttribute("pageUserInfo", pageUserInfo);
		if(profileInfo.getBirth()!=null) {
			String[] birth = profileInfo.getBirth().split("-");
			profileInfo.setBirth(birth[0] + "年 " + birth[1] + "月 " + birth[2] + "日");
		}
		if(profileInfo.getPrgrmLang()!=null) {
			String prgrmL = profileInfo.getPrgrmLang().replace("[", "").replace("]", "");
			profileInfo.setPrgrmLang(prgrmL);
		}
		if(profileInfo.getHobby()!=null) {
			String hobbys = profileInfo.getHobby().replace("[", "").replace("]", "");
			profileInfo.setHobby(hobbys);
		}
		model.addAttribute("profileInfo", profileInfo);
		
		if(isLogin) {
			model.addAttribute("user", "OK");
		}
		return model;
	}

}
