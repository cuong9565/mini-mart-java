package DAO;

import DTO.Bill2DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Bill2DAO {
    private static Bill2DAO instance = null;

    private Bill2DAO() {}
    public static Bill2DAO getInstance() {
        if (instance == null) instance = new Bill2DAO();
        return instance;
    }

    public List<Bill2DTO>load(){
        List<Bill2DTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select bill.*, staff.*, offerbill.*, offer.*, customer.* " +
                "from bill " +
                "join staff on bill.idStaff = staff.id " +
                "left join offerbill on bill.idOfferBill = offerbill.id " +
                "left join offer on offerbill.idOffer = offer.id " +
                "left join customer on bill.idCustomer = customer.id";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new Bill2DTO(rs, 1));
        }
        catch (Exception e) {
            System.out.println("Lỗi hàm load Bill2DAO " + e.getMessage());
        }
        DataProvider.getInstance().CloseConnection(con);
        return list;
    }
}
