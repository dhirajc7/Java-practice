package cms.repositories.impl;

import cms.models.Student;
import cms.models.Teacher;
import cms.repositories.CrudRepository;
import cms.util.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements CrudRepository<Student> {
    @Override
    public List<Student> findAll() {
        Connection connection = DbConnector.getConnection();
        List<Student> studentList = new ArrayList<>();

        String sql = "SELECT * from student";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("lastName");
                Double gpa = resultSet.getDouble("gpa");
                Student tea = new Student(id, firstName, lastName, gpa);
                studentList.add(tea);
            }
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student findById(int id) {
        Connection connection=DbConnector.getConnection();
        String sql="SELECT * from student where id=?";

        try{
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet=ps.executeQuery();

            while(resultSet.next()){
                int studentId=resultSet.getInt("id");
                String firstName=resultSet.getString("firstName");
                String lastName=resultSet.getString("lastName");
                Double gpa=resultSet.getDouble("GPA");

                Student student=new Student(studentId,firstName,lastName,gpa);
                return student;

            }
        }catch(Exception e){}
        return null;
    }

    @Override
    public boolean updateInfo(Student student) {

        String sql="Update student set FirstName=?,LastName=?,gpa=? where id=?";
        try{
            Connection connection=DbConnector.getConnection();

            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,student.getFirstName());
            ps.setString(2,student.getLastName());
            ps.setDouble(3,student.getGpa());
            ps.setInt(4,student.getId());
            int rs=ps.executeUpdate();

            return rs>0?true:false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String sql="Delete from student where id=?";
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

    @Override
    public boolean insert(Student student) {
        String sql="Insert into student (id, FirstName,LastName,gpa) values (?,?,?,?)";
        try{
            Connection connection=DbConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setString(2,student.getFirstName());
            preparedStatement.setString(3,student.getLastName());
            preparedStatement.setDouble(4,student.getGpa());
            int resultSet=preparedStatement.executeUpdate();


            return resultSet>0?true:false;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
