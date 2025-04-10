package BUS;

import DAO.OfferProductDAO;
import DAO.SupplierDAO;
import DTO.OfferDTO;
import DTO.OfferProductDTO;

import java.util.ArrayList;
import java.util.List;

public class OfferProductBUS {
    private static OfferProductBUS instance;
    private static List<OfferProductDTO>list = null;
    private static String error = null;

    public OfferProductBUS() {}
    public static OfferProductBUS getInstance() {
        if (instance == null) instance = new OfferProductBUS();
        return instance;
    }

    public List<OfferProductDTO> getList() {
        list = OfferProductDAO.getInstance().getList();
        return list;
    }

    public List<OfferProductDTO> getListBy(int col, String txt) {
        List<OfferProductDTO> ls = new ArrayList<>();
        for(OfferProductDTO o : list) switch (col){
            case 0: if(String.valueOf(o.getId()).contains(txt)) ls.add(o); break;
            case 1: if(o.getOffer().getFormattedDateStart().contains(txt)) ls.add(o); break;
            case 2: if(o.getOffer().getFormattedDateEnd().contains(txt)) ls.add(o); break;
            case 3: if((o.getDiscount() + "%").contains(txt)) ls.add(o); break;
        }
        return ls;
    }

    public OfferProductDTO getItemById(int id) {
        for(OfferProductDTO o : list)
            if (o.getId()==id)
                return o;
        return null;
    }

    public List<OfferProductDTO> getListDiscount() {
        List<OfferProductDTO> ls = new ArrayList<>();
        ls.add(new OfferProductDTO(0, new OfferDTO(), 0));

        for (OfferProductDTO o : OfferProductDAO.getInstance().getListDistinctDiscount())
            if(o.getDiscount() != ls.getLast().getDiscount())
                ls.add(o);
        return ls;
    }

    public int getIdBy(int discount, int idOffer) {
        for(OfferProductDTO o : getList())
            if (o.getDiscount() == discount && o.getOffer().getId() == idOffer)
                return o.getId();
        return 0;
    }

    public boolean add(OfferProductDTO o) {
        if(o.getOffer()==null) {
            error = "Thời gian giảm giá không được để trống!!!";
            return false;
        }
        try {
            OfferProductDAO.getInstance().add(o);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean update(OfferProductDTO o) {
        if(o.getOffer()==null) {
            error = "Thời gian giảm giá không được để trống!!!";
            return false;
        }
        try {
            OfferProductDAO.getInstance().update(o);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try{
            if(OfferProductDAO.getInstance().delete(id)){
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
