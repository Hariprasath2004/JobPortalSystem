package com.jobportal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jobportal.dao.JobApplicationDAO;
import com.jobportal.model.Applicant;
import com.opensymphony.xwork2.ActionSupport;

public class ViewApplicantsAction extends ActionSupport {

    private int jobId;

    private List<Applicant> applicants;


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


        // Get logged-in recruiter
        Integer recruiterId =
                (Integer) session.getAttribute("userId");

        if (recruiterId == null) {
            return "login";
        }


        // Only recruiters can view applicants
        String role =
                (String) session.getAttribute("role");

        if (!"EMPLOYER".equalsIgnoreCase(role)
                && !"RECRUITER".equalsIgnoreCase(role)) {

            addActionError(
                "Only recruiters can view applicants."
            );

            return ERROR;
        }


        // Validate job ID
        if (jobId <= 0) {

            addActionError(
                "Invalid job selected."
            );

            return ERROR;
        }


        /*
         * Make sure this job belongs
         * to the logged-in recruiter.
         */
        com.jobportal.dao.JobDAO jobDAO =
                new com.jobportal.dao.JobDAO();

        List<com.jobportal.model.Job> recruiterJobs =
                jobDAO.getJobsByRecruiterId(recruiterId);


        boolean ownsJob = false;

        for (com.jobportal.model.Job job : recruiterJobs) {

            if (job.getJobId() == jobId) {

                ownsJob = true;
                break;
            }
        }


        if (!ownsJob) {

            addActionError(
                "You are not authorized to view applicants for this job."
            );

            return ERROR;
        }


        // Get applicants
        JobApplicationDAO dao =
                new JobApplicationDAO();

        applicants =
                dao.getApplicantsByJobId(jobId);


        return SUCCESS;
    }


    public int getJobId() {
        return jobId;
    }


    public void setJobId(int jobId) {
        this.jobId = jobId;
    }


    public List<Applicant> getApplicants() {
        return applicants;
    }


    public void setApplicants(
            List<Applicant> applicants) {

        this.applicants = applicants;
    }
}