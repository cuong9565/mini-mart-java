package DAO;

import DTO.StaffDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffDAO {
    private static StaffDAO instance = null;
    private StaffDAO() {}
    public static StaffDAO getInstance() {
        if (instance == null) instance = new StaffDAO();
        return instance;
    }

    public StaffDTO Login(String phone, String password) {
        StaffDTO staff = new StaffDTO();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from staff where phone = ? and password = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, phone);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) staff = new StaffDTO(rs);
            else throw  new RuntimeException("Số điện thoại hoặc mật khẩu không hợp lệ!!!\nVui lòng nhập lại!!!");
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return staff;
    }

    public StaffDTO GetStaffById(int id) {
        StaffDTO staff = new StaffDTO();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from staff where id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) staff = new StaffDTO(rs);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return staff;
    }

    public boolean CheckPassword(int id, String currPassword) {
        boolean result;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from staff where id = ? and password = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.setString(2, currPassword);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) result = true;
            else {
                DataProvider.getInstance().CloseConnection(con);
                throw new RuntimeException("Mật khẩu hiện tại không đúng!!!");
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return result;
    }

    public boolean UpdateStaffPassword(int id, String password){
        int res = 0;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "update staff set password = ? where id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, password);
            ps.setInt(2, id);
            res = ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }

    public boolean UpdateAccount(StaffDTO staff) {
        int res = 0;
        String sql = "update staff set phone = ?, password = ?, firstName = ?, lastName = ?, address = ?, salary = ?, role = ?, gender = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, staff.getPhone());
            ps.setString(2, staff.getPassword());
            ps.setString(3, staff.getFirstName());
            ps.setString(4, staff.getLastName());
            ps.setString(5, staff.getAddress());
            ps.setDouble(6, staff.getSalary());
            ps.setString(7, staff.getRole());
            ps.setString(8, staff.getGender());
            ps.setInt(9, staff.getId());
            res = ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }

    public List<StaffDTO> getList(){
        List<StaffDTO> list = new ArrayList<>();
        String sql = "select * from staff";
        Connection con = DataProvider.getInstance().getConnection();
        try(
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()) list.add(new StaffDTO(rs));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public boolean add(StaffDTO staff) {
        int res = 0;
        String sql = "insert into staff(phone, password, firstName, lastName, address, salary, role, gender) values(?,?,?,?,?,?,?,?)";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, staff.getPhone());
            ps.setString(2, staff.getPassword());
            ps.setString(3, staff.getFirstName());
            ps.setString(4, staff.getLastName());
            ps.setString(5, staff.getAddress());
            ps.setDouble(6, staff.getSalary());
            ps.setString(7, staff.getRole());
            ps.setString(8, staff.getGender());
            res = ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }

    public boolean update(StaffDTO staff) {
        int res = 0;
        String sql = "update staff set phone = ?, password = ?, firstName = ?, lastName = ?, address = ?, salary = ?, state = ?, role = ?, gender = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, staff.getPhone());
            ps.setString(2, staff.getPassword());
            ps.setString(3, staff.getFirstName());
            ps.setString(4, staff.getLastName());
            ps.setString(5, staff.getAddress());
            ps.setDouble(6, staff.getSalary());
            ps.setString(7, staff.getState());
            ps.setString(8, staff.getRole());
            ps.setString(9, staff.getGender());
            ps.setInt(10, staff.getId());
            res = ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return res>0;
    }

    public boolean delete(int id) {
        int res = 0;
        String sql = "delete from staff where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            res = ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }
}