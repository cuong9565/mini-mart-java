package DTO;

import Components.MyDate;

import java.sql.ResultSet;

public class OfferBill2DTO {
    private int id = 0;
    private int discount = 0;
    private OfferDTO offer = new OfferDTO();

    public OfferBill2DTO() {}
    public OfferBill2DTO(int id, OfferDTO offer, int discount) {
        this.id = id;
        this.offer = offer;
        this.discount = discount;
    }
    public OfferBill2DTO(ResultSet rs, int i){
        try {
            id = rs.getInt(i++);
            discount = rs.getInt(i++); i+=1;
            offer = new OfferDTO(rs, i);
        }
        catch(Exception e){
            System.out.println("Lỗi constructor ResultSet của OfferBill2DTO" + e.getMessage());
        }
    }

    public int getId() {return id;}
    public int getDiscount() {return discount;}
    public OfferDTO getOffer() {return offer;}

    public void setId(int id) {this.id = id;}
    public void setDiscount(int discount) {this.discount = discount;}
    public void setOffer(OfferDTO offer) {this.offer = offer;}

    @Override
    public String toString() {
        return (this.id!=0)?discount + "%":"Chưa có ưu đãi";
    }
    public Object[] getObjects() {return new Object[]{id, offer.getDateStart(), offer.getDateEnd(), discount + "%"};}

}