package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.model.Applicant;
import com.jobportal.model.JobApplication;
import com.jobportal.model.MyApplication;
import com.jobportal.util.DBConnection;

public class JobApplicationDAO {

	public boolean hasApplied(int jobId, int userId) {

		String sql = "SELECT application_id " + "FROM job_applications " + "WHERE job_id = ? AND seeker_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);
			ps.setInt(2, userId);

			System.out.println("Connecting to PostgreSQL...");
			System.out.println("PostgreSQL connection successful.");

			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			System.out.println("ERROR WHILE CHECKING APPLICATION");
			e.printStackTrace();

			return false;
		}
	}

	// APPLY FOR JOB
	public boolean applyForJob(int jobId, int userId) {

		String sql = "INSERT INTO job_applications " + "(job_id, seeker_id, application_status) " + "VALUES (?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			System.out.println("Connecting to PostgreSQL...");
			System.out.println("PostgreSQL connection successful.");

			System.out.println("INSERT jobId=" + jobId + ", seekerId=" + userId + ", status=APPLIED");

			ps.setInt(1, jobId);
			ps.setInt(2, userId);

			ps.setString(3, "APPLIED");

			int rows = ps.executeUpdate();

			System.out.println("Rows inserted into job_applications : " + rows);

			return rows > 0;

		} catch (Exception e) {

			System.out.println("DATABASE ERROR WHILE APPLYING");

			e.printStackTrace();

			return false;
		}
	}

	// GET APPLICATION

	public JobApplication getApplication(int jobId, int userId) {

		String sql = "SELECT application_id, " + "job_id, " + "seeker_id, " + "application_status, " + "applied_at "
				+ "FROM job_applications " + "WHERE job_id = ? AND seeker_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);
			ps.setInt(2, userId);

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

			System.out.println("ERROR WHILE GETTING APPLICATION");

			e.printStackTrace();
		}

		return null;
	}


	public List<Integer> getAppliedJobIds(int userId) {

		List<Integer> appliedJobIds = new ArrayList<>();

		String sql = "SELECT job_id " + "FROM job_applications " + "WHERE seeker_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				appliedJobIds.add(rs.getInt("job_id"));
			}

		} catch (Exception e) {

			System.out.println("ERROR WHILE GETTING APPLIED JOB IDS");

			e.printStackTrace();
		}

		return appliedJobIds;
	}

	public List<MyApplication> getApplicationsBySeekerId(int userId) {

		List<MyApplication> applications = new ArrayList<>();

		String sql = "SELECT " + "a.application_id, " + "a.job_id, " + "j.job_title, " + "j.company_name, "
				+ "j.location, " + "j.job_type, " + "a.application_status, " + "a.applied_at "
				+ "FROM job_applications a " + "INNER JOIN jobs j " + "ON a.job_id = j.job_id "
				+ "WHERE a.seeker_id = ? " + "ORDER BY a.applied_at DESC";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

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

			System.out.println("ERROR WHILE GETTING SEEKER APPLICATIONS");

			e.printStackTrace();
		}

		return applications;
	}


	public List<Applicant> getApplicantsByJobId(int jobId) {

		List<Applicant> applicants = new ArrayList<>();

		String sql = "SELECT " + "a.application_id, " + "a.job_id, " + "a.seeker_id, " + "u.full_name, " + "u.email, "
				+ "j.job_title, " + "a.application_status, " + "a.applied_at " + "FROM job_applications a "
				+ "INNER JOIN users u " + "ON a.seeker_id = u.user_id " + "INNER JOIN jobs j "
				+ "ON a.job_id = j.job_id " + "WHERE a.job_id = ? " + "ORDER BY a.applied_at DESC";

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

			System.out.println("ERROR WHILE GETTING APPLICANTS");

			e.printStackTrace();
		}

		return applicants;
	}


	public boolean updateApplicationStatus(int applicationId, int recruiterId, String status) {

		String sql = "UPDATE job_applications a " + "SET application_status = ? " + "FROM jobs j "
				+ "WHERE a.application_id = ? " + "AND a.job_id = j.job_id " + "AND j.recruiter_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, status);
			ps.setInt(2, applicationId);
			ps.setInt(3, recruiterId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			System.out.println("ERROR WHILE UPDATING APPLICATION STATUS");

			e.printStackTrace();

			return false;
		}
	}
}