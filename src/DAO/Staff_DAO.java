package DAO;

import DTO.Staff_DTO;
import DTO.connect_data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Staff_DAO {
    private connect_data db;

    public Staff_DAO() {
        db = new connect_data();
    }

    // Thêm nhân viên vào database
    public boolean addStaff(Staff_DTO staff) {
        String query = "INSERT INTO staff (phone, password, firstName, lastName, address, salary, type, status, gender) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = db.executeUpdate(query, staff.getPhone(),staff.getPassword() ,staff.getFirstName(),staff.getLastName(), staff.getAddress(),
                staff.getSalary(),  staff.getRole(),
            staff.getStatus(),staff.getGender());
        return rowsAffected > 0;
    }

    // Lấy tất cả nhân viên từ database
    public List<Staff_DTO> getAllStaff() {
        List<Staff_DTO> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff";
        ResultSet rs = db.executeQuery(query);
        try {
            while (rs.next()) {
                Staff_DTO staff = new Staff_DTO(
                        rs.getString("id"),
                        rs.getString ("phone"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("type"),
                        rs.getDouble("salary"),
                        rs.getString("status")

                );
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    // Cập nhật thông tin nhân viên
    public boolean updateStaff(Staff_DTO staff) {
        String query = "UPDATE staff SET phone = ?, password = ?, firstName = ?, lastName = ?, gender = ?, " +
                "address = ?, salary = ?, type = ?, status = ? WHERE id = ?";
        int rowsAffected = db.executeUpdate(query,
                staff.getPhone(),
                staff.getPassword(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getGender(),
                staff.getAddress(),
                staff.getSalary(),
                staff.getRole(),
                staff.getStatus(),
                staff.getId());
        return rowsAffected > 0;
    }

    // Khóa
    public boolean lockStaff(String id, String status) {
        String query = "UPDATE staff SET status = ? WHERE id = ?";
        int rowsAffected = db.executeUpdate(query, status, id); // thứ tự gán
        return rowsAffected > 0;
    }
}