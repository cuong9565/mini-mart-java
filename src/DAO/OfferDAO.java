package DAO;

import DTO.Discount_DTO;
import DTO.OfferDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferDAO {
    private static OfferDAO instance;

    private OfferDAO() {}
    public static OfferDAO getInstance() {
        if (instance == null) instance = new OfferDAO();
        return instance;
    }

    public List<OfferDTO> getList() {
        List<OfferDTO> list = new ArrayList<>();
        String sql = "select * from offer";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new OfferDTO(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
