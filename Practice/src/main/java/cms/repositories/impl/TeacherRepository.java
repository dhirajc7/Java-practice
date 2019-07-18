package cms.repositories.impl;

import cms.models.Teacher;
import cms.repositories.CrudRepository;
import cms.util.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements CrudRepository<Teacher> {
    public List<Teacher> findAll() {
        Connection connection = DbConnector.getConnection();
        List<Teacher> teacherList = new ArrayList<>();

        String sql = "SELECT * from teachers";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("lastName");
                String subject = resultSet.getString("subject");
                Teacher tea = new Teacher(id, firstName, lastName, subject);
                teacherList.add(tea);
            }
            return teacherList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Teacher findById(int id) {
        Connection connection=DbConnector.getConnection();
        String sql="SELECT * from teachers where id=?";

        try{
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet=ps.executeQuery();

            while(resultSet.next()){
                int teacherId=resultSet.getInt("id");
                String firstName=resultSet.getString("firstName");
                String lastName=resultSet.getString("lastName");
                String subject=resultSet.getString("subject");

                Teacher teacher=new Teacher(teacherId,firstName,lastName,subject);
                return teacher;

            }
        }catch(Exception e){}
        return null;
    }

    @Override
    public boolean updateInfo(Teacher teacher) {

        String sql="Update teachers set FirstName=?,LastName=?,Subject=? where id=?";
        try{
            Connection connection=DbConnector.getConnection();

            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,teacher.getFirstName());
            ps.setString(2,teacher.getLastName());
            ps.setString(3,teacher.getSubject());
            ps.setInt(4,teacher.getId());
            int rs=ps.executeUpdate();

            return rs>0?true:false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Teacher teacher) {
        String sql="Insert into teachers (id, FirstName,LastName,Subject) values (?,?,?,?)";
        try{
            Connection connection=DbConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,teacher.getId());
            preparedStatement.setString(2,teacher.getFirstName());
            preparedStatement.setString(3,teacher.getLastName());
            preparedStatement.setString(4,teacher.getSubject());
             int resultSet=preparedStatement.executeUpdate();


             return resultSet>0?true:false;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {

        String sql="Delete from teachers where id=?";
        try{
            Connection connection=DbConnector.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            int resultSet=ps.executeUpdate();
            return resultSet>0?true:false;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }




}