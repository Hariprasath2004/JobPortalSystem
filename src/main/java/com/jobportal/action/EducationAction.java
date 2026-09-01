package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.EducationDAO;
import com.jobportal.model.Education;
import com.opensymphony.xwork2.ActionSupport;

public class EducationAction extends ActionSupport {

	private Education education;

	@Override
	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession(false);

		// Check whether user is logged in
		if (session == null) {
			return LOGIN;
		}

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return LOGIN;
		}

		// Create Education object if it is null
		if (education == null) {
			education = new Education();
		}

		// Set logged-in user's ID
		education.setUserId(userId);

		// Save education
		EducationDAO dao = new EducationDAO();

		boolean saved = dao.saveEducation(education);

		if (saved) {
			return SUCCESS;
		}

		addActionError("Unable to save education.");

		return ERROR;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}
}