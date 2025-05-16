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

    public void insert(ImportInfoDTO importInfo) {
        String sql = "insert into importorderdetail(idImportOrder, idProduct, price, quantity, unit, nameProduct) values(?, ?, ?, ?, ?, ?)";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, importInfo.getIdImport());
            ps.setInt(2, importInfo.getIdProduct());
            ps.setDouble(3, importInfo.getPrice());
            ps.setInt(4, importInfo.getQuantity());
            ps.setString(5, importInfo.getUnit());
            ps.setString(6, importInfo.getNameProduct());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public void update(ImportInfoDTO importInfo) {
        String sql = "update importorderdetail set idImportOrder = ?, idProduct = ?, price = ?, quantity = ?, unit = ?, nameProduct = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, importInfo.getIdImport());
            ps.setInt(2, importInfo.getIdProduct());
            ps.setDouble(3, importInfo.getPrice());
            ps.setInt(4, importInfo.getQuantity());
            ps.setString(5, importInfo.getUnit());
            ps.setString(6, importInfo.getNameProduct());
            ps.setInt(7, importInfo.getId());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
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

    public void delete(int idImport, int idProduct) {
        String sql = "delete from importorderdetail where idImportOrder = ? and idProduct = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idImport);
            ps.setInt(2, idProduct);
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

}
