package DTO;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OfferProductDTO {
    private int id, discount;
    private OfferDTO offer;
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public OfferProductDTO() {}
    public OfferProductDTO(int id, OfferDTO offer, int discount) {
        this.id = id;
        this.offer = offer;
        this.discount = discount;
    }
    public OfferProductDTO(ResultSet rs, int curr){
        try{
            this.id = rs.getInt("idOfferProduct");
            this.discount = rs.getInt("discount");
            this.offer = new OfferDTO(
                    rs.getInt("idOffer"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate")
            );
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public OfferProductDTO(ResultSet rs) {
        try{
            this.id = rs.getInt("id");
            this.discount = rs.getInt("discount");
            this.offer = new OfferDTO(
                    rs.getInt("idOffer"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate")
            );
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
    public Object[] getObjects() {
        return new Object[]{id,
                dateFormat.format(offer.getDateStart()),
                dateFormat.format(offer.getDateEnd()),
                discount + "%"
        };
    }

}
