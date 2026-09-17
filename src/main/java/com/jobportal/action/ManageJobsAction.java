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

	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession(false);

		if (session == null) {
			return "login";
		}

		Integer userId = (Integer) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");

		if (userId == null) {
			return "login";
		}

		if (!"EMPLOYER".equalsIgnoreCase(role)) {
			return "login";
		}

		JobDAO dao = new JobDAO();

		jobs = dao.getJobsByRecruiterId(userId);

		return SUCCESS;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
}