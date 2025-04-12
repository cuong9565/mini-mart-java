package DTO;

import Components.MyDate;
import java.sql.ResultSet;

public class OfferDTO {
    private int id;
    private MyDate dateStart, dateEnd;

    public OfferDTO() {}
    public OfferDTO(int id){
        this.id = id;
    }
    public OfferDTO(int id, String dateStart, String dateEnd) {
        this.id = id;
        this.dateStart = new MyDate(dateStart);
        this.dateEnd = new MyDate(dateEnd);
    }
    public OfferDTO(int id, MyDate dateStart, MyDate dateEnd) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public OfferDTO(ResultSet rs){
        try {
            this.id = rs.getInt("id");
            this.dateStart = new MyDate(rs.getDate("startDate"));
            this.dateEnd = new MyDate(rs.getDate("endDate"));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {return id;}
    public MyDate getDateStart() {return dateStart;}
    public MyDate getDateEnd() {return dateEnd;}
    public void setId(int id) {this.id = id;}
    public void setDateStart(MyDate dateStart) {this.dateStart = dateStart;}
    public void setDateEnd(MyDate dateEnd) {this.dateEnd = dateEnd;}

    @Override
    public String toString() {
        return (id==0)?"Không áp dụng":"<html>Từ <b>" + dateStart + "</b> đến <b>" + dateEnd + "</b></html>";
    }

    public Object[] getObjects() {
        return new Object[]{id, dateStart, dateEnd};
    }
}
