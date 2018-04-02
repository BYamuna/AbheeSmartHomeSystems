package com.charvikent.abheeSmartHomeSystems.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.dao.UserDao;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.User;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserDao userRepository;
	@Autowired
	private  UserDao userRolesRepository;
	
	@Autowired
	private  CustomerDao customerRepository;
	@Autowired
	private  CustomerDao customerRolesRepository;
	
	
	private static String USER_TYPE = "userType";
	
	/*@Autowired
    public CustomUserDetailsService(UserDao userRepository,UserDao userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository=userRolesRepository;
    }*/
	
        
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String userType = (String) RequestContextHolder.getRequestAttributes().getAttribute(USER_TYPE, RequestAttributes.SCOPE_SESSION);
		System.out.println(userType);
		
		User user =null ;
		Customer customer=null;
		if(userType.equals("adminUser"))
		{
			 user=userRepository.findByUserName(username);
		
		
		
		if(null == user){
			throw new UsernameNotFoundException("No user present with username: "+username);
		}else{
			List<String> userRoles=userRolesRepository.findRoleByUserName(username);
			return new CustomUserDetails(user,userRoles);
		}
		}
		else
		{
			customer = customerRepository.findByUserName(username);
			if(null == customer){
				throw new UsernameNotFoundException("No user present with username: "+username);
			}else{
				List<String> roleslist =new ArrayList<String>();
				roleslist.add("ROLE_CUSTOMER");
				
				return new CustomCustomerUserDetails(customer,roleslist);
			}
			
		}
	}
		
}
