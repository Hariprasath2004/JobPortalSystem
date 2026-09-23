package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.model.Experience;
import com.jobportal.util.DBConnection;

public class ExperienceDAO {

	public boolean saveExperience(Experience experience) {

		String sql = "INSERT INTO experience (user_id, job_title, company_name, location, start_date, end_date, currently_working, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, experience.getUserId());
			ps.setString(2, experience.getJobTitle());
			ps.setString(3, experience.getCompanyName());
			ps.setString(4, experience.getLocation());

			if (experience.getStartDate() != null && !experience.getStartDate().isEmpty()) {

				ps.setDate(5, java.sql.Date.valueOf(experience.getStartDate()));

			} else {
				ps.setNull(5, java.sql.Types.DATE);
			}

			if (experience.getEndDate() != null && !experience.getEndDate().isEmpty()) {

				ps.setDate(6, java.sql.Date.valueOf(experience.getEndDate()));

			} else {
				ps.setNull(6, java.sql.Types.DATE);
			}

			ps.setBoolean(7, experience.isCurrentlyWorking());

			ps.setString(8, experience.getDescription());

			int rowsInserted = ps.executeUpdate();

			return rowsInserted > 0;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public List<Experience> getExperiencesByUserId(int userId) {

		List<Experience> experiences = new ArrayList<>();

		String sql = "SELECT experience_id, user_id, job_title, company_name, location, start_date, end_date, currently_working, description FROM experience WHERE user_id = ? ORDER BY start_date DESC";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Experience experience = new Experience();

				experience.setExperienceId(rs.getInt("experience_id"));

				experience.setUserId(rs.getInt("user_id"));

				experience.setJobTitle(rs.getString("job_title"));

				experience.setCompanyName(rs.getString("company_name"));

				experience.setLocation(rs.getString("location"));

				if (rs.getDate("start_date") != null) {
					experience.setStartDate(rs.getDate("start_date").toString());
				}

				if (rs.getDate("end_date") != null) {
					experience.setEndDate(rs.getDate("end_date").toString());
				}

				experience.setCurrentlyWorking(rs.getBoolean("currently_working"));

				experience.setDescription(rs.getString("description"));

				experiences.add(experience);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return experiences;
	}
}