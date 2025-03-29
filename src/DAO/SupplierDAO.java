package DAO;

import DTO.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private static SupplierDAO instance;

    public SupplierDAO() {}
    public static SupplierDAO getInstance() {
        if (instance == null) instance = new SupplierDAO();
        return instance;
    }

    public List<SupplierDTO> getListSupplier() {
        List<SupplierDTO>list = new ArrayList<SupplierDTO>();
        String sql = "select * from provider";
        Connection con = DataProvider.getInstance().getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new SupplierDTO(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public boolean addSupplier(SupplierDTO supplier) throws Exception {
        int res = 0;
        String sql = "insert into Provider(name, phone, address, email) values(?, ?, ?, ?)";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getEmail());
            res = ps.executeUpdate();
            DataProvider.getInstance().CloseConnection(con);
        }
        catch (Exception e) {
            throw new Exception("Lỗi SQL: " + e.getMessage());
        }

        if(res>0) return true;
        else {
            throw new Exception("Không thể thêm nhà cung cấp!");
        }
    }

    public boolean editSupplier(SupplierDTO supplier) throws Exception {
        int res = 0;
        String sql = "update provider set name = ?, phone = ?, address = ?, email = ? where id = ?";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getEmail());
            ps.setInt(5, supplier.getId());
            res = ps.executeUpdate();
            DataProvider.getInstance().CloseConnection(con);
        }
        catch (Exception e) {
            throw new Exception("Lỗi SQL: " + e.getMessage());
        }

        if(res>0) return true;
        else {
            throw new Exception("Không thể thay đổi thông tin nhà cung cấp!");
        }
    }

    public boolean deleteSupplier(SupplierDTO supplier) throws Exception {
        int res = 0;
        String sql = "delete from provider where id = ?";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, supplier.getId());
            res = ps.executeUpdate();
            DataProvider.getInstance().CloseConnection(con);
        }
        catch (Exception e) {
            throw new Exception("Lỗi SQL: " + e.getMessage());
        }
        if(res>0) return true;
        else throw new Exception("Không thể xóa nhà cung cấp!");
    }
}
