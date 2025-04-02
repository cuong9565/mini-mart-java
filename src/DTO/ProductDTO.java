package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDTO {
    private int id, idProductType, idProductDetail, idOfferProduct, offer, quantity;
    private String name, unit, type, detail;
    private double price;

    public ProductDTO() {}

    public ProductDTO(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.idProductType = rs.getInt("idProductType");
            this.idProductDetail = rs.getInt("idProductDetail");
            this.idOfferProduct = rs.getInt("idOfferProduct");
            if (rs.wasNull()) this.idOfferProduct = -1; // Handle NULL
            this.name = rs.getString("name");
            this.price = rs.getDouble("price");
            this.unit = rs.getString("unit");
            this.quantity = rs.getInt("quantity");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ProductDTO(int id, int idProductType, int idProductDetail, int idOfferProduct, String name, double price, String unit, int quantity, String type, String detail, int offer) {
        this.id = id;
        this.idProductType = idProductType;
        this.idProductDetail = idProductDetail;
        this.idOfferProduct = idOfferProduct;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
        this.type = type;
        this.detail = detail;
        this.offer = offer;
    }

    public int getId() { return id; }
    public int getIdProductType() { return idProductType; }
    public int getIdProductDetail() { return idProductDetail; }
    public int getIdOfferProduct() { return idOfferProduct; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getUnit() { return unit; }
    public int getQuantity() { return quantity; }
    public String getType() { return type; }
    public String getDetail() { return detail; }
    public int getOffer() { return offer; }
    public void setId(int id) { this.id = id; }
    public void setIdProductType(int idProductType) { this.idProductType = idProductType; }
    public void setIdProductDetail(int idProductDetail) { this.idProductDetail = idProductDetail; }
    public void setIdOfferProduct(int idOfferProduct) { this.idOfferProduct = idOfferProduct; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setType(String type) { this.type = type; }
    public void setDetail(String detail) { this.detail = detail; }
    public void setOffer(int offer) { this.offer = offer; }

}