package DAO;

import DTO.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static CustomerDAO instance = null;
    public CustomerDAO() {}
    public static CustomerDAO getInstance() {
        if(instance == null) instance = new CustomerDAO();
        return instance;
    }
    public List<CustomerDTO> getAllList(){
        List<CustomerDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from customer";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new CustomerDTO(rs));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public List<CustomerDTO> getSearch(String whr, String str){
        List<CustomerDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = String.format("select * from customer where %s like ?", whr);

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, str);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new CustomerDTO(rs));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
        return list;
    }
    public int add(CustomerDTO customer) {
        int res = 0;
        String sql = "insert into customer(phone, lastName, firstName, address, gender) values(?,?,?,?,?)";
        Connection con = DataProvider.getInstance().getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getPhone());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getFirstName());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getGender());
            res = ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
        return res;
    }
    public int update(CustomerDTO customer) {
        int res = 0;
        String sql = "update customer set phone = ?, lastName = ?, firstName = ?, address = ?, gender = ?, state = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getPhone());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getFirstName());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getGender());
            ps.setString(6, customer.getState());
            ps.setInt(7, customer.getId());
            res = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    public int delete(CustomerDTO customer) {
        int res = 0;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "delete from customer where id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            res = ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
        return res;
    }
}
