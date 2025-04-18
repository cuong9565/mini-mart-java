package DTO;

import Components.MyDate;
import java.sql.ResultSet;

public class BillDTO {
    private int id = 0;
    private StaffDTO staff = new StaffDTO();
    private OfferBillDTO offerBill = new OfferBillDTO();
    private CustomerDTO customer = new CustomerDTO();
    private MyDate dateCreate = new MyDate();
    private double price = 0;
    private String state = "";

    public BillDTO() {}
    public BillDTO(int id, StaffDTO staff, OfferBillDTO offerBill, CustomerDTO customer, MyDate dateCreate, double price, String state) {
        this.id = id;
        this.staff = staff;
        this.offerBill = offerBill;
        this.customer = customer;
        this.dateCreate = dateCreate;
        this.price = price;
        this.state = state;
    }
    public BillDTO(ResultSet rs){
        try {
            id = rs.getInt("bill.id");
            dateCreate = new MyDate(rs.getDate("bill.dateCreate"));
            price = rs.getDouble("bill.price");
            state = rs.getString("bill.state");
            staff = new StaffDTO(rs);
            offerBill = new OfferBillDTO(rs);
            customer = new CustomerDTO(rs);
        }
        catch(Exception e){
            System.out.println("Lỗi constructor ResultSet của BillDTO: " + e.getMessage());
        }
    }

    public int getId() {return id;}
    public StaffDTO getStaff() {return staff;}
    public OfferBillDTO getOfferBill() {return offerBill;}
    public CustomerDTO getCustomer() {return customer;}
    public MyDate getDateCreate() {return dateCreate;}
    public double getPrice() {return price;}
    public String getState() {return state;}

    public void setId(int id) {this.id = id;}
    public void setStaff(StaffDTO staff) {this.staff = staff;}
    public void setOffer2Bill(OfferBillDTO offerBill) {this.offerBill = offerBill;}
    public void setCustomer(CustomerDTO customer) {this.customer = customer;}
    public void setDateCreate(MyDate dateCreate) {this.dateCreate = dateCreate;}
    public void setPrice(double price) {this.price = price;}
    public void setState(String state) {this.state = state;}

    public Object[] getRowObjects(){
        return new Object[]{
                id,
                dateCreate.toString(),
                String.format("%,.0fđ", price),
                state,
        };
    }
}
