package com.jobportal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.JobApplicationDAO;
import com.jobportal.model.MyApplication;
import com.opensymphony.xwork2.ActionSupport;

public class MyApplicationsAction extends ActionSupport {

    private List<MyApplication> applications;


    @Override
    public String execute() {

        HttpServletRequest request =
                ServletActionContext.getRequest();

        HttpSession session =
                request.getSession(false);


        // Check login
        if (session == null) {
            return "login";
        }


        // Get logged-in user
        Integer seekerId =
                (Integer) session.getAttribute("userId");

        if (seekerId == null) {
            return "login";
        }


        // Only job seekers can view applications
        String role =
                (String) session.getAttribute("role");

        if (!"USER".equalsIgnoreCase(role)
                && !"SEEKER".equalsIgnoreCase(role)
                && !"JOB_SEEKER".equalsIgnoreCase(role)) {

            addActionError(
                "Only job seekers can view applications."
            );

            return ERROR;
        }


        // Get applications
        JobApplicationDAO dao =
                new JobApplicationDAO();

        applications =
                dao.getApplicationsBySeekerId(seekerId);


        return SUCCESS;
    }


    public List<MyApplication> getApplications() {
        return applications;
    }


    public void setApplications(
            List<MyApplication> applications) {

        this.applications = applications;
    }
}