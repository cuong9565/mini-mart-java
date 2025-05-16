package DAO;

import Components.MyDate;
import DTO.ProductStatisticDTO;

import javax.management.RuntimeMBeanException;
import java.lang.management.MemoryType;
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
        String sql =
                "select * " +
                "from product " +
                "left join importorderdetail on product.id = importorderdetail.idProduct " +
                "left join importorder on importorderdetail.idImportOrder = importorder.id " +
                "order by product.id;";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new ProductStatisticDTO(rs,1));
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }
// hiển thị tổng doanh thu
    public double getProfit(){
        double importDTO, exportDTO, res;
        Connection con = DataProvider.getInstance().getConnection();
        String sqlCalImport =
                "select sum(importorder.total) as result " +
                "from importorder";
        String sqlCalExport =
                "select sum(bill.price) as result " +
                "from bill";
        try (PreparedStatement psImport = con.prepareStatement(sqlCalImport);
            PreparedStatement psExport = con.prepareStatement(sqlCalExport)) {
            ResultSet rsImport = psImport.executeQuery(); rsImport.next();
            ResultSet rsExport = psExport.executeQuery(); rsExport.next();
            importDTO = rsImport.getDouble("result");
            exportDTO = rsExport.getDouble("result");

            res = exportDTO - importDTO;
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    public double getObjectImportQ(int i , MyDate start ,MyDate end){
        double rs=0;
        Connection conn = DataProvider.getInstance().getConnection();
        String sql = "select sum(total) as result from importorder where (month(dateCreate) between ? and ? ) and (dateCreate between ? and ?)";
        try (PreparedStatement stm = conn.prepareStatement(sql)){
            stm.setInt(1,i*3-2);
            stm.setInt(2,i*3);
            stm.setDate(3,start.getSqlDate());
            stm.setDate(4,end.getSqlDate());
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                rs = resultSet.getDouble("result");
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            DataProvider.getInstance().CloseConnection(conn);
        }
        return rs;

    }

    public double getObjectExportQ(int i, MyDate l, MyDate r){
        double res = 0;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select sum(price) as result\n" +
                "from bill\n" +
                "where (month(dateCreate) between ? and ?) and (dateCreate between ? and ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, i*3-2);
            ps.setInt(2, i*3);
            ps.setDate(3, l.getSqlDate());
            ps.setDate(4, r.getSqlDate());
            ResultSet rs = ps.executeQuery(); rs.next();
            res = rs.getDouble("result");
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }
}