package DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connect_data {
    private Connection conn;
    private String url;
    private String username;
    private String password;

    public connect_data() {
        this.url = "jdbc:mysql://localhost:3306/mini_mart_java";
        this.username = "root";
        this.password = "";
        connect();
    }

    public connect_data(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        connect();
    }

    private void connect() {
        try {
            // Đăng ký driver JDBC (tùy thuộc vào phiên bản Java, bước này có thể không cần)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Phương thức thực thi câu truy vấn SELECT
    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Lỗi khi thực thi câu truy vấn: " + e.getMessage());
        }
        return rs;
    }

    // Phương thức thực thi câu truy vấn INSERT, UPDATE, DELETE với PreparedStatement
    public int executeUpdate(String query, Object... params) {
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            // Gán tham số cho câu truy vấn
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            rowsAffected = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thực thi câu truy vấn: " + e.getMessage());
        }
        return rowsAffected;
    }

    // Phương thức thực thi câu truy vấn SELECT với PreparedStatement
    public ResultSet executeQueryWithParams(String query, Object... params) {
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            // Gán tham số cho câu truy vấn
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thực thi câu truy vấn: " + e.getMessage());
        }
        return rs;
    }

    // Phương thức đóng kết nối
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Đã đóng kết nối cơ sở dữ liệu!");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
        }
    }

    // Getter và Setter (nếu cần)
    public Connection getConnection() {
        return conn;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}