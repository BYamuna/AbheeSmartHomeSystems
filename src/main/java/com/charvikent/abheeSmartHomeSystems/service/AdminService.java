package com.charvikent.abheeSmartHomeSystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.dao.AdminDao;
import com.charvikent.abheeSmartHomeSystems.model.Admin;

@Service
@Transactional
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	public void saveAdmin(Admin admin)
	{
		adminDao.saveAdmin(admin);
	}
	

}
