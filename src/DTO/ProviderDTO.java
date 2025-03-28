package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderDTO {
    private int id;
    private String name, phone, address, email;
    public ProviderDTO() {}
    public ProviderDTO(int id, String name, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    public ProviderDTO(ResultSet rs) {
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
}
