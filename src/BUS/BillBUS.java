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
    private static List<BillDTO> list = null;
    private static String error = null;

    private BillBUS() {}
    public static BillBUS getInstance() {
        if (instance == null) instance = new BillBUS();
        return instance;
    }

    public List<BillDTO>load(){
        list = BillDAO.getInstance().load();
        return list;
    }

    public BillDTO getBillById(int id){
        for (BillDTO billDTO : load()) {
            if(billDTO.getId() == id) return billDTO;
        }
        return new BillDTO();
    }

    public List<BillDTO> search(int col, String txt){
        List<BillDTO> ls = new ArrayList<>();
        for (BillDTO bill : list) switch (col) {
            case 0: if(String.valueOf(bill.getId()).contains(txt)) ls.add(bill); break;
            case 1: if((bill.getStaff().getLastName() + " " + bill.getStaff().getFirstName()).contains(txt)) ls.add(bill); break;
            case 2: if((bill.getOfferBill().getDiscount() + "%").contains(txt)) ls.add(bill); break;
            case 3: if((bill.getCustomer().getLastName() + " " + bill.getCustomer().getFirstName()).contains(txt)) ls.add(bill); break;
            case 4: if(bill.getDateCreate().toString().contains(txt)) ls.add(bill); break;
            case 5: if(String.format("%,.0fđ", bill.getPrice()).contains(txt)) ls.add(bill); break;
            case 6: if(bill.getState().contains(txt)) ls.add(bill); break;
        }
        return ls;
    }

    public BillDTO getBillNotPaid(int idStaff){
        for (BillDTO bill : load())
            if(bill.getStaff().getId() == idStaff && bill.getState().equals("Chưa thanh toán"))
                return bill;
        return new BillDTO();
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

    public void delete(int id){
        try {
            BillInfoDAO.getInstance().deleteByIdBill(id);
            BillDAO.getInstance().delete(id);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
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

    public String getError() {return error;}
}
