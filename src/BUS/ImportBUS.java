package BUS;

import DAO.ImportDAO;
import DAO.ImportInfoDAO;
import DAO.ProductDAO;
import DTO.ImportDTO;
import DTO.ImportInfoDTO;
import DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ImportBUS {
    private static ImportBUS instance = null;
    private static List<ImportDTO> list = null;

    private ImportBUS() {}
    public static ImportBUS getInstance() {
        if (instance == null) instance = new ImportBUS();
        return instance;
    }

    public List<ImportDTO>load(){
        list = ImportDAO.getInstance().load();
        return list;
    }

    public ImportDTO getImportById(int id){
        return ImportDAO.getInstance().getImportById(id);
    }

    public List<ImportDTO> search(int col, String txt){
        List<ImportDTO> ls = new ArrayList<>();
        for (ImportDTO importDTO : list) switch (col) {
            case 0: if(String.valueOf(importDTO.getId()).contains(txt)) ls.add(importDTO); break;
            case 1: if(importDTO.getDateCreate().toString().contains(txt)) ls.add(importDTO); break;
            case 2: if(String.format("%,.0fđ", importDTO.getPrice()).contains(txt)) ls.add(importDTO); break;
            case 3: if(importDTO.getState().contains(txt)) ls.add(importDTO); break;
        }
        return ls;
    }

    public void delete(int id){
        try {
            ImportInfoDAO.getInstance().deleteByIdImport(id);
            ImportDAO.getInstance().delete(id);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

//    public ImportDTO getImportById(int id){
//        for (ImportDTO billDTO : load()) {
//            if(billDTO.getId() == id) return billDTO;
//        }
//        return new ImportDTO();
//    }
//
//
//    public ImportDTO getImportNotPaid(int idStaff){
//        for (ImportDTO bill : load())
//            if(bill.getStaff().getId() == idStaff && bill.getState().equals("Chưa thanh toán"))
//                return bill;
//        return new ImportDTO();
//    }
//
//    public void addImport(int idStaff){
//        if(getImportNotPaid(idStaff).getId()!=0) return; // Đã có Import trong danh sách
//
//        try {ImportDAO.getInstance().addImport(idStaff);}
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public void updateIdCustomer(int idImport, int idCustomer){
//        try {
//            ImportDAO.getInstance().updateIdCustomer(idImport, idCustomer);
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public void updateIdOfferImport(int idImport, int idOfferImport){
//        try {
//            ImportDAO.getInstance().updateIdOfferImport(idImport, idOfferImport);
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public void Cancel(int idImport){
//        try{
//            ImportInfoDAO.getInstance().deleteByIdImport(idImport);
//            ImportDAO.getInstance().delete(idImport);
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//
//    public void Pay(int idImport, double price){
//        try{
//            ImportDAO.getInstance().Pay(idImport, price);
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//
//        try{
//            for(ImportInfoDTO billInfoS : ImportInfoBUS.getInstance().loadByIdImport(idImport)){
//                ProductDTO product = ProductDAO.getInstance().getItemById(billInfoS.getIdProduct());
//                ProductDAO.getInstance().updateQuantity(product.getId(), product.getQuantity() - billInfoS.getQuantity());
//            }
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
}
