package BUS;

import DAO.TypeProductDAO;
import DTO.TypeProductDTO;

import java.util.ArrayList;
import java.util.List;

public class TypeProductBUS {
    private static TypeProductBUS instance = null;
    private static List<TypeProductDTO>list = null;

    public TypeProductBUS() {}
    public static TypeProductBUS getInstance() {
        if (instance == null) instance = new TypeProductBUS();
        return instance;
    }

    // Check Same Name
    public boolean isSameName(String name){
        return TypeProductDAO.getInstance().isSameName(name);
    }

    // Item
    public TypeProductDTO getItemById(int id){
        return TypeProductDAO.getInstance().getItemById(id);
    }
    public TypeProductDTO getItemByName(String name){
        return TypeProductDAO.getInstance().getItemByName(name);
    }

    // List
    public List<TypeProductDTO> getList(){
        list = TypeProductDAO.getInstance().getList();
        return list;
    }
    public List<TypeProductDTO> getListBy(int col, String txt){ // Search
        List<TypeProductDTO>products = new ArrayList<>();
        for (TypeProductDTO product: list) switch (col){
            case 0: if(String.valueOf(product.getId()).contains(txt)) products.add(product); break;
            case 1: if(product.getName().contains(txt)) products.add(product); break;
        }
        return products;
    }

    // Insert
    public void add(TypeProductDTO product){
        try{
            if(product.getName().isEmpty())
                throw new Exception("Không được để trống thông tin!!!");

            if(isSameName(product.getName()))
                throw new Exception(String.format("Tên loại '%s' đã tồn tại", product.getName()));

            TypeProductDAO.getInstance().add(product);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    // Update
    public void edit(TypeProductDTO newProduct){
        try {
            if(newProduct.getName().isEmpty())
                throw new Exception("Không được để trống thông tin!!!");

            TypeProductDTO currProduct = TypeProductDAO.getInstance().getItemById(newProduct.getId());
            if(isSameName(newProduct.getName()) && !currProduct.getName().equals(newProduct.getName()))
                throw new Exception(String.format("Tên loại '%s' đã tồn tại", newProduct.getName()));

            TypeProductDAO.getInstance().edit(newProduct);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    // Delete
    public void delete(TypeProductDTO product){
        try {
            TypeProductDAO.getInstance().delete(product);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
