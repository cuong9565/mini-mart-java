package DAO;

import DTO.BillDTO;

import java.sql.*;

public class BillDAO {
    private static BillDAO instance = null;

    private BillDAO() {}
    public static BillDAO getInstance() {
        if (instance == null) instance = new BillDAO();
        return instance;
    }

    public BillDTO getBillNotPaid(int idStaff){
        BillDTO billDTO = new BillDTO(0);
        String sql = "select * from bill where idStaff = ? and state = 'Chưa thanh toán'";
        Connection con = DataProvider.getInstance().getConnection();

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idStaff);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) billDTO = new BillDTO(rs);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
        return billDTO;
    }

    public void updateIdCustomer(int idBill, int idCustomer){
        String sql = "update bill set idCustomer = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            if(idCustomer==0) ps.setNull(1, Types.INTEGER);
            else ps.setInt(1, idCustomer);
            ps.setInt(2, idBill);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateIdOfferBill(int idBill, int idOfferBill){
        String sql = "update bill set idOfferBill = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            if(idOfferBill==0) ps.setNull(1, Types.INTEGER);
            else ps.setInt(1, idOfferBill);
            ps.setInt(2, idBill);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(int id){
        String sql = "delete from bill where id = ?";
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

    public void addBill(int idStaff){
        String sql = "insert into bill( idStaff, dateCreate ) values( ?, ? )";
        Connection con = DataProvider.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idStaff);
            ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
    }

    public void Pay(int idBill, double price){
        String sql = "update bill set price = ?, state = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setDouble(1, price);
            ps.setString(2, "Đã thanh toán");
            ps.setInt(3, idBill);
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
    }
}
