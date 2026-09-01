package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.JobDAO;
import com.jobportal.model.Job;
import com.opensymphony.xwork2.ActionSupport;

public class PostJobAction extends ActionSupport {

    private Job job;

    public String execute() {

        HttpServletRequest request =
                ServletActionContext.getRequest();

        HttpSession session =
                request.getSession(false);

        if (session == null) {
            return "login";
        }

        Integer userId =
                (Integer) session.getAttribute("userId");

        String role =
                (String) session.getAttribute("role");

        if (userId == null) {
            return "login";
        }

        if (!"EMPLOYER".equalsIgnoreCase(role)) {
            return "login";
        }

        // GET request
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            return SUCCESS;
        }

        if (job == null) {
            addActionError("Job details are required.");
            return ERROR;
        }

        job.setRecruiterId(userId);

        JobDAO dao = new JobDAO();

        boolean saved = dao.saveJob(job);

        if (saved) {
            return SUCCESS;
        }

        addActionError("Unable to post job.");

        return ERROR;
    }


    public Job getJob() {
        return job;
    }


    public void setJob(Job job) {
        this.job = job;
    }
}