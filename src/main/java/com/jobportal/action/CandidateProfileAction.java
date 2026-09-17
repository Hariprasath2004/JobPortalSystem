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

		// Check login
		if (session == null) {
			return LOGIN;
		}

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return LOGIN;
		}

		CandidateProfileDAO dao = new CandidateProfileDAO();

		/*
		 * POST = Save / Update profile
		 */
		if ("POST".equalsIgnoreCase(request.getMethod())) {

			// Safety check
			if (profile == null) {
				profile = new CandidateProfile();
			}

			// Always use logged-in user's ID
			profile.setUserId(userId);

			// Calculate actual profile completion
			int completion = calculateProfileCompletion(profile);

			profile.setProfileCompletion(completion);

			boolean saved = dao.saveProfile(profile);

			if (saved) {

				// Reload saved profile
				profile = dao.getProfileByUserId(userId);

				return SUCCESS;
			}

			addActionError("Unable to save profile. Check Eclipse console.");

			return ERROR;
		}

		/*
		 * GET = Open profile page
		 */
		profile = dao.getProfileByUserId(userId);

		/*
		 * If profile doesn't exist, create empty object.
		 */
		if (profile == null) {

			profile = new CandidateProfile();

			profile.setUserId(userId);

			profile.setProfileCompletion(0);
		}

		return SUCCESS;
	}

	/*
	 * Calculate profile completion percentage.
	 *
	 * Total = 100%
	 *
	 * Phone = 15% Date of Birth = 10% Gender = 10% Location = 10% Headline = 15%
	 * Summary = 15% Resume = 25%
	 */
	private int calculateProfileCompletion(CandidateProfile profile) {

		int completion = 0;

		// Phone - 15%
		if (isNotEmpty(profile.getPhone())) {
			completion += 15;
		}

		// Date of Birth - 10%
		if (isNotEmpty(profile.getDateOfBirth())) {
			completion += 10;
		}

		// Gender - 10%
		if (isNotEmpty(profile.getGender())) {
			completion += 10;
		}

		// Location - 10%
		if (isNotEmpty(profile.getLocation())) {
			completion += 10;
		}

		// Professional Headline - 15%
		if (isNotEmpty(profile.getHeadline())) {
			completion += 15;
		}

		// Summary - 15%
		if (isNotEmpty(profile.getSummary())) {
			completion += 15;
		}

		// Resume - 25%
		if (isNotEmpty(profile.getResumePath())) {
			completion += 25;
		}

		return completion;
	}

	/*
	 * Check whether a String has a value.
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