package DTO;

import java.sql.ResultSet;

public class BillInfoDTO {
    private int id = 0;
    private int idBill = 0;
    private int idProduct = 0;
    private int quantity = 0, discount = 0;
    private double price = 0, total = 0;
    private String nameProduct = "", unit = "";

    public BillInfoDTO() {}
    public BillInfoDTO(int id, int idBill, int idProduct, int quantity, int discount, double price, double total, String nameProduct, String unit) {
        this.id = id;
        this.idBill = idBill;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.discount = discount;
        this.price = price;
        this.total = total;
        this.nameProduct = nameProduct;
        this.unit = unit;
    }
    public BillInfoDTO(ResultSet rs) {
        try {
            id = rs.getInt("billinfo.id");

            idBill = rs.getInt("billinfo.idBill");
            idProduct = rs.getInt("billinfo.idProduct");

            price = rs.getDouble("billinfo.price");
            quantity = rs.getInt("billinfo.quantity");
            discount = rs.getInt("billinfo.discount");
            total = rs.getDouble("billinfo.total");
            unit = rs.getString("billinfo.unit");
            nameProduct = rs.getString("billinfo.nameProduct");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {return id;}
    public int getIdBill() {return idBill;}
    public int getIdProduct() {return idProduct;}
    public int getQuantity() {return quantity;}
    public int getDiscount() {return discount;}
    public double getPrice() {return price;}
    public String getFormatPrice() {return String.format("%,.0fđ", price);}
    public double getTotal() {return total;}
    public String getFormatTotal() {return String.format("%,.0fđ", total);}
    public String getNameProduct() {return nameProduct;}
    public String getUnit() {return unit;}

    public void setId(int id) {this.id = id;}
    public void setIdBill(int idBill) {this.idBill = idBill;}
    public void setIdProduct(int idProduct) {this.idProduct = idProduct;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setDiscount(int discount) {this.discount = discount;}
    public void setPrice(double price) {this.price = price;}
    public void setTotal(double total) {this.total = total;}
    public void setNameProduct(String nameProduct) {this.nameProduct = nameProduct;}
    public void setUnit(String unit) {this.unit = unit;}

    public Object[] getSellObjects(){
        return new Object[]{idProduct, nameProduct, getFormatPrice(), quantity, discount + "%", unit, getFormatTotal()};
    }
}
