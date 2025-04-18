package DTO;

import java.sql.ResultSet;

public class ImportInfoDTO {
    private int id = 0;
    private int idImport = 0;
    private int idProduct = 0;
    private int quantity = 0;
    private double price = 0, total = 0;
    private String nameProduct = "", unit = "";

    public ImportInfoDTO() {}
    public ImportInfoDTO(int id, int idImport, int idProduct, int quantity, double price, double total, String nameProduct, String unit) {
        this.id = id;
        this.idImport = idImport;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.nameProduct = nameProduct;
        this.unit = unit;
    }
    public ImportInfoDTO(ResultSet rs) {
        try {
            id = rs.getInt("importorderdetail.id");
            idImport = rs.getInt("importorderdetail.idImportOrder");
            idProduct = rs.getInt("importorderdetail.idProduct");

            price = rs.getDouble("importorderdetail.price");
            quantity = rs.getInt("importorderdetail.quantity");

            total = price * quantity;
            unit = rs.getString("importorderdetail.unit");
            nameProduct = rs.getString("importorderdetail.nameProduct");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {return id;}
    public int getIdImport() {return idImport;}
    public int getIdProduct() {return idProduct;}
    public int getQuantity() {return quantity;}
    public double getPrice() {return price;}
    public String getFormatPrice() {return String.format("%,.0fđ", price);}
    public double getTotal() {return total;}
    public String getFormatTotal() {return String.format("%,.0fđ", total);}
    public String getNameProduct() {return nameProduct;}
    public String getUnit() {return unit;}

    public void setId(int id) {this.id = id;}
    public void setIdImport(int idImport) {this.idImport = idImport;}
    public void setIdProduct(int idProduct) {this.idProduct = idProduct;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setPrice(double price) {this.price = price;}
    public void setTotal(double total) {this.total = total;}
    public void setNameProduct(String nameProduct) {this.nameProduct = nameProduct;}
    public void setUnit(String unit) {this.unit = unit;}

    public Object[] getSellObjects(){
        return new Object[]{idProduct, nameProduct, getFormatPrice(), quantity, unit, getFormatTotal()};
    }
}
