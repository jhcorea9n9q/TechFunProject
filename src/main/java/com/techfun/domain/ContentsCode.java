package com.techfun.domain;

/**投稿ソースコードドメイン*/
public class ContentsCode {
	
	private Integer codeNo;
	private Integer userId;
	private Integer contNo;
	private String code;
	private Integer editUserId;
	private String editCode;
	private String editYn;
	private String inpDate;
	private String updDate;
	
	public Integer getCodeNo() {
		return codeNo;
	}
	public void setCodeNo(Integer codeNo) {
		this.codeNo = codeNo;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getEditUserId() {
		return editUserId;
	}
	public void setEditUserId(Integer editUserId) {
		this.editUserId = editUserId;
	}
	public String getEditCode() {
		return editCode;
	}
	public void setEditCode(String editCode) {
		this.editCode = editCode;
	}
	public String getEditYn() {
		return editYn;
	}
	public void setEditYn(String editYn) {
		this.editYn = editYn;
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
