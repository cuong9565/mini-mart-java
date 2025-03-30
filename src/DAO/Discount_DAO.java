package DAO;

import DTO.Discount_DTO;
import DTO.Staff_DTO;
import DTO.connect_data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Discount_DAO {

    private connect_data datadis;

    public Discount_DAO() {
        this.datadis = new connect_data();
    }

    public List<Discount_DTO> GetDiscountData() {
        List<Discount_DTO> lsdiscount = new ArrayList<>();
        String query = "SELECT * FROM offer";

        try (ResultSet rs = datadis.executeQuery(query)) {
            while (rs.next()) {
                Discount_DTO discount = new Discount_DTO(
                        rs.getString("id"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("type"),
                        rs.getInt("quantity"),
                        rs.getString("status")
                );
                lsdiscount.add(discount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lsdiscount;
    }
    public Boolean addDiscount (Discount_DTO disdto){
        String query = "INSERT INTO offer (startDate,endDate,type,quantity,status) " +
                "VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = datadis.executeUpdate(query, disdto.getDatecreate(),disdto.getDatedue(),disdto.getType(),disdto.getQuanty(),disdto.getStatus());
        return rowsAffected > 0;
    }
    public boolean updateDis(Discount_DTO dis ) {
        String query = "UPDATE offer set startDate= ?, endDate= ? , type = ? ,quantity=? , status =? ";
        int rowsAffected = datadis.executeUpdate(query,
               dis.getDatecreate(),
                dis.getDatedue(),
                dis.getType(),
                dis.getQuanty(),
            dis.getStatus());
        return rowsAffected > 0;
    }

    public boolean Deledis (String id ){
        String query = "DELETE FROM offer WHERE id = ?";
        int row = datadis.executeUpdate(query,id);
        return row >0;
    }
}
