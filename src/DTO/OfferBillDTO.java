package DTO;

import java.sql.ResultSet;

public class OfferBillDTO {
    private int id;
    private int discount;
    private OfferDTO offer;

    public OfferBillDTO() {
    }

    public OfferBillDTO(int id, int discount, OfferDTO offer) {
        this.id = id;
        this.discount = discount;
        this.offer = offer;
    }
    public OfferBillDTO(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.discount = rs.getInt("discount");
            this.offer = new OfferDTO(
                    rs.getInt("idOffer"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate")
            );
        } catch (Exception e) {
            System.out.println("OfferBillDTO: " + e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public OfferDTO getOffer() {
        return offer;
    }

    public void setOffer(OfferDTO offer) {
        this.offer = offer;
    }

    public Object[] getObjects() {
        return new Object[] {
                id,
                offer.getDateStart(),
                offer.getDateEnd(),
                discount
        };
    }
}
