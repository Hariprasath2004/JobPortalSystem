package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.model.MyApplication;
import com.jobportal.model.Applicant;
import com.jobportal.model.JobApplication;
import com.jobportal.util.DBConnection;

public class JobApplicationDAO {
	// GET CANDIDATE ID FROM USER ID
	private int getCandidateId(int userId) {

		String sql = "SELECT candidate_profile_id FROM candidate_profiles WHERE user_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("candidate_profile_id");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return 0;
	}
	// CHECK ALREADY APPLIED
	public boolean hasApplied(int jobId, int userId) {

		int candidateId = getCandidateId(userId);

		if (candidateId == 0) {
			return false;
		}

		String sql = "SELECT application_id FROM applications WHERE job_id = ? AND candidate_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);
			ps.setInt(2, candidateId);

			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
	// APPLY FOR JOB
	public boolean applyForJob(int jobId, int userId) {

		int candidateId = getCandidateId(userId);

		if (candidateId == 0) {

			System.out.println("Candidate profile not found");

			return false;
		}

		String sql = "INSERT INTO applications (job_id, candidate_id, status) VALUES (?, ?, 'APPLIED')";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);
			ps.setInt(2, candidateId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
	// GET APPLICATION
	public JobApplication getApplication(int jobId, int userId) {

		int candidateId = getCandidateId(userId);

		if (candidateId == 0) {
			return null;
		}

		String sql = "SELECT application_id, job_id, candidate_id, status, applied_at FROM applications WHERE job_id = ? AND candidate_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);
			ps.setInt(2, candidateId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				JobApplication application = new JobApplication();

				application.setApplicationId(rs.getInt("application_id"));
				application.setJobId(rs.getInt("job_id"));
				application.setSeekerId(rs.getInt("candidate_id"));
				application.setApplicationStatus(rs.getString("status"));
				application.setAppliedAt(rs.getTimestamp("applied_at"));

				return application;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}
	// GET APPLIED JOB IDS
	public List<Integer> getAppliedJobIds(int userId) {

		List<Integer> appliedJobIds = new ArrayList<>();

		int candidateId = getCandidateId(userId);

		if (candidateId == 0) {
			return appliedJobIds;
		}

		String sql = "SELECT job_id FROM applications WHERE candidate_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, candidateId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				appliedJobIds.add(rs.getInt("job_id"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return appliedJobIds;
	}

	// =====================================================
	// GET APPLICATIONS BY SEEKER
	// =====================================================

	public List<MyApplication> getApplicationsBySeekerId(int userId) {

		List<MyApplication> applications = new ArrayList<>();

		int candidateId = getCandidateId(userId);

		if (candidateId == 0) {
			return applications;
		}

		String sql = "SELECT a.application_id, a.job_id, j.job_title, j.company_name, j.location, j.job_type, a.status, a.applied_at FROM applications a INNER JOIN jobs j ON a.job_id = j.job_id WHERE a.candidate_id = ? ORDER BY a.applied_at DESC";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, candidateId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				MyApplication application = new MyApplication();

				application.setApplicationId(rs.getInt("application_id"));
				application.setJobId(rs.getInt("job_id"));
				application.setJobTitle(rs.getString("job_title"));
				application.setCompanyName(rs.getString("company_name"));
				application.setLocation(rs.getString("location"));
				application.setJobType(rs.getString("job_type"));
				application.setApplicationStatus(rs.getString("status"));
				application.setAppliedAt(rs.getTimestamp("applied_at"));

				applications.add(application);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return applications;
	}
	// GET APPLICANTS FOR RECRUITER
	public List<Applicant> getApplicantsByJobId(int jobId) {

		List<Applicant> applicants = new ArrayList<>();

		String sql = "SELECT a.application_id, a.job_id, a.candidate_id, u.full_name, u.email, j.job_title, a.status, a.applied_at FROM applications a INNER JOIN candidate_profiles cp ON a.candidate_id = cp.candidate_profile_id INNER JOIN users u ON cp.user_id = u.user_id INNER JOIN jobs j ON a.job_id = j.job_id WHERE a.job_id = ? ORDER BY a.applied_at DESC";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Applicant applicant = new Applicant();

				applicant.setApplicationId(rs.getInt("application_id"));
				applicant.setJobId(rs.getInt("job_id"));
				applicant.setSeekerId(rs.getInt("candidate_id"));
				applicant.setSeekerName(rs.getString("full_name"));
				applicant.setSeekerEmail(rs.getString("email"));
				applicant.setJobTitle(rs.getString("job_title"));
				applicant.setApplicationStatus(rs.getString("status"));
				applicant.setAppliedAt(rs.getTimestamp("applied_at"));

				applicants.add(applicant);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return applicants;
	}
// UPDATE APPLICATION STATUS
	public boolean updateApplicationStatus(int applicationId, int recruiterId, String status) {

		String sql = "UPDATE applications a SET status = ? FROM jobs j WHERE a.application_id = ? AND a.job_id = j.job_id AND j.recruiter_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, status);
			ps.setInt(2, applicationId);
			ps.setInt(3, recruiterId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
}