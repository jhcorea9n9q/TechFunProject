package com.techfun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techfun.domain.Contents;
import com.techfun.domain.ContentsCode;
import com.techfun.domain.ContentsPhoto;
import com.techfun.persistence.ContentsMapper;

@Service
public class ContentsDBAccessService implements ContentsDBAccessSample {
	
	@Autowired
	private ContentsMapper mapper;

	@Override
	public Integer newContNo() {
		return mapper.newContNo();
	}

	@Override
	@Transactional
	public void insertCont(Contents domain) {
		mapper.insertCont(domain);
	}

	@Override
	@Transactional
	public void insertContPhoto(ContentsPhoto domain) {
		mapper.insertContPhoto(domain);
	}

	@Override
	@Transactional
	public void insertContCode(ContentsCode domain) {
		mapper.insertContCode(domain);
	}

	@Override
	public List<Contents> mainList(Integer limit) {
		return mapper.mainList(limit);
	}

	@Override
	public List<Contents> mainOne(Contents domain, Integer limit) {
		return mapper.mainOne(domain, limit);
	}

	@Override
	public ContentsPhoto mainPhoto(ContentsPhoto domain) {
		return mapper.mainPhoto(domain);
	}

	@Override
	public ContentsCode mainCode(ContentsCode domain) {
		return mapper.mainCode(domain);
	}

	@Override
	@Transactional
	public void contDel(Contents contDomain, 
			ContentsPhoto contPhotoDomain, ContentsCode contCodeDomain) {
		mapper.contCodeDel(contCodeDomain);
		mapper.contPhotoDel(contPhotoDomain);
		mapper.contDel(contDomain);
	}

}
