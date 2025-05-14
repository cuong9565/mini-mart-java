package DAO;

import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static ProductDAO instance = null;

    public ProductDAO() {}
    public static ProductDAO getInstance(){
        if(instance == null) instance = new ProductDAO();
        return instance;
    }

    public List<ProductDTO> getbyidoffer(int idoffer) {
        List<ProductDTO> list = new ArrayList<>();
        Connection con =DataProvider.getInstance().getConnection();
        String sql = "select * " +
        "from product " +
                "left join producttype on product.idProductType = producttype.id " +
                "left join productdetail on product.idProductDetail = productdetail.id " +
                "left join offer on product.idOfferProduct = offer.id " +
                "where product.idOfferProduct=?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, idoffer);
            ResultSet rs = smt.executeQuery();
            while (rs.next()){
               list.add(new ProductDTO(rs));
            }
        }
        catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

        return list;
    }

    // Check same product
    public boolean isSameProduct(String name, String unit){
        boolean res;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from product where name=? and unit=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, unit);
            ResultSet rs = ps.executeQuery();
            res = rs.next();
            rs.close();
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return res;
    }

    // Item
    public ProductDTO getItemById(int id) {
        ProductDTO product = new ProductDTO();
        Connection con = DataProvider.getInstance().getConnection();
        String sql ="SELECT *  \n" +
                "FROM product \n" +
                "JOIN producttype ON product.idProductType = producttype.id \n" +
                "JOIN productdetail ON product.idProductDetail = productdetail.id \n" +
                "LEFT JOIN offer ON product.idOfferProduct = offer.id \n" +
                "WHERE product.id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) product = new ProductDTO(rs);
            rs.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return product;
    }

    // List
    public List<ProductDTO> load() {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql ="select * \n" +
                "                from product \n" +
                "                join producttype on product.idProductType = producttype.id \n" +
                "                join productdetail on product.idProductDetail = productdetail.id \n" +
                "                left join offer on product.idOfferProduct = offer.id \n" +
                "                order by product.id asc;";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new ProductDTO(rs));
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    // Insert
    public void add(int idProductType, int idProductDetail, int idOfferProduct, String name, double price, String unit, int quantity){
        String sql = "insert into product(idProductType, idProductDetail, idOfferProduct, name, price, unit, quantity) values(?,?,?,?,?,?,?)";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idProductType);
            ps.setInt(2, idProductDetail);
            if((idOfferProduct!=0)) ps.setInt(3, idOfferProduct);
            else ps.setNull(3, Types.INTEGER);
            ps.setString(4, name);
            ps.setDouble(5, price);
            ps.setString(6, unit);
            ps.setInt(7, quantity);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }
    // Update
    public void update(int id, int idProductType, int idOfferProduct, String name, double price, String unit, int quantity){
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "update product set idProductType = ?, idOfferProduct = ?, name = ?, price = ?, unit = ?, quantity = ? where id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idProductType);
            if(idOfferProduct!=0) ps.setInt(2, idOfferProduct);
            else ps.setNull(2, Types.INTEGER);
            ps.setString(3, name);
            ps.setDouble(4, price);
            ps.setString(5, unit);
            ps.setInt(6, quantity);
            ps.setInt(7, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public void updateQuantity(int id, int quantity){
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "update product set quantity = ? where id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public void delete(ProductDTO product) {
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "delete from product where id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, product.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public int getNumberProduct() {
        int res = 0;
        String sql = "SELECT count(*) as rs FROM product";
        Connection conn = DataProvider.getInstance().getConnection();
        try (PreparedStatement smt = conn.prepareStatement(sql)) {
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                res = rs.getInt("rs");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DataProvider.getInstance().CloseConnection(conn);
        }
        return res;
    }

    public void upoffer(int idproduct, int idoffer) {
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "update product set idOfferProduct= ? where id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idoffer);
            ps.setInt(2, idproduct);
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        DataProvider.getInstance().CloseConnection(con);
    }
}