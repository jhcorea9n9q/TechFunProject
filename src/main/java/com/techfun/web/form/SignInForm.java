package com.techfun.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class SignInForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	private String pwd_c;

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

	public String getPwd_c() {
		return pwd_c;
	}

	public void setPwd_c(String pwd_c) {
		this.pwd_c = pwd_c;
	}
	
}
