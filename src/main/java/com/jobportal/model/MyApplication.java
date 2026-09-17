package com.jobportal.model;

import java.sql.Timestamp;

public class MyApplication {

	private int applicationId;

	private int jobId;

	private String jobTitle;

	private String companyName;

	private String location;

	private String jobType;

	private String applicationStatus;

	private Timestamp appliedAt;

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public Timestamp getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(Timestamp appliedAt) {
		this.appliedAt = appliedAt;
	}
}