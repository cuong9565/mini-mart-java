package DAO;

import DTO.DetailProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailProductDAO {
    private static DetailProductDAO instance = null;

    private DetailProductDAO() {}
    public static DetailProductDAO getInstance() {
        if (instance == null) instance = new DetailProductDAO();
        return instance;
    }

    public List<DetailProductDTO> getList(){
        List<DetailProductDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from productdetail";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new DetailProductDTO(rs));
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public void add(DetailProductDTO dp) {
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "insert into productdetail(detailInfo) values(?)";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, dp.getText());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public void update(DetailProductDTO dp) {
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "update productdetail set detailInfo = ? where id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, dp.getText());
            ps.setInt(2, dp.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public void delete(DetailProductDTO dp) {
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "delete from productdetail where id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, dp.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }
}
