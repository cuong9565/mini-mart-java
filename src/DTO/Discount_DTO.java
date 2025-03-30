package DTO;
import java.util.Date;

public class Discount_DTO {
    private String id;
    private String type;
    private Date datecreate;
    private Date datedue;
    private int quanty;
    private String status;


    public Discount_DTO(String id, Date datecreate, Date datedue,String type, int quanty, String status) {
        this.id = id;
        this.type = type;
        this.datecreate = datecreate;
        this.datedue = datedue;
        this.quanty = quanty;
        this.status = status;
    }


    public Discount_DTO() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public Date getDatedue() {
        return datedue;
    }

    public void setDatedue() {
        this.datedue = datedue;
    }

    public int getQuanty() {
        return quanty;
    }

    public void setQuanty(int quanty) {
        this.quanty = quanty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}