package DTO;

import java.sql.Date;

public class BillDTO {
    private int id;
    private StaffDTO staff;
    private CustomerDTO customer;
    private OfferBillDTO offerBill;
    private Date dateCreate;
    private double price;
    private String state;
    public BillDTO() {}
    public BillDTO(int id, StaffDTO staff, CustomerDTO customer, OfferBillDTO offerBill, Date dateCreate, double price, String state) {
        this.id = id;
        this.staff = staff;
        this.customer = customer;
        this.offerBill = offerBill;
        this.dateCreate = dateCreate;
        this.price = price;
        this.state = state;
    }
    public int getId() {return id;}
    public StaffDTO getStaff() {return staff;}
    public CustomerDTO getCustomer() {return customer;}
    public OfferBillDTO getOfferBill() {return offerBill;}
    public Date getDateCreate() {return dateCreate;}
    public double getPrice() {return price;}
    public String getState() {return state;}

    public void setId(int id) {this.id = id;}
    public void setStaff(StaffDTO staff) {this.staff = staff;}
    public void setCustomer(CustomerDTO customer) {this.customer = customer;}
    public void setOfferBill(OfferBillDTO offerBill) {this.offerBill = offerBill;}
    public void setDateCreate(Date dateCreate) {this.dateCreate = dateCreate;}
    public void setPrice(double price) {this.price = price;}
    public void setState(String state) {this.state = state;}

}