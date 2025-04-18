package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDTO {
    private int id = 0;
    private String name = "", phone = "", address = "", email = "";

    public SupplierDTO() {}
    public SupplierDTO(int id, String name, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    public SupplierDTO(ResultSet rs) {
        try {
            id = rs.getInt("provider.id");
            name = rs.getString("provider.name");
            phone = rs.getString("provider.phone");
            address = rs.getString("provider.address");
            email = rs.getString("provider.email");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getPhone() {return phone;}
    public String getAddress() {return address;}
    public String getEmail() {return email;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setAddress(String address) {this.address = address;}
    public void setEmail(String email) {this.email = email;}

    public Object[] getObjects() {
        return new Object[]{id, name, phone, address, email};
    }

    @Override
    public String toString() {
        return name;
    }
}
