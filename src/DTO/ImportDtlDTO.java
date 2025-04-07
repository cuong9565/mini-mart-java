package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportDtlDTO {
    private int idImport, idProduct,quantity;
    private double price;
    private String unit;

    public ImportDtlDTO() {}

    public ImportDtlDTO(Object[] ob) {
        this.idImport = (int) ob[0];
        this.idProduct = (int) ob[1];
        this.quantity = (int) ob[2];
        this.price = (double) ob[4];
        this.unit = (String) ob[3];
    }

    public ImportDtlDTO(ResultSet rs) {
        try {
            this.idImport = Integer.parseInt(rs.getString("idImport"));
            this.idProduct= Integer.parseInt(rs.getString("idProduct"));
            this.quantity = Integer.parseInt(rs.getString("quantity"));
            this.price = Double.parseDouble(rs.getString("price"));
            this.unit = rs.getString("unit");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ImportDtlDTO(int idImport, int idProduct, int quantity, double price, String unit) {
        this.idImport  = idImport;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.unit = unit;
    }

    public int getIdImport() {return idImport;}
    public void setIdImport(int idImport) {this.idImport = idImport;}
    public int getIdProduct() {return idProduct;}
    public void setIdProduct(int idProduct) {this.idProduct = idProduct;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    public String getUnit() {return unit;}
    public void setUnit(String unit) {this.unit = unit;}
    public Object[] getObjects() {return new Object[]{idImport,idProduct,quantity,price,unit};} //co the bi loi
}
