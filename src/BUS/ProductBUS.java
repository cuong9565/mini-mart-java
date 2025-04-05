package BUS;

import DAO.DetailProductDAO;
import DAO.ProductDAO;
import DTO.DetailProductDTO;
import DTO.ProductDTO;
import org.apache.commons.math3.stat.descriptive.summary.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBUS {
    private static ProductBUS instance = null;
    private static List<ProductDTO> list = null;
    private static String error = null;
    public ProductBUS() {}
    public static ProductBUS getInstance() {
        if (instance == null) instance = new ProductBUS();
        return instance;
    }

    public ProductDTO getItemById(int id) {
        for(ProductDTO p : list)
            if(p.getId() == id)
                return p;
        return null;
    }

    public List<ProductDTO> getList() {
        list = ProductDAO.getInstance().getList();
        return list;
    }

    public List<ProductDTO> getListSearch(int col, String txt) {
        List<ProductDTO> ls = new ArrayList<>();
        switch (col){
            case 0:
                for (ProductDTO p : list) if (p.getName().contains(txt)) ls.add(p);
                break;
            case 1:
                for (ProductDTO p : list) if (String.valueOf(p.getId()).contains(txt)) ls.add(p);
                break;
            case 2:
                for (ProductDTO p : list) if (p.getType().getName().contains(txt)) ls.add(p);
                break;
            case 3:
                for (ProductDTO p : list) if (p.toString().contains(txt)) ls.add(p);
                break;
            case 4:
                for (ProductDTO p : list) if (String.format("%,.0fđ", p.getPrice()).contains(txt)) ls.add(p);
                break;
            case 5:
                for (ProductDTO p : list) if (p.getUnit().contains(txt)) ls.add(p);
                break;
            case 6:
                for (ProductDTO p : list) if (String.valueOf(p.getQuantity()).contains(txt)) ls.add(p);
                break;
        }
        return ls;
    }

    public boolean add(int idProductType, String detail, int idOfferProduct, String name, double price, String unit, int quantity){
        if(name.isEmpty() || unit.isEmpty()) {
            error = "Không được để trống thôn tin!!!";
            return false;
        }
        try {
            DetailProductDAO.getInstance().add(new DetailProductDTO(-1, detail));
            int idProductDetail = DetailProductBUS.getInstance().getList().getLast().getId();
            ProductDAO.getInstance().add(idProductType, idProductDetail, idOfferProduct, name, price, unit, quantity);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean update(int id, int idProductType, DetailProductDTO detail, int idOfferProduct, String name, double price, String unit, int quantity){
        if(name.isEmpty() || unit.isEmpty()) {
            error = "Không được để trống thôn tin!!!";
            return false;
        }
        try{
            DetailProductDAO.getInstance().update(detail);
            ProductDAO.getInstance().update(id, idProductType, idOfferProduct, name, price, unit, quantity);
        }catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean delete(ProductDTO product){
        try {
            ProductDAO.getInstance().delete(product);
            DetailProductDAO.getInstance().delete(product.getDetail());
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public String getError() {return error;}


}
