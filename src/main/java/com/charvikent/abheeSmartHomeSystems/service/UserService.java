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
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.dao.UserDao;
/*import com.charvikent.abheeSmartHomeSystems.model.Category;
import com.charvikent.abheeSmartHomeSystems.model.Department;*/
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
	@Autowired
    private Environment environment;

    SendSMS smstemplate =new SendSMS();

	@SuppressWarnings("unused")
	public void saveUser(User user) throws IOException
	{
		
		String msg =user.getFirstname()+" "+user.getLastname()+",  Successfully registered with AbheeSmartHomeSystems. \n You can login using \n Username:  "+user.getUsername()+"\n password: "+user.getPassword();
		String mbnum=user.getMobilenumber();
		 String tmsg =environment.getProperty("app.empregistration");
		 System.out.println(tmsg);
		tmsg=  tmsg.replaceAll("_fullname_", user.getFirstname()+" "+user.getLastname());
		tmsg=  tmsg.replaceAll("_username_", user.getUsername());
		tmsg= tmsg.replaceAll("_password_", user.getPassword());
		userDao.saveuser(user);
		logger.info("Sending message.......");
		smsTemplate.sendSMS(tmsg,mbnum);
	}

	public List<User> getAllUsers()
	{
    User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		List<User> usersList =new ArrayList<>();
		List<User> usersListForMaster= userDao.getAllUsers();
		
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
		{
			for(User entry :usersListForMaster)
			 {  
				 
					 if(entry.getId()!=(objuserBean.getId()))
					 usersList.add(entry);
				 
			 }
			
			return usersList;
			
		}
			   
		 else 
		 {
			 
			 for(User entry :usersListForMaster)
			 { 
				 
				 if(entry.getBranchId().equals(objuserBean.getBranchId()))
				 {
					 if(entry.getId()!=(objuserBean.getId()))
					 usersList.add(entry);
				 }
			 }
			 return usersList;
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
		
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		List<Designation> rolesList= userDao.getRoles();
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
		{
		for(Designation bean: rolesList){
			rolesMap.put(bean.getId(), bean.getName());
		}

		return rolesMap;
		}
		else
		{
			
			for(Designation bean: rolesList){
				if(bean.getId()!=1 && bean.getId()!=2 && bean.getId()!=3)
				rolesMap.put(bean.getId(), bean.getName());
			}
			return rolesMap;
			
		}

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
			if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			{
		for(User bean: rolesList){
			if(bean.getId()!=(objuserBean.getId()))
			{
				userMapForMaster.put(bean.getId(), bean.getUsername());
			}
		

	} 
		}
			return userMapForMaster;
		

	}
	


	@SuppressWarnings("unused")
	public Map<Integer, String> getReportToUsers()
	{
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		
		Map<Integer, String>userMapForMaster = new LinkedHashMap<Integer, String>();
		
		List<User> rolesList= userDao.getUserNames();
			if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			{
		for(User bean: rolesList){
				userMapForMaster.put(bean.getId(), bean.getUsername());
		

	} 
		}
			return userMapForMaster;
	}
		/*else
		{
			for(User bean: rolesList){
				if(bean.getBranchId().equals(objuserBean.getBranchId()))
				{
				
					userMapForMaster.put(bean.getId(), bean.getUsername());
				}
			}
			
		
			return userMapForMaster;
		}*/

	


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
	public List<User> getCustomerInActiveList() {

		return userDao.getCustomerInActiveList();
	}
	public User checkCustomerExistOrNotbyMobile(String custMobile) {
		// TODO Auto-generated method stub
		return userDao.checkCustomerExistOrNotbyMobile(custMobile);
	}

	public User checkCustomerExistOrNotbyEmail(String custMobile) {
		// TODO Auto-generated method stub
		return userDao.checkCustomerExistOrNotByEmail(custMobile);
	}

	public User checkuserExistOrNot(User user) {
		return userDao.checkuserExistOrNot(user);
		
	}
	
	public User getUserDesignationById(Integer id) {
		User obj=userDao.getUserDesignationById(id);
		return obj;
	}

	public User geCustomerByObject(User user) {
		// TODO Auto-generated method stub
		return userDao.getCustomerByObject(user);
	}

	public User checkEmployeeExistOrNotbyMobile(String custMobile) {
		// TODO Auto-generated method stub
		return userDao.heckEmployeeExistOrNotbyMobile(custMobile);
	}

	public User checkEmployeeExistOrNotbyEmail(String empcemail) {
		// TODO Auto-generated method stub
		return userDao.ceckEmployeeExistOrNotbyEmail(empcemail);
	}

	public User checkEmployeeExistOrNotbyEmail(String empcemail, String editFieldsId) {
		// TODO Auto-generated method stub
		return userDao.checkEmployeeExistOrNotbyEmail(empcemail,editFieldsId);
	}

	public User checkEmployeeExistOrNotbyMobileOnEdit(String custMobile, String editFieldsId) {
		return userDao.checkEmployeeExistOrNotbyMobileOnEdit(custMobile,editFieldsId);
	}


}
