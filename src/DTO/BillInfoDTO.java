package DTO;

import java.sql.ResultSet;

public class BillInfoDTO {
    private int id, idBill, idProduct, quantity, discount;
    private double price, total;
    private String nameProduct, unit;
    public BillInfoDTO() {}
    public BillInfoDTO(int id){
        this.id = id;
    }
    public BillInfoDTO(ResultSet rs) {
        try {
            id = rs.getInt("id");
            idBill = rs.getInt("idBill");
            idProduct = rs.getInt("idProduct");
            quantity = rs.getInt("quantity");
            discount = rs.getInt("discount");
            price = rs.getDouble("price");
            total = rs.getDouble("total");
            nameProduct = rs.getString("nameProduct");
            unit = rs.getString("unit");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
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
