package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.JobDAO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteJobAction extends ActionSupport {

	private int jobId;

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