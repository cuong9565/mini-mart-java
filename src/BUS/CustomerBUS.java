package BUS;

import DAO.CustomerDAO;
import DTO.CustomerDTO;
import java.util.ArrayList;
import java.util.List;

public class CustomerBUS {
    private static CustomerBUS instance = null;
    private static List<CustomerDTO>customerList = null;

    public CustomerBUS() {}
    public static CustomerBUS getInstance() {
        if (instance == null) instance = new CustomerBUS();
        return instance;
    }

    // Check
    public boolean isSamePhone(String phone){
        return CustomerDAO.getInstance().isSamePhone(phone);
    }
    public int getNumberCustomer() {
        return CustomerDAO.getInstance().getNumberCustomer();
    }

    // Item
    public CustomerDTO getItemById(int id){
        return CustomerDAO.getInstance().getItemById(id);
    }

    // List
    public List<CustomerDTO>getAllList(){
        customerList = CustomerDAO.getInstance().getAllList();
        return customerList;
    }
    public List<CustomerDTO>getSupplierListBy(int col, String txt){ // Search
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

    // Insert
    public void add(CustomerDTO customer) {
        try{
            if(customer.getPhone().isEmpty() || customer.getLastName().isEmpty() || customer.getFirstName().isEmpty() || customer.getAddress().isEmpty())
                throw new Exception("Dữ liệu không được để trống!!!");

            if(!customer.getPhone().matches("^0[0-9]{8,10}$"))
                throw new Exception("Số điện thoại định dạng không hợp lệ!!!");

            if(isSamePhone(customer.getPhone()))
                throw new Exception(String.format("Số điện thoại %s đã tồn tại!!!", customer.getPhone()));

            CustomerDAO.getInstance().add(customer);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Update
    public void update(CustomerDTO newCustomer) {
        try {
            if(newCustomer.getPhone().isEmpty() || newCustomer.getLastName().isEmpty() || newCustomer.getFirstName().isEmpty() || newCustomer.getAddress().isEmpty())
                throw new Exception("Dữ liệu không được để trống!!!");

            if(!newCustomer.getPhone().matches("^0[0-9]{8,10}$"))
                throw new Exception("Số điện thoại định dạng không hợp lệ!!!");

            CustomerDTO currCustomer = CustomerDAO.getInstance().getItemById(newCustomer.getId());
            if(isSamePhone(newCustomer.getPhone()) && !currCustomer.getPhone().equals(newCustomer.getPhone()))
                throw new Exception(String.format("Số điện thoại %s đã tồn tại!!!", newCustomer.getPhone()));

            CustomerDAO.getInstance().update(newCustomer);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Delete
    public void delete(CustomerDTO customer) {
        try{
            CustomerDAO.getInstance().delete(customer);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
