package com.techfun.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class ContentsForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	@NotEmpty
	private String cont;
	
	private String photoYn;
	private String codeYn;
	
	private Integer contNo;
	private String photoNm;
	private String photoUrl;
	private String code;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getPhotoYn() {
		return photoYn;
	}
	public void setPhotoYn(String photoYn) {
		this.photoYn = photoYn;
	}
	public String getCodeYn() {
		return codeYn;
	}
	public void setCodeYn(String codeYn) {
		this.codeYn = codeYn;
	}
	public Integer getContNo() {
		return contNo;
	}
	public void setContNo(Integer contNo) {
		this.contNo = contNo;
	}
	public String getPhotoNm() {
		return photoNm;
	}
	public void setPhotoNm(String photoNm) {
		this.photoNm = photoNm;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
