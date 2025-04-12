package BUS;
import DAO.OfferDAO;
import DTO.OfferDTO;
import DTO.OfferProductDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferBUS {
    private static OfferBUS instance = null;
    public static List<OfferDTO> list = null;
    private static String error = null;

    public OfferBUS() {}

    public static OfferBUS getInstance() {
        if (instance == null) instance = new OfferBUS();
        return instance;
    }

    public List<OfferDTO> getList() {
        list = OfferDAO.getInstance().getList();
        return list;
    }

    public List<OfferDTO> getListBy(int col, String txt) {
        List<OfferDTO> ls = new ArrayList<>();
        for (OfferDTO o : list) {
            switch (col) {
                case 0: if(String.valueOf(o.getId()).contains(txt)) ls.add(o); break;
                case 1: if(o.getDateStart().toString().contains(txt)) ls.add(o); break;
                case 2: if(o.getDateStart().toString().contains(txt)) ls.add(o); break;
            }
        }
        return ls;
    }

    public OfferDTO getItemByDate(String dateStart, String dateEnd) {
        for (OfferDTO o : getList())
            if(o.getDateStart().toString().contains(dateStart) && o.getDateEnd().toString().contains(dateEnd)) return o;
        return null;
    }

    public List<OfferDTO> getListByOfferProduct(OfferProductDTO offerProduct) {
        List<OfferDTO> ls = new ArrayList<>();
        if (offerProduct.getId() == 0) ls.add(new OfferDTO(0));
        else {
            for (OfferProductDTO op : OfferProductBUS.getInstance().getList())
                if (op.getDiscount() == offerProduct.getDiscount())
                    ls.add(op.getOffer());
        }
        return ls;
    }

    public boolean add(OfferDTO offer) {
        if(offer.getDateStart().compareTo(offer.getDateEnd())>0){
            error = "Ngày bắt đầu phải trước ngày kết thúc!!!";
            return false;
        }
        try {
            OfferDAO.getInstance().add(offer);
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean update(OfferDTO offer) {
        if(offer.getDateStart().compareTo(offer.getDateEnd())>0){
            error = "Ngày bắt đầu phải trước ngày kết thúc!!!";
            return false;
        }
        try {
            OfferDAO.getInstance().update(offer);
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean delete(OfferDTO offer) {
        try {
            OfferDAO.getInstance().delete(offer);
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public String getError() {
        return error;
    }

    public OfferDTO getOfferById(int id) {
        for (OfferDTO of : list)
            if (of.getId() == id)
                return of;
        return null;
    }

}
