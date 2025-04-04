package BUS;

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

    public List<ProductDTO> getList() {
        list = ProductDAO.getInstance().getList();
        return list;
    }

    public boolean add(ProductDTO product) {
        try {ProductDAO.getInstance().add(product);}
        catch (Exception e) {
            error = "Lỗi: " + e.getMessage();
            return false;
        }
        return true;
    }

    public boolean add(int idProductType, String detail, int idOfferProduct, String name, double price, String unit, int quantity){
        if(name.isEmpty() || unit.isEmpty()) {
            error = "Không được để trống thôn tin!!!";
            return false;
        }
        try {
            int idProductDetail = 0;
            try {
                idProductDetail = DetailProductBUS.getInstance().add(new DetailProductDTO(-1, detail));
            } catch (Exception e) {
                error = e.getMessage();
                return false;
            }
            ProductDAO.getInstance().add(idProductType, idProductDetail, idOfferProduct, name, price, unit, quantity);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public String getError() {return error;}


}
