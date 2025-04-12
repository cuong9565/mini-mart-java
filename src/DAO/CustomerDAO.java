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

    public CustomerDTO getItemById(int id) {
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from customer where id = ?";
        CustomerDTO customer;
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) customer = new CustomerDTO(rs);
            else customer = new CustomerDTO(0);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return customer;
    }

    public int adds(List<CustomerDTO>list){
        int res = 0, pos = 1;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "insert into customer(phone, lastName, firstName, address, gender) values(?,?,?,?,?)";
        for(int i=1; i<list.size(); i++)
            sql += ",(?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            for(CustomerDTO customer : list){
                ps.setString(pos++, customer.getPhone());
                ps.setString(pos++, customer.getLastName());
                ps.setString(pos++, customer.getFirstName());
                ps.setString(pos++, customer.getAddress());
                ps.setString(pos++, customer.getGender());
            }
            res = ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
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
