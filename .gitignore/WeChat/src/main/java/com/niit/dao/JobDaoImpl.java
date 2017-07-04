package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Job;

@Repository("jobdao")
@Transactional
public class JobDaoImpl  implements JobDao
{
	
	@Autowired(required = true)
	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	}


	public List<Job> list() {
	
		return sessionFactory.getCurrentSession().createQuery("from Job",Job.class).getResultList();

	}

	@SuppressWarnings("deprecation")
	public Job get(int id) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.createQuery("from Job where jobId="+id).getSingleResult();
		return job;

	}

	public void add(Job job) {

		System.out.println("i am in add job dao");
		sessionFactory.getCurrentSession().save(job);
		
		//Transaction tx = session.beginTransaction();
		//session.saveOrUpdate(job);
		//tx.commit();

	}

	public void delete(int id) {
		Job job = new Job();
		job.setJobId(id);
		sessionFactory.getCurrentSession().delete(job);
		
	}

}
