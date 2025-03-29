package BUS;

import DAO.SupplierDAO;
import DTO.SupplierDTO;

import java.util.List;

public class SupplierBUS {
    private static SupplierBUS instance;

    public SupplierBUS() {}
    public static SupplierBUS getInstance() {
        if (instance == null) instance = new SupplierBUS();
        return instance;
    }

    public List<SupplierDTO> getListSupplier() {
        return SupplierDAO.getInstance().getListSupplier();
    }
}
