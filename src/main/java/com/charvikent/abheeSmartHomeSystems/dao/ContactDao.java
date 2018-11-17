package com.charvikent.abheeSmartHomeSystems.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.charvikent.abheeSmartHomeSystems.model.Contact;

@Repository
@Transactional
public class ContactDao 
{
	@PersistenceContext private EntityManager entityManager;
	public void saveContact(Contact contact) 
	{
		entityManager.persist(contact);
	}
}
