package DAO;

import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportDtlDAO {
    private static ImportDtlDAO instance;
    private Connection conn;

    // Singleton constructor
    private ImportDtlDAO() {
        try {
            String url = "jdbc:mysql://localhost:3306/mini_mart_java";
            String user = "root";
            String password = "chibaolun";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database!", e);
        }
    }

    public static ImportDtlDAO getInstance() {
        if (instance == null) {
            synchronized (ImportDtlDAO.class) {
                if (instance == null) {
                    instance = new ImportDtlDAO();
                }
            }
        }
        return instance;
    }

    // Insert a new ImportOrderDetail record
    public boolean insert(ImportDtlDTO detailDTO) {
        String sql = "INSERT INTO ImportOrderDetail (idProduct, quantity, price, unit) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, detailDTO.getIdProduct());
            stmt.setInt(2, detailDTO.getQuantity());
            stmt.setDouble(3, detailDTO.getPrice());
            stmt.setString(4, detailDTO.getUnit());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        detailDTO.setIdProduct(generatedKeys.getInt(1)); ////
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed: " + e.getMessage(), e);
        }
    }

    // Get all ImportOrderDetail records
    public List<ImportDtlDTO> getAll() {
        List<ImportDtlDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ImportOrderDetail";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ImportDtlDTO(
                        rs.getInt("id"),
                        rs.getInt("idProduct"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("unit")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve details: " + e.getMessage(), e);
        }
        return list;
    }

    // Get ImportOrderDetail by ID
    public ImportDtlDTO getById(int id) {
        String sql = "SELECT * FROM ImportOrderDetail WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ImportDtlDTO(
                            rs.getInt("id"),
                            rs.getInt("idProduct"),
                            rs.getInt("quantity"),
                            rs.getDouble("price"),
                            rs.getString("unit")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve detail by ID: " + e.getMessage(), e);
        }
        return null;
    }

    // Update an ImportOrderDetail record
    public boolean update(ImportDtlDTO detailDTO) {
        String sql = "UPDATE ImportOrderDetail SET idProduct = ?, quantity = ?, price = ?, unit = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detailDTO.getIdProduct());
            stmt.setInt(2, detailDTO.getQuantity());
            stmt.setDouble(3, detailDTO.getPrice());
            stmt.setString(4, detailDTO.getUnit());
            stmt.setInt(5, detailDTO.getIdProduct());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Update failed: " + e.getMessage(), e);
        }
    }
}