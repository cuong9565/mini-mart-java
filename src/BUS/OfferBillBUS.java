package BUS;

import DAO.OfferBillDAO;
import DTO.OfferBillDTO;

import java.util.ArrayList;
import java.util.List;

public class OfferBillBUS {
    private static OfferBillBUS instance = null;
    private static List<OfferBillDTO>list = null;

    public OfferBillBUS() {}
    public static OfferBillBUS getInstance() {
        if (instance == null) instance = new OfferBillBUS();
        return instance;
    }

    public List<OfferBillDTO> getList() {
        list = OfferBillDAO.getInstance().getList();
        return list;
    }

    public List<OfferBillDTO> getListBy(int col, String txt) {
        List<OfferBillDTO> ls = new ArrayList<>();
        for(OfferBillDTO o : list) switch (col){
            case 0: if(String.valueOf(o.getId()).contains(txt)) ls.add(o); break;
            case 1: if(o.getOffer().getDateStart().toString().contains(txt)) ls.add(o); break;
            case 2: if(o.getOffer().getDateEnd().toString().contains(txt)) ls.add(o); break;
            case 3: if((o.getDiscount() + "%").contains(txt)) ls.add(o); break;
        }
        return ls;
    }

    // Item
    public OfferBillDTO getItemById(int id) {
        return OfferBillDAO.getInstance().getItemById(id);
    }

    // Check Same OfferBill
    public boolean isSameOfferBill(OfferBillDTO o){
        return OfferBillDAO.getInstance().isSameOfferBill(o);
    }

    // Add
    public void add(OfferBillDTO o) {
        try {
            if(o.getOffer().getId()==0)
                throw new Exception("Thời gian giảm giá không được để trống!!!");

            if(isSameOfferBill(o))
                throw new Exception(String.format("Thời gian từ %s đến %s với giảm giá %s đã tồn tại!!!", o.getOffer().getDateStart(), o.getOffer().getDateEnd(), o.getDiscount() + "%"));

            OfferBillDAO.getInstance().add(o);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Update
    public void update(OfferBillDTO o) {
        try {
            if(o.getOffer().getId()==0)
                throw new Exception("Thời gian giảm giá không được để trống!!!");

            OfferBillDTO currO = OfferBillDAO.getInstance().getItemById(o.getId());
            if(isSameOfferBill(o) && (currO.getOffer().getId()!=o.getOffer().getId() || currO.getDiscount()!=o.getDiscount()))
                throw new Exception(String.format("Thời gian từ %s đến %s với giảm giá %s đã tồn tại!!!", o.getOffer().getDateStart(), o.getOffer().getDateEnd(), o.getDiscount() + "%"));

            OfferBillDAO.getInstance().update(o);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Delete
    public void delete(int id) {
        try{
            OfferBillDAO.getInstance().delete(id);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
