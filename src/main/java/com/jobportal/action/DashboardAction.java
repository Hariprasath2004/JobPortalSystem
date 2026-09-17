package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DashboardAction extends ActionSupport {

	private String dashboardType;

	@Override
	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession(false);

		// No login session
		if (session == null) {
			return LOGIN;
		}

		String role = (String) session.getAttribute("role");

		// No role stored in session
		if (role == null) {
			return LOGIN;
		}

		// Job Seeker dashboard
		if (dashboardType.equals("seeker") && role.equals("JOB_SEEKER")) {

			return SUCCESS;
		}

		// Recruiter dashboard
		if (dashboardType.equals("recruiter") && role.equals("EMPLOYER")) {

			return SUCCESS;
		}

		// Wrong dashboard for the logged-in role
		return LOGIN;
	}

	public String getDashboardType() {
		return dashboardType;
	}

	public void setDashboardType(String dashboardType) {
		this.dashboardType = dashboardType;
	}
}