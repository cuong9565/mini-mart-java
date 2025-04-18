package DTO;

import Components.MyDate;
import java.sql.ResultSet;

public class ImportDTO {
    private int id = 0;
    private StaffDTO staff = new StaffDTO();
    private SupplierDTO customer = new SupplierDTO();
    private MyDate dateCreate = new MyDate();
    private double price = 0;
    private String state = "";

    public ImportDTO() {}
    public ImportDTO(int id, StaffDTO staff, SupplierDTO customer, MyDate dateCreate, double price, String state) {
        this.id = id;
        this.staff = staff;
        this.customer = customer;
        this.dateCreate = dateCreate;
        this.price = price;
        this.state = state;
    }
    public ImportDTO(ResultSet rs){
        try {
            id = rs.getInt("importorder.id");
            dateCreate = new MyDate(rs.getDate("importorder.dateCreate"));
            price = rs.getDouble("importorder.total");
            state = rs.getString("importorder.state");
            staff = new StaffDTO(rs);
            customer = new SupplierDTO(rs);
        }
        catch(Exception e){
            System.out.println("Lỗi constructor ResultSet của ImportDTO: " + e.getMessage());
        }
    }

    public int getId() {return id;}
    public StaffDTO getStaff() {return staff;}
    public SupplierDTO getSupplier() {return customer;}
    public MyDate getDateCreate() {return dateCreate;}
    public double getPrice() {return price;}
    public String getState() {return state;}

    public void setId(int id) {this.id = id;}
    public void setStaff(StaffDTO staff) {this.staff = staff;}
    public void setSupplier(SupplierDTO customer) {this.customer = customer;}
    public void setDateCreate(MyDate dateCreate) {this.dateCreate = dateCreate;}
    public void setPrice(double price) {this.price = price;}
    public void setState(String state) {this.state = state;}

    public Object[] getRowObjects(){
        return new Object[]{
                id,
                dateCreate.toString(),
                String.format("%,.0fđ", price),
                state
        };
    }
}
