package com.techfun.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class ProfileForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	@NotEmpty
	private String nicknm;
	
	private String profPicUrl;
	
	private Integer gender;
	private Integer genderPrvt;
	
	private String year;
	private String month;
	private String day;
	private String birth;
	private Integer birthPrvt;
	
	private String hobby;
	private Integer hobbyPrvt;
	
	private String prgrmLang;
	
	private String intrdc;
	private Integer intrdcPrvt;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNicknm() {
		return nicknm;
	}
	public void setNicknm(String nicknm) {
		this.nicknm = nicknm;
	}
	public String getProfPicUrl() {
		return profPicUrl;
	}
	public void setProfPicUrl(String profPicUrl) {
		this.profPicUrl = profPicUrl;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getGenderPrvt() {
		return genderPrvt;
	}
	public void setGenderPrvt(Integer genderPrvt) {
		this.genderPrvt = genderPrvt;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public Integer getBirthPrvt() {
		return birthPrvt;
	}
	public void setBirthPrvt(Integer birthPrvt) {
		this.birthPrvt = birthPrvt;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Integer getHobbyPrvt() {
		return hobbyPrvt;
	}
	public void setHobbyPrvt(Integer hobbyPrvt) {
		this.hobbyPrvt = hobbyPrvt;
	}
	public String getPrgrmLang() {
		return prgrmLang;
	}
	public void setPrgrmLang(String prgrmLang) {
		this.prgrmLang = prgrmLang;
	}
	public String getIntrdc() {
		return intrdc;
	}
	public void setIntrdc(String intrdc) {
		this.intrdc = intrdc;
	}
	public Integer getIntrdcPrvt() {
		return intrdcPrvt;
	}
	public void setIntrdcPrvt(Integer intrdcPrvt) {
		this.intrdcPrvt = intrdcPrvt;
	}

}
