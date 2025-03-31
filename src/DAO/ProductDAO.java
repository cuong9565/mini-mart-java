package DAO;

import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static ProductDAO instance;
    private Connection conn;

    private ProductDAO() {
        try {
            String url = "jdbc:mysql://localhost:3306/mini_mart_java";
            String user = "root";
            String password = ""; // Update with your credentials
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database!", e);
        }
    }

    public static ProductDAO getInstance() {
        if (instance == null) {
            synchronized (ProductDAO.class) {
                if (instance == null) {
                    instance = new ProductDAO();
                }
            }
        }
        return instance;
    }

    // Get all products
    public List<ProductDTO> getAll() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ProductDTO(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve products!", e);
        }
        return list;
    }

    // Get product by ID
    public ProductDTO getById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductDTO(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve product by ID!", e);
        }
        return null;
    }

    // Insert a new product
    public boolean insert(ProductDTO productDTO) {
        String sql = "INSERT INTO Product (idProductType, idProductDetail, idOfferProduct, name, price, unit, quantity) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, productDTO.getIdProductType());
            stmt.setInt(2, productDTO.getIdProductDetail());
            stmt.setInt(3, productDTO.getIdOfferProduct() == -1 ? null : productDTO.getIdOfferProduct());
            stmt.setString(4, productDTO.getName());
            stmt.setDouble(5, productDTO.getPrice());
            stmt.setString(6, productDTO.getUnit());
            stmt.setInt(7, productDTO.getQuantity());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        productDTO.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed!", e);
        }
    }

    // Update product
    public boolean update(ProductDTO productDTO) {
        String sql = "UPDATE Product SET idProductType = ?, idProductDetail = ?, idOfferProduct = ?, " +
                "name = ?, price = ?, unit = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productDTO.getIdProductType());
            stmt.setInt(2, productDTO.getIdProductDetail());
            stmt.setInt(3, productDTO.getIdOfferProduct() == -1 ? null : productDTO.getIdOfferProduct());
            stmt.setString(4, productDTO.getName());
            stmt.setDouble(5, productDTO.getPrice());
            stmt.setString(6, productDTO.getUnit());
            stmt.setInt(7, productDTO.getQuantity());
            stmt.setInt(8, productDTO.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Update failed!", e);
        }
    }

    // Delete product
    public boolean delete(int id) {
        String sql = "DELETE FROM Product WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed!", e);
        }
    }

    // Close connection
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close connection!", e);
        }
    }
}