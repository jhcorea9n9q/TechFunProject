package com.techfun.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.techfun.domain.Contents;
import com.techfun.domain.ContentsCode;
import com.techfun.domain.ContentsPhoto;
import com.techfun.domain.Profile;
import com.techfun.domain.SNSUser;
import com.techfun.service.ContentsDBAccessSample;
import com.techfun.service.UserDBAccessSample;
import com.techfun.util.MainPageUtil;
import com.techfun.web.form.ContentsForm;

@Controller
@SessionAttributes({"scopedTarget.sNSUser"})
public class ContentsController {
	
	@Autowired
	private SNSUser userInfo;
	
	@Autowired
	private UserDBAccessSample UDS;
	
	@Autowired
	private ContentsDBAccessSample CDS;
	
	@ModelAttribute("contForm")
	public ContentsForm setContentsForm() {
		return new ContentsForm();
	}
	
	@RequestMapping(value="/input")
	public String inputContents(Model model) {
		if(userInfo.getEmail()==null || !userInfo.getProfYn().equals("Y")) {
			return "redirect:/println";
		}
		model.addAttribute("user", "OK");
		return "contents/input";
	}
	
	@RequestMapping(value="/input-end", method = RequestMethod.POST)
	public String inputEnd(@Validated
			@ModelAttribute("contForm") ContentsForm contForm,
			BindingResult result, Model model,
			@RequestParam("file") MultipartFile file
			) {
		if(result.hasErrors()) {
			return "contents/input";
		}
		
		contForm.setUserId(userInfo.getId());
		
		boolean photoYn = false;
		boolean codeYn = false;
		
		photoYn = !file.isEmpty();
		if(!contForm.getCode().equals("")) {
			codeYn = !codeYn;
		}
		
		if(photoYn) {
			contForm.setPhotoYn("Y");
		}else {
			contForm.setPhotoYn("N");
		}
		if(codeYn) {
			contForm.setCodeYn("Y");
		}else {
			contForm.setCodeYn("N");
		}
		
		Contents contDomain = new Contents();
		BeanUtils.copyProperties(contForm, contDomain);
		CDS.insertCont(contDomain);
		
		if(photoYn || codeYn) {
			contForm.setContNo(CDS.newContNo());
			if(photoYn) {
				String fileNm = file.getOriginalFilename();
				OutputStream out = null;
				try {
					byte[] bytes = file.getBytes();
					String path = "C:\\spring\\kpsLee\\src\\main\\resources\\static\\contentsPhoto\\" + contForm.getContNo() + "\\";
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
				contForm.setPhotoNm(fileNm);
				contForm.setPhotoUrl("/contentsPhoto/" + contForm.getContNo() + "/" + fileNm);
				ContentsPhoto contPhotoDomain = new ContentsPhoto();
				BeanUtils.copyProperties(contForm, contPhotoDomain);
				CDS.insertContPhoto(contPhotoDomain);
			}
			if(codeYn) {
				ContentsCode contCodeDomain = new ContentsCode();
				BeanUtils.copyProperties(contForm, contCodeDomain);
				CDS.insertContCode(contCodeDomain);
			}
		}
		
		return "redirect:/println/main";
	}
	
	@RequestMapping(value = "/println/main")
	public String mainRequest(Model model) {
		if(userInfo.getEmail()==null || !userInfo.getProfYn().equals("Y")) {
			return "redirect:/println";
		}
		model = MainPageUtil.modelMaker(model, userInfo, userInfo, UDS.getProfileData(userInfo), true);
		
		/////////////////
		List<Contents> contList = new ArrayList<>();
		List<ContentsPhoto> contPhotos = new ArrayList<>();
		List<ContentsCode> contCode = new ArrayList<>();
		List<Profile> profileList = new ArrayList<>();
		
		contList = CDS.mainList(0);
		for(Contents cont : contList) {
			
			SNSUser userIf = new SNSUser();
			userIf.setId(cont.getUserId());
			profileList.add(UDS.getProfileData(userIf));
			
			if(cont.getPhotoYn().equals("Y")) {
				ContentsPhoto domain = new ContentsPhoto();
				domain.setContNo(cont.getContNo());
				contPhotos.add(CDS.mainPhoto(domain));
			}else {
				contPhotos.add(null);
			}
			
			if(cont.getCodeYn().equals("Y")) {
				ContentsCode domain = new ContentsCode();
				domain.setContNo(cont.getContNo());
				contCode.add(CDS.mainCode(domain));
			}else {
				contCode.add(null);
			}
			
		}
		
		List<SNSUser> snsList = new ArrayList<>();
		List<Profile> snsPList = new ArrayList<>();
		snsList = UDS.getUserList();
		for(SNSUser sl : snsList) {
			snsPList.add(UDS.getProfileData(sl));
		}
		
		model.addAttribute("contList", contList);
		model.addAttribute("contPhotos", contPhotos);
		model.addAttribute("contCode", contCode);
		model.addAttribute("profileList", profileList);
		model.addAttribute("snsPList", snsPList);
		/////////////////
		
		return "contents/main";
	}
	
	@RequestMapping(value = "/println/main/{userNo}")
	public String mainRequest(Model model, @PathVariable("userNo") String userNo) {
		SNSUser pageUserDomain = new SNSUser();
		SNSUser pageUserInfo = new SNSUser();
		pageUserDomain.setId(Integer.parseInt(userNo));
		pageUserInfo = UDS.getUserData(pageUserDomain);
		if(pageUserInfo==null || !pageUserInfo.getProfYn().equals("Y")) {
			return "redirect:/println";
		}
		if(userInfo.getEmail()==null || !userInfo.getProfYn().equals("Y")) {
			model = MainPageUtil.modelMaker(model, null, pageUserInfo, UDS.getProfileData(pageUserInfo), false);
		}else {
			model = MainPageUtil.modelMaker(model, userInfo, pageUserInfo, UDS.getProfileData(pageUserInfo), true);
		}
		
		/////////////////
		List<Contents> contList = new ArrayList<>();
		List<ContentsPhoto> contPhotos = new ArrayList<>();
		List<ContentsCode> contCode = new ArrayList<>();
		List<Profile> profileList = new ArrayList<>();
		
		contList = CDS.mainList(0);
		for(Contents cont : contList) {
			
			SNSUser userIf = new SNSUser();
			userIf.setId(cont.getUserId());
			profileList.add(UDS.getProfileData(userIf));
			
			if(cont.getPhotoYn().equals("Y")) {
				ContentsPhoto domain = new ContentsPhoto();
				domain.setContNo(cont.getContNo());
				contPhotos.add(CDS.mainPhoto(domain));
			}else {
				contPhotos.add(null);
			}
			
			if(cont.getCodeYn().equals("Y")) {
				ContentsCode domain = new ContentsCode();
				domain.setContNo(cont.getContNo());
				contCode.add(CDS.mainCode(domain));
			}else {
				contCode.add(null);
			}
			
		}
		
		List<SNSUser> snsList = new ArrayList<>();
		List<Profile> snsPList = new ArrayList<>();
		snsList = UDS.getUserList();
		for(SNSUser sl : snsList) {
			snsPList.add(UDS.getProfileData(sl));
		}
		
		model.addAttribute("contList", contList);
		model.addAttribute("contPhotos", contPhotos);
		model.addAttribute("contCode", contCode);
		model.addAttribute("profileList", profileList);
		model.addAttribute("snsPList", snsPList);
		/////////////////
		
		return "contents/main";
	}

}
