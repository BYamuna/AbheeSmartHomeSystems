package com.charvikent.abheeSmartHomeSystems.dao;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
/*import java.util.HashSet;*/
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/*import javax.persistence.Query;*/
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.config.KptsUtil;
import com.charvikent.abheeSmartHomeSystems.config.SendSMS;
import com.charvikent.abheeSmartHomeSystems.config.SendingMail;
import com.charvikent.abheeSmartHomeSystems.model.AbheeTask;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
/*import com.charvikent.abheeSmartHomeSystems.model.ProductGuarantee;
import com.charvikent.abheeSmartHomeSystems.model.TaskHistoryLogs;*/
//import com.charvikent.abheeSmartHomeSystems.model.Customer;
//import com.charvikent.abheeSmartHomeSystems.model.TaskHistory;
//import com.charvikent.abheeSmartHomeSystems.model.TaskHistoryLogs;
import com.charvikent.abheeSmartHomeSystems.model.User;


@Repository
@Transactional
public class ReportIssueDao {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	HttpSession session;
	@Autowired
	FilesStuff fileTemplate;
	
	@Autowired
	KptsUtil utilities;
	
	@Autowired
	TaskHistoryDao taskHistoryDao;
	@Autowired
	TaskHistoryLogsDao taskHistoryLogsDao;
	@Autowired
	UserDao userDao;
	@Autowired
	SendingMail sendingMail;
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	SendSMS sendSMS;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment environment;
	

	public void saveReportIssue(AbheeTask reportIssue) {
		String randomNum = utilities.randNum();
		
		reportIssue.setTaskno(randomNum);
		
		if(reportIssue.getUploadfile()!=null)
	     {
			reportIssue.setUploadfile(fileTemplate.concurrentFileNames());
			 fileTemplate.clearFiles();
			
	     } 
		
		//reportIssue.setInvoiceId(randomInvoiceId("00000"));
		em.persist(reportIssue);
		
		
		
		
		taskHistoryLogsDao.historyLogForcustomerEntry(reportIssue);
		
	}
	
	public void saveServiceRequest(AbheeTask reportIssue) {
		String randomNum = utilities.randNum();
		
		reportIssue.setTaskno(randomNum);
		
		/*if(reportIssue.getUploadfile()!=null)
	     {
			reportIssue.setUploadfile(fileTemplate.concurrentFileNames());
			 fileTemplate.clearFiles();
			
	     }*/ 
		em.persist(reportIssue);
		
		
		
		
		taskHistoryLogsDao.historyLogForcustomerEntry(reportIssue);
		
	}

	/* @SuppressWarnings("unchecked")
public List<ReportIssue> getAllReportIssues()
 {
	 return (List<ReportIssue>) em.createQuery("select reportIssue from ReportIssue reportIssue").getResultList();
 }
	 */
 

