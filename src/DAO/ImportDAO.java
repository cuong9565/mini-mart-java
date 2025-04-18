package DAO;

import DTO.ImportDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportDAO {
    private static ImportDAO instance = null;

    private ImportDAO() {}
    public static ImportDAO getInstance() {
        if (instance == null) instance = new ImportDAO();
        return instance;
    }

    public ImportDTO getImportById(int id){
        ImportDTO importDTO = new ImportDTO();
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select * " +
                "from importorder " +
                "left join staff on importorder.idStaff = staff.id " +
                "left join provider on importorder.idProvider = provider.id " +
                "where importorder.id = ?";;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return new ImportDTO(rs);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return importDTO;
    }

    public List<ImportDTO>load(){
        List<ImportDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select * " +
                "from importorder " +
                "left join staff on importorder.idStaff = staff.id " +
                "left join provider on importorder.idProvider = provider.id " +
                "order by importorder.id";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new ImportDTO(rs));
        }
        catch (Exception e) {
            System.out.println("Lỗi hàm load ImportDAO " + e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public void delete(int id){
        String sql = "delete from importorder where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

//    public void addImport(int idStaff){
//        String sql = "insert into bill( idStaff, dateCreate ) values( ?, ? )";
//        Connection con = DataProvider.getInstance().getConnection();
//
//        try (PreparedStatement ps = con.prepareStatement(sql)){
//            ps.setInt(1, idStaff);
//            ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
//            ps.executeUpdate();
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        DataProvider.getInstance().CloseConnection(con);
//    }
//
//
//    public void updateIdCustomer(int idImport, int idCustomer){
//        String sql = "update bill set idCustomer = ? where id = ?";
//        Connection con = DataProvider.getInstance().getConnection();
//        try(PreparedStatement ps = con.prepareStatement(sql)){
//            if(idCustomer==0) ps.setNull(1, Types.INTEGER);
//            else ps.setInt(1, idCustomer);
//            ps.setInt(2, idImport);
//            ps.executeUpdate();
//        }
//        catch (Exception e){
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public void updateIdOfferImport(int idImport, int idOfferImport){
//        String sql = "update bill set idOfferImport = ? where id = ?";
//        Connection con = DataProvider.getInstance().getConnection();
//        try(PreparedStatement ps = con.prepareStatement(sql)){
//            if(idOfferImport==0) ps.setNull(1, Types.INTEGER);
//            else ps.setInt(1, idOfferImport);
//            ps.setInt(2, idImport);
//            ps.executeUpdate();
//        }
//        catch (Exception e){
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public void Pay(int idImport, double price){
//        String sql = "update bill set price = ?, state = ? where id = ?";
//        Connection con = DataProvider.getInstance().getConnection();
//        try (PreparedStatement ps = con.prepareStatement(sql)){
//            ps.setDouble(1, price);
//            ps.setString(2, "Đã thanh toán");
//            ps.setInt(3, idImport);
//            ps.executeUpdate();
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        DataProvider.getInstance().CloseConnection(con);
//    }
}
