package BUS;

import DAO.CustomerDAO;
import DTO.CustomerDTO;
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

    public int getNumberCustomer() {
        return CustomerDAO.getInstance().getNumberCustomer();
    }

    public CustomerDTO getItemById(int id){
        return CustomerDAO.getInstance().getItemById(id);
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
        for (CustomerDTO customer: customerList) {
            switch (col) {
                case 0: if(String.valueOf(customer.getId()).contains(txt)) list.add(customer); break;
                case 1: if(customer.getPhone().contains(txt)) list.add(customer); break;
                case 2: if(customer.getLastName().contains(txt)) list.add(customer); break;
                case 3: if(customer.getFirstName().contains(txt)) list.add(customer); break;
                case 4: if(customer.getAddress().contains(txt)) list.add(customer); break;
                case 5: if(customer.getGender().contains(txt)) list.add(customer); break;
                case 6: if(customer.getState().contains(txt)) list.add(customer); break;
            }
        }
        return list;
    }
}
