package com.jobportal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.JobApplicationDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ApplyJobAction extends ActionSupport {

    private int jobId;


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


        // Get logged-in seeker
        Integer seekerId =
                (Integer) session.getAttribute("userId");

        if (seekerId == null) {
            return "login";
        }


        // Check role
        String role =
                (String) session.getAttribute("role");

        if (!"JOB_SEEKER".equalsIgnoreCase(role)
                && !"USER".equalsIgnoreCase(role)
                && !"SEEKER".equalsIgnoreCase(role)) {

            session.setAttribute(
                "applicationError",
                "Only job seekers can apply for jobs."
            );

            return "search";
        }


        // Validate job ID
        if (jobId <= 0) {

            session.setAttribute(
                "applicationError",
                "Invalid job selected."
            );

            return "search";
        }


        JobApplicationDAO dao =
                new JobApplicationDAO();


        // Check already applied
        if (dao.hasApplied(jobId, seekerId)) {

            session.setAttribute(
                "applicationError",
                "You have already applied for this job."
            );

            return "search";
        }


        // Apply
        boolean applied =
                dao.applyForJob(jobId, seekerId);


        if (applied) {

            session.setAttribute(
                "applicationSuccess",
                "Application submitted successfully."
            );

        } else {

            session.setAttribute(
                "applicationError",
                "Failed to apply for the job."
            );
        }


        return "search";
    }


    public int getJobId() {
        return jobId;
    }


    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}