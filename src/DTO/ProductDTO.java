package DTO;

import Components.MyDate;

import java.sql.ResultSet;

public class ProductDTO {
    private int id = 0, quantity = 0;
    private TypeProductDTO type = new TypeProductDTO();
    private DetailProductDTO detail = new DetailProductDTO();
    private OfferProductDTO offerProduct = new OfferProductDTO();
    private String name = "", unit = "";
    private double price = 0;

    public ProductDTO() {}
    public ProductDTO(int id, String name, int qty, String productUnit, double price) {
        this.id = id;
        this.name = name;
        this.quantity = qty;
        this.unit = productUnit;
        this.price = price;
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
    public ProductDTO(ResultSet rs) {
        try {
            id = rs.getInt("product.id");
            name = rs.getString("product.name");
            price = rs.getDouble("product.price");
            unit = rs.getString("product.unit");
            quantity = rs.getInt("product.quantity");
            type = new TypeProductDTO(rs);
            detail = new DetailProductDTO(rs);
            offerProduct = new OfferProductDTO(rs);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() { return id; }
    public TypeProductDTO getType() { return type; }
    public DetailProductDTO getDetail(){return detail;}
    public OfferProductDTO getOfferProduct(){return offerProduct;}
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getFormatPrice(){return String.format("%,.0fđ", price);}
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

    @Override
    public String toString() {
        MyDate today = MyDate.getCurrentDate();
        String str;
        if(offerProduct.getId()==0) str = "Chưa có ưu đãi";
        else if(today.compareTo(offerProduct.getOffer().getDateStart())<0) str = "Chưa đến kì giảm giá";
        else if(today.compareTo(offerProduct.getOffer().getDateEnd())>0) str = "Đã qua kì giảm giá";
        else str = offerProduct.getDiscount() + "%";
        return str;
    }

    public Object[] getRowObjects() {
        return new Object[]{id, type.getName(), toString(), name, String.format("%,.0fđ", price), unit, quantity};
    }
    public Object[] getRowObjectsSell() {
        return new Object[]{id, name, getFormatPrice(), unit, quantity};
    }


    public int getDiscountVal (){
        return offerProduct.getId();
    }
}