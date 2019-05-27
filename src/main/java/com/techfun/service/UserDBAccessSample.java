package com.techfun.service;

import com.techfun.domain.Profile;
import com.techfun.domain.SNSUser;

public interface UserDBAccessSample {
	
	public boolean emailHasSame(SNSUser domain);
	
	public void insertSign(SNSUser domain);
	
	public SNSUser accountCheck(SNSUser domain);
	
	public Profile getProfileData(SNSUser userInfo);

}
