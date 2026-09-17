package com.jobportal.dao;

import java.sql.Connection;
import com.jobportal.model.MyApplication;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.jobportal.model.Applicant;
import com.jobportal.model.JobApplication;
import com.jobportal.util.DBConnection;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAO {

	public boolean hasApplied(int jobId, int seekerId) {

		String sql = """
				SELECT application_id
				FROM job_applications
				WHERE job_id = ?
				AND seeker_id = ?
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);
			ps.setInt(2, seekerId);

			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean applyForJob(int jobId, int seekerId) {

		String sql = """
				INSERT INTO job_applications
				(
				    job_id,
				    seeker_id,
				    application_status
				)
				VALUES (?, ?, 'APPLIED')
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);
			ps.setInt(2, seekerId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public JobApplication getApplication(int jobId, int seekerId) {

		String sql = """
				SELECT
				    application_id,
				    job_id,
				    seeker_id,
				    application_status,
				    applied_at
				FROM job_applications
				WHERE job_id = ?
				AND seeker_id = ?
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);
			ps.setInt(2, seekerId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				JobApplication application = new JobApplication();

				application.setApplicationId(rs.getInt("application_id"));

				application.setJobId(rs.getInt("job_id"));

				application.setSeekerId(rs.getInt("seeker_id"));

				application.setApplicationStatus(rs.getString("application_status"));

				application.setAppliedAt(rs.getTimestamp("applied_at"));

				return application;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public List<Integer> getAppliedJobIds(int seekerId) {

		List<Integer> appliedJobIds = new ArrayList<>();

		String sql = """
				SELECT job_id
				FROM job_applications
				WHERE seeker_id = ?
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, seekerId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				appliedJobIds.add(rs.getInt("job_id"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return appliedJobIds;
	}

	public List<MyApplication> getApplicationsBySeekerId(int seekerId) {

		List<MyApplication> applications = new ArrayList<>();

		String sql = """
				SELECT
				    ja.application_id,
				    ja.job_id,
				    j.job_title,
				    j.company_name,
				    j.location,
				    j.job_type,
				    ja.application_status,
				    ja.applied_at
				FROM job_applications ja
				INNER JOIN jobs j
				    ON ja.job_id = j.job_id
				WHERE ja.seeker_id = ?
				ORDER BY ja.applied_at DESC
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, seekerId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				MyApplication application = new MyApplication();

				application.setApplicationId(rs.getInt("application_id"));

				application.setJobId(rs.getInt("job_id"));

				application.setJobTitle(rs.getString("job_title"));

				application.setCompanyName(rs.getString("company_name"));

				application.setLocation(rs.getString("location"));

				application.setJobType(rs.getString("job_type"));

				application.setApplicationStatus(rs.getString("application_status"));

				application.setAppliedAt(rs.getTimestamp("applied_at"));

				applications.add(application);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return applications;
	}

	public List<Applicant> getApplicantsByJobId(int jobId) {

		List<Applicant> applicants = new ArrayList<>();

		String sql = """
				SELECT
				    ja.application_id,
				    ja.job_id,
				    ja.seeker_id,
				    u.full_name,
				    u.email,
				    j.job_title,
				    ja.application_status,
				    ja.applied_at
				FROM job_applications ja
				JOIN users u
				    ON ja.seeker_id = u.user_id
				JOIN jobs j
				    ON ja.job_id = j.job_id
				WHERE ja.job_id = ?
				ORDER BY ja.applied_at DESC
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Applicant applicant = new Applicant();

				applicant.setApplicationId(rs.getInt("application_id"));

				applicant.setJobId(rs.getInt("job_id"));

				applicant.setSeekerId(rs.getInt("seeker_id"));

				applicant.setSeekerName(rs.getString("full_name"));

				applicant.setSeekerEmail(rs.getString("email"));

				applicant.setJobTitle(rs.getString("job_title"));

				applicant.setApplicationStatus(rs.getString("application_status"));

				applicant.setAppliedAt(rs.getTimestamp("applied_at"));

				applicants.add(applicant);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return applicants;
	}

	public boolean updateApplicationStatus(int applicationId, int recruiterId, String status) {

		String sql = """
				UPDATE job_applications ja
				SET application_status = ?
				FROM jobs j
				WHERE ja.application_id = ?
				  AND ja.job_id = j.job_id
				  AND j.recruiter_id = ?
				""";

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