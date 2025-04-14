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
                "select offerproduct.*, offer.* " +
                "from offerproduct, offer " +
                "where offerproduct.idOffer = offer.id " +
                "order by offerproduct.id";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new OfferProductDTO(rs, 1));
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
    public List<OfferProductDTO> getListDistinctDiscount(){
        List<OfferProductDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select distinct discount " +
                    "from offerproduct " +
                    "order by discount;";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new OfferProductDTO(-1, null, Integer.parseInt(rs.getString("discount"))));
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }

    public boolean add(OfferProductDTO offerProductDTO){
        int res = 0;
        String sql = "insert into offerproduct(idOffer, discount) values(?,?)";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, offerProductDTO.getOffer().getId());
            ps.setInt(2, offerProductDTO.getDiscount());
            res = ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res > 0;
    }

    public boolean update(OfferProductDTO offerProductDTO){
        int res = 0;
        String sql = "update offerproduct set discount = ?, idOffer = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, offerProductDTO.getDiscount());
            ps.setInt(2, offerProductDTO.getOffer().getId());
            ps.setInt(3, offerProductDTO.getId());
            res = ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res > 0;
    }

    public boolean delete(int id){
        int res = 0;
        String sql = "delete from offerproduct where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            res = ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res > 0;
    }
}
