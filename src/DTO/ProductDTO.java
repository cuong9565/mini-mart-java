package DTO;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

public class ProductDTO {
    private int id, quantity;
    private TypeProductDTO type;
    private DetailProductDTO detail;
    private OfferProductDTO offerProduct;
    private String name, unit;
    private double price;

    public ProductDTO() {}
    public ProductDTO(int id, String name, int qty, String productUnit, double price) {
        this.id = id;
        this.name = name;
        this.quantity = qty;
        this.unit = productUnit;
        this.price = price;
    }

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

    @Override
    public String toString() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        today = cal.getTime();

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
        return new Object[]{id, name, String.format("%,.0fđ", price), unit, quantity};
    }
}