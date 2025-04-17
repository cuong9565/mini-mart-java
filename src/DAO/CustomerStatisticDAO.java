package DAO;

import DTO.CustomerStatisticDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerStatisticDAO {
    private static CustomerStatisticDAO instance = null;

    private CustomerStatisticDAO() {}
    public static CustomerStatisticDAO getInstance() {
        if (instance == null) instance = new CustomerStatisticDAO();
        return instance;
    }

    public List<CustomerStatisticDTO> load(){
        List<CustomerStatisticDTO> ls = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql =
                "select * " +
                "from customer " +
                "left join bill on customer.id = bill.idCustomer " +
                "order by customer.id";
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) ls.add(new CustomerStatisticDTO(rs));
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        DataProvider.getInstance().CloseConnection(con);
        return ls;
    }
}
