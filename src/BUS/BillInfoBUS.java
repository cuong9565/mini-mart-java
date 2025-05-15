package BUS;

import Components.MyDate;
import DAO.BillInfoDAO;
import DTO.BillInfoDTO;
import DTO.ProductDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillInfoBUS {
    private static BillInfoBUS instance = null;
    private List<BillInfoDTO> list;
    private String error = null;

    private BillInfoBUS() {}
    public static BillInfoBUS getInstance() {
        if (instance == null) instance = new BillInfoBUS();
        return instance;
    }

    public List<BillInfoDTO>loadByIdBill(int idBill){
        list = BillInfoDAO.getInstance().getList(idBill);
        return list;
    }

    public List<BillInfoDTO>Search(int col, String txt){
        List<BillInfoDTO>ls = new ArrayList<>();
        for(BillInfoDTO b : list) switch (col) {
            case 0: if(String.valueOf(b.getIdProduct()).contains(txt)) ls.add(b); break;
            case 1: if(b.getNameProduct().contains(txt)) ls.add(b); break;
            case 2: if(b.getFormatPrice().contains(txt)) ls.add(b); break;
            case 3: if(String.valueOf(b.getQuantity()).contains(txt)) ls.add(b); break;
            case 4: if((b.getDiscount() + "%").contains(txt)) ls.add(b); break;
            case 5: if(b.getUnit().contains(txt)) ls.add(b); break;
            case 6: if(b.getFormatTotal().contains(txt)) ls.add(b); break;
        }
        return ls;
    }

    public boolean addProduct(int idBill, int idProduct, int quantity){
        ProductDTO product = ProductBUS.getInstance().getItemById(idProduct);
        BillInfoDTO billInfoDTO = new BillInfoDTO();
        List<BillInfoDTO> lsBillInfo = BillInfoDAO.getInstance().getList(idBill);
        for(BillInfoDTO billInfo : lsBillInfo)
            if(billInfo.getIdProduct()==idProduct){
                billInfoDTO = billInfo;
                quantity += billInfo.getQuantity();
                break;
            }

        if(quantity > product.getQuantity()){
            error = "Số lượng nhập vượt quá số lượng sản phẩm trong kho";
            return false;
        }

        int discount = 0;
        if(product.toString().contains("%"))
//            System.out.println(MyDate.getCurrentDate());
        if (MyDate.getCurrentDate().bettween(product.getOfferProduct().getOffer().getDateStart(),product.getOfferProduct().getOffer().getDateEnd())){
            discount = product.getOfferProduct().getOffer().getValue();
        }
//        System.out.println(product.getOfferProduct().getOffer().getValue());
//        System.out.println(product.getOfferProduct().getOffer().getDateStart());
//        System.out.println(discount);
        double total = product.getPrice() * (100 - discount) / 100 * quantity;
        if(billInfoDTO.getId() != 0){ // Nếu có thì cập nhật lại số lượng
            billInfoDTO.setQuantity(quantity);
            billInfoDTO.setTotal(total);
            billInfoDTO.setDiscount(discount);
            BillInfoDAO.getInstance().update(billInfoDTO);
        }
        else { // Nếu không có thì thêm mới 1 billInfo
            billInfoDTO = new BillInfoDTO(-1, idBill, idProduct, quantity, discount, product.getPrice(), total, product.getName(), product.getUnit());
            BillInfoDAO.getInstance().insert(billInfoDTO);
        }

        return true;
    }

    public boolean fixQuantityProduct(int idBill, int idProduct, int quantity){
        ProductDTO product = ProductBUS.getInstance().getItemById(idProduct);
        BillInfoDTO billInfoDTO = new BillInfoDTO();
        List<BillInfoDTO> lsBillInfo = BillInfoDAO.getInstance().getList(idBill);
        for(BillInfoDTO billInfo : lsBillInfo)
            if(billInfo.getIdProduct()==idProduct){
                billInfoDTO = billInfo;
                break;
            }

        if(quantity > product.getQuantity()){
            error = "Số lượng nhập vượt quá số lượng sản phẩm trong kho";
            return false;
        }

        int discount = 0;
        if(product.toString().contains("%")) discount = product.getOfferProduct().getDiscount();
        double total = product.getPrice() * (100 - discount) / 100 * quantity;

        billInfoDTO.setQuantity(quantity);
        billInfoDTO.setDiscount(discount);
        billInfoDTO.setTotal(total);
        BillInfoDAO.getInstance().update(billInfoDTO);

        return true;
    }

    public boolean deleteProduct(int idBill, int idProduct){
        try {
            BillInfoDAO.getInstance().delete(idBill, idProduct);
        }
        catch(Exception e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public String getError(){return error;}
}
