package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Table(name="Job", schema="ANGULARDB")
@Entity
public class Job
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long jobId;
	@Size(min=5,max=100,message="Job title should be between 5 to 100 characters.")
	private String title;
	@Size(min=5,max=300,message="your job name should be between 5 and 300")
	private String description;
	private Date dated;
	@Size(min=2,max=50,message="your qualification should be between 5-50 characters.")
	private String qualification;
	private String status;
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDated() {
		return dated;
	}
	public void setDated(Date dated) {
		this.dated = dated;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
