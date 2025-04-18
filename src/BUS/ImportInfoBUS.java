package BUS;

import DAO.ImportInfoDAO;
import DTO.ImportInfoDTO;
import DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ImportInfoBUS {
    private static ImportInfoBUS instance = null;
    private List<ImportInfoDTO> list;

    private ImportInfoBUS() {}
    public static ImportInfoBUS getInstance() {
        if (instance == null) instance = new ImportInfoBUS();
        return instance;
    }

    public List<ImportInfoDTO>loadByIdImport(int idImport){
        list = ImportInfoDAO.getInstance().loadByIdImport(idImport);
        return list;
    }

//    public boolean addProduct(int idImport, int idProduct, int quantity){
//        ProductDTO product = ProductBUS.getInstance().getItemById(idProduct);
//
//        ImportInfoDTO billInfoDTO = new ImportInfoDTO();
//        List<ImportInfoDTO> lsImportInfo = ImportInfoDAO.getInstance().getList(idImport);
//        for(ImportInfoDTO billInfo : lsImportInfo)
//            if(billInfo.getIdProduct()==idProduct){
//                billInfoDTO = billInfo;
//                quantity += billInfo.getQuantity();
//                break;
//            }
//
//        if(quantity > product.getQuantity()){
//            error = "Số lượng nhập vượt quá số lượng sản phẩm trong kho";
//            return false;
//        }
//
//        int discount = 0;
//        if(product.toString().contains("%")) discount = product.getOfferProduct().getDiscount();
//        double total = product.getPrice() * (100 - discount) / 100 * quantity;
//        if(billInfoDTO.getId() != 0){ // Nếu có thì cập nhật lại số lượng
//            billInfoDTO.setQuantity(quantity);
//            billInfoDTO.setTotal(total);
//            billInfoDTO.setDiscount(discount);
//            ImportInfoDAO.getInstance().update(billInfoDTO);
//        }
//        else { // Nếu không có thì thêm mới 1 billInfo
//            billInfoDTO = new ImportInfoDTO(-1, idImport, idProduct, quantity, discount, product.getPrice(), total, product.getName(), product.getUnit());
//            ImportInfoDAO.getInstance().insert(billInfoDTO);
//        }
//
//        return true;
//    }
//
//    public boolean fixQuantityProduct(int idImport, int idProduct, int quantity){
//        ProductDTO product = ProductBUS.getInstance().getItemById(idProduct);
//
//        ImportInfoDTO billInfoDTO = new ImportInfoDTO();
//        List<ImportInfoDTO> lsImportInfo = ImportInfoDAO.getInstance().getList(idImport);
//        for(ImportInfoDTO billInfo : lsImportInfo)
//            if(billInfo.getIdProduct()==idProduct){
//                billInfoDTO = billInfo;
//                break;
//            }
//
//        if(quantity > product.getQuantity()){
//            error = "Số lượng nhập vượt quá số lượng sản phẩm trong kho";
//            return false;
//        }
//
//        int discount = 0;
//        if(product.toString().contains("%")) discount = product.getOfferProduct().getDiscount();
//        double total = product.getPrice() * (100 - discount) / 100 * quantity;
//
//        billInfoDTO.setQuantity(quantity);
//        billInfoDTO.setDiscount(discount);
//        billInfoDTO.setTotal(total);
//        ImportInfoDAO.getInstance().update(billInfoDTO);
//
//        return true;
//    }
//
//    public boolean deleteProduct(int idImport, int idProduct){
//        try {
//            ImportInfoDAO.getInstance().delete(idImport, idProduct);
//        }
//        catch(Exception e){
//            error = e.getMessage();
//            return false;
//        }
//        return true;
//    }
}
