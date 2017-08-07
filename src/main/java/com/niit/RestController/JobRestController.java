package com.niit.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobApplicantDao;
import com.niit.dao.JobDao;
import com.niit.model.Job;
import com.niit.model.JobApplicants;
import com.niit.model.Job;

@RestController
public class JobRestController {
	
	@Autowired
	JobDao jobDao;
	
	@Autowired
	JobApplicantDao jobApplicantDao;
	@PostMapping("/jobposting")
	public ResponseEntity<Job> jobPosting(@RequestBody Job job)
	{
		jobDao.add(job);
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	
	@GetMapping("/alljobs/{id}")
	public ResponseEntity<List<Job>> allJobs(@PathVariable("id") Integer userId)
	{
 		List<Job> list=jobDao.list(userId);
 		for(Job j: list)
 		{
 			System.out.println(j.getDated());
 		}
		return new ResponseEntity<List<Job>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/applyJob")
	public ResponseEntity<List<Job>> applyJob(@RequestBody JobApplicants jobApplicants)
	{
		jobApplicantDao.addJob(jobApplicants);
		
	return new ResponseEntity<>(jobDao.list(jobApplicants.getUserId()),HttpStatus.OK)	;
		
	}
	
	@GetMapping("/appliedJobs/{id}")
	public ResponseEntity<List<Job>> applyJob(@PathVariable("id") Integer userId)
	{
		
		
	return new ResponseEntity<List<Job>>(jobDao.appliedJobs(userId),HttpStatus.OK)	;
		
	}
}