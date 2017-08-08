package com.niit.testcase;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.config.ApplicationContextConfig;
import com.niit.dao.BlogDao;
import com.niit.dao.JobDao;
import com.niit.dao.RegistrationDao;
import com.niit.model.Blog;
import com.niit.model.Job;
import com.niit.model.RegistrationPage;

import jdk.nashorn.internal.runtime.Context;

public class AppTest 
{
	@SuppressWarnings("resource")
	public static void main(String args[])
	{
		System.out.println("main started");
		@SuppressWarnings("unused")
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
		System.out.println("main ended");
	

		RegistrationDao registrationDao=(RegistrationDao)context.getBean("regDao");
		RegistrationPage user=new RegistrationPage();
		/*user.setUsername("sowmya");
		user.setEmail("sowmya@gmail.com");
		user.setMobileno("9246150869");
		user.setPassword("password");
		user.setUserid(101);
		user.setRole("user");
		user.setIsactive(true);
		registrationDao.addUser(user);*/
		
		BlogDao blogDao=(BlogDao)context.getBean("blogDao");
		Blog blog = new Blog();
		/*blog.setTitle("collaboration ");
		blog.setDescription("angualar project");
		blog.setUser(registrationDao.getUserId(2));*/
	
		blogDao.addBlog(blog);
		
		
		JobDao jobDao=(JobDao)context.getBean("jobdao");
		Job job= new Job();
		job.setTitle("java Developer");
		job.setDescription("java developer is required and must know java,core java");
		job.setJobId(1);
		job.setQualification("B.Tech");
		job.setStatus("true");
		job.setDated( new Date());
		jobDao.add(job);
		System.out.println("successfully record enterd");
	}
	
	
}
