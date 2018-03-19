package com.charvikent.abheeSmartHomeSystems.service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.dao.UserDao;
import com.charvikent.abheeSmartHomeSystems.model.Category;
import com.charvikent.abheeSmartHomeSystems.model.Department;
import com.charvikent.abheeSmartHomeSystems.model.Designation;
import com.charvikent.abheeSmartHomeSystems.model.User;

@Service
@Transactional
public class UserService {

	private final static Logger logger = Logger.getLogger(AdminService.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private SendSMS smsTemplate;


    SendSMS smstemplate =new SendSMS();

	public void saveUser(User user) throws IOException
	{
		
		String msg =user.getFirstname()+" "+user.getLastname()+",  Successfully registered with KPTMS. \n You can login using \n Username:  "+user.getUsername()+"\n password: "+user.getPassword();
		String mbnum=user.getMobilenumber();
		userDao.saveuser(user);
		logger.info("Sending message.......");
		//smsTemplate.sendSMS(msg,mbnum);
	}

	public List<User> getAllUsers()
	{
    User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		List<User> usersListForAdmin =new ArrayList<>();
		List<User> usersListForMaster= userDao.getAllUsers();
		
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_MASTERADMIN")))
			   return usersListForMaster;
		 else 
		 {
			 
			 for(User entry :usersListForMaster)
			 {  
				 if(entry.getBranchId().equals(objuserBean.getBranchId()))
				 {
					 if(entry.getId()!=(objuserBean.getId()))
					 usersListForAdmin.add(entry);
				 }
			 }
			 return usersListForAdmin;
		 }

	}


	/*public Map<Integer, String> getDepartments()
	{
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		Map<Integer, String> deptsMap = new LinkedHashMap<Integer, String>();
		
		List<Department> departmentList= userDao.getDepartmentslist();
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_MASTERADMIN")))
		{
		for(Department bean: departmentList){
			deptsMap.put(bean.getId(), bean.getName());
		}

	
		return deptsMap;
		}
		else
		{
			for(Department bean: departmentList){
				
				if(bean.getBranchId().equals(objuserBean.getBranchId())
				{
				deptsMap.put(bean.getId(), bean.getName());
				}
			}
			
			return deptsMap;
			
		}
		

	}*/


	public Map<Integer, String> getRoles()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Designation> rolesList= userDao.getRoles();
		for(Designation bean: rolesList){
			rolesMap.put(bean.getId(), bean.getName());
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;


	}

		public User  findWithName(String username, String lpassword)
	    {

		 User userdao=null;

		try {
			userdao= userDao.findWithName(username, lpassword);
		} catch (Exception e) {
			System.out.println("error occured service");


			e.printStackTrace();
		}

				return userdao;

	}

	public boolean deleteUser(Integer id,String enabled) {

		return userDao.deleteUser(id,enabled);

	}

	public User getUserById(Integer id) {

		User obj=userDao.getUserById(id);
		return obj;
	}

	public void updateUser(User user) {

		userDao.updateUser(user);

	}

	public void updatePassword(User user) {

		userDao.updatePassword(user);

	}

	public Map<Integer, String> getUserName()
	{
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		Map<Integer, String>userMapForMaster = new LinkedHashMap<Integer, String>();
		
		List<User> rolesList= userDao.getUserNames();
			if(authorities.contains(new SimpleGrantedAuthority("ROLE_MASTERADMIN")))
			{
		for(User bean: rolesList){
			if(bean.getId()!=(objuserBean.getId()))
			{
				userMapForMaster.put(bean.getId(), bean.getUsername());
			}
		

	} 
		return userMapForMaster;
		}
		else
		{
			for(User bean: rolesList){
				if(bean.getBranchId().equals(objuserBean.getBranchId()))
				{
				if(bean.getId()!=(objuserBean.getId()))
				{
					userMapForMaster.put(bean.getId(), bean.getUsername());
				}
				}
			}
			
		
			return userMapForMaster;
		}

	}
	


	public Map<Integer, String> getReportToUsers()
	{
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		Map<Integer, String>userMapForMaster = new LinkedHashMap<Integer, String>();
		
		List<User> rolesList= userDao.getUserNames();
			if(authorities.contains(new SimpleGrantedAuthority("ROLE_MASTERADMIN")))
			{
		for(User bean: rolesList){
				userMapForMaster.put(bean.getId(), bean.getUsername());
		

	} 
		return userMapForMaster;
		}
		else
		{
			for(User bean: rolesList){
				if(bean.getBranchId().equals(objuserBean.getBranchId()))
				{
				
					userMapForMaster.put(bean.getId(), bean.getUsername());
				}
			}
			
		
			return userMapForMaster;
		}

	}


	public void setLoginRecord(Integer id,String str) {

		userDao.setLoginRecord(id,str);
	}

	public boolean checkUserExist(String username) {

		List<User> usersList= userDao.getUserNames();

		for(User bean: usersList){
			  if(username.equalsIgnoreCase(bean.getUsername()))
			  {

				  return true;
		       }
	}
		return false;
	}

	public User getUserByObject(User user) {
		// TODO Auto-generated method stub
		return userDao.getUserByObject(user);
	}

	public List<User> getInActiveList() {

		return userDao.getInActiveList();
	}

	public User checkCustomerExistOrNotbyMobile(String custMobile) {
		// TODO Auto-generated method stub
		return userDao.checkCustomerExistOrNotbyMobile(custMobile);
	}


}
