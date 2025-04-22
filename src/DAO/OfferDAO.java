package DAO;

import Components.MyDate;
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

    // Item
    public OfferDTO getOfferById(int id) {
        OfferDTO offer = new OfferDTO();
        String sql = "select * from offer where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) offer = new OfferDTO(rs);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return offer;
    }

    // Check
    public boolean isSameDay(MyDate l, MyDate r) {
        boolean res;
        String sql = "select * from offer where startDate = ? and endDate = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, l.getSqlDate());
            ps.setDate(2, r.getSqlDate());
            ResultSet rs = ps.executeQuery();
            res = rs.next();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    public List<OfferDTO> getList() {
        List<OfferDTO> list = new ArrayList<>();
        String sql = "select * from offer";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new OfferDTO(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    public void add(OfferDTO offer) {
        String sql = "insert into offer(startDate, endDate) values (?, ?)";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, offer.getDateStart().getSqlDate());
            ps.setDate(2, offer.getDateEnd().getSqlDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public boolean update(OfferDTO offer) {
        int res = 0;
        String sql = "update offer set startDate = ?, endDate = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setDate(1, offer.getDateStart().getSqlDate());
            ps.setDate(2, offer.getDateEnd().getSqlDate());
            ps.setInt(3, offer.getId());
            res = ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }

    // Delete
    public void delete(int id) {
        String sql = "delete from offer where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }
}
