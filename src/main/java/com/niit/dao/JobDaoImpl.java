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
		 sessionFactory.getCurrentSession().saveOrUpdate(job);
		

	}

	public void delete(int id) {
		Job job = new Job();
		job.setJobId(id);
		sessionFactory.getCurrentSession().delete(job);
		
	}


	@Override
	public List<Job> list(Integer userId) {
		return sessionFactory.getCurrentSession().createQuery("from Job where jobId not IN (select jobId from JobApplicants where userId=:userId)",Job.class)
				.setParameter("userId", userId).getResultList();
	}


	@Override
	public List<Job> appliedJobs(Integer userId) {
		System.out.println("in appliedJobs impl user id is "+userId);
		// TODO Auto-generated method stub
	try
	{
		return sessionFactory.getCurrentSession().createQuery("from Job where jobId in (select jobId from JobApplicants where userId=:userId)",Job.class)
		.setParameter("userId", userId)
		.getResultList();
	}catch(Exception e)
	{
		System.out.println(e);
		return null;
	}
	}

}
