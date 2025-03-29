package BUS;

import DAO.SupplierDAO;
import DTO.SupplierDTO;

import java.util.List;

public class SupplierBUS {
    private static SupplierBUS instance;
    private String error = null;

    public SupplierBUS() {}
    public static SupplierBUS getInstance() {
        if (instance == null) instance = new SupplierBUS();
        return instance;
    }

    public List<SupplierDTO> getListSupplier() {
        return SupplierDAO.getInstance().getListSupplier();
    }

    public List<SupplierDTO> getListSupplierBy(String whr, String str){
        return SupplierDAO.getInstance().getListSupplierBy(whr, str);
    }

    public boolean addProvider(SupplierDTO supplier) {
        if(supplier.getName().isEmpty() || supplier.getPhone().isEmpty() || supplier.getAddress().isEmpty() || supplier.getEmail().isEmpty()) {
            error = "Không được để trống thông tin!";
            return false;
        }
        if(!supplier.getPhone().matches("^0[0-9]{8,10}$")){
            error = "Số điện thoại không hợp lệ!";
            return false;
        }
        if(!supplier.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
            error = "Email không hợp lệ!";
            return false;
        }
        try{
            if(SupplierDAO.getInstance().addSupplier(supplier)){
                return true;
            }
        }catch (Exception e){
            error = e.getMessage();
            return false;
        }
        return false;
    }

    public boolean editSupplier(SupplierDTO supplier) {
        if(supplier.getName().isEmpty() || supplier.getPhone().isEmpty() || supplier.getAddress().isEmpty() || supplier.getEmail().isEmpty()) {
            error = "Không được để trống thông tin!";
            return false;
        }
        if(!supplier.getPhone().matches("^0[0-9]{8,10}$")){
            error = "Số điện thoại không hợp lệ!";
            return false;
        }
        if(!supplier.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
            error = "Email không hợp lệ!";
            return false;
        }
        try{
            if(SupplierDAO.getInstance().editSupplier(supplier)){
                return true;
            }
        }catch (Exception e){
            error = e.getMessage();
            return false;
        }
        return false;
    }

    public boolean deleteSupplier(SupplierDTO supplier) {
        try{
            if(SupplierDAO.getInstance().deleteSupplier(supplier)){
                return true;
            }
        }
        catch (Exception e){
            error = e.getMessage();
            return false;
        }
        return false;
    }

    public String getError(){return error;}
}
