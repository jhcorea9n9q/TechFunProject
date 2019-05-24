package com.techfun.domain;

/**投稿コンテンツドメイン*/
public class Contents {
	
	private Integer contNo;
	private Integer userId;
	private String cont;
	private String photoYn;
	private String codeYn;
	private Integer comCount;
	private String inpDate;
	private String updDate;
	private String delYn;
	
	public Integer getContNo() {
		return contNo;
	}
	public void setContNo(Integer contNo) {
		this.contNo = contNo;
	}
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
	public Integer getComCount() {
		return comCount;
	}
	public void setComCount(Integer comCount) {
		this.comCount = comCount;
	}
	public String getInpDate() {
		return inpDate;
	}
	public void setInpDate(String inpDate) {
		this.inpDate = inpDate;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

}
