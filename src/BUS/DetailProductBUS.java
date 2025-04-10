package BUS;

import DAO.DetailProductDAO;
import DTO.DetailProductDTO;

import java.util.ArrayList;
import java.util.List;

public class DetailProductBUS {
    private static DetailProductBUS instance = null;
    private static List<DetailProductDTO> list = null;
    private static String error = null;
    private static int numLine = 0;

    private DetailProductBUS() {}
    public static DetailProductBUS getInstance() {
        if (instance == null) instance = new DetailProductBUS();
        return instance;
    }
    public List<DetailProductDTO> getList() {
        list = DetailProductDAO.getInstance().getList();
        return list;
    }
    /*
    public int add(DetailProductDTO dp) {
        try {DetailProductDAO.getInstance().add(dp);}
        catch (Exception e) {
            error = e.getMessage();
            return 0;
        }
        getList();
        return list.getList().getId();
    }  */
    public boolean update(DetailProductDTO dp) {
        try {DetailProductDAO.getInstance().update(dp);}
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }
    public boolean delete(DetailProductDTO dp) {
        try {DetailProductDAO.getInstance().delete(dp);}
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public String getError() {return error;}
    public int getNumLine() {return numLine;}
}
