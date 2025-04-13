package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeProductDTO {
    private int id = 0;
    private String name = "";

    public TypeProductDTO() {}
    public TypeProductDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public TypeProductDTO(ResultSet rs, int curr){
        try {
            this.id = Integer.parseInt(rs.getString("idProductType"));
            this.name = rs.getString("type");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public TypeProductDTO(ResultSet rs) {
        try {
            this.id = Integer.parseInt(rs.getString("id"));
            this.name = rs.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public TypeProductDTO(Object[] obs) {
        this.id = Integer.parseInt(obs[0].toString());
        this.name = obs[1].toString();
    }
    public int getId() {return id;}
    public String getName() {return name;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {return this.name;}
    public Object[] getObjects() {return new Object[] { id, name };}
}
