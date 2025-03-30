package BUS;

import DAO.CustomerDAO;
import DTO.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerBUS {
    private static CustomerBUS instance;

    public CustomerBUS() {}
    public static CustomerBUS getInstance() {
        if (instance == null) instance = new CustomerBUS();
        return instance;
    }

    public List<CustomerDTO>getAllList(){
        return CustomerDAO.getInstance().getAllList();
    }

}
