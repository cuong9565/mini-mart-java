package BUS;

import DAO.DetailProductDAO;
import DTO.DetailProductDTO;

import java.util.List;

public class DetailProductBUS {
    private static DetailProductBUS instance = null;

    private DetailProductBUS() {}
    public static DetailProductBUS getInstance() {
        if (instance == null) instance = new DetailProductBUS();
        return instance;
    }

    public List<DetailProductDTO> getList() {
        return DetailProductDAO.getInstance().getList();
    }

    public void add(DetailProductDTO dp) {
        try {
            DetailProductDAO.getInstance().add(dp);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public void delete(DetailProductDTO dp) {
        try {
            DetailProductDAO.getInstance().delete(dp);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
