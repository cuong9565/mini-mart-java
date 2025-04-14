package DTO;

import Components.MyDate;
import java.sql.ResultSet;

public class Bill2DTO {
    private int id = 0;
    private StaffDTO staff = new StaffDTO();
    private OfferBillDTO offerBill = new OfferBillDTO();
    private CustomerDTO customer = new CustomerDTO();
    private MyDate dateCreate = new MyDate();
    private double price = 0;
    private String state = "";

    public Bill2DTO() {}
    public Bill2DTO(int id, StaffDTO staff, OfferBillDTO offerBill, CustomerDTO customer, MyDate dateCreate, double price, String state) {
        this.id = id;
        this.staff = staff;
        this.offerBill = offerBill;
        this.customer = customer;
        this.dateCreate = dateCreate;
        this.price = price;
        this.state = state;
    }
    public Bill2DTO(ResultSet rs, int i){
        try {
            id = rs.getInt(i++); i+=3;
            dateCreate = new MyDate(rs.getDate(i++));
            price = rs.getDouble(i++);
            state = rs.getString(i++);
            staff = new StaffDTO(rs, i); i+=10;
            offerBill = new OfferBillDTO(rs, i); i+=6;
            customer = new CustomerDTO(rs, i);
        }
        catch(Exception e){
            System.out.println("Lỗi constructor ResultSet của Bill2DTO: " + e.getMessage());
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
                staff.getLastName() + " " + staff.getFirstName(),
                offerBill.getDiscount() + "%",
                (customer.getId()==0?"":customer.getLastName() + " " + customer.getFirstName()),
                dateCreate.toString(),
                String.format("%,.0fđ", price),
                state,
        };
    }
}
