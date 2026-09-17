package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.model.Job;
import com.jobportal.util.DBConnection;

public class JobDAO {

	public boolean saveJob(Job job) {

		String sql = """
				INSERT INTO jobs
				(
				    recruiter_id,
				    job_title,
				    company_name,
				    job_description,
				    required_skills,
				    location,
				    job_type,
				    experience_required,
				    salary_min,
				    salary_max
				)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, job.getRecruiterId());
			ps.setString(2, job.getJobTitle());
			ps.setString(3, job.getCompanyName());
			ps.setString(4, job.getJobDescription());
			ps.setString(5, job.getRequiredSkills());
			ps.setString(6, job.getLocation());
			ps.setString(7, job.getJobType());
			ps.setString(8, job.getExperienceRequired());
			ps.setBigDecimal(9, job.getSalaryMin());
			ps.setBigDecimal(10, job.getSalaryMax());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Job> getJobsByRecruiterId(int recruiterId) {

		List<Job> jobs = new ArrayList<>();

		String sql = """
				SELECT
				    job_id,
				    recruiter_id,
				    job_title,
				    company_name,
				    job_description,
				    required_skills,
				    location,
				    job_type,
				    experience_required,
				    salary_min,
				    salary_max,
				    created_at
				FROM jobs
				WHERE recruiter_id = ?
				ORDER BY created_at DESC
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, recruiterId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Job job = new Job();

				job.setJobId(rs.getInt("job_id"));
				job.setRecruiterId(rs.getInt("recruiter_id"));
				job.setJobTitle(rs.getString("job_title"));
				job.setCompanyName(rs.getString("company_name"));
				job.setJobDescription(rs.getString("job_description"));
				job.setRequiredSkills(rs.getString("required_skills"));
				job.setLocation(rs.getString("location"));
				job.setJobType(rs.getString("job_type"));
				job.setExperienceRequired(rs.getString("experience_required"));
				job.setSalaryMin(rs.getBigDecimal("salary_min"));
				job.setSalaryMax(rs.getBigDecimal("salary_max"));
				job.setCreatedAt(rs.getTimestamp("created_at"));

				jobs.add(job);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jobs;
	}

	public boolean deleteJob(int jobId, int recruiterId) {

		String sql = """
				DELETE FROM jobs
				WHERE job_id = ?
				AND recruiter_id = ?
				""";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, jobId);
			ps.setInt(2, recruiterId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Job> getAllJobs() {

		List<Job> jobs = new ArrayList<>();

		String sql = """
				SELECT
				    job_id,
				    recruiter_id,
				    job_title,
				    company_name,
				    job_description,
				    required_skills,
				    location,
				    job_type,
				    experience_required,
				    salary_min,
				    salary_max,
				    created_at
				FROM jobs
				ORDER BY created_at DESC
				""";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				Job job = new Job();

				job.setJobId(rs.getInt("job_id"));
				job.setRecruiterId(rs.getInt("recruiter_id"));

				job.setJobTitle(rs.getString("job_title"));

				job.setCompanyName(rs.getString("company_name"));

				job.setJobDescription(rs.getString("job_description"));

				job.setRequiredSkills(rs.getString("required_skills"));

				job.setLocation(rs.getString("location"));

				job.setJobType(rs.getString("job_type"));

				job.setExperienceRequired(rs.getString("experience_required"));

				job.setSalaryMin(rs.getBigDecimal("salary_min"));

				job.setSalaryMax(rs.getBigDecimal("salary_max"));

				job.setCreatedAt(rs.getTimestamp("created_at"));

				jobs.add(job);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return jobs;
	}
}