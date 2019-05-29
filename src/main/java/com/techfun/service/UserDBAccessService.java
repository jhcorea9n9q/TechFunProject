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

	@Override
	@Transactional
	public void insertProfile(SNSUser userInfo, Profile domain) {
		mapper.insertProfile(domain);
		mapper.profileComplete(userInfo);
	}

	@Override
	@Transactional
	public void accountUpd(SNSUser userInfo) {
		mapper.accountUpd(userInfo);
	}

	@Override
	@Transactional
	public void profileUpd(Profile domain) {
		mapper.profileUpd(domain);
	}

	@Override
	@Transactional
	public void profPicUpd(Profile domain) {
		mapper.profileUpd(domain);
		mapper.profPicUpd(domain);
	}

	@Override
	@Transactional
	public void accountDel(SNSUser userInfo, Profile domain) {
		mapper.profileDel(domain);
		mapper.accountDel(userInfo);
	}
	
}
