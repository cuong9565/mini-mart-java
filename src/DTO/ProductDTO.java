package DTO;

import java.sql.ResultSet;

public class ProductDTO {
    private int id, quantity;
    private TypeProductDTO type;
    private DetailProductDTO detail;
    private OfferProductDTO offerProduct;
    private String name, unit;
    private double price;

    public ProductDTO() {}

    public ProductDTO(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.type = new TypeProductDTO(rs, 0);
            this.detail = new DetailProductDTO(rs, 0);
            this.offerProduct = new OfferProductDTO(rs, 0);
            this.name = rs.getString("name");
            this.price = rs.getDouble("price");
            this.unit = rs.getString("unit");
            this.quantity = rs.getInt("quantity");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ProductDTO(int id, TypeProductDTO type, DetailProductDTO detail, OfferProductDTO offerProduct, String name, double price, String unit, int quantity, int discount) {
        this.id = id;
        this.type = type;
        this.detail = detail;
        this.offerProduct = offerProduct;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public TypeProductDTO getType() { return type; }
    public DetailProductDTO getDetail(){return detail;}
    public OfferProductDTO getOfferProduct(){return offerProduct;}
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getUnit() { return unit; }
    public int getQuantity() { return quantity; }

    public void setId(int id) { this.id = id; }
    public void setType(TypeProductDTO type) { this.type = type; }
    public void setDetail(DetailProductDTO detail) { this.detail = detail; }
    public void setOfferProduct(OfferProductDTO offerProduct) { this.offerProduct = offerProduct; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Object[] getRowObjects() {
        java.util.Date today = new java.util.Date();
        String str = "";
        if(offerProduct.getId()==0) str = "Chưa có ưu đãi";
        else if(today.compareTo(offerProduct.getOffer().getDateStart())<0) str = "Chưa đến kì giảm giá";
        else if(today.compareTo(offerProduct.getOffer().getDateEnd())>0) str = "Đã qua kì giảm giá";
        else str = offerProduct.getDiscount() + "%";
        return new Object[]{id, type.getName(), str, name, String.format("%,.0fđ", price), unit, quantity};
    }
}