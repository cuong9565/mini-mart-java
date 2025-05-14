package DTO;

import Components.MyDate;
import jdk.jfr.Category;

import java.sql.ResultSet;
import java.util.Date;

public class OfferDTO {
    private int id = 0;
    private String name ;
    private MyDate dateStart = new MyDate();
    private MyDate dateEnd = new MyDate();
    private String Category;
    private int value ;

    public OfferDTO() {}
    public OfferDTO(int id, String name ,String dateStart, String dateEnd,String category, int value) {
        this.id = id;
        this.name= name;
        this.dateStart = new MyDate(dateStart);
        this.dateEnd = new MyDate(dateEnd);
        this.Category= category;
        this.value= value;
    }
    public OfferDTO(int id, MyDate dateStart, MyDate dateEnd) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
    public OfferDTO(int id, String name,MyDate dateStart, MyDate dateEnd, String category, int value) {
        this.id = id;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.value= value;
        this.Category= category;
    }


    public OfferDTO(ResultSet rs){
        try{
            id = rs.getInt("offer.id");
            name=rs.getString("offer.name");
            dateStart = new MyDate(rs.getDate("offer.startDate"));
            dateEnd = new MyDate(rs.getDate("offer.endDate"));
            Category = rs.getString("offer.category");
            value = rs.getInt("offer.value");
        }catch (Exception e){
            System.out.println("Lỗi constructor ResultSet của OfferDTO: " + e.getMessage());
        }
    }


    public int getId() {return id;}
    public MyDate getDateStart() {return dateStart;}
    public MyDate getDateEnd() {return dateEnd;}
    public String getName(){
        return name;
    }
    public String getCategory(){
        return Category;
    }
    public int getValue (){
        return  value;
    }
    public void setValue(int val){
        value= val;
    }
    public void setName(String Name){
        name= Name;
    }
    public void setId(int id) {this.id = id;}
    public void setDateStart(MyDate dateStart) {this.dateStart = dateStart;}
    public void setDateEnd(MyDate dateEnd) {this.dateEnd = dateEnd;}
    public String getStatus(Date startDate, Date endDate) {
        Date today = new Date();
        if (today.before(startDate)) {
            return "Chưa đến ngày";
        } else if (today.after(endDate)) {
            return "Đã kết thúc";
        } else {
            return "Hoạt động";
        }
    }

    @Override
    public String toString() {
//        return (id==0)?"Không áp dụng":"<html>Từ <b>" + dateStart + "</b> đến <b>" + dateEnd + "</b></html>";
        return name;
    }
    public Object[] getObjects() {
        return new Object[]{id,name, dateStart, dateEnd,Category,value+"%",getStatus(dateStart.getSqlDate(),dateEnd.getSqlDate())};
    }
}
