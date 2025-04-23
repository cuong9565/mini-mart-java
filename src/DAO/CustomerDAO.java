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

    // Check
    public boolean isSamePhone(String phone){
        boolean res;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from customer where phone = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            res = rs.next();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }
    public int getNumberCustomer(){
        int res = 0;
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select count(*) as result\n" +
                        "from Customer";
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()) res = rs.getInt("result");
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    // List
    public List<CustomerDTO> getAllList(){
        List<CustomerDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from customer";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new CustomerDTO(rs));
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    // Item
    public CustomerDTO getItemById(int id) {
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from customer where id = ?";
        CustomerDTO customer = new CustomerDTO();
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) customer = new CustomerDTO(rs);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return customer;
    }

    // Add
    public void add(CustomerDTO customer) {
        String sql = "insert into customer(phone, lastName, firstName, address, gender) values(?,?,?,?,?)";
        Connection con = DataProvider.getInstance().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getPhone());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getFirstName());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getGender());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    // Update
    public void update(CustomerDTO customer) {
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
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    // Delete
    public void delete(CustomerDTO customer) {
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "delete from customer where id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        DataProvider.getInstance().CloseConnection(con);
    }

}
