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
    public OfferDTO(int id, String dateStart, String dateEnd) {
        this.id = id;
        this.dateStart = convert(dateStart);
        this.dateEnd = convert(dateEnd);
    }
    public OfferDTO(int id, Date dateStart, Date dateEnd) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public OfferDTO(ResultSet rs){
        try {
            this.id = rs.getInt("id");
            this.dateStart = rs.getDate("startDate");
            this.dateEnd = rs.getDate("endDate");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {return id;}
    public Date getDateStart() {return dateStart;}
    public Date getDateEnd() {return dateEnd;}
    public java.util.Date getDateStartUtil() {return new java.util.Date(dateStart.getTime());}
    public java.util.Date getDateEndUtil() {return new java.util.Date(dateEnd.getTime());}
    public String getFormattedDateStart() {return dateFormat.format(dateStart);}
    public String getFormattedDateEnd() {return dateFormat.format(dateEnd);}
    public void setId(int id) {this.id = id;}
    public void setDateStart(Date dateStart) {this.dateStart = dateStart;}
    public void setDateEnd(Date dateEnd) {this.dateEnd = dateEnd;}

    @Override
    public String toString() {
        return (id==0)?"Không áp dụng":"<html>Từ <b>" + dateFormat.format(dateStart)+ "</b> đến <b>" + dateFormat.format(dateEnd)+ "</b></html>";
    }

    public Object[] getObjects() {
        return new Object[]{id, dateFormat.format(dateStart), dateFormat.format(dateEnd)};
    }

    public Date convert(String date){
        try{
            java.util.Date utilDate = dateFormat.parse(date);
            return new Date(utilDate.getTime());
        }catch (Exception e) {
            System.out.println("Lỗi định dạng ngày: " + e.getMessage());
            return null;
        }
    }
}
