package DAO;

import DTO.ImportInfoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImportInfoDAO {
    private static ImportInfoDAO instance = null;

    private ImportInfoDAO() {}
    public static ImportInfoDAO getInstance() {
        if (instance == null) instance = new ImportInfoDAO();
        return instance;
    }

    public List<ImportInfoDTO> loadByIdImport(int idImport){
        List<ImportInfoDTO> list = new ArrayList<>();
        String sql =
                "select * " +
                "from importorderdetail " +
                "where idImportOrder = ?";
        Connection con = DataProvider.getInstance().getConnection();

        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idImport);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new ImportInfoDTO(rs));
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public void deleteByIdImport(int idImport){
        String sql = "delete from importorderdetail where idImportOrder = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idImport);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }


//    public boolean insert(ImportInfoDTO billInfo) {
//        int res = 0;
//        String sql = "insert into billinfo(idImport, idProduct, price, quantity, discount, total, unit, nameProduct) values(?, ?, ?, ?, ?, ?, ?, ?)";
//        Connection con = DataProvider.getInstance().getConnection();
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, billInfo.getIdImport());
//            ps.setInt(2, billInfo.getIdProduct());
//            ps.setDouble(3, billInfo.getPrice());
//            ps.setInt(4, billInfo.getQuantity());
//            ps.setDouble(5, billInfo.getDiscount());
//            ps.setDouble(6, billInfo.getTotal());
//            ps.setString(7, billInfo.getUnit());
//            ps.setString(8, billInfo.getNameProduct());
//            res = ps.executeUpdate();
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        DataProvider.getInstance().CloseConnection(con);
//        return res>0;
//    }
//
//    public boolean update(ImportInfoDTO billInfo) {
//        int res = 0;
//        String sql = "update billinfo set idImport = ?, idProduct = ?, price = ?, quantity = ?, discount = ?, total = ?, unit = ?, nameProduct = ? where id = ?";
//        Connection con = DataProvider.getInstance().getConnection();
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, billInfo.getIdImport());
//            ps.setInt(2, billInfo.getIdProduct());
//            ps.setDouble(3, billInfo.getPrice());
//            ps.setInt(4, billInfo.getQuantity());
//            ps.setDouble(5, billInfo.getDiscount());
//            ps.setDouble(6, billInfo.getTotal());
//            ps.setString(7, billInfo.getUnit());
//            ps.setString(8, billInfo.getNameProduct());
//            ps.setInt(9, billInfo.getId());
//            res = ps.executeUpdate();
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        DataProvider.getInstance().CloseConnection(con);
//        return res>0;
//    }
//
//    public boolean delete(int idImport, int idProduct) {
//        int res = 0;
//        String sql = "delete from billinfo where idImport = ? and idProduct = ?";
//        Connection con = DataProvider.getInstance().getConnection();
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, idImport);
//            ps.setInt(2, idProduct);
//            res = ps.executeUpdate();
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        DataProvider.getInstance().CloseConnection(con);
//        return res>0;
//    }
//
}
