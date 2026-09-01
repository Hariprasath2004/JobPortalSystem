package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.model.Skill;
import com.jobportal.util.DBConnection;

public class SkillDAO {

	public boolean saveSkill(Skill skill) {

	    String checkSql = """
	            SELECT skill_id
	            FROM skills
	            WHERE user_id = ?
	              AND LOWER(skill_name) = LOWER(?)
	            """;

	    String updateSql = """
	            UPDATE skills
	            SET skill_level = ?
	            WHERE skill_id = ?
	            """;

	    String insertSql = """
	            INSERT INTO skills
	            (user_id, skill_name, skill_level)
	            VALUES (?, ?, ?)
	            """;

	    try (Connection con = DBConnection.getConnection()) {

	        // Check whether this skill already exists for this user
	        try (PreparedStatement checkPs = con.prepareStatement(checkSql)) {

	            checkPs.setInt(1, skill.getUserId());
	            checkPs.setString(2, skill.getSkillName());

	            ResultSet rs = checkPs.executeQuery();

	            if (rs.next()) {

	                // Skill already exists -> update proficiency
	                int skillId = rs.getInt("skill_id");

	                try (PreparedStatement updatePs =
	                        con.prepareStatement(updateSql)) {

	                    updatePs.setString(1, skill.getSkillLevel());
	                    updatePs.setInt(2, skillId);

	                    return updatePs.executeUpdate() > 0;
	                }

	            } else {

	                // Skill does not exist -> insert new skill
	                try (PreparedStatement insertPs =
	                        con.prepareStatement(insertSql)) {

	                    insertPs.setInt(1, skill.getUserId());
	                    insertPs.setString(2, skill.getSkillName());
	                    insertPs.setString(3, skill.getSkillLevel());

	                    return insertPs.executeUpdate() > 0;
	                }
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

    public List<Skill> getSkillsByUserId(int userId) {

        List<Skill> skills = new ArrayList<>();

        String sql = """
                SELECT
                    skill_id,
                    user_id,
                    skill_name,
                    skill_level
                FROM skills
                WHERE user_id = ?
                ORDER BY skill_id
                """;

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Skill skill = new Skill();

                skill.setSkillId(rs.getInt("skill_id"));

                skill.setUserId(rs.getInt("user_id"));

                skill.setSkillName(
                    rs.getString("skill_name")
                );

                skill.setSkillLevel(
                    rs.getString("skill_level")
                );

                skills.add(skill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return skills;
    }
}