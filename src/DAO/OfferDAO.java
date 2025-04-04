package DAO;

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

    public boolean add(OfferDTO offer) {
        int res = 0;
        String sql = "insert into offer(id, startDate, endDate) values(?,?,?)";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, offer.getId());
            ps.setDate(2, offer.getDateStart());
            ps.setDate(3, offer.getDateEnd());
            res = ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }

    public boolean update(OfferDTO offer) {
        int res = 0;
        String sql = "update offer set startDate = ?, endDate = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setDate(1, offer.getDateStart());
            ps.setDate(2, offer.getDateEnd());
            ps.setInt(3, offer.getId());
            res = ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }

    public boolean delete(OfferDTO offer) {
        int res = 0;
        String sql = "delete from offer where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, offer.getId());
            res = ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return res>0;
    }
}
