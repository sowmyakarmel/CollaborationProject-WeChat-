package com.niit.dao;

import java.util.List;

import com.niit.model.JobApplicants;
import com.niit.model.Blog;

public interface JobApplicantDao {
	public void addJob(JobApplicants job);
	public void updateJob(JobApplicants job);
	public void deleteJob(JobApplicants job);
	public JobApplicants getjobId (long jobId);
	public List<JobApplicants> getAppliedJObs(int userid);
	
	

}
