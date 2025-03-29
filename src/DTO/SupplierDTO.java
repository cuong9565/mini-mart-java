package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDTO {
    private int id;
    private String name, phone, address, email;
    public SupplierDTO() {}
    public SupplierDTO(Object[] obj) {
        this.id = (int) obj[0];
        this.name = (String) obj[1];
        this.phone =  (String) obj[2];
        this.address = (String) obj[3];
        this.email = (String) obj[4];
    }
    public SupplierDTO(int id, String name, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    public SupplierDTO(ResultSet rs) {
        try {
            this.id = Integer.parseInt(rs.getString("id"));
            this.name = rs.getString("name");
            this.phone = rs.getString("phone");
            this.address = rs.getString("address");
            this.email = rs.getString("email");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // region GET SET
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
    // endregion
    public Object[] getObjects() {
        return new Object[]{id, name, phone, address, email};
    }
}
