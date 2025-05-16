package DAO;

import DTO.StaffDTO;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public boolean checkSamePhone(String phone){
        boolean check = false;
        String sql = "select * from staff where phone = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) check = true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return check;
    }

    public int getNumberStaff(){
        int res = 0;
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select count(*) as result " +
                "from staff";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()) res = rs.getInt("result");
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    public StaffDTO Login(String phone, String password) {
        StaffDTO staff;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from staff where phone = ? and password = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, phone);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) staff = new StaffDTO(rs);
            else throw new RuntimeException("Số điện thoại hoặc mật khẩu không hợp lệ!!!\nVui lòng nhập lại!!!");
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

    public void CheckPassword(int id, String currPassword) {
        boolean result;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from staff where id = ? and password = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.setString(2, currPassword);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);

        if(!result) throw new RuntimeException("Mật khẩu hiện tại không đúng!!!");
    }

    public void UpdateStaffPassword(int id, String password){
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "update staff set password = ? where id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, password);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public void UpdateAccount(StaffDTO staff) {
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
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public List<StaffDTO> load(){
        List<StaffDTO> list =new ArrayList<>();
        String sql = "select * from staff";
        Connection conn = DataProvider.getInstance().getConnection();
        try(PreparedStatement smt = conn.prepareStatement(sql);
            ResultSet rsl = smt.executeQuery()){
            while(rsl.next()){
                list.add(new StaffDTO(rsl));
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(conn);
        return list;
    }

    public void add(StaffDTO staff){
        Connection conn = DataProvider.getInstance().getConnection();
        String sql = "insert into staff (phone,password,firstname, lastname,address,salary,role,gender) value(?,?,?,?,?,?,?,?)";
        try(PreparedStatement stm = conn.prepareStatement(sql)){
            stm.setString(1,staff.getPhone());
            stm.setString(2,staff.getPassword());
           stm.setString(3,staff.getFirstName());
           stm.setString(4,staff.getLastName());
           stm.setString(5,staff.getAddress());
           stm.setDouble(6,staff.getSalary());
           stm.setString(7,staff.getRole());
           stm.setString(8,staff.getGender());
            stm.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            DataProvider.getInstance().CloseConnection(conn);
        }
    }

    public void delete(int id) {
        String sql = "delete from staff where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }
}