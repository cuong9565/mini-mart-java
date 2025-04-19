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

    public ImportDTO getImportNotPaidByIdStaff(int idStaff){
        return ImportDAO.getInstance().getImportNotPaidByIdStaff(idStaff);
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

    public void addImportByIdStaff(int idStaff){
        try {
            ImportDAO.getInstance().addImportByIdStaff(idStaff);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateIdSupplier(int idImport, int idSupplier){
        try {
            ImportDAO.getInstance().updateIdSupplier(idImport, idSupplier);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
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

    public void Pay(int idImport, double price){
        try{
            ImportDAO.getInstance().Pay(idImport, price);
            for(ImportInfoDTO importDTO : ImportInfoBUS.getInstance().loadByIdImport(idImport)){
                ProductDTO product = ProductDAO.getInstance().getItemById(importDTO.getIdProduct());
                ProductDAO.getInstance().updateQuantity(product.getId(), product.getQuantity() + importDTO.getQuantity());
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
