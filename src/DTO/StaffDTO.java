package DTO;

import java.sql.ResultSet;

public class StaffDTO {
    private int id = 0;
    private String password = "";
    private String lastName = "";
    private String firstName = "";
    private String gender = "";
    private String address = "";
    private String role = "";
    private double salary = 0;
    private String phone = "";
    private String state = "";

    public StaffDTO() {}
    public StaffDTO(int id, String phone ,String password , String firstName,String lastName,String gender, String address, String role, double salary, String state) {
        this.id = id;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.address = address;
        this.role = role;
        this.salary = salary;
        this.phone = phone;
        this.state = state;
    }
    public StaffDTO(ResultSet rs){
        try {
            id = rs.getInt("staff.id");
            phone = rs.getString("staff.phone");
            password = rs.getString("staff.password");
            firstName = rs.getString("staff.firstName");
            lastName = rs.getString("staff.lastName");
            address = rs.getString("staff.address");
            salary = rs.getDouble("staff.salary");
            role = rs.getString("staff.role");
            state = rs.getString("staff.state");
            gender = rs.getString("staff.gender");
        }
        catch (Exception e) {
            System.out.println("Lỗi constructor ResultSet của StaffDTO: " + e.getMessage());
        }
    }

    public int getId() {return id;}
    public String getPassword() {return password;}
    public String getLastName() {return lastName;}
    public String getFirstName() {return firstName;}
    public String getGender() {return gender;}
    public String getAddress() {return address;}
    public String getRole() {return role;}
    public double getSalary() {return salary;}
    public String  getFormatSalary() {return  String.format("%,.0fđ", salary);}
    public String getPhone() {return phone;}
    public String getState() {return state;}

    public void setId(int id) {this.id = id;}
    public void setPassword(String password) {this.password = password;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setGender(String gender) {this.gender = gender;}
    public void setAddress(String address) {this.address = address;}
    public void setRole(String role) {this.role = role;}
    public void setSalary(double salary) {this.salary = salary;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setState(String state) {this.state = state;}

    public Object[] getObjects() {
        return new Object[]{id, lastName, firstName, gender, phone, address, role, String.format("%,.0fđ", salary), state, password};
    }
}