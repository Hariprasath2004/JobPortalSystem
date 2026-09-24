package com.jobportal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.ExperienceDAO;
import com.jobportal.model.Experience;
import com.opensymphony.xwork2.ActionSupport;

public class ExperienceAction extends ActionSupport {

	private Experience experience;
	private List<Experience> experiences;

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

		ExperienceDAO dao = new ExperienceDAO();

		// Save experience
		if ("POST".equalsIgnoreCase(request.getMethod())) {

			if (experience == null) {
				experience = new Experience();
			}

			experience.setUserId(userId);

			if (!dao.saveExperience(experience)) {
				addActionError("Unable to save experience.");
				experiences = dao.getExperiencesByUserId(userId);
				return ERROR;
			}
		}

		// Load user's experiences
		experiences = dao.getExperiencesByUserId(userId);

		return SUCCESS;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}
}