package BUS;

import DAO.CustomerDAO;
import DTO.CustomerDTO;
import DTO.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBUS {
    private static CustomerBUS instance = null;
    private static String error = null;
    private static int numLine = 0;
    private static List<CustomerDTO>customerList;

    public CustomerBUS() {}
    public static CustomerBUS getInstance() {
        if (instance == null) instance = new CustomerBUS();
        return instance;
    }

    public List<CustomerDTO>getAllList(){
        customerList = CustomerDAO.getInstance().getAllList();
        return customerList;
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
    public List<CustomerDTO>getSupplierListBy(int col, String txt){
        List<CustomerDTO>list = new ArrayList<>();

        switch (col){
            case 0:
                for (CustomerDTO customerDTO : customerList)
                    if (customerDTO.getFirstName().equals(txt))
                        list.add(customerDTO);
                break;
            case 1:
                for (CustomerDTO customerDTO : customerList)
                    if(String.valueOf(customerDTO.getId()).equals(txt))
                        list.add(customerDTO);
                break;
            case 2:
                for (CustomerDTO customerDTO : customerList)
                    if (customerDTO.getPhone().equals(txt))
                        list.add(customerDTO);
                break;
            case 3:
                for (CustomerDTO customerDTO : customerList)
                    if (customerDTO.getLastName().equals(txt))
                        list.add(customerDTO);
                break;
            case 4:
                for (CustomerDTO customerDTO : customerList)
                    if (customerDTO.getAddress().equals(txt))
                        list.add(customerDTO);
                break;
            case 5:
                for (CustomerDTO customerDTO : customerList)
                    if (customerDTO.getGender().equals(txt))
                        list.add(customerDTO);
                break;
            case 6:
                for (CustomerDTO customerDTO : customerList)
                    if (customerDTO.getState().equals(txt))
                        list.add(customerDTO);
                break;
        }
        return list;
    }
}
