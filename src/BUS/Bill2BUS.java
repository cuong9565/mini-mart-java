package BUS;

import DAO.Bill2DAO;
import DTO.Bill2DTO;

import java.util.ArrayList;
import java.util.List;

public class Bill2BUS {
    private static Bill2BUS instance = null;
    private List<Bill2DTO> list;

    private Bill2BUS() {}
    public static Bill2BUS getInstance() {
        if (instance == null) instance = new Bill2BUS();
        return instance;
    }

    public List<Bill2DTO>load(){
        list = Bill2DAO.getInstance().load();
        return list;
    }

    public List<Bill2DTO> search(int col, String txt){
        List<Bill2DTO> ls = new ArrayList<>();
        for (Bill2DTO bill : list) switch (col) {
            case 0: if(String.valueOf(bill.getId()).contains(txt)) ls.add(bill); break;
            case 1: if((bill.getStaff().getLastName() + " " + bill.getStaff().getFirstName()).contains(txt)) ls.add(bill); break;
            case 2: if((bill.getOfferBill().getDiscount() + "%").contains(txt)) ls.add(bill); break;
            case 3: if((bill.getCustomer().getLastName() + " " + bill.getCustomer().getFirstName()).contains(txt)) ls.add(bill); break;
            case 4: if(bill.getDateCreate().toString().contains(txt)) ls.add(bill); break;
            case 5: if(String.format("%,.0fđ", bill.getPrice()).contains(txt)) ls.add(bill); break;
            case 6: if(bill.getState().contains(txt)) ls.add(bill); break;
        }
        return ls;
    }
}
