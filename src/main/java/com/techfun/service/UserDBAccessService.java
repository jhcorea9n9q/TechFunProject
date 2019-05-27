package com.techfun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techfun.domain.Profile;
import com.techfun.domain.SNSUser;
import com.techfun.persistence.UserMapper;

@Service
public class UserDBAccessService implements UserDBAccessSample {

	@Autowired
	private UserMapper mapper;

	@Override
	public boolean emailHasSame(SNSUser domain) {
		if(mapper.emailHasSame(domain) > 0) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void insertSign(SNSUser domain) {
		mapper.userInsert(domain);
	}

	@Override
	public SNSUser accountCheck(SNSUser domain) {
		SNSUser result = mapper.accountCheck(domain);
		if (result == null) {
			result = mapper.accountCheckbyNick(domain);
		}
		return result;
	}

	@Override
	public Profile getProfileData(SNSUser userInfo) {
		return mapper.getProfileData(userInfo);
	}
	
}