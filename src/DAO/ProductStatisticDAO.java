package DAO;

import DTO.ProductStatisticDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductStatisticDAO {
    private static ProductStatisticDAO instance = null;
    private ProductStatisticDAO() {}

    public static ProductStatisticDAO getInstance() {
        if (instance == null) instance = new ProductStatisticDAO();
        return instance;
    }

    public List<ProductStatisticDTO>loadExport(){
        List<ProductStatisticDTO> list = new ArrayList<>();
        String sql =
                "select *\n" +
                "from product\n" +
                "left join billinfo on product.id = billinfo.idProduct\n" +
                "left join bill on billinfo.idBill = bill.id\n" +
                "order by product.id;";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new ProductStatisticDTO(rs));
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public List<ProductStatisticDTO>loadImport(){
        List<ProductStatisticDTO> list = new ArrayList<>();
//        String sql =
//                "select * " +
//                "from product " +
//                "left join importorderdetail on product.id = importorderdetail.idProduct " +
//                "left join importorder on importorderdetail.idBill = bill.id " +
//                "order by product.id;";
//        Connection con = DataProvider.getInstance().getConnection();
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) list.add(new ProductStatisticDTO(rs));
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        DataProvider.getInstance().CloseConnection(con);
        return list;
    }
}
