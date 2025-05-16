package BUS;

import DAO.DetailProductDAO;
import DAO.ProductDAO;
import DTO.DetailProductDTO;
import DTO.OfferDTO;
import DTO.ProductDTO;
import DTO.TypeProductDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductBUS {
    private static ProductBUS instance = null;
    private static List<ProductDTO> list = null;
    private static String error = null;
    private static List<ProductDTO> listofoffer = null;
    public ProductBUS() {}
    public static ProductBUS getInstance() {
        if (instance == null) instance = new ProductBUS();
        return instance;
    }
    public List<ProductDTO> getListofoffer(int idoffer){
        listofoffer = ProductDAO.getInstance().getbyidoffer(idoffer);
        return  listofoffer;
    }


    // Check Same Product By Name And Unit
    public boolean isSameProduct(String name, String unit){
        return ProductDAO.getInstance().isSameProduct(name, unit);
    }
    public int getNumberProduct(){
        return ProductDAO.getInstance().getNumberProduct();
    }

    // Item
    public ProductDTO getItemById(int id) {
        return ProductDAO.getInstance().getItemById(id);
    }

    // List
    public List<ProductDTO> load() {
        List<ProductDTO> newlist = new ArrayList<>();
        Date today = new Date(); // ngày hiện tại
        for (ProductDTO pro : list = ProductDAO.getInstance().load()) {
            if (pro.getOfferProduct().getOffer().getId()!=0) {
                OfferDTO offer = pro.getOfferProduct().getOffer();
                Date start = offer.getDateStart().getSqlDate();
                Date end = offer.getDateEnd().getSqlDate();
                // today >= start && today <= end
                if ((today.compareTo(start) >= 0) && (today.compareTo(end) <= 0)) {
                    newlist.add(pro);
                } else {
                    offer.setValue(0); // hết hiệu lực, xóa giảm giá
                    newlist.add(pro);
                }
            }
            newlist.add(pro);
        }

        return newlist;
    }
//    public List<ProductDTO> load() {
//        list = ProductDAO.getInstance().load();
//        return list;
//    }

    public List<ProductDTO> getListSearch(int col, String txt) { // Search
        List<ProductDTO> ls = new ArrayList<>();
        for (ProductDTO p : list) switch (col) {
            case 0: if (String.valueOf(p.getId()).contains(txt)) ls.add(p); break;
            case 1: if (p.getType().getName().contains(txt)) ls.add(p); break;
//            case 2: if (p.toString().contains(txt)) ls.add(p); break;
            case 2: if (p.getName().contains(txt)) ls.add(p); break;
            case 3: if (String.format("%,.0fđ", p.getPrice()).contains(txt)) ls.add(p); break;
            case 4: if (p.getUnit().contains(txt)) ls.add(p); break;
            case 5: if (String.valueOf(p.getQuantity()).contains(txt)) ls.add(p); break;
        }
        return ls;
    }
    public List<ProductDTO> getListSearchSell(int col, String txt) { // Search
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
    public List<ProductDTO> SearchAd(int priceOp, TypeProductDTO type, int quantityOp, boolean isOr) { // Search
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

            if (isOr) { // or
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
// Insert
    public void add(int idProductType, String detail, int idOffer, String name, double price, String unit, int quantity){
        try {
            if(name.isEmpty() || unit.isEmpty())
                throw new RuntimeException("Không được để trống thông tin!!!");

            if (isSameProduct(name, unit))
                throw new RuntimeException(String.format("Tên sản phẩm '%s' cùng đơn vị '%s' đã tồn tại!!!", name, unit));

            DetailProductDAO.getInstance().add(new DetailProductDTO(-1, detail));
            int idProductDetail = DetailProductBUS.getInstance().getList().getLast().getId();
            ProductDAO.getInstance().add(idProductType, idProductDetail, idOffer, name, price, unit, quantity);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(int id, int idProductType, String detail, int idOfferProduct, String name, double price, String unit, int quantity){
        try{
            if(name.isEmpty() || unit.isEmpty())
                throw new RuntimeException("Không được để trống thông tin!!!");

            ProductDTO curr = ProductDAO.getInstance().getItemById(id);
            if (isSameProduct(name, unit) && (!curr.getName().equals(name) || !curr.getUnit().equals(unit)))
                throw new RuntimeException(String.format("Tên sản phẩm '%s' cùng đơn vị '%s' đã tồn tại!!!", name, unit));
            ProductDAO.getInstance().update(id, idProductType, idOfferProduct, name, price, unit, quantity);
            DetailProductDAO.getInstance().update(detail,id);

        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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

    public void updateoffer(int idproduct, int idoffer) {
        ProductDAO.getInstance().upoffer(idproduct,idoffer);
    }
}
