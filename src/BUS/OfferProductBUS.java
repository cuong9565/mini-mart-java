package BUS;

import DAO.OfferProductDAO;
import DTO.OfferDTO;
import DTO.OfferProductDTO;

import java.util.ArrayList;
import java.util.List;

public class OfferProductBUS {
    private static OfferProductBUS instance;
    private static List<OfferProductDTO>list = null;
    private static String error = null;
    private static int numLine = 0;

    public OfferProductBUS() {}
    public static OfferProductBUS getInstance() {
        if (instance == null) instance = new OfferProductBUS();
        return instance;
    }

    public List<OfferProductDTO> getList() {
        list = OfferProductDAO.getInstance().getList();
        return list;
    }
    public List<OfferProductDTO> getListDiscount() {
        List<OfferProductDTO> ls = new ArrayList<>();
        ls.add(new OfferProductDTO(0, new OfferDTO(), 0));
        for (OfferProductDTO o : getList())
            if(o.getDiscount() != ls.getLast().getDiscount())
                ls.add(o);
        return ls;
    }
}
