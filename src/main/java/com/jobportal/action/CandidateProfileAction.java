package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.CandidateProfileDAO;
import com.jobportal.model.CandidateProfile;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateProfileAction extends ActionSupport {

	private CandidateProfile profile;

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

		CandidateProfileDAO dao = new CandidateProfileDAO();

		// Save or update profile
		if ("POST".equalsIgnoreCase(request.getMethod())) {

			if (profile == null) {
				profile = new CandidateProfile();
			}

			// Always associate the profile with the logged-in user
			profile.setUserId(userId);

			int completion = calculateProfileCompletion(profile);
			profile.setProfileCompletion(completion);

			if (dao.saveProfile(profile)) {

				// Load the saved profile again
				profile = dao.getProfileByUserId(userId);

				return SUCCESS;
			}

			addActionError("Unable to save profile. Please check the application logs.");

			return ERROR;
		}

		// Load existing profile
		profile = dao.getProfileByUserId(userId);

		// Create an empty profile for first-time users
		if (profile == null) {

			profile = new CandidateProfile();
			profile.setUserId(userId);
			profile.setProfileCompletion(0);
		}

		return SUCCESS;
	}

	/**
	 * Calculates profile completion based on available information.
	 */
	private int calculateProfileCompletion(CandidateProfile profile) {

		int completion = 0;

		if (isNotEmpty(profile.getPhone())) {
			completion += 15;
		}

		if (isNotEmpty(profile.getDateOfBirth())) {
			completion += 10;
		}

		if (isNotEmpty(profile.getGender())) {
			completion += 10;
		}

		if (isNotEmpty(profile.getLocation())) {
			completion += 10;
		}

		if (isNotEmpty(profile.getHeadline())) {
			completion += 15;
		}

		if (isNotEmpty(profile.getSummary())) {
			completion += 15;
		}

		if (isNotEmpty(profile.getResumePath())) {
			completion += 25;
		}

		return completion;
	}

	/**
	 * Returns true when the given value is not null or blank.
	 */
	private boolean isNotEmpty(String value) {

		return value != null && !value.trim().isEmpty();
	}

	public CandidateProfile getProfile() {
		return profile;
	}

	public void setProfile(CandidateProfile profile) {
		this.profile = profile;
	}
}