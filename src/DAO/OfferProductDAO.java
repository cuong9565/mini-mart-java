package DAO;

import DTO.OfferProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OfferProductDAO {
    private static OfferProductDAO instance;

    public OfferProductDAO() {}
    public static OfferProductDAO getInstance() {
        if (instance == null) instance = new OfferProductDAO();
        return instance;
    }

    public List<OfferProductDTO> getList(){
        List<OfferProductDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select op.*, o.startDate, o.endDate\n" +
                "from offerproduct op, offer o\n" +
                "where op.idOffer = o.id\n" +
                "order by op.discount";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new OfferProductDTO(rs));
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return list;
    }
}
