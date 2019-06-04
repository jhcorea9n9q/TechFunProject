package com.techfun.service;

import java.util.List;

import com.techfun.domain.Contents;
import com.techfun.domain.ContentsCode;
import com.techfun.domain.ContentsPhoto;

public interface ContentsDBAccessSample {
	
	public Integer newContNo();
	
	public void insertCont(Contents domain);
	
	public void insertContPhoto(ContentsPhoto domain);
	
	public void insertContCode(ContentsCode domain);
	
	public List<Contents> mainList(Integer limit);
	
	public List<Contents> mainOne(Contents domain, Integer limit);
	
	public ContentsPhoto mainPhoto(ContentsPhoto domain);
	
	public ContentsCode mainCode(ContentsCode domain);
	
	public void contDel(Contents contDomain, 
			ContentsPhoto contPhotoDomain, ContentsCode contCodeDomain);

}
