package DAO;

import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDAO {
    private static ProductDAO instance = null;

    public ProductDAO() {}
    public static ProductDAO getInstance(){
        if(instance == null) instance = new ProductDAO();
        return instance;
    }

    public List<ProductDTO> getList() {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select pd.*, pdtype.name as type, pddetail.detailInfo as detailInfo, pdoffer.discount as discount, o.id as idOffer, o.startDate as startDate, o.endDate as endDate\n" +
                "from product pd\n" +
                "join producttype pdtype on pd.idProductType = pdtype.id\n" +
                "join productdetail pddetail on pd.idProductDetail = pddetail.id\n" +
                "left join offerproduct pdoffer on pd.idOfferProduct = pdoffer.id\n" +
                "left join offer o on pdoffer.idOffer = o.id\n" +
                "order by pd.id asc;";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) list.add(new ProductDTO(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi: " + e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public ProductDTO getItemById(int id) {
        ProductDTO res = null;
        String sql = "select * from product where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            res = new ProductDTO(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return res;
    }

    public int add(int idProductType, int idProductDetail, int idOfferProduct, String name, double price, String unit, int quantity){
        int res = 0;
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
            res = ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException();
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    public int update(int id, int idProductType, int idOfferProduct, String name, double price, String unit, int quantity){
        int res = 0;
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
            res = ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    public int delete(ProductDTO product) {
        int res = 0;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "delete from product where id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, product.getId());
            res = ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }



}