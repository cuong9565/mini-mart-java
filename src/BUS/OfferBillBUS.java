package BUS;

import DAO.OfferBillDAO;
import DAO.OfferDAO;
import DTO.OfferBillDTO;
import DTO.OfferDTO;

import java.util.ArrayList;
import java.util.List;

public class OfferBillBUS {
    private static OfferBillBUS instance;
    private static List<OfferBillDTO> list = null;
    private static String error = null;
    private static int numLine = 0;

    public OfferBillBUS() {}

    public static OfferBillBUS getInstance() {
        if (instance == null) instance = new OfferBillBUS();
        return instance;
    }

    public List<OfferBillDTO> getList() {
        list = OfferBillDAO.getInstance().getList();
        return list;
    }

    public boolean addOfferBill(OfferBillDTO offer) {
        try {
            OfferBillDAO.getInstance().addBill(offer);
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }
    public String getError() {
        return error;
    }

    public int getNumLine() {
        return numLine;
    }

    public boolean delete(OfferBillDTO offer) {
        try {
            if (OfferBillDAO.getInstance().delete(offer)) {
                return true;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return false;
    }

    public OfferBillDTO getById(int id) {
        for (OfferBillDTO of : list)
            if (of.getId() == id)
                return of;
        return null;
    }

    public boolean updateOfferBill(OfferBillDTO updated) {
        try {
            OfferBillDAO.getInstance().update(updated);
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;

    }

}
