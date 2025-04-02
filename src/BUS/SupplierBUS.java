package BUS;

import DAO.SupplierDAO;
import DTO.SupplierDTO;

import java.util.ArrayList;
import java.util.List;

public class SupplierBUS {
    private static SupplierBUS instance = null;
    private static String error = null;
    private static int numLine = 0;
    private static List<SupplierDTO>supplierList;

    public SupplierBUS() {}
    public static SupplierBUS getInstance() {
        if (instance == null) instance = new SupplierBUS();
        return instance;
    }

    public List<SupplierDTO> getListSupplier() {
        supplierList = SupplierDAO.getInstance().getListSupplier();
        return supplierList;
    }

    public SupplierDTO getSupplierByRow(int row) {
        return supplierList.get(row);
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

    public boolean addSuppliers(List<SupplierDTO>listSupplier) {
        if(listSupplier == null || listSupplier.isEmpty()) {
            error = "Không lấy được dữ liệu!!!";
            return false;
        }

        try{
            numLine = SupplierDAO.getInstance().addSuppliers(listSupplier);
        }
        catch (Exception e){
            error = e.getMessage();
            return false;
        }
        return true;
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
    public int getNumLine(){return numLine;}
    public List<SupplierDTO>getSupplierList(){return supplierList;}
    public List<SupplierDTO>getSupplierListBy(int col, String txt){
        List<SupplierDTO>list = new ArrayList<>();

        switch (col){
            case 0:
                for(SupplierDTO supplier : supplierList)
                    if(supplier.getName().equals(txt))
                        list.add(supplier);
                break;
            case 1:
                for(SupplierDTO supplier : supplierList)
                    if(String.valueOf(supplier.getId()).equals(txt))
                        list.add(supplier);
                break;
            case 2:
                for(SupplierDTO supplier : supplierList)
                    if(supplier.getPhone().equals(txt))
                        list.add(supplier);
                break;
            case 3:
                for(SupplierDTO supplier : supplierList)
                    if(supplier.getAddress().equals(txt))
                        list.add(supplier);
                break;
            case 4:
                for(SupplierDTO supplier : supplierList)
                    if(supplier.getEmail().equals(txt))
                        list.add(supplier);
                break;
        }

        return list;
    }
}
