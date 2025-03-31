package BUS;

import DAO.CustomerDAO;
import DTO.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBUS {
    private static CustomerBUS instance = null;
    private static String error = null;
    private static int numLine = 0;

    public CustomerBUS() {}
    public static CustomerBUS getInstance() {
        if (instance == null) instance = new CustomerBUS();
        return instance;
    }

    public List<CustomerDTO>getAllList(){
        return CustomerDAO.getInstance().getAllList();
    }

    public List<CustomerDTO>getSearch(String whr, String str){
        return CustomerDAO.getInstance().getSearch(whr, str);
    }

    public boolean add(CustomerDTO customer) {
        if(customer.getPhone().isEmpty() || customer.getLastName().isEmpty() || customer.getFirstName().isEmpty() || customer.getAddress().isEmpty()) {
            error = "Dữ liệu không được để trống!!!";
            return false;
        }
        if(!customer.getPhone().matches("^0[0-9]{8,10}$")){
            error = "Số điện thoại định dạng không hợp lệ!!!";
            return false;
        }
        try{
            CustomerDAO.getInstance().add(customer);
        }
        catch (Exception e) {
            error = "Lỗi SQL: " + e.getMessage();
            return false;
        }
        return true;
    }

    public boolean adds(List<CustomerDTO> customers) {
        if(customers.isEmpty() || customers==null) {
            error = "Dữ liệu không hợp lệ";
            return false;
        }
        try{
            numLine = CustomerDAO.getInstance().adds(customers);
        }
        catch (Exception e) {
            error = "Lỗi " + e.getMessage();
            return false;
        }
        return true;
    }

    public boolean update(CustomerDTO customer) {
        if(customer.getPhone().isEmpty() || customer.getLastName().isEmpty() || customer.getFirstName().isEmpty() || customer.getAddress().isEmpty()) {
            error = "Dữ liệu không được để trống!!!";
            return false;
        }
        if(!customer.getPhone().matches("^0[0-9]{8,10}$")){
            error = "Số điện thoại định dạng không hợp lệ!!!";
            return false;
        }
        try {
            CustomerDAO.getInstance().update(customer);
        }
        catch (Exception e) {
            error = "Lỗi SQL: " + e.getMessage();
            return false;
        }
        return true;
    }

    public boolean delete(CustomerDTO customer) {
        try{
            CustomerDAO.getInstance().delete(customer);
        }catch (Exception e) {
            error = e.getMessage();
        }
        return true;
    }

    public String getError(){return error;}
    public int getNumLine(){return numLine;}
}
