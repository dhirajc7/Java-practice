package cms.repositories.impl;

import cms.models.Staff;
import cms.repositories.CrudRepository;
import cms.util.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffRepository implements CrudRepository<Staff> {
    @Override
    public List<Staff> findAll() {
        List<Staff> staffs=new ArrayList<>();
        String sql="Select * from staff";
        try{
            Connection connection= DbConnector.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet resultSet=ps.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String firstName=resultSet.getString("firstName");
                String lastName=resultSet.getString("lastName");
                String job_role=resultSet.getString("job_role");
                Staff staff=new Staff(id,firstName,lastName,job_role);
                staffs.add(staff);
            }
            return staffs;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Staff findById(int id) {

        String sql="select * from staff where id=?";

        try{
            Connection connection=DbConnector.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int staffId=rs.getInt("id");
                String fName=rs.getString("firstname");
                String lName=rs.getString("lastname");
                String job_role=rs.getString("job_role");

                return new Staff(staffId,fName,lName,job_role);
            }
        }catch(Exception e){

        }


        return null;
    }

    public boolean updateInfo(Staff staff) {
        try(Connection connection = DbConnector.getConnection()) {
            String sql = "update staff set firstName=?, lastName=?, job_role=? where id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, staff.getFirstname());
            ps.setString(2, staff.getLastname());
            ps.setString(3, staff.getJob_role());
            ps.setInt(4, staff.getId());

            int rs = ps.executeUpdate();

            return rs > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


    @Override
    public boolean deleteById(int id) {
        String sql="Delete from staff where id=?";
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
    public boolean insert(Staff staff) {
        String sql="Insert into staff (id, FirstName,LastName,Job_Role) values (?,?,?,?)";
        try{
            Connection connection=DbConnector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,staff.getId());
            preparedStatement.setString(2,staff.getFirstname());
            preparedStatement.setString(3,staff.getLastname());
            preparedStatement.setString(4,staff.getJob_role());
            int resultSet=preparedStatement.executeUpdate();


            return resultSet>0?true:false;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
