package DAO;

import javax.swing.*;
import java.sql.*;

public class DataProvider {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mini_mart_java";
    private static String user = "root";
    private static String password = "";
    private static DataProvider instance;

    public DataProvider() {}

    public static DataProvider getInstance() {
        if (instance == null) instance = new DataProvider();
        return instance;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
        return con;
    }

    public void CloseConnection(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
