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

		// User must be logged in
		if (session == null) {
			return LOGIN;
		}

		String role = (String) session.getAttribute("role");

		// Role must be available in the session
		if (role == null) {
			return LOGIN;
		}

		// Allow job seekers to access the seeker dashboard
		if ("seeker".equalsIgnoreCase(dashboardType) && "JOB_SEEKER".equalsIgnoreCase(role)) {

			return SUCCESS;
		}

		// Allow recruiters to access the recruiter dashboard
		if ("recruiter".equalsIgnoreCase(dashboardType) && "EMPLOYER".equalsIgnoreCase(role)) {

			return SUCCESS;
		}

		// Prevent access to an unauthorized dashboard
		return LOGIN;
	}

	public String getDashboardType() {
		return dashboardType;
	}

	public void setDashboardType(String dashboardType) {
		this.dashboardType = dashboardType;
	}
}