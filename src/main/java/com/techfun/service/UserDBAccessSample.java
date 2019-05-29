package com.techfun.service;

import com.techfun.domain.Profile;
import com.techfun.domain.SNSUser;

public interface UserDBAccessSample {
	
	public boolean emailHasSame(SNSUser domain);
	
	public void insertSign(SNSUser domain);
	
	public SNSUser accountCheck(SNSUser domain);
	
	public Profile getProfileData(SNSUser userInfo);
	
	public void insertProfile(SNSUser userInfo, Profile domain);
	
	public void accountUpd(SNSUser userInfo);
	
	public void profileUpd(Profile domain);
	
	public void profPicUpd(Profile domain);
	
	public void accountDel(SNSUser userInfo, Profile domain);

}
