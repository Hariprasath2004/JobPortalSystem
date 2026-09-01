package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jobportal.model.Education;
import com.jobportal.util.DBConnection;

public class EducationDAO {

    public boolean saveEducation(Education education) {

        String sql = """
                INSERT INTO education
                (user_id, degree, institution, specialization,
                 start_year, end_year, grade)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, education.getUserId());
            ps.setString(2, education.getDegree());
            ps.setString(3, education.getInstitution());
            ps.setString(4, education.getSpecialization());
            ps.setInt(5, education.getStartYear());
            ps.setInt(6, education.getEndYear());
            ps.setString(7, education.getGrade());

            int rowsInserted = ps.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}