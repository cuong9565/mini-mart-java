package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDTO {
    private int id;
    private int idProductType;
    private int idProductDetail;
    private int idOfferProduct; // Nullable in DB, so can be -1 or null
    private String name;
    private double price;
    private String unit;
    private int quantity;

    // Default constructor
    public ProductDTO() {}

    // Constructor from ResultSet
    public ProductDTO(ResultSet rs) throws SQLException {
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

    // Parameterized constructor
    public ProductDTO(int id, int idProductType, int idProductDetail, int idOfferProduct,
                      String name, double price, String unit, int quantity) {
        this.id = id;
        this.idProductType = idProductType;
        this.idProductDetail = idProductDetail;
        this.idOfferProduct = idOfferProduct;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdProductType() { return idProductType; }
    public void setIdProductType(int idProductType) { this.idProductType = idProductType; }
    public int getIdProductDetail() { return idProductDetail; }
    public void setIdProductDetail(int idProductDetail) { this.idProductDetail = idProductDetail; }
    public int getIdOfferProduct() { return idOfferProduct; }
    public void setIdOfferProduct(int idOfferProduct) { this.idOfferProduct = idOfferProduct; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "ProductDTO{id=" + id + ", name=" + name + ", price=" + price + ", unit=" + unit + ", quantity=" + quantity + "}";
    }
}