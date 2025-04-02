package DTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProductDTO {
    private int id, idProductType, idProductDetail, idOfferProduct, idOffer, quantity, discount;
    private String name, unit, type, detail;
    private double price;
    private Date startDate, endDate;

    public ProductDTO() {}

    public ProductDTO(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.idProductType = rs.getInt("idProductType");
            this.idProductDetail = rs.getInt("idProductDetail");
            this.idOfferProduct = rs.getInt("idOfferProduct");
            this.idOffer = rs.getInt("idOffer");
            this.name = rs.getString("name");
            this.price = rs.getDouble("price");
            this.unit = rs.getString("unit");
            this.quantity = rs.getInt("quantity");
            this.type = rs.getString("type");
            this.detail = rs.getString("detail");
            this.detail = rs.getString("detail");
            this.discount = rs.getInt("discount");
            this.startDate = rs.getDate("startDate");
            this.endDate = rs.getDate("endDate");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ProductDTO(int id, int idProductType, int idProductDetail, int idOfferProduct, int idOffer, String name, double price, String unit, int quantity, String type, String detail, int discount, Date startDate, Date endDate) {
        this.id = id;
        this.idProductType = idProductType;
        this.idProductDetail = idProductDetail;
        this.idOfferProduct = idOfferProduct;
        this.idOffer = idOffer;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
        this.type = type;
        this.detail = detail;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() { return id; }
    public int getIdProductType() { return idProductType; }
    public int getIdProductDetail() { return idProductDetail; }
    public int getIdOfferProduct() { return idOfferProduct; }
    public int getIdOffer() { return idOffer; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getUnit() { return unit; }
    public int getQuantity() { return quantity; }
    public String getType() { return type; }
    public String getDetail() { return detail; }
    public int getDiscount() { return discount; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }

    public void setId(int id) { this.id = id; }
    public void setIdProductType(int idProductType) { this.idProductType = idProductType; }
    public void setIdProductDetail(int idProductDetail) { this.idProductDetail = idProductDetail; }
    public void setIdOfferProduct(int idOfferProduct) { this.idOfferProduct = idOfferProduct; }
    public void setIdOffer(int idOffer) { this.idOffer = idOffer; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setType(String type) { this.type = type; }
    public void setDetail(String detail) { this.detail = detail; }
    public void setDiscount(int discount) { this.discount = discount; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public Object[] getRowObjects() {
        java.util.Date today = new java.util.Date();
        return new Object[]{id, type, ((idOffer!=0 && today.after(startDate) && today.before(endDate))?discount:0) + "%", name, String.format("%,.0fđ", price), unit, quantity};
    }
}