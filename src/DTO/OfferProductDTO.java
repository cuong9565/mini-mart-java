package DTO;

public class OfferProductDTO {
    private int id, idOffer, discount;
    public OfferProductDTO() {}
    public OfferProductDTO(int id, int idOffer, int discount) {
        this.id = id;
        this.idOffer = idOffer;
        this.discount = discount;
    }
    public int getId() {return id;}
    public int getIdOffer() {return idOffer;}
    public int getDiscount() {return discount;}
    public void setId(int id) {this.id = id;}
    public void setIdOffer(int idOffer) {this.idOffer = idOffer;}
    public void setDiscount(int discount) {this.discount = discount;}
    @Override
    public String toString() {
        return discount + "%";
    }
}
