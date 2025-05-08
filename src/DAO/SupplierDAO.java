package DAO;

import DTO.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private static SupplierDAO instance = null;

    public SupplierDAO() {}
    public static SupplierDAO getInstance() {
        if (instance == null) instance = new SupplierDAO();
        return instance;
    }

    // List
    public List<SupplierDTO> load() {
        List<SupplierDTO>list = new ArrayList<>();
        String sql = "select * from provider";
        Connection con = DataProvider.getInstance().getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new SupplierDTO(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    // Item
    public SupplierDTO getSupplierById(int id) {
        SupplierDTO supplier = new SupplierDTO();
        String sql = "select * from provider where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) supplier = new SupplierDTO(rs);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return supplier;
    }

    // Check
    public boolean checkSamePhoneSupplier(String phone){
        boolean res = false;
        String sql = "select * from provider where phone = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) res = true;
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    // Add
    public void addSupplier(SupplierDTO supplier) throws Exception {
        String sql = "insert into Provider(name, phone, address, email) values(?, ?, ?, ?)";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getEmail());
            ps.executeUpdate();
            DataProvider.getInstance().CloseConnection(con);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Update
    public void editSupplier(SupplierDTO supplier) throws Exception {
        String sql = "update provider set name = ?, phone = ?, address = ?, email = ? where id = ?";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getEmail());
            ps.setInt(5, supplier.getId());
            ps.executeUpdate();
            DataProvider.getInstance().CloseConnection(con);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Delete
    public void deleteSupplier(SupplierDTO supplier) throws Exception {
        String sql = "delete from provider where id = ?";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, supplier.getId());
            ps.executeUpdate();
            DataProvider.getInstance().CloseConnection(con);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
