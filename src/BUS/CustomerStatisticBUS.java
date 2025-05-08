package BUS;

import Components.MyDate;
import DAO.CustomerStatisticDAO;
import DTO.CustomerStatisticDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerStatisticBUS {
    private static CustomerStatisticBUS instance = null;

    private CustomerStatisticBUS() {}
    public static CustomerStatisticBUS getInstance() {
        if (instance == null) instance = new CustomerStatisticBUS();
        return instance;
    }

    public List<CustomerStatisticDTO>loadByDate(MyDate l, MyDate r) {
        CustomerStatisticDTO curr = new CustomerStatisticDTO();

        List<CustomerStatisticDTO> list = new ArrayList<>();
        for(CustomerStatisticDTO customer: CustomerStatisticDAO.getInstance().load()){
            if(curr.getId()!=customer.getId())
                list.add(new CustomerStatisticDTO(customer.getId(), customer.getLastName(), customer.getFirstName(), customer.getPhone(), customer.getDateCreate()));

            if(customer.getDateCreate().bettween(l, r))
                list.set(list.size()-1,
                        list.getLast().addQ(customer.getQ()));
        }

        return list;
    }
}
