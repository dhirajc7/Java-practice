package com.vastika.training.java.jdbc.cms;

import com.vastika.training.java.jdbc.cms.models.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements CRUDRepository<Teacher> {
    public List<Teacher> findAll(){
        Connection connection = DbConnector.getConnection();
        List<Teacher> teachers = new ArrayList<>();


        try {
            PreparedStatement ps = connection.prepareStatement("select * from teachers");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String subject = rs.getString("subject");

                Teacher teacher = new Teacher(id, firstName, lastName,subject);

                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;

    }

    public Teacher findById(int userId) {
        Connection connection = DbConnector.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from teachers where id = " + userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String subject = rs.getString("subject");

                return new Teacher(id, firstName, lastName, subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insertTeacher(Teacher teacher) {
        Connection connection = DbConnector.getConnection();

        try {

            String sql = "INSERT INTO teachers (id, FirstName, LastName, Subject) VALUES ("+teacher.getId()+", '"+teacher.getFirstName()+"',"+"'"+teacher.getLastName()+"',"+"'"+teacher.getSubject()+"')";

            PreparedStatement ps = connection.prepareStatement(sql);

            int rs = ps.executeUpdate();

            return rs > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public boolean update(Teacher teacher) {
        Connection connection = DbConnector.getConnection();

        try {
            String sql = "update teachers set firstName='" + teacher.getFirstName()
                    + "', lastName='" + teacher.getLastName() + "', subject='" + teacher.getSubject() + "' where id=" + teacher.getId();

            PreparedStatement ps = connection.prepareStatement(sql);

            int rs = ps.executeUpdate();

            return rs > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteById(int teacherId){


        try {
            String sql = "DELETE FROM teachers WHERE id = "+teacherId;
                Connection connection = DbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql); {


            // execute the delete statement
            ps.executeUpdate();
            return true;

        }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
    }

return false;
    }
}
