package com.jobportal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.JobDAO;
import com.jobportal.model.Job;
import com.opensymphony.xwork2.ActionSupport;

public class ManageJobsAction extends ActionSupport {

	private List<Job> jobs;

	@Override
	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);

		// Check whether the user is logged in
		if (session == null) {
			return LOGIN;
		}

		Integer userId = (Integer) session.getAttribute("userId");
		String role = (String) session.getAttribute("role");

		if (userId == null) {
			return LOGIN;
		}

		// Only recruiters can manage jobs
		if (!"EMPLOYER".equalsIgnoreCase(role)) {
			return LOGIN;
		}

		// Load jobs posted by the logged-in recruiter
		JobDAO jobDAO = new JobDAO();
		jobs = jobDAO.getJobsByRecruiterId(userId);

		return SUCCESS;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
}