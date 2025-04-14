package DAO;

import DTO.BillInfoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillInfoDAO {
    private static BillInfoDAO instance = null;

    private BillInfoDAO() {}
    public static BillInfoDAO getInstance() {
        if (instance == null) instance = new BillInfoDAO();
        return instance;
    }

    public List<BillInfoDTO> getList(int idBill){
        List<BillInfoDTO> list = new ArrayList<>();
        String sql = "select * from billinfo where idBill = ?";
        Connection con = DataProvider.getInstance().getConnection();

        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idBill);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new BillInfoDTO(rs));
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public boolean insert(BillInfoDTO billInfo) {
        int res = 0;
        String sql = "insert into billinfo(idBill, idProduct, price, quantity, discount, total, unit, nameProduct) values(?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, billInfo.getIdBill());
            ps.setInt(2, billInfo.getIdProduct());
            ps.setDouble(3, billInfo.getPrice());
            ps.setInt(4, billInfo.getQuantity());
            ps.setDouble(5, billInfo.getDiscount());
            ps.setDouble(6, billInfo.getTotal());
            ps.setString(7, billInfo.getUnit());
            ps.setString(8, billInfo.getNameProduct());
            res = ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }

    public boolean update(BillInfoDTO billInfo) {
        int res = 0;
        String sql = "update billinfo set idBill = ?, idProduct = ?, price = ?, quantity = ?, discount = ?, total = ?, unit = ?, nameProduct = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, billInfo.getIdBill());
            ps.setInt(2, billInfo.getIdProduct());
            ps.setDouble(3, billInfo.getPrice());
            ps.setInt(4, billInfo.getQuantity());
            ps.setDouble(5, billInfo.getDiscount());
            ps.setDouble(6, billInfo.getTotal());
            ps.setString(7, billInfo.getUnit());
            ps.setString(8, billInfo.getNameProduct());
            ps.setInt(9, billInfo.getId());
            res = ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }

    public boolean delete(int idBill, int idProduct) {
        int res = 0;
        String sql = "delete from billinfo where idBill = ? and idProduct = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idBill);
            ps.setInt(2, idProduct);
            res = ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res>0;
    }

    public void deleteByIdBill(int idBill){
        String sql = "delete from billInfo where idBill = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idBill);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }
}
