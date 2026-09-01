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

        HttpServletRequest request =
                ServletActionContext.getRequest();

        HttpSession session =
                request.getSession(false);

        // Check whether user is logged in
        if (session == null) {
            return "login";
        }

        Integer userId =
                (Integer) session.getAttribute("userId");

        if (userId == null) {
            return "login";
        }

        /*
         * POST request means user submitted
         * the experience form.
         */
        if ("POST".equalsIgnoreCase(request.getMethod())) {

            // Create object if it is null
            if (experience == null) {
                experience = new Experience();
            }

            // Set logged-in user's ID
            experience.setUserId(userId);

            ExperienceDAO dao =
                    new ExperienceDAO();

            boolean saved =
                    dao.saveExperience(experience);

            if (saved) {

                // Reload all experiences after saving
                experiences =
                        dao.getExperiencesByUserId(userId);

                return SUCCESS;
            }

            addActionError("Unable to save experience.");

            // Load existing experiences
            experiences =
                    dao.getExperiencesByUserId(userId);

            return ERROR;
        }

        /*
         * GET request means open the
         * experience page.
         */
        ExperienceDAO dao =
                new ExperienceDAO();

        experiences =
                dao.getExperiencesByUserId(userId);

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