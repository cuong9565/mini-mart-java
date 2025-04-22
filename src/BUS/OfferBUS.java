package BUS;
import Components.MyDate;
import DAO.OfferDAO;
import DTO.OfferDTO;
import DTO.OfferProductDTO;
import java.util.ArrayList;
import java.util.List;

public class OfferBUS {
    private static OfferBUS instance = null;
    public static List<OfferDTO> list = null;

    public OfferBUS() {}
    public static OfferBUS getInstance() {
        if (instance == null) instance = new OfferBUS();
        return instance;
    }

    // List
    public List<OfferDTO> getList() {
        list = OfferDAO.getInstance().getList();
        return list;
    }

    // Search
    public List<OfferDTO> getListBy(int col, String txt) {
        List<OfferDTO> ls = new ArrayList<>();
        for (OfferDTO o : list) {
            switch (col) {
                case 0: if(String.valueOf(o.getId()).contains(txt)) ls.add(o); break;
                case 1: if(o.getDateStart().toString().contains(txt)) ls.add(o); break;
                case 2: if(o.getDateEnd().toString().contains(txt)) ls.add(o); break;
            }
        }
        return ls;
    }

    // Item
    public OfferDTO getOfferById(int id) {
        return OfferDAO.getInstance().getOfferById(id);
    }

    public OfferDTO getItemByDate(String dateStart, String dateEnd) {
        for (OfferDTO o : getList())
            if(o.getDateStart().toString().contains(dateStart) && o.getDateEnd().toString().contains(dateEnd)) return o;
        return null;
    }

    // Check
    public boolean isSameDay(MyDate l, MyDate r) {
        return OfferDAO.getInstance().isSameDay(l, r);
    }

    public List<OfferDTO> getListByOfferProduct(OfferProductDTO offerProduct) {
        List<OfferDTO> ls = new ArrayList<>();
        if (offerProduct.getId() == 0) ls.add(new OfferDTO());
        else {
            for (OfferProductDTO op : OfferProductBUS.getInstance().getList())
                if (op.getDiscount() == offerProduct.getDiscount())
                    ls.add(op.getOffer());
        }
        return ls;
    }

    // Insert
    public void add(OfferDTO offer) {
        try {
            if(offer.getDateStart().compareTo(offer.getDateEnd())>0)
                throw new RuntimeException("Ngày bắt đầu phải trước ngày kết thúc!!!");

            if(isSameDay(offer.getDateStart(), offer.getDateEnd()))
                throw new RuntimeException(String.format("Thời gian từ ngày %s đến ngày %s đã tồn tại!!!", offer.getDateStart(), offer.getDateEnd()));

            OfferDAO.getInstance().add(offer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Update
    public void update(OfferDTO offer) {
        try {
            if(offer.getDateStart().compareTo(offer.getDateEnd())>0)
                throw new RuntimeException("Ngày bắt đầu phải trước ngày kết thúc!!!");

            OfferDTO currOffer = OfferDAO.getInstance().getOfferById(offer.getId());
            if(isSameDay(offer.getDateStart(), offer.getDateEnd()) && (currOffer.getDateStart().compareTo(offer.getDateStart())!=0 || currOffer.getDateEnd().compareTo(offer.getDateEnd())!=0))
                throw new RuntimeException(String.format("Thời gian từ ngày %s đến ngày %s đã tồn tại!!!", offer.getDateStart(), offer.getDateEnd()));

            OfferDAO.getInstance().update(offer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Delete
    public void delete(int id) {
        try {
            OfferDAO.getInstance().delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