	public Set<AbheeTask> getIssuesAssignBy(String id) {
		Set<AbheeTask> listissue=new TreeSet<AbheeTask>();

		try {
			@SuppressWarnings("unchecked")
			List <Object[]> rows=em.createQuery("select r.id , u.username, s.colour, p.priority,r.uploadfile,r.subject ,c.category,r.createdTime,ks.scolour,ks.name ,r.status, r.taskno from ReportIssue r, Category c, Priority p, User u, Severity s, KpStatus ks where r.assignto=u.id and r.kstatus=ks.id and p.id=r.priority and s.id=r.severity and c.id=r.category and r.kstatus<>'1'  and  r.assignby =:custName").setParameter("custName", id).getResultList();
			for(Object[] row: rows)
			{
				AbheeTask issue =new AbheeTask();
				int j = Integer.parseInt(String.valueOf(row[0]));
				Integer intobj=new Integer(j);
				issue.setId(intobj);
				
				issue.setAssignto((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setUploadfile((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setCategory((String) row[6]);
				issue.setCreatedTime((Date) row[7]);
				issue.setKstatus( row[8].toString());         
				issue.setAssignby((String) row[9]);
				issue.setStatus((String) row[10]);
				issue.setTaskno((String) row[11]);
				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

		return  listissue;

	}


	public Set<AbheeTask> getIssuesAssignTo(String id) {
		Set<AbheeTask> listissue=new TreeSet<AbheeTask>();

		try {
			@SuppressWarnings("unchecked")
			List <Object[]> rows=em.createNativeQuery("select r.id, r.taskno,r.subject,c.category as cname,r.category cid,p.priority as pname,r.priority as pid,u.username, r.assignto,r.created_time from report_issue r, kpcategory c, kppriority p, kpusers u, kpseverity s, kpstatus ks    where  r.kstatus=ks.id and r.assignto=u.id and p.id=r.priority and s.id=r.severity and c.id=r.category and r.assignto=:custid").setParameter("custid", id).getResultList();
			for(Object[] row: rows)
			{
				AbheeTask issue =new AbheeTask();
				int j = Integer.parseInt(String.valueOf(row[0]));
				Integer intobj=new Integer(j);
				issue.setId(intobj);
				issue.setAssignby((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setUploadfile((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setCreatedTime((Date) row[6]);
				issue.setCategory((String) row[7]);
				issue.setAssignto((String) row[8]);                  
				issue.setKstatus( (String) row[9]);
				issue.setStatus((String) row[10]);
				issue.setTaskno((String) row[11]);


				listissue.add(issue);

			}
			
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}
           
		return  listissue;

	}


	public Set <AbheeTask>getIssuesAssignToResolved(String id) {
		//List<ReportIssue> listissue=new ArrayList<>();
		
		Set<AbheeTask> listissue=new TreeSet<AbheeTask>();

		try {
			@SuppressWarnings("unchecked")
			List <Object[]> rows=em.createQuery("select r.id , u.username, s.colour, p.priority,r.uploadfile,r.subject ,r.createdTime,c.category,ks.scolour,ks.name, t.status ,r.taskno  from ReportIssue r, Category c, Priority p, User u, Severity s, KpStatus ks   where  r.kstatus=ks.id and r.assignto=u.id and p.id=r.priority and s.id=r.severity and c.id=r.category  and r.kstatus='4' and  r.assignto =:custName").setParameter("custName", id).getResultList();
			for(Object[] row: rows)
			{
				AbheeTask issue =new AbheeTask();
				int j = Integer.parseInt(String.valueOf(row[0]));
				Integer intobj=new Integer(j);
				issue.setId(intobj);
				issue.setAssignby((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setUploadfile((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setCreatedTime((Date) row[6]);
				issue.setCategory((String) row[7]);
				issue.setKstatus((String) row[8]);
				issue.setAssignto((String) row[9]);
				issue.setStatus((String) row[10]);
				issue.setTaskno((String) row[11]);


				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

		return  listissue;

	}








	public Set<AbheeTask> getAllReportIssues() {
		Set<AbheeTask> listissue=new LinkedHashSet<AbheeTask>();

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rows = em.createQuery("select r.id, c.category ,s.severity,p.priority,"
					+ "u.username ,r.subject ,r.uploadfile,"
					+ "DATE(r.createdTime), Date(r.updatedTime),r.status,r.description,r.assignto,r.category,r.priority,r.severity,r.status from ReportIssue r, Category c ,Severity s,Priority p,User u  where r.category=c.id and r.severity=s.id and r.priority=p.id and r.assignto=u.id")
			.getResultList();
			for (Object[] row : rows) {
				AbheeTask issue = new AbheeTask();

				issue.setId(Integer.parseInt(String.valueOf(row[0])));

				issue.setCategory((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setAssignto((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setUploadfile((String) row[6]);
				issue.setCreatedTime((Date) row[7]);
				issue.setUpdatedTime((Date) row[8]);
				issue.setStatus((String) row[9]);
				issue.setDescription((String) row[10]);
				issue.setAssigntoid((String) row[11]);
				issue.setCategoryid((String) row[12]);
				issue.setPriorityid((String) row[13]);
				issue.setSeverityid((String) row[14]);
				issue.setTaskno((String) row[15]);
				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

		return  listissue;

	}


	@SuppressWarnings("unchecked")
	public Map<Integer, Integer>  getGapAndCount() {

		List<AbheeTask> listissuegap=new ArrayList<>();
		AbheeTask issuegap =null;

		List<Object[]> rows = 	em.createNativeQuery("SELECT DATEDIFF(CURDATE(),created_time ) as gap ,count(id)  from report_issue group by gap ").getResultList();

		Map<Integer, Integer> issueTimelines = new HashMap<Integer, Integer>();

		for (Object[] row : rows) {
			issuegap = new AbheeTask();
			issuegap.setGapdays(Integer.parseInt(String.valueOf(row[0])));
			issuegap.setGapcount(Integer.parseInt(String.valueOf(row[1])));
			listissuegap.add(issuegap);

			issueTimelines.put(Integer.parseInt(String.valueOf(row[0])), Integer.parseInt(String.valueOf(row[1])));
		}


		return issueTimelines;

	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Integer>  getGapAndCountForClosed() {

		List<AbheeTask> listissuegap=new ArrayList<>();
		AbheeTask issuegap =null;

		//String custName=null;

		List<Object[]> rows = 	em.createNativeQuery(" SELECT DATEDIFF(CURDATE(),created_time ) as gap ,count(id)  from report_issue where kstatus =:custName  group by gap  ").setParameter("custName", "1").getResultList();

		Map<Integer, Integer> issueTimelines = new HashMap<Integer, Integer>();

		for (Object[] row : rows) {
			issuegap = new AbheeTask();
			issuegap.setGapdays(Integer.parseInt(String.valueOf(row[0])));
			issuegap.setGapcount(Integer.parseInt(String.valueOf(row[1])));
			listissuegap.add(issuegap);

			issueTimelines.put(Integer.parseInt(String.valueOf(row[0])), Integer.parseInt(String.valueOf(row[1])));
		}
		return issueTimelines;

	}

	@SuppressWarnings("unchecked")
	public  Set<AbheeTask> getRecentlyModified(String id) {
		
		
		
			

		Set<AbheeTask> listissue=new TreeSet<AbheeTask>();

		try {
			List<Object[]> rows = em
			.createNativeQuery(" select   r.id , u.username, s.colour, p.priority,r.uploadfile,r.subject ,c.category,r.created_time,ks.name,ks.scolour,r.status ,r.taskno from report_issue r, kpcategory c, kppriority p, kpusers u, kpseverity s,kpstatus ks  where r.kstatus=ks.id and r.assignto=u.id and p.id=r.priority and s.id=r.severity and c.id=r.category and r.kstatus='1'  and DATEDIFF (CURDATE(),r.updated_time )<=30 and  r.assignby =:custName union (select   r.id , u.username, s.colour, p.priority,r.uploadfile,r.subject ,c.category,r.created_time,ks.name,ks.scolour from report_issue r, category c, priority p, kpusers u, severity s,kpstatus ks  where r.kstatus=ks.id and r.assignto=u.id and p.id=r.priority and s.id=r.severity and c.id=r.category and r.kstatus='1'  and DATEDIFF (CURDATE(),r.updated_time )<=30 and  r.assignto =:custName )").setParameter("custName", id)
			.getResultList();
			for (Object[] row : rows) {
				AbheeTask issue = new AbheeTask();
				issue.setId(Integer.parseInt(String.valueOf(row[0])));
				issue.setAssignto((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setUploadfile((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setCategory((String) row[6]);
				issue.setCreatedTime((Date) row[7]);
				issue.setKstatus((String) row[9]);
				issue.setAssignby((String) row[8]);
				issue.setStatus((String) row[10]);
				issue.setTaskno((String) row[11]);
				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

		return  listissue;


	}

	/**
	 * @param 
	 * @return
	 */
	public AbheeTask getReportIssueById(Integer id) {

		return em.find(AbheeTask.class, id);
	}
	

	
	public void updateIssue(AbheeTask issue) throws IOException {
		
		/*
		 * User objuserBean =
		 * (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 */
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
			
			//TaskHistoryLogs history=new TaskHistoryLogs();
			
			AbheeTask editissue=getReportIssueById(issue.getId());
			 editissue.setAdditionalinfo("0");
			
			if(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")))
			{
			     editissue.setDescription(issue.getDescription());
			     editissue.setKstatus(issue.getKstatus());
			     editissue.setAddComment(issue.getAddComment());
			     if(issue.getWarranty() == null) {
			    	 editissue.setWarranty(" ");
			     }else {
			     editissue.setWarranty(issue.getWarranty());
			     }
			     if(issue.getImgfile()!=null)
			     {
			    	 
			     editissue.setImgfile(issue.getImgfile());
			     }
			     else
			     {
			    	 editissue.setImgfile(issue.getImgfile());
			     }
			  
			     editissue.setAmountreceived(issue.getAmountreceived());
			     editissue.setDiscount(issue.getDiscount());
			     editissue.setTax(issue.getTax());
			     editissue.setTotal(issue.getTotal());
			     //history.setWebstatus(1);
			     taskHistoryLogsDao.historyLog(editissue);
				
			}
			else
			{
		
     editissue.setAssignto(issue.getAssignto());
     //editissue.setCategory(issue.getCategory());
     editissue.setDescription(issue.getDescription());
     editissue.setPriority(issue.getPriority());
     editissue.setSeverity(issue.getSeverity());
     editissue.setSubject(issue.getSubject());
     editissue.setTaskdeadline(issue.getTaskdeadline());
     editissue.setKstatus(issue.getKstatus());
     editissue.setAddComment(issue.getAddComment());
     if(issue.getWarranty() == null) {
    	 editissue.setWarranty(" ");
     }else {
     editissue.setWarranty(issue.getWarranty());
     }
     //editissue.setWarranty(issue.getWarranty());
     
     if(issue.getUploadfile()!=null)
     {
     editissue.setUploadfile(fileTemplate.concurrentFileNames());
     }
   /*  else {
    	 editissue.setUploadfile("icon.png");
     }*/
		if(issue.getInvoiceId().length()!=0) 
		{
			editissue.setInvoiceId(issue.getInvoiceId()); 
		} 
		else 
		{
			editissue.setInvoiceId("(NULL)"); 
		}
		
		if(issue.getInvimg()!=null)
	     {
	     editissue.setInvimg(issue.getInvimg());
	     }
		else 
		{
			editissue.setInvimg(issue.getInvimg());
		}
	     editissue.setExpenditure(issue.getExpenditure());
	     	
	     if(issue.getImgfile()!=null)
	     {
	     editissue.setImgfile(issue.getImgfile());
	     }
	   else
	     {
		   editissue.setImgfile(issue.getImgfile());
	     }
		//em.flush();
     	
			
        
		taskHistoryLogsDao.historyLog(editissue);
		try 
		{
			if(editissue.getKstatus().equals("1")||editissue.getKstatus().equals("6")) 
			{
				
			sendingMail.sendMailTocustomer(editissue);
			sendingMail.sendMailToUser(editissue);
			String assigntechnician=editissue.getAssignto();
			User emp=userDao.getUserById(Integer.parseInt(assigntechnician));
			String customerid =  editissue.getCustomerId();
			Customer customer= customerDao.findCustomerByCustId(customerid);
			//sendsmsToCustomer
			String mobilenum=customer.getMobilenumber();
			String tmsg =environment.getProperty("app.tmrmsg");
			tmsg= tmsg.replaceAll("_technicianname_", emp.getFirstname()+" "+emp.getLastname());
			tmsg= tmsg.replaceAll("_mobilenum_", emp.getMobilenumber());
			tmsg= tmsg.replaceAll("_ServiceRequestNo_", editissue.getTaskno());
			tmsg= tmsg.replaceAll("_requestdesc_", editissue.getDescription());
			tmsg= tmsg.replaceAll("_taskdeadline_", editissue.getTaskdeadline());
			System.out.println(tmsg);
			sendSMS.sendSMS(tmsg,mobilenum);
			//sendsmsToUser
			String mobileno=emp.getMobilenumber();
			String msg =environment.getProperty("app.taskmsg");
			System.out.println(msg);
			 msg= msg.replaceAll("_ServiceRequestNo_", editissue.getTaskno());
			 msg= msg.replaceAll("_fullname_",customer.getFirstname()+" "+customer.getLastname());
			 msg= msg.replaceAll("_mobileno_", customer.getMobilenumber());
			 msg= msg.replaceAll("_reqdesc_", editissue.getDescription());
			 msg= msg.replaceAll("_taskdeadline_", editissue.getTaskdeadline());
			 msg= msg.replaceAll("_communicationaddress_", editissue.getCommunicationaddress());
			 System.out.println(msg);
			 sendSMS.sendSMS(msg,mobileno); 
			}
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		
		}
	}
	public void updateRequest(AbheeTask issue)
	{
		/*User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();*/
			
			AbheeTask editissue=getRequestDetailsByObject(issue);
			 editissue.setAdditionalinfo("0");
			
			/*if(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")))
			{*/
				if(issue.getTaskno().equals(editissue.getTaskno()))
				{	
				     //editissue.setDescription(issue.getDescription());
				     editissue.setKstatus(issue.getKstatus());
				     editissue.setAddComment(issue.getAddComment());
				    if(issue.getKstatus().toString().equals("8")) {
				     editissue.setImgfile(issue.getImgfile().toString());
				    }else {
				    	 editissue.setImgfile(issue.getAssignto());
				    }
				    
				   /* if(issue.getKstatus().toString().equals("3")) {
					     editissue.setInvimg(issue.getInvimg().toString());
					    }else {
					    	 editissue.setInvimg(issue.getInvimg());
					    }*/
				     editissue.setWarranty(issue.getWarranty());
				} 
			     taskHistoryLogsDao.historyLogs(editissue);
			}     
	/*}*/


	/*@SuppressWarnings("unchecked")
	public List<KpStatus> getKpStatues() {
		return em.createQuery("SELECT kpstatus FROM KpStatus kpstatus").getResultList();
	}

	*/

	public  Map<String,Integer> getCountByStatusWise() {
		
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		

		Map<String,Integer> statusCounts =new LinkedHashMap<String,Integer>();

		Integer opentotal=0;

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rows = em
			.createNativeQuery(" select ks.name,count(*)as count from report_issue r,kpstatus ks  where  r.kstatus=ks.id  and r.assignto =:id group by kstatus").setParameter("id", id).getResultList();
			for (Object[] row : rows) {
				opentotal=opentotal+Integer.parseInt(String.valueOf(row[1]));
				statusCounts.put((String)row[0], Integer.parseInt(String.valueOf(row[1])));

			}

			statusCounts.put("Open",opentotal);
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}
		return statusCounts;


	}

	/*public Set<KpStatusLogs> getRepeatlogsById(int id) {
		
		Set<KpStatusLogs> listRepeatlogs =new TreeSet<KpStatusLogs>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rows = em
			.createNativeQuery("select l.comment,l.uploadfiles,l.statustime,kp.username,s.name from kpstatuslogs l,kpusers kp,kpstatus s where l.iassignto=kp.id and l.kpstatus=s.id and l.issueid =:custName" ).setParameter("custName", id)
			.getResultList();

			
			

			for (Object[] row : rows) {
				KpStatusLogs logs=new KpStatusLogs();
				logs.setComment((String) row[0]);
				logs.setUploadfiles((String) row[1]);
				logs.setStatustime((Date)row[2]);
				logs.setIssueid((String) row[3]);  // passing username
				logs.setKpstatus((String) row[4]);
				listRepeatlogs.add(logs);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}
		
		
		
		return listRepeatlogs;
	}*/
	
	
	
	public boolean deleteTask(Integer id, String status) {
		Boolean delete=false;
		try{
			
			AbheeTask task= (AbheeTask)em.find(AbheeTask.class ,id);
			   task.setStatus(status);
			   em.merge(task);
			   taskHistoryLogsDao.historyLog(task);

		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	/*public void saveSubTask(KpStatusLogs subtask) {
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id=String.valueOf(objuserBean.getId());
		subtask.setIassignto(id);
		
		if(subtask.getUploadfiles()!=null)
	     {
		subtask.setUploadfiles(fileTemplate.concurrentFileNames());
		fileTemplate.clearFiles();
	     }
		em.persist(subtask);
		
		
		
	}*/

	

	@SuppressWarnings("unchecked")
	public Set<AbheeTask> getissuesByselectionAssignTo(String id) {
		Set<AbheeTask> listissue=new LinkedHashSet<AbheeTask>();
		//User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			List<Object[]> rows = em.createNativeQuery("select r.id, r.taskno,r.subject,c.category as cname,r.category cid,p.priority as pname,r.priority as pid,u.username, r.assignto,r.created_time,s.severity as sname ,r.severity  as sid,r.status,r.description ,r.taskdeadline ,u1.username as asby,r.assignby,r.kstatus from report_issue r, kpcategory c, kppriority p, kpusers u, kpusers u1, kpseverity s, kpstatus ks , kpstatuslogs kpl  where  r.kstatus=ks.id and r.assignto=u.id and r.assignby=u1.id and p.id=r.priority and s.id=r.severity and c.id=r.category and kpl.issueid=r.id and r.assignto =:id  order by kpl.statustime desc").setParameter("id",id).getResultList();
			for (Object[] row : rows) {
				AbheeTask issue = new AbheeTask();
				issue.setId(Integer.parseInt(String.valueOf(row[0])));
				issue.setTaskno((String) row[1]);
				issue.setSubject((String) row[2]);
				issue.setCategory((String) row[3]);
				issue.setCategoryid((String) row[4]);
				issue.setPriority((String) row[5]);
				issue.setPriorityid((String) row[6]);
				issue.setAssignto((String) row[7]);
				issue.setAssigntoid((String) row[8]);
				issue.setCreatedTime((Date) row[9]);
				issue.setSeverity((String) row[10]);
				issue.setSeverityid((String) row[11]);
				issue.setStatus((String) row[12]);
				issue.setDescription((String) row[13]);
				issue.setTaskdeadline((String) row[14]);
				issue.setAssignby((String) row[15]);
				issue.setAssignbyid((String) row[16]);
				
				//issue.setKstatus((String) row[17]);

				
				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}
		
		
		return listissue;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Set<AbheeTask> getissuesByselectionAssignBy(String id) {
		Set<AbheeTask> listissue=new LinkedHashSet<AbheeTask>();
		try {
			List<Object[]> rows = em.createNativeQuery(" select r.id, r.taskno,r.subject,c.category as cname,r.category cid,p.priority as pname,r.priority as pid,u.username, r.assignto,r.created_time,s.severity as sname ,r.severity  as sid ,r.status,r.description ,r.taskdeadline,r.assignby,u1.username as asby,r.kstatus from report_issue r, kpcategory c, kppriority p, kpusers u, kpusers u1, kpseverity s, kpstatus ks ,kpstatuslogs kpl   where  r.kstatus=ks.id and r.assignto=u.id and  r.assignby=u1.id and p.id=r.priority and s.id=r.severity and c.id=r.category  and kpl.issueid=r.id and  r.assignby=:id  order by kpl.statustime desc").setParameter("id",id).getResultList();
			for (Object[] row : rows) {
				AbheeTask issue = new AbheeTask();
				issue.setId(Integer.parseInt(String.valueOf(row[0])));
				issue.setTaskno((String) row[1]);
				issue.setSubject((String) row[2]);
				issue.setCategory((String) row[3]);
				issue.setCategoryid((String) row[4]);
				issue.setPriority((String) row[5]);
				issue.setPriorityid((String) row[6]);
				issue.setAssignto((String) row[7]);
				issue.setAssigntoid((String) row[8]);
				issue.setCreatedTime((Date) row[9]);
				issue.setSeverity((String) row[10]);
				issue.setSeverityid((String) row[11]);
				issue.setStatus((String) row[12]);
				issue.setDescription((String) row[13]);
				issue.setTaskdeadline((String) row[14]);
				issue.setAssignbyid((String) row[15]);
				issue.setAssignby((String) row[16]);
				//issue.setKstatus((String) row[17]);


				
				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}
		
		
		return listissue;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public Set<AbheeTask> getDepartmentWise(String deptid) {
		Set<AbheeTask> listissue=new LinkedHashSet<AbheeTask>();
		
		
		try {
			List<Object[]> rows = em.createNativeQuery("select  r.id , u.username, s.severity as sev, p.priority as pp,r.uploadfile,r.subject ,r.created_time,c.category as cc,ks.name,r.status ,r.taskno ,r.severity as sid, r.priority as pid,r.assignto , r.category as rcid,r.description ,r.taskdeadline,r.assignby,u1.username as asby ,r.kstatus from report_issue r, kpcategory c, kppriority p, kpusers u, kpusers u1, kpseverity s, kpstatus ks, kpstatuslogs kpl    where  r.kstatus=ks.id and r.assignto=u.id and r.assignby=u1.id and p.id=r.priority and s.id=r.severity and c.id=r.category   and  kpl.issueid=r.id and u.department =:custName order by kpl.statustime desc").setParameter("custName", deptid).getResultList();
			for (Object[] row : rows) {
				AbheeTask issue = new AbheeTask();
				issue.setId(Integer.parseInt(String.valueOf(row[0])));
				issue.setAssignto((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setUploadfile((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setCreatedTime((Date) row[6]);
				issue.setCategory((String) row[7]);
				issue.setKstatus((String) row[8]);
				issue.setStatus((String) row[9]);
				issue.setTaskno((String) row[10]);
				
				issue.setSeverityid((String) row[11]);
				issue.setPriorityid((String) row[12]);
				issue.setAssigntoid((String) row[13]);
			    issue.setCategoryid((String) row[14]);
			    issue.setDescription((String) row[15]);
				issue.setTaskdeadline((String) row[16]);
				issue.setAssignbyid((String) row[17]);
				issue.setAssignby((String) row[18]);
				
				//issue.setKstatus((String) row[19]);
			    
			    
				
				listissue.add(issue);

			}
			
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}
		
		
		return listissue;
	}

	public boolean openTask(Integer id, String status) {
		Boolean delete=false;
		try{
			
			AbheeTask task= (AbheeTask)em.find(AbheeTask.class ,id);
			   task.setAdditionalinfo(status);
			   task.setKstatus("2");
			   em.merge(task);
			if(!status.equals(task.getAdditionalinfo()))
			{
				delete=true;
			}
			
			if(!status.equals(task.getStatus()))
			{
				delete=true;
			}
			
//			recording task history
		/*	KpStatusLogs slogs=new KpStatusLogs();

			slogs.setIssueid(String.valueOf(id));
			slogs.setIassignto(task.getAssignto());
			slogs.setComment("Task Mark As Read");
			slogs.setKpstatus(task.getKstatus());
			

			em.persist(slogs);*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	@SuppressWarnings("unchecked")
	public Set<AbheeTask> getOpenTasks(String id) {
		Set<AbheeTask> listissue=new LinkedHashSet<AbheeTask>();
		//User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			List<Object[]> rows = em.createNativeQuery("select r.id, r.taskno,r.subject,c.category as cname,r.category cid,p.priority as pname,r.priority as pid,u.username, r.assignto,r.created_time,s.severity as sname ,r.severity  as sid,r.status,r.description ,r.taskdeadline ,r.additionalinfo,u1.username as asby,r.assignby from report_issue r, kpcategory c, kppriority p, kpusers u, kpseverity s, kpstatus ks , kpstatuslogs kpl ,kpusers u1 where  r.kstatus=ks.id and r.assignto=u.id and r.assignby =u1.id  and p.id=r.priority and s.id=r.severity and c.id=r.category and kpl.issueid=r.id and r.assignto =:id  and r.additionalinfo ='0' order by kpl.statustime desc").setParameter("id",id).getResultList();
			for (Object[] row : rows) {
				AbheeTask issue = new AbheeTask();
				issue.setId(Integer.parseInt(String.valueOf(row[0])));
				issue.setTaskno((String) row[1]);
				issue.setSubject((String) row[2]);
				issue.setCategory((String) row[3]);
				issue.setCategoryid((String) row[4]);
				issue.setPriority((String) row[5]);
				issue.setPriorityid((String) row[6]);
				issue.setAssignto((String) row[7]);
				issue.setAssigntoid((String) row[8]);
				issue.setCreatedTime((Date) row[9]);
				issue.setSeverity((String) row[10]);
				issue.setSeverityid((String) row[11]);
				issue.setStatus((String) row[12]);
				issue.setDescription((String) row[13]);
				issue.setTaskdeadline((String) row[14]);
				issue.setAdditionalinfo((String) row[15]);
				
				issue.setAssignby((String) row[16]);
				issue.setAssignbyid((String) row[17]);

				

				
				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}
		
		
		return listissue;
	}

	public Map<String, Object> checkServiceRequestExisrOrNot(AbheeTask task) {
		
		//String hql ="from AbheeTask where customerId =' "+task.getCustomerId()+"' and modelid='"+task.getModelid()+"' and kstatus <> '4' ";
		
		String hql =" select * from abhee_task where category ='"+task.getCategory()+"' and customer_id='"+task.getCustomerId()+"' and modelid ='"+task.getModelid()+"' and service_type='"+task.getServiceType()+"'and kstatus <> '4' ";
		//Query query = em.createQuery(hql);
		
		List<Map<String,Object>>  list = jdbcTemplate.queryForList(hql,new Object[]{});
		
		System.out.println(list);
		
		
		if(list.size()>0)
			return list.get(0);
		else
			return null;
		
		
	}

	
	
	public void saveServiceRequestFromCustomer(AbheeTask reportIssue) {
		String randomNum = utilities.randNum();
		
		reportIssue.setTaskno(randomNum);
		
		
		em.persist(reportIssue);
		
		
		
		
		taskHistoryLogsDao.historyLogForcustomerEntry(reportIssue);
		
	}

	public List<Map<String, Object>> getAllTasksListById(User user) 
	{
		String sql="select t.id,t.additionalinfo,t.created_time,t.description,t.subject,t.taskdeadline,t.taskno,t.updated_time,t.uploadfile,t.add_comment,t.customer_id,t.communicationaddress,t.warranty,u1.username as assignby,u2.username as assignto,c.category,ts.name as kstatus,p.priority,s.severity,st.servicetypename as servicetype,ap.name as modelname from abhee_task t,abheeusers u1,abheeusers u2,abheecategory c, abheetaskstatus ts,abheepriority p,abheeseverity s,abheeservicetype st,abhee_product ap where t.assignby=u1.id and t.assignto=u2.id and t.category=c.id and t.kstatus=ts.id and t.priority=p.id and t.severity=s.id and t.service_type =st.id and t.modelid=ap.id and u1.user_id='"+user.getUserId()+"'";

		/*RowMapper<AbheeTask> rowMapper = new BeanPropertyRowMapper<AbheeTask>(AbheeTask.class);
	    return  this.jdbcTemplate.query(sql, rowMapper);*/
	    List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	
	public List<Map<String, Object>> getTasksList() 
	{
		String sql="select t.id,t.additionalinfo,t.created_time,t.description,t.subject,t.taskdeadline,t.taskno,t.updated_time,t.uploadfile,t.add_comment,t.customer_id,t.communicationaddress,t.warranty,u1.username as assignby,u2.username as assignto,c.category,ts.name as kstatus,p.priority,s.severity,st.servicetypename as servicetype,ap.name as modelname,u2.user_id as empid from abhee_task t,abheeusers u1,abheeusers u2,abheecategory c, abheetaskstatus ts,abheepriority p,abheeseverity s,abheeservicetype st,abhee_product ap where t.assignby=u1.id and t.assignto=u2.id and t.category=c.id and t.kstatus=ts.id and t.priority=p.id and t.severity=s.id and t.service_type =st.id and t.modelid=ap.id";
	    List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	public List<Map<String, Object>> getTasksList1() 
	{
		//String sql="select t.id,t.additionalinfo,t.imgfile,t.created_time,t.description,t.subject,t.taskdeadline,t.taskno,t.updated_time,t.uploadfile,t.add_comment,t.customer_id,t.communicationaddress,t.warranty,u2.username as assignto,c.category,ts.name as kstatus,p.priority,s.severity,st.servicetypename as servicetype,ap.name as modelname,u2.user_id as empid  from abhee_task t,abheeusers u2,abheecategory c, abheetaskstatus ts,abheepriority p,abheeseverity s,abheeservicetype st,abhee_product ap  where  t.assignto=u2.id and t.category=c.id and t.kstatus=ts.id  and t.priority=p.id and t.severity=s.id and t.service_type =st.id and t.modelid=ap.id  and t.status='1' and t.kstatus<>'4'"; 
			String sql="select t.id,t.additionalinfo,t.imgfile,t.invimg,t.created_time,t.description,t.subject,t.taskdeadline,t.taskno,t.updated_time,t.uploadfile,t.add_comment,t.customer_id,t.communicationaddress,t.warranty,u2.username as assignto,c.category,ts.name as kstatus,p.priority,s.severity,st.servicetypename as servicetype,ap.name as modelname,u2.user_id as empid,ac.firstname,ac.lastname,ac.mobilenumber  from abhee_task t,abheeusers u2,abheecategory c, abheetaskstatus ts,abheepriority p,abheeseverity s,abheeservicetype st,abhee_product ap,abhee_customer ac  where  t.assignto=u2.id and t.category=c.id and t.kstatus=ts.id  and t.priority=p.id and t.severity=s.id and t.service_type =st.id and t.modelid=ap.id  and t.status='1' and t.customer_id=ac.customer_id and t.kstatus<>'4'";	
	    List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	
	@SuppressWarnings("unchecked")
	public AbheeTask getRequestDetailsByObject(AbheeTask task) 
	{
		String hql ="from AbheeTask where taskno ='"+ task.getTaskno()+"'";
		List<AbheeTask> pwd= em.createQuery(hql).getResultList();
		if(pwd.size()>0)
			return pwd.get(0);
		return null;
	}
	
	public String randomInvoiceId()
	{
		String prefix="";
		String result="";
		String sql="select invoice_id from abhee_task where (invoice_id IS NULL or invoice_id <> ' ') order by invoice_id desc limit 1 ";
		System.out.println(sql);
		result = jdbcTemplate.queryForObject(sql, String.class);
		System.out.println(result);
		if(!result.equals("(NULL)"))
		{
			prefix=result;
		}
		else
		{
			prefix="00000";
		}
		String invid="";
	    //String prefix = "11111"; 
	    int sufix=Integer.parseInt(prefix)+1;
	    String randnum=String.valueOf(sufix);
	    switch(randnum.length())  
	    {
	        case 1 :
	             invid="0000"+randnum;
	             break;
	        case 2 :
	             invid="000"+randnum;
	             break;
	        case 3 :
	            invid="00"+randnum;
	             break;
	        case 4 :
	            invid="0"+randnum;
	             break;
	        default:
	        invid=randnum;
	    }
	      System.out.println(prefix);
	      System.out.println(sufix);
	      System.out.println(invid);
		return invid;
	}
}








