package com.jobportal.action;

import com.jobportal.dao.CandidateProfileDAO;
import com.jobportal.model.CandidateProfile;
import com.opensymphony.xwork2.ActionSupport;

public class RecruiterCandidateProfileAction extends ActionSupport {

    private int userId;

    private CandidateProfile profile;


    @Override
    public String execute() {

        if (userId <= 0) {
            addActionError("Invalid candidate.");
            return ERROR;
        }

        CandidateProfileDAO dao =
                new CandidateProfileDAO();

        profile = dao.getProfileByUserId(userId);

        if (profile == null) {
            addActionError("Candidate profile not found.");
            return ERROR;
        }

        return SUCCESS;
    }


    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public CandidateProfile getProfile() {
        return profile;
    }


    public void setProfile(CandidateProfile profile) {
        this.profile = profile;
    }
}