package BUS;

import DAO.BillDAO;
import DAO.BillInfoDAO;
import DAO.ProductDAO;
import DTO.BillDTO;
import DTO.BillInfoDTO;
import DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class BillBUS {
    private static BillBUS instance = null;
    private List<BillDTO> list;
    private String error = null;
    private BillBUS() {}

    public static BillBUS getInstance() {
        if (instance == null) instance = new BillBUS();
        return instance;
    }

    public BillDTO getBillNotPaid(int idStaff){
        BillDTO billDTO = new BillDTO(0);

        try {billDTO = BillDAO.getInstance().getBillNotPaid(idStaff);}
        catch (Exception e) {error = e.getMessage();}

        return billDTO;
    }

    public List<BillDTO> load(){
        list = BillDAO.getInstance().load();
        return list;
    }

    public void addBill(int idStaff){
        if(getBillNotPaid(idStaff).getId()!=0) return; // Đã có Bill trong danh sách

        try {BillDAO.getInstance().addBill(idStaff);}
        catch (Exception e) {error = e.getMessage();}
    }

    public boolean updateIdCustomer(int idBill, int idCustomer){
        try {
            BillDAO.getInstance().updateIdCustomer(idBill, idCustomer);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean updateIdOfferBill(int idBill, int idOfferBill){
        try {
            BillDAO.getInstance().updateIdOfferBill(idBill, idOfferBill);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean Pay(int idBill, double price){
        try{
            BillDAO.getInstance().Pay(idBill, price);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        try{
            for(BillInfoDTO billInfoS : BillInfoBUS.getInstance().loadByIdBill(idBill)){
                ProductDTO product = ProductDAO.getInstance().getItemById(billInfoS.getIdProduct());
                ProductDAO.getInstance().updateQuantity(product.getId(), product.getQuantity() - billInfoS.getQuantity());
            }
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean Cancel(int idBill){
        try{
            BillInfoDAO.getInstance().deleteByIdBill(idBill);
            BillDAO.getInstance().delete(idBill);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public String getError(){return error;}
}
