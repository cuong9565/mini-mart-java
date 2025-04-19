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


    public void addProduct(int idImport, ProductDTO product, int quantity){
        ImportInfoDTO importInfoDTO = new ImportInfoDTO();
        for(ImportInfoDTO importInfo : ImportInfoDAO.getInstance().loadByIdImport(idImport))
            if(importInfo.getIdProduct()==product.getId()){
                importInfoDTO = importInfo;
                quantity += importInfo.getQuantity();
                break;
            }

        double total = product.getPrice() * quantity;
        if(importInfoDTO.getId() != 0){ // Nếu có thì cập nhật lại số lượng
            importInfoDTO.setQuantity(quantity);
            importInfoDTO.setTotal(total);
            ImportInfoDAO.getInstance().update(importInfoDTO);
        }
        else { // Nếu không có thì thêm mới 1 importInfo
            importInfoDTO = new ImportInfoDTO(-1, idImport, product.getId(), quantity, product.getPrice(), total, product.getName(), product.getUnit());
            ImportInfoDAO.getInstance().insert(importInfoDTO);
        }
    }

    public boolean updateProduct(int idImport, ProductDTO product, int quantity){
        ImportInfoDTO importInfoDTO = new ImportInfoDTO();
        for(ImportInfoDTO importInfo : ImportInfoBUS.getInstance().loadByIdImport(idImport))
            if(importInfo.getIdProduct()==product.getId()){
                importInfoDTO = importInfo;
                break;
            }

        double total = product.getPrice() * quantity;
        importInfoDTO.setQuantity(quantity);
        importInfoDTO.setTotal(total);
        ImportInfoDAO.getInstance().update(importInfoDTO);
        return true;
    }

    public void deleteProduct(int idImport, int idProduct){
        try {
            ImportInfoDAO.getInstance().delete(idImport, idProduct);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
