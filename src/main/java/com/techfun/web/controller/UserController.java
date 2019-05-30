package com.techfun.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
import org.springframework.web.multipart.MultipartFile;

import com.techfun.domain.Profile;
import com.techfun.domain.SNSUser;
import com.techfun.service.UserDBAccessSample;
import com.techfun.web.form.ProfileForm;
import com.techfun.web.form.SignInForm;
import com.techfun.web.form.UserForm;

@Controller
@SessionAttributes({"scopedTarget.sNSUser","signForm", "userForm","profForm"})
public class UserController {
	
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
	
	@RequestMapping(value = "/trylogin", params = "loginBtn", method = RequestMethod.POST)
	public String tryLogin(@Validated @ModelAttribute("userForm") UserForm userForm, 
			BindingResult result, Model model) {
		
		if(!userForm.getEmail().equals("") && !userForm.getPassword().equals("")) {
			SNSUser domain = new SNSUser();
			BeanUtils.copyProperties(userForm, domain);
			SNSUser sns_user = UDS.accountCheck(domain);
			
			if(sns_user == null) {
				result.reject("userLoginFail");
			}else {
				BeanUtils.copyProperties(sns_user, userInfo);
			}
		}
		
		if(result.hasErrors()) {
			return "user/login";
		}
		
		if(userInfo.getProfYn().equals("N")) {
			return "user/profile/profileIn";
		}
		
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("profileInfo", UDS.getProfileData(userInfo));
		return "contents/main";
	}
	
	@RequestMapping(value = "/trylogin", params = "backBtn", method = RequestMethod.POST)
	public String loginBack(SessionStatus sess) {
		sess.setComplete();
		return "home";
	}
	
