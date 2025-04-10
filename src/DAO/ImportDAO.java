package DAO;

import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportDAO {
    private static ImportDAO instance = null;

    public ImportDAO() {}
    public static ImportDAO getInstance() {
        if (instance == null) instance = new ImportDAO();
        return instance;
    }


    public List<ImportDTO> getListImport() {
        List<ImportDTO> list = new ArrayList<ImportDTO>();
        String sql = "select * from ImportOrder";
        Connection con = DataProvider.getInstance().getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new ImportDTO(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public List<ImportDTO> getListImportBy(String whr, String str) {
        List<ImportDTO> list = new ArrayList<>();
        String sql = String.format("select * form importorder where %s like ?", whr);
        Connection con = DataProvider.getInstance().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + str + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new ImportDTO(rs));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public boolean addImport(ImportDTO imp) throws Exception {
        String sql = "INSERT INTO ImportOrder(idStaff, idProvider, dateCreate, total) VALUES(?, ?, ?, ?)";
        Connection con = DataProvider.getInstance().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, imp.getIdStaff());
            ps.setInt(2, imp.getIdSupplier());
            ps.setTimestamp(3, imp.getDate());
            ps.setDouble(4, imp.getTotal());
            int res = ps.executeUpdate();
            if (res > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    imp.setIdImport(rs.getInt(1)); // Lấy ID tự động sinh
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataProvider.getInstance().CloseConnection(con);
        }
    }

    public int addImports(List<ImportDTO> listImport) {
        int res = 0, pos = 1;
        String sql = "insert into ImportOrder(id, idStaff, idProvider, dateCreate, total) values(?, ?, ?, ?, ?)";
        for(int i=1; i<listImport.size(); i++) sql += ",(?,?,?,?,?)";
        Connection con = DataProvider.getInstance().getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            for(ImportDTO imp: listImport) {
                ps.setInt(pos, imp.getIdImport());
                ps.setInt(pos, imp.getIdStaff());
                ps.setInt(pos, imp.getIdSupplier());
                ps.setTimestamp(pos, imp.getDate());
                ps.setDouble(pos, imp.getTotal());
            }
            res = ps.executeUpdate();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    public boolean editImport(ImportDTO imp) throws Exception {
        int res = 0;
        String sql = "update ImportOrder set idStaff = ?, idProvider = ?, dateCreate = ?, total = ? where id = ?";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, imp.getIdImport());
            ps.setInt(2, imp.getIdStaff());
            ps.setInt(3, imp.getIdSupplier());
            ps.setTimestamp(4, imp.getDate());
            ps.setDouble(5, imp.getTotal());
            res = ps.executeUpdate();
            DataProvider.getInstance().CloseConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (res > 0)
            return true;
        else {
            throw new Exception("Không thể thay đổi thông tin!");
        }
    }



    public boolean deleteImport(ImportDTO imp) throws Exception {
        int res = 0;
        String sql = "delete from ImportOrder where id = ?";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,imp.getIdImport());
            res = ps.executeUpdate();
            DataProvider.getInstance().CloseConnection(con);
        } catch (Exception e) {
            throw new Exception("Loi SQl: " + e.getMessage());
        }
        if(res>0) return true;
        else throw new Exception("Không thể xóa");
    }


}




