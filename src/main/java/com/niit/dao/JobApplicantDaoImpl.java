package com.niit.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.JobApplicants;

@Repository("jobApplicantDao")
@Transactional
public class JobApplicantDaoImpl implements JobApplicantDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addJob(JobApplicants job) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().save(job);
		
	}

	@Override
	public void updateJob(JobApplicants job) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(job);
		
	}

	@Override
	public void deleteJob(JobApplicants job) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JobApplicants getjobId(long jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobApplicants> getAppliedJObs(int userid) {
		// TODO Auto-generated method stub
		try{
		return sessionFactory.getCurrentSession().createQuery("From JobApplicants where userId=:id",JobApplicants.class)
		.setParameter("id", userid)
		.getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	

}
