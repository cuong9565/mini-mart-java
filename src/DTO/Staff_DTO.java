package DTO;

public class Staff_DTO {
    private String id;
    private String password ;
    private String lastName;
    private String firstName;
    private String gender;
    private String address;
    private String role;
    private double salary;
    private String phone;
    private String status;

    public Staff_DTO(String id,String phone ,String password , String firstName,String lastName,String gender, String address,
                     String role, double salary, String status) {
        this.id = id;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.address = address;
        this.role = role;
        this.salary = salary;
        this.phone = phone;
        this.status = status;
    }


    public  String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}