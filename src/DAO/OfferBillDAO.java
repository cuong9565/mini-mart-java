package DAO;

import DTO.OfferBillDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OfferBillDAO {
    private static OfferBillDAO instance;

    public OfferBillDAO() {}
    public static OfferBillDAO getInstance() {
        if (instance == null) instance = new OfferBillDAO();
        return instance;
    }

    public List<OfferBillDTO> getList(){
        List<OfferBillDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select * " +
                "from offerbill, offer " +
                "where offerbill.idOffer = offer.id " +
                "order by offerbill.id";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new OfferBillDTO(rs));
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    public OfferBillDTO getItemById(int id){
        OfferBillDTO offerBill = new OfferBillDTO();
        Connection con = DataProvider.getInstance().getConnection();
        String sql
                = "select * " +
                "from offerbill " +
                "left join offer on offerbill.idOffer = offer.id " +
                "where offerbill.id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) offerBill = new OfferBillDTO(rs);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return offerBill;
    }

    public boolean isSameOfferBill(OfferBillDTO o){
        boolean res;
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from offerbill where idOffer = ? and discount = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, o.getOffer().getId());
            ps.setInt(2, o.getDiscount());
            ResultSet rs = ps.executeQuery();
            res = rs.next();
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return res;
    }

    public void add(OfferBillDTO offerProductDTO){
        String sql = "insert into offerbill(idOffer, discount) values(?,?)";
        Connection con = DataProvider.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, offerProductDTO.getOffer().getId());
            ps.setInt(2, offerProductDTO.getDiscount());
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public void update(OfferBillDTO offerProductDTO){
        String sql = "update offerbill set discount = ?, idOffer = ? where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, offerProductDTO.getDiscount());
            ps.setInt(2, offerProductDTO.getOffer().getId());
            ps.setInt(3, offerProductDTO.getId());
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }

    public void delete(int id){
        String sql = "delete from offerbill where id = ?";
        Connection con = DataProvider.getInstance().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
    }
}
