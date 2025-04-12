package BUS;

import DAO.OfferBillDAO;
import DAO.SupplierDAO;
import DTO.OfferDTO;
import DTO.OfferBillDTO;

import java.util.ArrayList;
import java.util.List;

public class OfferBillBUS {
    private static OfferBillBUS instance;
    private static List<OfferBillDTO>list = null;
    private static String error = null;

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

    public OfferBillDTO getItemById(int id) {
        for(OfferBillDTO o : getList())
            if (o.getId()==id)
                return o;
        return null;
    }

    public List<OfferBillDTO> getListDiscount() {
        List<OfferBillDTO> ls = new ArrayList<>();
        ls.add(new OfferBillDTO(0, new OfferDTO(), 0));
        for (OfferBillDTO o : getList())
            if(o.getDiscount() != ls.getLast().getDiscount())
                ls.add(o);
        return ls;
    }

    public int getIdBy(int discount, int idOffer) {
        for(OfferBillDTO o : getList())
            if (o.getDiscount() == discount && o.getOffer().getId() == idOffer)
                return o.getId();
        return 0;
    }

    public boolean add(OfferBillDTO o) {
        if(o.getOffer()==null) {
            error = "Thời gian giảm giá không được để trống!!!";
            return false;
        }
        try {
            OfferBillDAO.getInstance().add(o);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean update(OfferBillDTO o) {
        if(o.getOffer()==null) {
            error = "Thời gian giảm giá không được để trống!!!";
            return false;
        }
        try {
            OfferBillDAO.getInstance().update(o);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try{
            if(OfferBillDAO.getInstance().delete(id)){
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
