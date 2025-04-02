package BUS;

import DAO.TypeProductDAO;
import DTO.TypeProductDTO;

import java.util.List;

public class TypeProductBUS {
    private static TypeProductBUS instance = null;
    private static List<TypeProductDTO>list = null;
    private static String error = null;

    public TypeProductBUS() {}
    public static TypeProductBUS getInstance() {
        if (instance == null) instance = new TypeProductBUS();
        return instance;
    }

    public List<TypeProductDTO> getList(){
        list = TypeProductDAO.getInstance().getList();
        return list;
    }

    public boolean add(TypeProductDTO product){
        if(product.getName().isEmpty()){
            error = "Không được để trống thông tin!!!";
            return false;
        }
        try{
            TypeProductDAO.getInstance().add(product);
        }
        catch (Exception e){
            error = "Lỗi: " + e.getMessage();
            return false;
        }
        return true;
    }

    public boolean edit(TypeProductDTO product){
        if(product.getName().isEmpty()){
            error = "Không được để trống thông tin!!!";
            return false;
        }
        try {
            TypeProductDAO.getInstance().edit(product);
        }
        catch (Exception e){
            error = "Lỗi: " + e.getMessage();
            return false;
        }
        return true;
    }

    public boolean delete(TypeProductDTO product){
        try {
            TypeProductDAO.getInstance().delete(product);
        }catch (Exception e){
            error = "Lỗi: " + e.getMessage();
            return false;
        }
        return true;
    }

    public String getError(){return error;}
}
