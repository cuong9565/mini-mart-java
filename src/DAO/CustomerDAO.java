package DAO;

import DTO.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static CustomerDAO instance;
    public CustomerDAO() {}
    public static CustomerDAO getInstance() {
        if(instance == null) instance = new CustomerDAO();
        return instance;
    }
    public List<CustomerDTO> getAllList(){
        List<CustomerDTO> list = new ArrayList<>();
        Connection con = DataProvider.getInstance().getConnection();
        String sql = "select * from customer";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(new CustomerDTO(rs));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DataProvider.getInstance().CloseConnection(con);
        return list;
    }
}
