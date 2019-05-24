package com.techfun.domain;

/**投稿画像ドメイン*/
public class ContentsPhoto {
	
	private Integer photoNo;
	private Integer userId;
	private Integer contNo;
	private String photoNm;
	private String photoUrl;
	private String inpDate;
	private String updDate;
	
	public Integer getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(Integer photoNo) {
		this.photoNo = photoNo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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

}
