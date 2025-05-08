package DAO;

import Components.MyDate;
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
                "where importorder.id = ?";
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

    public ImportDTO getImportNotPaidByIdStaff(int idStaff){
        ImportDTO importDTO = new ImportDTO();
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select * " +
                "from importorder " +
                "left join staff on importorder.idStaff = staff.id " +
                "left join provider on importorder.idProvider = provider.id " +
                "where importorder.idStaff = ? and importorder.state = 'Chưa thanh toán'";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idStaff);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) importDTO = new ImportDTO(rs);
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

    public void addImportByIdStaff(int idStaff){
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "insert into importorder(idStaff, dateCreate) values(?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idStaff);
            ps.setDate(2, MyDate.getCurrentDate().getSqlDate());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public void updateIdSupplier(int idImport, int idSupplier){
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "update importorder set idProvider = ? where id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSupplier);
            ps.setInt(2, idImport);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
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

    public void Pay(int idImport, double price){
        String sql = "update importorder set total = ?, state = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setDouble(1, price);
            ps.setString(2, "Đã thanh toán");
            ps.setInt(3, idImport);
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
    }









































//            Tổng tiền phiếu nhập theo năm
    public double TongImportByYear(int year){
        double res = 0;
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select sum(total) as result " +
                "from importorder " +
                "where year(dateCreate) = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                res = rs.getDouble("result");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }


}
