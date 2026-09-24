package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.JobApplicationDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ApplyJobAction extends ActionSupport {

	private static final String LOGIN = "login";
	private static final String SEARCH = "search";

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

		if (userId == null) {
			return LOGIN;
		}

		// Only job seekers can apply
		String role = (String) session.getAttribute("role");

		if (!isJobSeeker(role)) {
			session.setAttribute("applicationError", "Only job seekers can apply for jobs.");
			return SEARCH;
		}

		// Validate job ID
		if (jobId <= 0) {
			session.setAttribute("applicationError", "Invalid job selected.");
			return SEARCH;
		}

		JobApplicationDAO dao = new JobApplicationDAO();

		// Prevent duplicate applications
		if (dao.hasApplied(jobId, userId)) {
			session.setAttribute("applicationError", "You have already applied for this job.");
			return SEARCH;
		}

		// Submit application
		boolean applied = dao.applyForJob(jobId, userId);

		if (applied) {
			session.setAttribute("applicationSuccess", "Application submitted successfully.");
		} else {
			session.setAttribute("applicationError", "Failed to apply for the job.");
		}

		return SEARCH;
	}

	/**
	 * Checks whether the logged-in user has a job seeker role.
	 */
	private boolean isJobSeeker(String role) {

		return role != null && ("JOB_SEEKER".equalsIgnoreCase(role) || "USER".equalsIgnoreCase(role)
				|| "SEEKER".equalsIgnoreCase(role));
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
}