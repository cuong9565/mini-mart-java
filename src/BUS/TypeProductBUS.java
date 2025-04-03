package BUS;

import DAO.TypeProductDAO;
import DTO.TypeProductDTO;

import java.util.ArrayList;
import java.util.List;

public class TypeProductBUS {
    private static TypeProductBUS instance = null;
    private static List<TypeProductDTO>list = null;
    private static String error = null;
    private static int numLine = 0;

    public TypeProductBUS() {}
    public static TypeProductBUS getInstance() {
        if (instance == null) instance = new TypeProductBUS();
        return instance;
    }

    public TypeProductDTO getItemById(int id){
        for(TypeProductDTO item : list)
            if(item.getId() == id) return item;
        return null;
    }

    public List<TypeProductDTO> getList(){
        list = TypeProductDAO.getInstance().getList();
        return list;
    }

    public List<TypeProductDTO> getListBy(int col, String txt){
        List<TypeProductDTO>products = new ArrayList<>();
        switch (col){
            case 0:
                for (TypeProductDTO product: list)
                    if(product.getName().contains(txt))
                        products.add(product);
                break;
            case 1:
                for (TypeProductDTO product: list)
                    if(String.valueOf(product.getId()).contains(txt))
                        products.add(product);
                break;
        }
        return products;
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

    public boolean adds(List<TypeProductDTO> list){
        try {
            numLine = TypeProductDAO.getInstance().adds(list);
        }
        catch (Exception e){
            error = e.getMessage();
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
    public int getNumLine(){return numLine;}
}
