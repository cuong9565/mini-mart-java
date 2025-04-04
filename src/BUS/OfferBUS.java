package BUS;

import DAO.OfferDAO;
import DTO.OfferDTO;
import DTO.OfferProductDTO;

import java.util.ArrayList;
import java.util.List;

public class OfferBUS {
    private static OfferBUS instance = null;
    private static List<OfferDTO>list = null;
    private static String error = null;
    private static int numLine = 0;

    public OfferBUS() {}
    public static OfferBUS getInstance() {
        if (instance == null) instance = new OfferBUS();
        return instance;
    }

    public List<OfferDTO> getList() {
        list = OfferDAO.getInstance().getList();
        return list;
    }

    public List<OfferDTO> getListByOfferProduct(OfferProductDTO offerProduct) {
        List<OfferDTO>ls = new ArrayList<>();
        if(offerProduct.getId()==0)
            ls.add(new OfferDTO(0,null,null));
        else {
            for(OfferProductDTO op: OfferProductBUS.getInstance().getList())
                if(op.getDiscount()==offerProduct.getDiscount())
                    ls.add(op.getOffer());
        }
        return ls;
    }

    public boolean add(OfferDTO offer) {
        try {
            OfferDAO.getInstance().add(offer);
        }
        catch(Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean update(OfferDTO offer) {
        try {
            OfferDAO.getInstance().update(offer);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean delete(OfferDTO offer) {
        try {
            OfferDAO.getInstance().delete(offer);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public String getError() {return error;}
    public int getNumLine() {return numLine;}
}
