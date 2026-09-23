package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jobportal.model.CompanyProfile;
import com.jobportal.util.DBConnection;

public class CompanyProfileDAO {

	public CompanyProfile getByRecruiterId(int recruiterId) {

		String sql = "SELECT company_id, recruiter_id, company_name, company_description, website, industry, company_size, location, created_at, updated_at FROM company_profiles WHERE recruiter_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, recruiterId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				CompanyProfile profile = new CompanyProfile();

				profile.setCompanyId(rs.getInt("company_id"));

				profile.setRecruiterId(rs.getInt("recruiter_id"));

				profile.setCompanyName(rs.getString("company_name"));

				profile.setCompanyDescription(rs.getString("company_description"));

				profile.setWebsite(rs.getString("website"));

				profile.setIndustry(rs.getString("industry"));

				profile.setCompanySize(rs.getString("company_size"));

				profile.setLocation(rs.getString("location"));

				profile.setCreatedAt(rs.getTimestamp("created_at"));

				profile.setUpdatedAt(rs.getTimestamp("updated_at"));

				return profile;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean saveProfile(CompanyProfile profile) {

		String sql = "INSERT INTO company_profiles (recruiter_id, company_name, company_description, website, industry, company_size, location) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, profile.getRecruiterId());

			ps.setString(2, profile.getCompanyName());

			ps.setString(3, profile.getCompanyDescription());

			ps.setString(4, profile.getWebsite());

			ps.setString(5, profile.getIndustry());

			ps.setString(6, profile.getCompanySize());

			ps.setString(7, profile.getLocation());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateProfile(CompanyProfile profile) {

		String sql = "UPDATE company_profiles SET company_name = ?, company_description = ?, website = ?, industry = ?, company_size = ?, location = ?, updated_at = CURRENT_TIMESTAMP WHERE recruiter_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, profile.getCompanyName());

			ps.setString(2, profile.getCompanyDescription());

			ps.setString(3, profile.getWebsite());

			ps.setString(4, profile.getIndustry());

			ps.setString(5, profile.getCompanySize());

			ps.setString(6, profile.getLocation());

			ps.setInt(7, profile.getRecruiterId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}