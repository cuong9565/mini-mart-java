package BUS;

import DAO.DetailProductDAO;
import DAO.ProductDAO;
import DTO.DetailProductDTO;
import DTO.ProductDTO;
import DTO.TypeProductDTO;
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
        for(ProductDTO p : load())
            if(p.getId() == id)
                return p;
        return null;
    }

    public List<ProductDTO> load() {
        list = ProductDAO.getInstance().getList();
        return list;
    }

    public List<ProductDTO> getListSearch(int col, String txt) {
        List<ProductDTO> ls = new ArrayList<>();
        for (ProductDTO p : list) switch (col) {
            case 0: if (String.valueOf(p.getId()).contains(txt)) ls.add(p); break;
            case 1: if (p.getType().getName().contains(txt)) ls.add(p); break;
            case 2: if (p.toString().contains(txt)) ls.add(p); break;
            case 3: if (p.getName().contains(txt)) ls.add(p); break;
            case 4: if (String.format("%,.0fđ", p.getPrice()).contains(txt)) ls.add(p); break;
            case 5: if (p.getUnit().contains(txt)) ls.add(p); break;
            case 6: if (String.valueOf(p.getQuantity()).contains(txt)) ls.add(p); break;
        }
        return ls;
    }

    public List<ProductDTO> getListSearchSell(int col, String txt) {
        List<ProductDTO> ls = new ArrayList<>();
        for(ProductDTO p : list) switch (col) {
            case 0: if (String.valueOf(p.getId()).contains(txt)) ls.add(p); break;
            case 1: if (p.getName().contains(txt)) ls.add(p); break;
            case 2: if (p.getFormatPrice().contains(txt)) ls.add(p); break;
            case 3: if (p.getUnit().contains(txt)) ls.add(p); break;
            case 4: if (String.valueOf(p.getQuantity()).contains(txt)) ls.add(p); break;
        }
        return ls;
    }

    public boolean add(int idProductType, String detail, int idOfferProduct, String name, double price, String unit, int quantity){
        if(name.isEmpty() || unit.isEmpty()) {
            error = "Không được để trống thông tin!!!";
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

    public boolean updateQuantity(int idProduct, int quantity){
        try {
            ProductDAO.getInstance().updateQuantity(idProduct, quantity);
        }
        catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public void delete(ProductDTO product){
        try {
            ProductDAO.getInstance().delete(product);
            DetailProductDAO.getInstance().delete(product.getDetail());
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getError() {return error;}

    public List<ProductDTO> SearchAd(int priceOp, TypeProductDTO type, int quantityOp, boolean isOr) {
        List<ProductDTO> resultList = new ArrayList<>();
        for (ProductDTO item : list) {
            boolean matchPrice = false;
            boolean matchType = false;
            boolean matchQuantity = false;
            //Giá
            if (priceOp == 1) {
                matchPrice = item.getPrice() <= 99000;
            } else if (priceOp == 2) {
                matchPrice = item.getPrice() > 99000;
            }
            //loại
            if (type!= null && type.getId() != 0 && item.getType() != null) {
                // id= 0 là măc định
                matchType = item.getType().getName().equals(type.getName());
            }
            // sl
            if (quantityOp == 1) {
                matchQuantity = item.getQuantity() <= 20;
            } else if (quantityOp == 2) {
                matchQuantity = item.getQuantity() > 50;
            }

            if (isOr) {
                if ((priceOp != 0 && matchPrice) ||
                        (type != null && type.getId() != 0 && matchType) ||
                        (quantityOp != 0 && matchQuantity)) {
                    resultList.add(item);
                }
            } else {
                boolean match = true;
                if (priceOp != 0 && !matchPrice) match = false;
                if (type != null && type.getId() != 0 && !matchType) match = false;
                if (quantityOp!= 0 && !matchQuantity) match = false;
                if (match) {
                    resultList.add(item);
                }
            }
        }
        return resultList;
    }


    public int getNumberProduct(){
        return ProductDAO.getInstance().getNumberProduct();
    }
}
