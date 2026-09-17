package com.jobportal.action;

import com.jobportal.dao.JobApplicationDAO;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

public class UpdateApplicationStatusAction extends ActionSupport {

	private int applicationId;
	private int jobId;
	private String status;

	private final JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();

	@Override
	public String execute() {

		Map<String, Object> session = org.apache.struts2.ServletActionContext.getContext().getSession();

		Object userIdObject = session.get("userId");

		if (userIdObject == null) {
			addActionError("Please login first.");
			return LOGIN;
		}

		int recruiterId = (Integer) userIdObject;

		if (status == null || status.trim().isEmpty()) {
			addActionError("Invalid application status.");
			return ERROR;
		}

		boolean updated = jobApplicationDAO.updateApplicationStatus(applicationId, recruiterId, status);

		if (updated) {
			addActionMessage("Application status updated successfully.");
			return SUCCESS;
		}

		addActionError("Unable to update application status.");

		return ERROR;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}