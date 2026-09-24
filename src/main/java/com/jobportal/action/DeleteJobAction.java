package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.JobDAO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteJobAction extends ActionSupport {

	private int jobId;

	@Override
	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);

		// User must be logged in
		if (session == null) {
			return LOGIN;
		}

		Integer userId = (Integer) session.getAttribute("userId");
		String role = (String) session.getAttribute("role");

		if (userId == null) {
			return LOGIN;
		}

		// Only recruiters can delete jobs
		if (!"EMPLOYER".equalsIgnoreCase(role)) {
			return LOGIN;
		}

		// Validate the selected job
		if (jobId <= 0) {
			addActionError("Invalid job selected.");
			return ERROR;
		}

		JobDAO dao = new JobDAO();

		boolean deleted = dao.deleteJob(jobId, userId);

		if (deleted) {
			return SUCCESS;
		}

		addActionError("Unable to delete job.");

		return ERROR;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
}