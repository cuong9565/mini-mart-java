package DAO;

import BUS.TypeProductBUS;
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
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
        return list;
    }
    public void add(TypeProductDTO product){
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "insert into producttype(name) values(?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public int adds(List<TypeProductDTO> list){
        int res = 0, pos = 1;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "insert into producttype(name) values(?)";
        for(int i=1; i<list.size(); i++)
            sql += ",(?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            for(TypeProductDTO product : list)
                ps.setString(pos++, product.getName());
            res = ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }
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
            throw new RuntimeException(e);
        }
    }

    public void delete(TypeProductDTO product){
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "delete from producttype where id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product.getId());
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
