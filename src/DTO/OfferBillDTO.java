package DTO;

import java.sql.ResultSet;

public class OfferBillDTO {
    private int id = 0;
    private int discount = 0;
    private OfferDTO offer = new OfferDTO();

    public OfferBillDTO() {}
    public OfferBillDTO(int id, OfferDTO offer, int discount) {
        this.id = id;
        this.offer = offer;
        this.discount = discount;
    }
    public OfferBillDTO(ResultSet rs) {
        try{
            offer = new OfferDTO(rs);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {return id;}
    public OfferDTO getOffer() {return offer;}
    public int getDiscount() {return discount;}

    public void setId(int id) {this.id = id;}
    public void setOffer(OfferDTO offer) {this.offer = offer;}
    public void setDiscount(int discount) {this.discount = discount;}

    @Override
    public String toString() {
        return (this.id!=0)?discount + "%":"Chưa có ưu đãi";
    }
    public Object[] getObjects() {return new Object[]{offer.getId(),offer.getName(), offer.getDateStart(), offer.getDateEnd(), offer.getValue()+ "%"};}

}
