package com.techfun.domain;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**サービス利用ユーザドメイン*/
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SNSUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String password;
	private String profYn;
	private String inpDate;
	private String updDate;
	private String delYn;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfYn() {
		return profYn;
	}
	public void setProfYn(String profYn) {
		this.profYn = profYn;
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
