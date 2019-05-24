package com.techfun.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;


public class UserForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;

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

}
