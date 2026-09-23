package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import com.jobportal.model.CandidateProfile;
import com.jobportal.util.DBConnection;

public class CandidateProfileDAO {

	public CandidateProfile getProfileByUserId(int userId) {

		String sql = "SELECT profile_id, user_id, phone, date_of_birth, gender, location, headline, summary, resume_path, profile_completion FROM candidate_profiles WHERE user_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				CandidateProfile profile = new CandidateProfile();

				profile.setProfileId(rs.getInt("profile_id"));

				profile.setUserId(rs.getInt("user_id"));

				profile.setPhone(rs.getString("phone"));

				profile.setDateOfBirth(rs.getString("date_of_birth"));

				profile.setGender(rs.getString("gender"));

				profile.setLocation(rs.getString("location"));

				profile.setHeadline(rs.getString("headline"));

				profile.setSummary(rs.getString("summary"));

				profile.setResumePath(rs.getString("resume_path"));

				profile.setProfileCompletion(rs.getInt("profile_completion"));

				return profile;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public boolean saveProfile(CandidateProfile profile) {

		String sql = "INSERT INTO candidate_profiles (user_id, phone, date_of_birth, gender, location, headline, summary, resume_path, profile_completion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT (user_id) DO UPDATE SET phone = EXCLUDED.phone, date_of_birth = EXCLUDED.date_of_birth, gender = EXCLUDED.gender, location = EXCLUDED.location, headline = EXCLUDED.headline, summary = EXCLUDED.summary, resume_path = EXCLUDED.resume_path, profile_completion = EXCLUDED.profile_completion";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, profile.getUserId());

			ps.setString(2, profile.getPhone());

			// Date of Birth
			if (profile.getDateOfBirth() != null && !profile.getDateOfBirth().isEmpty()) {

				ps.setDate(3, java.sql.Date.valueOf(profile.getDateOfBirth()));

			} else {

				ps.setNull(3, java.sql.Types.DATE);
			}

			ps.setString(4, profile.getGender());
			ps.setString(5, profile.getLocation());
			ps.setString(6, profile.getHeadline());
			ps.setString(7, profile.getSummary());
			ps.setString(8, profile.getResumePath());
			ps.setInt(9, profile.getProfileCompletion());

			int rowsAffected = ps.executeUpdate();

			return rowsAffected > 0;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
}