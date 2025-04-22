package BUS;

import DAO.SupplierDAO;
import DTO.SupplierDTO;

import java.util.ArrayList;
import java.util.List;

public class SupplierBUS {
    private static SupplierBUS instance = null;
    private static List<SupplierDTO>supplierList;

    public SupplierBUS() {}
    public static SupplierBUS getInstance() {
        if (instance == null) instance = new SupplierBUS();
        return instance;
    }

    // List
    public List<SupplierDTO> load() {
        supplierList = SupplierDAO.getInstance().load();
        return supplierList;
    }
    public List<SupplierDTO>getSupplierListBy(int col, String txt){
        List<SupplierDTO>list = new ArrayList<>();
        for (SupplierDTO supplier : supplierList) {
            switch (col){
                case 0: if(String.valueOf(supplier.getId()).contains(txt)) list.add(supplier); break;
                case 1: if(supplier.getName().contains(txt)) list.add(supplier); break;
                case 2: if(supplier.getPhone().contains(txt)) list.add(supplier); break;
                case 3: if(supplier.getAddress().contains(txt)) list.add(supplier); break;
                case 4: if(supplier.getEmail().contains(txt)) list.add(supplier); break;
            }
        }
        return list;
    }

    // Item
    public SupplierDTO getSupplierById(int id) {
        return SupplierDAO.getInstance().getSupplierById(id);
    }

    // check
    public boolean checkSamePhoneSupplier(String phone){
        return SupplierDAO.getInstance().checkSamePhoneSupplier(phone);
    }

    // add
    public void addProvider(SupplierDTO supplier) {
        try{
            if(supplier.getName().isEmpty() || supplier.getPhone().isEmpty() || supplier.getAddress().isEmpty() || supplier.getEmail().isEmpty())
                throw new RuntimeException("Không được để trống thông tin!");

            if(!supplier.getPhone().matches("^0[0-9]{8,10}$"))
                throw new RuntimeException("Số điện thoại không hợp lệ!");

            if(checkSamePhoneSupplier(supplier.getPhone()))
                throw new RuntimeException(String.format("Số điện thoại %s đã tồn tại!", supplier.getPhone()));

            SupplierDAO.getInstance().addSupplier(supplier);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    // update
    public void editSupplier(SupplierDTO supplier) {
        try{
            if(supplier.getName().isEmpty() || supplier.getPhone().isEmpty() || supplier.getAddress().isEmpty() || supplier.getEmail().isEmpty())
                throw new RuntimeException("Không được để trống thông tin!");

            if(!supplier.getPhone().matches("^0[0-9]{8,10}$"))
                throw new RuntimeException("Số điện thoại không hợp lệ!");

            SupplierDTO currSupplier = SupplierDAO.getInstance().getSupplierById(supplier.getId());
            if(checkSamePhoneSupplier(supplier.getPhone()) && !currSupplier.getPhone().equals(supplier.getPhone()))
                throw new RuntimeException(String.format("Số điện thoại %s đã tồn tại!", supplier.getPhone()));

            SupplierDAO.getInstance().editSupplier(supplier);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    // delete
    public void deleteSupplier(SupplierDTO supplier) {
        try{
            SupplierDAO.getInstance().deleteSupplier(supplier);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
