package DTO;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class OfferDTO {
    private int id;
    private Date dateStart, dateEnd;
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public OfferDTO() {}

    public OfferDTO(int id, Date dateStart, Date dateEnd) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public OfferDTO(ResultSet rs){
        try {
            this.id = rs.getInt("id");
            this.dateStart = rs.getDate("dateStart");
            this.dateEnd = rs.getDate("dateEnd");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }
    public java.sql.Date getDateStart() {return dateStart;}
    public Date getDateEnd() {return dateEnd;}
    public void setId(int id) {this.id = id;}
    public void setDateStart(Date dateStart) {this.dateStart = dateStart;}
    public void setDateEnd(Date dateEnd) {this.dateEnd = dateEnd;}

    @Override
    public String toString() {
        return (id==0)?"Không áp dụng":"<html>Từ <b>" + dateFormat.format(dateStart)+ "</b> đến <b>" + dateFormat.format(dateEnd)+ "</b></html>";
    }
}
