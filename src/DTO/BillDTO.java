package DTO;

import Components.MyDate;

import java.sql.ResultSet;

public class BillDTO {
    private int id;
    private int idStaff;
    private int idOfferBill;
    private int idCustomer;
    private MyDate dateCreate;
    private double price;
    private String state;
    public BillDTO() {}
    public BillDTO(int id){
        this.id = id;
    }
    public BillDTO(ResultSet rs){
        try{
            id = rs.getInt("id");
            idStaff = rs.getInt("idStaff");
            idOfferBill = rs.getInt("idOfferBill");
            idCustomer = rs.getInt("idCustomer");
            dateCreate = new MyDate(rs.getDate("dateCreate"));
            price = rs.getDouble("price");
            state = rs.getString("state");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public BillDTO(int id, int idStaff, int idOfferBill, int idCustomer, MyDate dateCreate, double price, String state) {
        this.id = id;
        this.idStaff = idStaff;
        this.idOfferBill = idOfferBill;
        this.idCustomer = idCustomer;
        this.dateCreate = dateCreate;
        this.price = price;
        this.state = state;
    }
    public int getId() {return id;}
    public int getIdStaff() {return idStaff;}
    public int getIdOfferBill() {return idOfferBill;}
    public int getIdCustomer() {return idCustomer;}
    public MyDate getDateCreate() {return dateCreate;}
    public double getPrice() {return price;}
    public String getState() {return state;}

    public void setId(int id) {this.id = id;}
    public void setIdStaff(int idStaff) {this.idStaff = idStaff;}
    public void setIdOfferBill(int idOfferBill) {this.idOfferBill = idOfferBill;}
    public void setIdCustomer(int idCustomer) {this.idCustomer = idCustomer;}
    public void setDateCreate(MyDate dateCreate) {this.dateCreate = dateCreate;}
    public void setPrice(double price) {this.price = price;}
    public void setState(String state) {this.state = state;}
}