package com.jobportal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.JobApplicationDAO;
import com.jobportal.dao.JobDAO;
import com.jobportal.model.Job;
import com.opensymphony.xwork2.ActionSupport;

public class JobSearchAction extends ActionSupport {

	private List<Job> jobs;
	private List<Integer> appliedJobIds;

	@Override
	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);

		if (session == null) {
			return LOGIN;
		}

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return LOGIN;
		}

		String role = (String) session.getAttribute("role");

		if (!"JOB_SEEKER".equalsIgnoreCase(role) && !"USER".equalsIgnoreCase(role)
				&& !"SEEKER".equalsIgnoreCase(role)) {

			addActionError("Only job seekers can search jobs.");
			return ERROR;
		}

		JobDAO jobDAO = new JobDAO();
		jobs = jobDAO.getAllJobs();

		JobApplicationDAO applicationDAO = new JobApplicationDAO();
		appliedJobIds = applicationDAO.getAppliedJobIds(userId);

		String successMessage = (String) session.getAttribute("applicationSuccess");

		if (successMessage != null) {
			addActionMessage(successMessage);
			session.removeAttribute("applicationSuccess");
		}

		String errorMessage = (String) session.getAttribute("applicationError");

		if (errorMessage != null) {
			addActionError(errorMessage);
			session.removeAttribute("applicationError");
		}

		return SUCCESS;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Integer> getAppliedJobIds() {
		return appliedJobIds;
	}

	public void setAppliedJobIds(List<Integer> appliedJobIds) {
		this.appliedJobIds = appliedJobIds;
	}
}