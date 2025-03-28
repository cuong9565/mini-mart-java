package DAO;

import DTO.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProviderDAO {
    private static ProviderDAO instance;

    public ProviderDAO() {}
    public static ProviderDAO getInstance() {
        if (instance == null) instance = new ProviderDAO();
        return instance;
    }

    public List<ProviderDTO> getListProvider() {
        List<ProviderDTO>list = new ArrayList<ProviderDTO>();
        String sql = "select * from provider";
        Connection con = DataProvider.getInstance().getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new ProviderDTO(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
        return list;
    }
}
