package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.CompanyProfileDAO;
import com.jobportal.model.CompanyProfile;
import com.opensymphony.xwork2.ActionSupport;

public class CompanyProfileAction extends ActionSupport {

	private CompanyProfile companyProfile;

	private CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();

	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession(false);

		// User session check
		if (session == null) {
			return "login";
		}

		Integer userId = (Integer) session.getAttribute("userId");

		String role = (String) session.getAttribute("role");

		// Login check
		if (userId == null) {
			return "login";
		}

		// Recruiter only
		if (!"EMPLOYER".equalsIgnoreCase(role)) {
			return "login";
		}

		// GET request
		if (!"POST".equalsIgnoreCase(request.getMethod())) {

			companyProfile = companyProfileDAO.getByRecruiterId(userId);

			return SUCCESS;
		}

		// POST request
		if (companyProfile == null) {
			addActionError("Company profile details are required.");
			return ERROR;
		}

		companyProfile.setRecruiterId(userId);

		CompanyProfile existingProfile = companyProfileDAO.getByRecruiterId(userId);

		boolean saved;

		if (existingProfile == null) {

			saved = companyProfileDAO.saveProfile(companyProfile);

		} else {

			saved = companyProfileDAO.updateProfile(companyProfile);
		}

		if (saved) {

			// Reload latest profile
			companyProfile = companyProfileDAO.getByRecruiterId(userId);

			return SUCCESS;
		}

		addActionError("Unable to save company profile.");

		return ERROR;
	}

	public CompanyProfile getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(CompanyProfile companyProfile) {
		this.companyProfile = companyProfile;
	}
}