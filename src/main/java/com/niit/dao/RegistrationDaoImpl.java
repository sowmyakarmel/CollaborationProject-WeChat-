package com.niit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.RegistrationPage;
import javax.persistence.NoResultException;

@Transactional
@Repository("regDao")
public class RegistrationDaoImpl implements RegistrationDao
{
@Autowired
SessionFactory sessionFactory;

public void setSessionFactory(SessionFactory sessionFactory) {
   this.sessionFactory = sessionFactory;
}


@Override
public void addUser(RegistrationPage user) 
{
System.out.println("i am in add user dao");
Session session=sessionFactory.getCurrentSession();
session.saveOrUpdate(user);

}
public RegistrationPage getUserByUsername(String username) {
Session session=sessionFactory.getCurrentSession();
RegistrationPage user=(RegistrationPage)session.createQuery("from RegistrationPage where username='"+username+"'").getSingleResult();
return user;
}

public RegistrationPage getEmail(String email,String password)
{
	System.out.println("i am in add user dao");
	
return (RegistrationPage)	sessionFactory.getCurrentSession().createQuery("from RegistrationPage where email='"+email+"' and password='"+password+"'").getSingleResult();
	

}
@SuppressWarnings("unchecked")
public List<RegistrationPage> listUsers() {
List<RegistrationPage> rgs=sessionFactory.getCurrentSession().createQuery("from RegistrationPage").getResultList();
return rgs;

}

public boolean isExistingUser(RegistrationPage user) {
RegistrationPage u=null;
try {
u=getUserByUsername(user.getUsername());
}catch(NoResultException nre){
}
if(u!=null)
{
return true;
}
else
return false;
}

public RegistrationPage getUserId(int userId) {
	System.out.println("i am in add user dao");
	return (RegistrationPage)sessionFactory.getCurrentSession().createQuery("from RegistrationPage where userId="+userId).getSingleResult();

	



}



}
