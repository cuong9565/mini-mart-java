package DTO;

import Components.MyDate;

import java.sql.ResultSet;

public class ProductStatisticDTO {
    private int id = 0;
    private String name = "";
    private MyDate date = new MyDate();
    double[] Q = new double[]{0,0,0,0};

    public ProductStatisticDTO() {}
    public ProductStatisticDTO(ResultSet rs) {
        try {
            id = rs.getInt("product.id");
            name = rs.getString("product.name");

            date = new MyDate(rs.getDate("bill.dateCreate"));
            double total = rs.getDouble("billinfo.total");

            setQ(date.getMm(), total);
        }
        catch (Exception e) {
            System.out.println("Lỗi ProductStatisticDTO; " + e.getMessage());
        }
    }
    public ProductStatisticDTO(ResultSet rs, int pos) {
        try {
            id = rs.getInt("product.id");
            name = rs.getString("product.name");

            date = new MyDate(rs.getDate("importorder.dateCreate"));

            int quantity = rs.getInt("importorderdetail.quantity");
            double price = rs.getDouble("importorderdetail.price");
            double total = quantity * price;

            setQ(date.getMm(), total);
        }
        catch (Exception e) {
            System.out.println("Lỗi ProductStatisticDTO; " + e.getMessage());
        }
    }
    public ProductStatisticDTO(int id, String name, MyDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public double[] getQ() {return Q;}
    public double getTotal() {return Q[0] + Q[1] + Q[2] + Q[3];}
    public MyDate getDate() {return date;}
    public String getFomatedQ1(){return String.format("%,.0fđ", Q[0]);}
    public String getFomatedQ2(){return String.format("%,.0fđ", Q[1]);}
    public String getFomatedQ3(){return String.format("%,.0fđ", Q[2]);}
    public String getFomatedQ4(){return String.format("%,.0fđ", Q[3]);}
    public String getFomatedTotal(){return String.format("%,.0fđ", getTotal());}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setDate(MyDate date) {this.date = date;}
    public void setQ(double[] Q) {this.Q = Q;}
    public void setQ(int pos, double value){Q[(pos-1)/3] = value;}
    public ProductStatisticDTO addQ(double[] Q){
        for(int i=0; i<Q.length; i++)
            this.Q[i] += Q[i];
        return this;
    }

    public Object[] getRRowObject() {
        return new Object[]{id, name, getFomatedQ1(), getFomatedQ2(), getFomatedQ3(), getFomatedQ4(), getFomatedTotal()};
    }
}
