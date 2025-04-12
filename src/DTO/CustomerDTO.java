package DTO;

import java.sql.ResultSet;

public class CustomerDTO{
    private int id;
    private String phone, lastName, firstName, address, gender, state;
    public CustomerDTO() {}
    public CustomerDTO(int id){
        this.id = id;
    }
    public CustomerDTO(int id, String phone, String lastName, String firstName, String address, String gender, String state) {
        this.id = id;
        this.phone = phone;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.gender = gender;
        this.state = state;
    }
    public CustomerDTO(ResultSet rs){
        try {
            this.id = Integer.parseInt(rs.getString("id"));
            this.phone = rs.getString("phone");
            this.lastName = rs.getString("lastName");
            this.firstName = rs.getString("firstName");
            this.address = rs.getString("address");
            this.gender = rs.getString("gender");
            this.state = rs.getString("state");
        } catch (Exception e) {
           System.out.println("Lỗi SQL: " + e.getMessage());
        }
    }
    public CustomerDTO(Object[] ojs){
        id = Integer.parseInt(ojs[0].toString());
        phone = ojs[1].toString();
        lastName = ojs[2].toString();
        firstName = ojs[3].toString();
        address = ojs[4].toString();
        gender = ojs[5].toString();
        state = ojs[6].toString();
    }
    public int getId() {return id;}
    public String getPhone() {return phone;}
    public String getLastName() {return lastName;}
    public String getFirstName() {return firstName;}
    public String getAddress() {return address;}
    public String getGender() {return gender;}
    public String getState() {return state;}

    public void setId(int id) {this.id = id;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setAddress(String address) {this.address = address;}
    public void setGender(String gender) {this.gender = gender;}
    public void setState(String state) {this.state = state;}
    public Object[] getObjects() {
        return new Object[]{id, phone, lastName, firstName, address, gender, state};
    }
}
