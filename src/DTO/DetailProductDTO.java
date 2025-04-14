package DTO;

import java.sql.ResultSet;

public class DetailProductDTO {
    private int id = 0;
    private String text = "";

    public DetailProductDTO() {}
    public DetailProductDTO(int id, String text) {
        this.id = id;
        this.text = text;
    }
    public DetailProductDTO(ResultSet rs, int i){
        try {
            id = rs.getInt(i++);
            text = rs.getString(i++);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {return id;}
    public String getText() {return text;}

    public void setId(int id) {this.id = id;}
    public void setText(String text) {this.text = text;}
}
