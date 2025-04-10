package DAO;

import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportDtlDAO {
    private static ImportDtlDAO instance = null;
    private Connection conn = DataProvider.getInstance().getConnection();



    public static ImportDtlDAO getInstance() {
        if (instance == null) {
            instance = new ImportDtlDAO();
        }
        return instance;
    }
    // Get all ImportOrderDetail records
    public List<ImportDtlDTO> getAll() {
        List<ImportDtlDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ImportOrderDetail";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ImportDtlDTO(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve details: " + e.getMessage(), e);
        }
        return list;
    }

    // Get ImportOrderDetail records by importId
    public List<ImportDtlDTO> getByImportId(int importId) {
        List<ImportDtlDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ImportOrderDetail WHERE idImport = ?";
        try (Connection con = DataProvider.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, importId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ImportDtlDTO(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve details by importId: " + e.getMessage(), e);
        }
        return list;
    }

    public boolean insert(ImportDtlDTO detailDTO) {
        String sql = "INSERT INTO ImportOrderDetail (id, idProduct, quantity, price, unit) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, detailDTO.getIdImport());
            stmt.setInt(2, detailDTO.getIdProduct());
            stmt.setInt(3, detailDTO.getQuantity());
            stmt.setDouble(4, detailDTO.getPrice());
            stmt.setString(5, detailDTO.getUnit());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        detailDTO.setIdImport(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed: " + e.getMessage(), e);
        }
    }



    public boolean update(ImportDtlDTO detailDTO) {
        String sql = "UPDATE ImportOrderDetail SET id = ?, idProduct = ?, quantity = ?, price = ?, unit = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detailDTO.getIdImport());
            stmt.setInt(2, detailDTO.getIdProduct());
            stmt.setInt(3, detailDTO.getQuantity());
            stmt.setDouble(4, detailDTO.getPrice());
            stmt.setString(5, detailDTO.getUnit());
            stmt.setInt(6, detailDTO.getIdImport()); // Assuming we need the primary key 'id'

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Update failed: " + e.getMessage(), e);
        }
    }

    // Add delete method
    public boolean delete(int id) {
        String sql = "DELETE FROM ImportOrderDetail WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed: " + e.getMessage(), e);
        }
    }
}