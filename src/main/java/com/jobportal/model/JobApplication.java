package com.jobportal.model;

import java.sql.Timestamp;

public class JobApplication {

	private int applicationId;

	private int jobId;

	private int seekerId;

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

	public int getSeekerId() {
		return seekerId;
	}

	public void setSeekerId(int seekerId) {
		this.seekerId = seekerId;
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