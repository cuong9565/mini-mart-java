package DAO;

import Components.MyDate;
import DTO.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private static BillDAO instance = null;

    private BillDAO() {}
    public static BillDAO getInstance() {
        if (instance == null) instance = new BillDAO();
        return instance;
    }

    public List<BillDTO>load(){
        List<BillDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select * " +
                "from bill " +
                "join staff on bill.idStaff = staff.id " +
                "left join offer on offer.id = bill.idOfferBill " +
                "left join customer on bill.idCustomer = customer.id";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new BillDTO(rs));
        }
        catch (Exception e) {
            System.out.println("Lỗi hàm load BillDAO " + e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;

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

    public void updateIdCustomer(int idBill, int idCustomer){
        String sql = "update bill set idCustomer = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            if(idCustomer==0) ps.setNull(1, Types.INTEGER);
            else ps.setInt(1, idCustomer);
            ps.setInt(2, idBill);
            ps.executeUpdate();
        }
        catch (Exception e) {
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

    public void Pay(int idBill, double price) {
        String sql = "update bill set price = ?, state = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, price);
            ps.setString(2, "Đã thanh toán");
            ps.setInt(3, idBill);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
    }






}
