package DTO;

import java.sql.ResultSet;

public class DetailProductDTO {
    private int id;
    private String text;
    public DetailProductDTO() {}
    public DetailProductDTO(ResultSet rs, int curr){
        try {
            this.id = rs.getInt("idProductDetail");
            this.text = rs.getString("detailInfo");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public DetailProductDTO(ResultSet rs){
        try {
            this.id = rs.getInt("id");
            this.text = rs.getString("detailInfo");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public DetailProductDTO(int id, String text) {
        this.id = id;
        this.text = text;
    }
    public int getId() {return id;}
    public String getText() {return text;}

    public void setId(int id) {this.id = id;}
    public void setText(String text) {this.text = text;}
}
