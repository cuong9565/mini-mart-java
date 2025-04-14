package DTO;

import java.sql.ResultSet;

public class CustomerDTO{
    private int id = 0;
    private String phone = "", lastName = "", firstName = "", address = "", gender = "", state = "";

    public CustomerDTO() {}
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
            id = rs.getInt("customer.id");
            phone = rs.getString("customer.phone");
            lastName = rs.getString("customer.lastName");
            firstName = rs.getString("customer.firstName");
            address = rs.getString("customer.address");
            state = rs.getString("customer.state");
            gender = rs.getString("customer.gender");
        }
        catch(Exception e){
            System.out.println("Lỗi constructor ResultSet của CustomerDTO: " + e.getMessage());
        }
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

    public Object[] getObjects() {return new Object[]{id, phone, lastName, firstName, address, gender, state};}
}
