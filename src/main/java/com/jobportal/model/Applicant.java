package com.jobportal.model;

import java.sql.Timestamp;

public class Applicant {

	private int applicationId;

	private int jobId;

	private int seekerId;

	private String seekerName;

	private String seekerEmail;

	private String jobTitle;

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

	public String getSeekerName() {
		return seekerName;
	}

	public void setSeekerName(String seekerName) {
		this.seekerName = seekerName;
	}

	public String getSeekerEmail() {
		return seekerEmail;
	}

	public void setSeekerEmail(String seekerEmail) {
		this.seekerEmail = seekerEmail;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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