	@RequestMapping(value="/main", method = RequestMethod.POST)
	public String profileConf(
			@Validated @ModelAttribute("profForm") ProfileForm profForm,  
			BindingResult result, @RequestParam("file") MultipartFile file
			) {
		
		if(result.hasErrors()) {
			return "user/profile/profileIn";
		}
		
		// プロフィール写真登録
		
		boolean profPicNotUploaded = file.isEmpty();
		String fileNm = "";
		
		if(!profPicNotUploaded) {
			fileNm = file.getOriginalFilename();
			
			OutputStream out = null;
			
			try {
				byte[] bytes = file.getBytes();
				String path = "C:\\spring\\kpsLee\\src\\main\\resources\\static\\profileImg\\" + userInfo.getId() + "\\";
				File dir = new File(path);
				if(!dir.exists()) {
					dir.mkdirs();
				}
				File f = new File(path + fileNm);
				out = new FileOutputStream(f);
				out.write(bytes);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(out !=null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		// フォーム完成
		
		profForm.setUserId(userInfo.getId());
		
		if(!profPicNotUploaded) {
			profForm.setProfPicUrl("/profileImg/" + userInfo.getId() + "/" + fileNm);
		}else {
			profForm.setProfPicUrl("/lib/mainIcon.png");
		}
		
		if(profForm.getGender()==null) {
			profForm.setGender(0);
		}
		
		String year = profForm.getYear();
		String month = profForm.getMonth();
		String day = profForm.getDay();
		boolean birthFlag = true;
		if(year.equals("")) {
			birthFlag = false;
		}else if(month.equals("")) {
			birthFlag = false;
		}else if(day.equals("")) {
			birthFlag = false;
		}
		if(birthFlag) {
			profForm.setBirth(year + "-" + month + "-" + day);
		}
		
		String hobby = "";
		if(profForm.getHobbys().length > 0) {
			for(int i = 0; i < profForm.getHobbys().length; i++) {
				if(i == profForm.getHobbys().length - 1) {
					hobby += "[" +profForm.getHobbys()[i]  + "]";
				}else {
					hobby += "[" +profForm.getHobbys()[i]  + "],";
				}
			}
			profForm.setHobby(hobby);
		}
		
		String pLang = "";
		if(profForm.getpLangs().length > 0) {
			for(int i = 0; i < profForm.getpLangs().length; i++) {
				if(i == profForm.getpLangs().length - 1) {
					pLang += "[" +profForm.getpLangs()[i]  + "]";
				}else {
					pLang += "[" +profForm.getpLangs()[i]  + "],";
				}
			}
			profForm.setPrgrmLang(pLang);
		}
		
		//////////////////////////////////////////////////////////////
		
		Profile domain = new Profile();
		BeanUtils.copyProperties(profForm, domain);
		UDS.insertProfile(userInfo, domain);
		
		userInfo.setProfYn("Y");
		
		return "redirect:/main?finish";
	}
	
	@RequestMapping(value="/main", params = "finish")
	public String profileFinish(Model model) {
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("profileInfo", UDS.getProfileData(userInfo));	
		return "contents/main";
	}
	
	@RequestMapping("/mypage")
	public String mypage(
			@ModelAttribute("signForm") SignInForm signForm, 
			@ModelAttribute("profForm") ProfileForm profForm,
			Model model
			) {
		Profile userProfile = UDS.getProfileData(userInfo);
		
		BeanUtils.copyProperties(userInfo, signForm);
		BeanUtils.copyProperties(userProfile, profForm);
		
		if(userProfile.getBirth()!=null) {
			String[] date = userProfile.getBirth().split("-");
			profForm.setYear(date[0]);
			profForm.setMonth(date[1]);
			profForm.setDay(date[2]);
		}else {
			profForm.setYear("");
			profForm.setMonth("");
			profForm.setDay("");
		}
		
		if(userProfile.getHobby()!=null) {
			String[] hobbys = userProfile.getHobby().split(",");
			for(int i = 0; i < hobbys.length; i++) {
				hobbys[i] = hobbys[i].replace("[","").replace("]","");
			}
			profForm.setHobbys(hobbys);
		}
		
		if(userProfile.getPrgrmLang()!=null) {
			String[] pLangs = userProfile.getPrgrmLang().split(",");
			for(int i = 0; i < pLangs.length; i++) {
				pLangs[i] = pLangs[i].replace("[","").replace("]","");
			}
			profForm.setpLangs(pLangs);
		}
		
		// ヘッダー用のユーザチェック
		if(userInfo.getEmail()!=null) {
			model.addAttribute("user", "OK");
		}
		
		return "user/profile/mypage";
	}
	
	@RequestMapping(value="/mypage-account", method = RequestMethod.POST)
	public String accountChange(
			@Validated @ModelAttribute("signForm") SignInForm signForm,
			BindingResult result,
			@ModelAttribute("profForm") ProfileForm profForm, Model model
			) {
		
		// 同じロジックがあります!
		if(!signForm.getPassword().equals("")) {
			if(signForm.getPassword().length() < 4) {
				result.rejectValue("password", "passwordTooShort");
			}else {
				if(!signForm.getPassword().equals(signForm.getPwd_c())) {
					result.rejectValue("pwd_c", "passwordCheckUndone");
				}
			}
		}
		
		if(!result.hasErrors()) {
			userInfo.setPassword(signForm.getPassword());
			UDS.accountUpd(userInfo);
			model.addAttribute("alertPass", "OK");
		}
		
		model.addAttribute("user", "OK");
		
		return "user/profile/mypage";
	}
	
	@RequestMapping(value="/mypage-profile", method = RequestMethod.POST)
	public String profileChange(
			@Validated @ModelAttribute("profForm") ProfileForm profForm,  
			BindingResult result, @RequestParam("file") MultipartFile file,
			@ModelAttribute("signForm") SignInForm signForm, Model model
			) {
		
		if(!result.hasErrors()) {
			boolean profPicNotUploaded = file.isEmpty();
			String fileNm = "";
			String oldPicURL = profForm.getProfPicUrl();
			
			if(!profPicNotUploaded) {
				fileNm = file.getOriginalFilename();
				OutputStream out = null;
				
				try {
					byte[] bytes = file.getBytes();
					String path = "C:\\spring\\kpsLee\\src\\main\\resources\\static\\profileImg\\" + userInfo.getId() + "\\";
					File dir = new File(path);
					if(!dir.exists()) {
						dir.mkdirs();
					}
					// ここ以外は同じロジック!
					if(!oldPicURL.equals("/lib/mainIcon.png")) {
						File oldFile = new File("C:\\spring\\kpsLee\\src\\main\\resources\\static" + oldPicURL);
						oldFile.delete();
					}
					File f = new File(path + fileNm);
					out = new FileOutputStream(f);
					out.write(bytes);
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					if(out !=null) {
						try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			// 同じロジックが使われています!
			profForm.setUserId(userInfo.getId());
			
			if(!profPicNotUploaded) {
				profForm.setProfPicUrl("/profileImg/" + userInfo.getId() + "/" + fileNm);
			} // ここは少し違います。
			
			// 同じロジックが使われています!
			if(profForm.getGender()==null) {
				profForm.setGender(0);
			}
			
			String year = profForm.getYear();
			String month = profForm.getMonth();
			String day = profForm.getDay();
			boolean birthFlag = true;
			if(year.equals("")) {
				birthFlag = false;
			}else if(month.equals("")) {
				birthFlag = false;
			}else if(day.equals("")) {
				birthFlag = false;
			}
			if(birthFlag) {
				profForm.setBirth(year + "-" + month + "-" + day);
			}
			
			String hobby = "";
			if(profForm.getHobbys().length > 0) {
				for(int i = 0; i < profForm.getHobbys().length; i++) {
					if(i == profForm.getHobbys().length - 1) {
						hobby += "[" +profForm.getHobbys()[i]  + "]";
					}else {
						hobby += "[" +profForm.getHobbys()[i]  + "],";
					}
				}
				profForm.setHobby(hobby);
			}
			
			String pLang = "";
			if(profForm.getpLangs().length > 0) {
				for(int i = 0; i < profForm.getpLangs().length; i++) {
					if(i == profForm.getpLangs().length - 1) {
						pLang += "[" +profForm.getpLangs()[i]  + "]";
					}else {
						pLang += "[" +profForm.getpLangs()[i]  + "],";
					}
				}
				profForm.setPrgrmLang(pLang);
			}
			
			Profile domain = new Profile();
			BeanUtils.copyProperties(profForm, domain);
			
			if(!profPicNotUploaded) {
				UDS.profPicUpd(domain);
			}else {
				UDS.profileUpd(domain);
			}
			model.addAttribute("alertProf", "OK");
		}
		
		model.addAttribute("secondEvent", "OK");
		model.addAttribute("user", "OK");
		
		return "user/profile/mypage";
	}
	
	@RequestMapping(value="/mypage-delete", method = RequestMethod.POST)
	public String accountDelete(SessionStatus sess, Model model) {
		Profile f = UDS.getProfileData(userInfo);
		UDS.accountDel(userInfo, f);
		
		// プロフィール写真フォルダー削除
		String picURL = f.getProfPicUrl();
		if(!picURL.equals("/lib/mainIcon.png")) { // デフォルト画像以外は削除
			File delDir = new File("C:\\spring\\kpsLee\\src\\main\\resources\\static\\profileImg\\" + userInfo.getId() + "\\");
			File[] delList = delDir.listFiles();
			for (File d : delList) {
				d.delete();
			}
			delDir.delete();
		}
		
		sess.setComplete();
		SNSUser domain = new SNSUser();
		BeanUtils.copyProperties(domain, userInfo);
		
		model.addAttribute("accountDeleted", "OK");
		
		return "home";
	}
	
}
