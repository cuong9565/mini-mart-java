package DAO;

import DTO.OfferBillDTO;
import DTO.OfferDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferBillDAO {
    private static OfferBillDAO instance;

    public OfferBillDAO() {}
    public static OfferBillDAO getInstance() {
        if (instance == null) instance = new OfferBillDAO();
        return instance;
    }

    public boolean addBill(OfferBillDTO of) {
        int res = 0;
        String sql = "INSERT INTO offerbill (idOffer, discount) VALUES (?, ?)";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, of.getOffer().getId());
            ps.setInt(2, of.getDiscount());
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return res > 0;

    }

    public List<OfferBillDTO> getList() {
        List<OfferBillDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "SELECT ob.*, o.startDate, o.endDate " +
                        "FROM offerbill ob, offer o " +
                        "WHERE ob.idOffer = o.id " +
                        "ORDER BY ob.discount";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OfferBillDTO(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi SQL: " + e.getMessage());
        }
        return list;
    }

    public boolean delete(OfferBillDTO o) throws Exception {
        int res = 0;
        String sql = "DELETE FROM offerbill WHERE id = ?";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, o.getId());
            res = ps.executeUpdate();
            DataProvider.getInstance().CloseConnection(con);
        } catch (Exception e) {
            throw new Exception("Lỗi SQL: " + e.getMessage());
        }
        if (res > 0) return true;
        else throw new Exception("Không thể xóa OfferBill!");
    }

    public boolean update(OfferBillDTO data) {
        int res = 0;
        String sql = "UPDATE offerbill SET idOffer = ?, discount = ? WHERE id = ?";
        Connection con = DataProvider.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, data.getOffer().getId());
            ps.setInt(2, data.getDiscount());
            ps.setInt(3, data.getId());
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật offerbill: " + e.getMessage());
        }

        DataProvider.getInstance().CloseConnection(con);
        return res > 0;
    }

}
