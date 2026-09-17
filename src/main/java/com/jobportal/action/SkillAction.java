package com.jobportal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.SkillDAO;
import com.jobportal.model.Skill;
import com.opensymphony.xwork2.ActionSupport;

public class SkillAction extends ActionSupport {

	private Skill skill;

	private List<Skill> skills;

	@Override
	public String execute() {

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession(false);

		// Check whether user is logged in
		if (session == null) {
			return "login";
		}

		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "login";
		}

		/*
		 * POST request means the user submitted the skill form.
		 */
		if ("POST".equalsIgnoreCase(request.getMethod())) {

			// Create object if it is null
			if (skill == null) {
				skill = new Skill();
			}

			// Set logged-in user's ID
			skill.setUserId(userId);

			SkillDAO dao = new SkillDAO();

			boolean saved = dao.saveSkill(skill);

			if (saved) {

				// Reload skills after saving
				skills = dao.getSkillsByUserId(userId);

				return SUCCESS;
			}

			addActionError("Unable to save skill.");

			// Load existing skills
			skills = dao.getSkillsByUserId(userId);

			return ERROR;
		}

		/*
		 * GET request means open the skills page.
		 */
		SkillDAO dao = new SkillDAO();

		skills = dao.getSkillsByUserId(userId);

		return SUCCESS;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
}