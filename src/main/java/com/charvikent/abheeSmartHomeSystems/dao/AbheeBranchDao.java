package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.model.AbheeBranch;
import com.charvikent.abheeSmartHomeSystems.model.User;

@Repository
@Transactional
public class AbheeBranchDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public void saveAbheeBranch(AbheeBranch abheeBranch) {
		entityManager.persist(abheeBranch);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AbheeBranch> getAbheeBranchNames()
	 {
		  
		return (List<AbheeBranch>)entityManager.createQuery("from AbheeBranch where status='1'").getResultList();
		 
	 }

	public AbheeBranch getAbheeBranchById(AbheeBranch abheeBranch) {
		@SuppressWarnings("unchecked")
		
		String hql ="";
		List<AbheeBranch> abheeBranchList =(List<AbheeBranch>) entityManager.createQuery(" FROM  AbheeBranch where name =:custName ").setParameter("custName",abheeBranch.getName()).getResultList();
		if(abheeBranchList.size() > 0)
			return abheeBranchList.get(0);
		return null;
	}

	public void updateDept1(AbheeBranch abheeBranch) {
		AbheeBranch uo= entityManager.find(AbheeBranch.class,abheeBranch.getId());
		uo.setName(abheeBranch.getName());
		uo.setBranchhead(abheeBranch.getBranchhead());
		entityManager.merge(uo);
		
	}
	
	
	
	public void updateAbheeBranch(AbheeBranch abheeBranch) {
		String hql="update AbheeBranch set  description =:d, name =:n, branchhead =:bh   where  id =:i";
		
		Query query =entityManager.createQuery(hql); 
		
		query.setParameter("d", abheeBranch.getDescription());
		query.setParameter("n", abheeBranch.getName());
		query.setParameter("i", abheeBranch.getId());
		query.setParameter("bh", abheeBranch.getBranchhead());
		query.executeUpdate(); 
		
	}

	/*public boolean deleteAbheeBranch1(Integer id, String status) {
		Boolean delete=false;
		try{
			
			AbheeBranchController org= (AbheeBranchController)entityManager.find(AbheeBranchController.class ,id);
			   org.setStatus(status);
			   entityManager.merge(org);
			if(!status.equals(org.getStatus()))
			{
				delete=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}*/
	
	public boolean deleteAbheeBranch(Integer id, String status) {
		
		String hql="update AbheeBranch set status =:s where  id =:i";
		Query query =entityManager.createQuery(hql);  
		query.setParameter("s", status);
		query.setParameter("i", id);
		
		
		int result=query.executeUpdate(); 
		if(result == 1)
			return true;
			else
		return false;
		
	}

	@SuppressWarnings("unchecked")
	public List<AbheeBranch> getInActiveList() {
		// TODO Auto-generated method stub
		return (List<AbheeBranch>)entityManager.createQuery(" from AbheeBranch where status='0'").getResultList();
		 	}
	
	
	
	public Map<Integer, String> getAbheeBranchNamesMap()
	{
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
		Map<Integer, String> abheeBranchListMap = new LinkedHashMap<Integer, String>();
		
		List<AbheeBranch> abheeBranchList= getAbheeBranchNames();
		if(authorities.contains(new SimpleGrantedAuthority("ROLE_MASTERADMIN")))
		{
		for(AbheeBranch bean: abheeBranchList){
			abheeBranchListMap.put(bean.getId(), bean.getName());
		}
		}
		else
		{
			for(AbheeBranch bean: abheeBranchList){
				
				
				if(String.valueOf(bean.getId()).equals(objuserBean.getBranchId()))
				{
					abheeBranchListMap.put(bean.getId(), bean.getName());
				}
			}
			
		}
		
		
				
		return abheeBranchListMap;
				
		
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getBranchHeads() 
	{
		
		String hql =" from User where designation='2'";
		Query query =entityManager.createQuery(hql);  
		 List<User> list= query.getResultList();
		 
		Map<Integer, String> abheeBranchHeadMap = new LinkedHashMap<Integer, String>();
		for(User user:list)
		 {
			abheeBranchHeadMap .put(user.getId(), user.getUsername());
			 
			 
		 } 
		return abheeBranchHeadMap;
	}
	@SuppressWarnings("unchecked")
	public List<AbheeBranch> getAllBranches() 
	{
		List<AbheeBranch> bheadlist =new ArrayList<AbheeBranch>();


		try {
			List<Object[]> rows = entityManager.createNativeQuery("select ab.id,ab.name,ab.description,ab.branchhead,u.username,ab.status from abheeusers u,  abhee_branch ab where   ab.branchhead=u.id and ab.status='1'").getResultList();
			for (Object[] row : rows) {
				AbheeBranch abheebranch=new AbheeBranch();

				abheebranch.setId(Integer.parseInt(String.valueOf(row[0])));
				abheebranch.setName((String) row[1]);
				abheebranch.setDescription((String) row[2]);
				abheebranch.setBranchhead((String) row[3]);
				abheebranch.setBranchheadname((String) row[4]);
				abheebranch.setStatus((String) row[5]);
				
				bheadlist.add(abheebranch);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bheadlist ;
		
	}
}
