package DAO;

import DTO.TypeProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeProductDAO {
    private static TypeProductDAO instance;
    public TypeProductDAO() {}
    public static TypeProductDAO getInstance() {
        if (instance == null) instance = new TypeProductDAO();
        return instance;
    }

    // Check Same Name
    public boolean isSameName(String name){
        boolean res;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from producttype where name = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            res = rs.next();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    // Item
    public TypeProductDTO getItemById(int id) {
        TypeProductDTO typeProduct = new TypeProductDTO();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from producttype where id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) typeProduct = new TypeProductDTO(rs);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return typeProduct;
    }
    public TypeProductDTO getItemByName(String name) {
        TypeProductDTO typeProduct = new TypeProductDTO();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from producttype where name = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) typeProduct = new TypeProductDTO(rs);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return typeProduct;
    }

    // List
    public List<TypeProductDTO>getList(){
        List<TypeProductDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from producttype";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new TypeProductDTO(rs));
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    // Insert
    public void add(TypeProductDTO product){
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "insert into producttype(name) values(?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    // Update
    public void edit(TypeProductDTO product){
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "update producttype set name=? where id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getId());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    // Delete
    public void delete(TypeProductDTO product){
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "delete from producttype where id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product.getId());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }
}
