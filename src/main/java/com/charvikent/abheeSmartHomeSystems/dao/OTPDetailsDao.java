package com.charvikent.abheeSmartHomeSystems.dao;


import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.OTPDetails;

@Repository
@Transactional
public class OTPDetailsDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public void saveOTPdetails(OTPDetails oTPDetails) {
		entityManager.persist(oTPDetails);
		
	}
	
	
	public List<Map<String, Object>> getCurrentDayList(String mobile)
	{
	String hql="select * FROM otp_details WHERE DATE_FORMAT(otp_details.created_time, '%Y-%m-%d') = CURDATE() and mobileno='"+mobile+"' order by created_time desc";
	System.out.println(hql);
	
	List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
	System.out.println(retlist);
	return retlist;
	}

}
