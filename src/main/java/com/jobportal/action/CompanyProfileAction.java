package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.CompanyProfileDAO;
import com.jobportal.model.CompanyProfile;
import com.opensymphony.xwork2.ActionSupport;

public class CompanyProfileAction extends ActionSupport {

	private CompanyProfile companyProfile;

	private final CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();

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

		// Only recruiters can access the company profile
		if (!"EMPLOYER".equalsIgnoreCase(role)) {
			return LOGIN;
		}

		// Load the existing profile
		if (!"POST".equalsIgnoreCase(request.getMethod())) {

			companyProfile = companyProfileDAO.getByRecruiterId(userId);

			return SUCCESS;
		}

		// Validate submitted profile
		if (companyProfile == null) {
			addActionError("Company profile details are required.");
			return ERROR;
		}

		// Associate the profile with the logged-in recruiter
		companyProfile.setRecruiterId(userId);

		CompanyProfile existingProfile = companyProfileDAO.getByRecruiterId(userId);

		boolean saved;

		if (existingProfile == null) {

			// Create a new company profile
			saved = companyProfileDAO.saveProfile(companyProfile);

		} else {

			// Update the existing company profile
			saved = companyProfileDAO.updateProfile(companyProfile);
		}

		if (saved) {

			// Reload the latest saved profile
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