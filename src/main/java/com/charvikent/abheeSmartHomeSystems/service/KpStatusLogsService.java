package com.charvikent.abheeSmartHomeSystems.service;

import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charvikent.abheeSmartHomeSystems.dao.KpStatusLogsDao;
import com.charvikent.abheeSmartHomeSystems.model.KpStatusLogs;

@Service
@Transactional
public class KpStatusLogsService {
     
	@Autowired
	KpStatusLogsDao kpStatusLogsDao;
	
	
	  public   KpStatusLogs getLastRecord(String issueid)
	  {
			TreeSet<KpStatusLogs>  list =kpStatusLogsDao.getKpStatusLogsDao();
			
			TreeSet<KpStatusLogs>  list2 =new TreeSet<KpStatusLogs>();
			
			for(KpStatusLogs entry: list){
				if(entry.getIssueid().equals(issueid))
				{
					list2.add(entry);
				}
			}
			
			
			
		  return list2.first();
	  }
	
	
	
}
