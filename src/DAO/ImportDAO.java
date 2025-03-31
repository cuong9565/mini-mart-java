package DAO;

import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportDAO {
    private static ImportDAO instance;
    private Connection conn;




    public static ImportDAO getInstance() {
        if (instance == null) {
            instance = new ImportDAO();
        }
        return instance;
    }

    // Thêm mới một bản ghi vào bảng Import
    public boolean insert(ImportDTO importDTO) {
        String sql = "INSERT INTO Import (idStaff, idSupplier, total, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, importDTO.getIdStaff());
            stmt.setInt(2, importDTO.getIdSupplier());
            stmt.setDouble(3, importDTO.getTotal());
            stmt.setString(4, importDTO.getDate());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        importDTO.setIdImport(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    // Lấy danh sách tất cả các bản ghi Import
    public List<ImportDTO> getAll() {
        List<ImportDTO> list = new ArrayList<ImportDTO>();
        String sql = "SELECT * FROM Import";
        conn = DataProvider.getInstance().getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(new ImportDTO(rs));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DataProvider.getInstance().CloseConnection(conn);
        return list;
    }

    // Tìm kiếm Import theo ID
    public ImportDTO getById(int id) {
        String sql = "SELECT * FROM Import WHERE id = ?";
         conn = DataProvider.getInstance().getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ImportDTO(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Cập nhật dữ liệu nhập hàng
    public boolean update(ImportDTO importDTO) {
        String sql = "UPDATE Import SET idStaff = ?, idSupplier = ?, total = ?, date = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, importDTO.getIdStaff());
            stmt.setInt(2, importDTO.getIdSupplier());
            stmt.setDouble(3, importDTO.getTotal());
            stmt.setString(4, importDTO.getDate());
            stmt.setInt(5, importDTO.getIdImport());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa bản ghi Import theo ID
    public boolean delete(int id) {
        String sql = "DELETE FROM Import WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
