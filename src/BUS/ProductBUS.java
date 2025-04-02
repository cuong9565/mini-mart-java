package BUS;

import DAO.ProductDAO;
import DTO.ProductDTO;
import org.apache.commons.math3.stat.descriptive.summary.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBUS {
    private static ProductBUS instance = null;
    private static List<ProductDTO> list = null;
    public ProductBUS() {}
    public static ProductBUS getInstance() {
        if (instance == null) instance = new ProductBUS();
        return instance;
    }

    public List<ProductDTO> getList() {
        list = ProductDAO.getInstance().getList();
        return list;
    }
}
