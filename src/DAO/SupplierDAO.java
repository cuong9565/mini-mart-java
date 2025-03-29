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
}
