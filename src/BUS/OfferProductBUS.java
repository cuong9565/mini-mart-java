package BUS;

import DAO.OfferProductDAO;
import DTO.OfferDTO;
import DTO.OfferProductDTO;

import java.util.ArrayList;
import java.util.List;

public class OfferProductBUS {
    private static OfferProductBUS instance = null;
    private static List<OfferProductDTO>list = null;

    public OfferProductBUS() {}
    public static OfferProductBUS getInstance() {
        if (instance == null) instance = new OfferProductBUS();
        return instance;
    }

    // List
    public List<OfferProductDTO> load() {
        list = OfferProductDAO.getInstance().getList();
        return list;
    }
    public List<OfferProductDTO> getListBy(int col, String txt) {
        List<OfferProductDTO> ls = new ArrayList<>();
        for(OfferProductDTO o : list) switch (col){
            case 0: if(String.valueOf(o.getId()).contains(txt)) ls.add(o); break;
            case 1: if(o.getOffer().getDateStart().toString().contains(txt)) ls.add(o); break;
            case 2: if(o.getOffer().getDateEnd().toString().contains(txt)) ls.add(o); break;
            case 3: if((o.getDiscount() + "%").contains(txt)) ls.add(o); break;
        }
        return ls;
    }
    public List<OfferProductDTO> getListDiscount() {
        List<OfferProductDTO> ls = new ArrayList<>();
        ls.add(new OfferProductDTO(0, new OfferDTO(), 0));

        for (OfferProductDTO o : OfferProductDAO.getInstance().getListDistinctDiscount())
            if(o.getDiscount() != ls.getLast().getDiscount())
                ls.add(o);
        return ls;
    }

    // Item
    public OfferProductDTO getItemById(int id) {
        return OfferProductDAO.getInstance().getItemById(id);
    }
    public int getIdBy(int discount, int idOffer) {
        for(OfferProductDTO o : load())
            if (o.getDiscount() == discount && o.getOffer().getId() == idOffer)
                return o.getId();
        return 0;
    }

    // Check Same OfferProduct
    public boolean isSameOfferProduct(OfferProductDTO o){
        return OfferProductDAO.getInstance().isSameOfferProduct(o);
    }

    // Add
    public void add(OfferProductDTO o) {
        try {
            if(o.getOffer().getId()==0)
                throw new Exception("Thời gian giảm giá không được để trống!!!");

            if(isSameOfferProduct(o))
                throw new Exception(String.format("Thời gian từ %s đến %s với giảm giá %s đã tồn tại!!!", o.getOffer().getDateStart(), o.getOffer().getDateEnd(), o.getDiscount() + "%"));

            OfferProductDAO.getInstance().add(o);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Update
    public void update(OfferProductDTO o) {
        try {
            if(o.getOffer().getId()==0)
                throw new Exception("Thời gian giảm giá không được để trống!!!");

            OfferProductDTO currO = OfferProductDAO.getInstance().getItemById(o.getId());
            if(isSameOfferProduct(o) && (currO.getOffer().getId()!=o.getOffer().getId() || currO.getDiscount()!=o.getDiscount()))
                throw new Exception(String.format("Thời gian từ %s đến %s với giảm giá %s đã tồn tại!!!", o.getOffer().getDateStart(), o.getOffer().getDateEnd(), o.getDiscount() + "%"));

            OfferProductDAO.getInstance().update(o);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Delete
    public void delete(int id) {
        try{
            OfferProductDAO.getInstance().delete(id);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
