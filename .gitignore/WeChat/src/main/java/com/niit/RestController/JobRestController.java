package com.niit.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobDao;
import com.niit.model.Job;

@RestController
public class JobRestController {
	
	@Autowired
	JobDao jobDao;
	
	@PostMapping("/jobposting")
	public ResponseEntity<Job> jobPosting(@RequestBody Job job)
	{
		jobDao.add(job);
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	
	@GetMapping("/alljobs")
	public ResponseEntity<List<Job>> allJobs()
	{
 		List<Job> list=jobDao.list();
 		for(Job j: list)
 		{
 			System.out.println(j.getDated());
 		}
		return new ResponseEntity<List<Job>>(list,HttpStatus.OK);
	}
}
