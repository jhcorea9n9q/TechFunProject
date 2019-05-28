package com.techfun.persistence;

import com.techfun.domain.Profile;
import com.techfun.domain.SNSUser;

public interface UserMapper {
	
	public Integer emailHasSame(SNSUser domain);
	
	public void userInsert(SNSUser domain);
	
	public SNSUser accountCheck(SNSUser domain);
	
	public SNSUser accountCheckbyNick(SNSUser domain);
	
	public Profile getProfileData(SNSUser userInfo);
	
	public void insertProfile(Profile domain);
	
	public void profileComplete(SNSUser userInfo);

}
