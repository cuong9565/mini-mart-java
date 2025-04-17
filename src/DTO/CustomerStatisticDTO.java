package DTO;

import Components.MyColor;
import Components.MyDate;

import java.sql.ResultSet;

public class CustomerStatisticDTO {
    private int id = 0;
    private String lastName = "", firstName = "", phone = "";
    private MyDate dateCreate = new MyDate();
    private double[] Q = new double[]{0,0,0,0};

    public CustomerStatisticDTO() {}
    public CustomerStatisticDTO(ResultSet rs) {
        try {
            id = rs.getInt("customer.id");
            lastName = rs.getString("customer.lastName");
            firstName = rs.getString("customer.firstName");
            phone = rs.getString("customer.phone");

            dateCreate = new MyDate(rs.getDate("bill.dateCreate"));
            double total = rs.getDouble("bill.price");

            Q[(dateCreate.getMm()-1)/3] += total;
        }
        catch (Exception e) {
            System.out.println("Lỗi constructor CustomerStatisticDTO" + e.getMessage());
        }
    }
    public CustomerStatisticDTO(int id, String lastName, String firstName, String phone, MyDate dateCreate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.dateCreate = dateCreate;
    }

    public int getId() {return id;}
    public String getLastName() {return lastName;}
    public String getFirstName() {return firstName;}
    public String getPhone() {return phone;}
    public MyDate getDateCreate() {return dateCreate;}
    public double[] getQ() {return Q;}
    public double getTotal() {return Q[0] + Q[1] + Q[2] + Q[3];}
    public String getFormattedQ1() {return String.format("%,.0fđ", Q[0]);}
    public String getFormattedQ2() {return String.format("%,.0fđ", Q[1]);}
    public String getFormattedQ3() {return String.format("%,.0fđ", Q[2]);}
    public String getFormattedQ4() {return String.format("%,.0fđ", Q[3]);}
    public String getFormattedTotal() {return String.format("%,.0fđ", getTotal());}

    public void setId(int id) {this.id = id;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setDateCreate(MyDate dateCreate) {this.dateCreate = dateCreate;}
    public void setQ(double[] q) {this.Q = q;}
    public CustomerStatisticDTO addQ(double[] q) {
        for (int i = 0; i < Q.length; i++)
            Q[i] += q[i];
        return this;
    }

    public Object[] getRowObjects(){
        return new Object[]{id, lastName, firstName, phone, getFormattedQ1(), getFormattedQ2(), getFormattedQ3(), getFormattedQ4(), getFormattedTotal()};
    }
}
