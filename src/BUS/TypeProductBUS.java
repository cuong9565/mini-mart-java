package BUS;

import Components.MyDate;
import DAO.ProductStatisticDAO;
import DAO.TypeProductDAO;
import DTO.ProductStatisticDTO;
import DTO.TypeProductDTO;
import org.apache.poi.ss.formula.functions.DProduct;

import javax.sound.sampled.Port;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
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
// thống kê
    public static class ProductStatisticBUS {
        private static ProductStatisticBUS instance = null;
        public static ProductStatisticBUS getInstance() {
            if (instance == null) instance = new ProductStatisticBUS();
            return instance;
        }

        public List<ProductStatisticDTO>loadExportByDate(MyDate startDate, MyDate endDate){
            int currId = 0;
            ArrayList<ProductStatisticDTO> listExport = new ArrayList<>();
            for(ProductStatisticDTO p: ProductStatisticDAO.getInstance().loadExport()){
                if(p.getId()!=currId){
                    listExport.add(new ProductStatisticDTO(p.getId(), p.getName(), p.getDate()));
                    currId = p.getId(); // tránh trùng
                }
                if(p.getDate().bettween(startDate, endDate))
                    listExport.set(
                            listExport.size()-1,
                            listExport.getLast().addQ(p.getQ())
                    );
            }
            return listExport;
        }

        public List<ProductStatisticDTO>loadImportByDate(MyDate startDate, MyDate endDate){
            int currId = 0;
            ArrayList<ProductStatisticDTO> list = new ArrayList<>();
            for(ProductStatisticDTO p: ProductStatisticDAO.getInstance().loadImport()){
                if(p.getId()!=currId){
                    list.add(new ProductStatisticDTO(p.getId(), p.getName(), p.getDate()));
                    currId = p.getId();
                }
                if(p.getDate().bettween(startDate, endDate))
                    list.set(
                            list.size()-1,
                            list.getLast().addQ(p.getQ())
                    );
            }
            return list;
        }
        // doanh thu tat ca
        public double getProfit(){
            return ProductStatisticDAO.getInstance().getProfit();
        }
        // thống kê bill
        public Object[] getRowObjectImport(MyDate startDate, MyDate endDate){
            double total = 0;
            Object[] row = new Object[6];
            row[0] = "Tổng chi";
            for(int i=1; i<=4; i++){
                double res = ProductStatisticDAO.getInstance().getObjectImportQ(i, startDate, endDate);
                row[i] = String.format("%,.0fđ", res);
                total += res;
            }
            row[5] = String.format("%,.0fđ", total);
            return row;
        }

        public Object[] getRowObjectExport(MyDate startDate, MyDate endDate){
            double total = 0;
            Object[] row = new Object[6];
            row[0] = "Tổng thu";
            for(int i=1; i<=4; i++){
                double res = ProductStatisticDAO.getInstance().getObjectExportQ(i, startDate, endDate);
                row[i] = String.format("%,.0fđ", res);
                total += res;
            }
            row[5] = String.format("%,.0fđ", total);
            return row;
        }

        // doanh thu theo quý
        public Object[] getRowObjectProfit(MyDate startDate, MyDate endDate){
            double total = 0;
            Object[] row = new Object[6];
            row[0] = "Tổng doanh thu";
            for(int i=1; i<=4; i++){
                double resImport = ProductStatisticDAO.getInstance().getObjectImportQ(i, startDate, endDate);
                double resExport = ProductStatisticDAO.getInstance().getObjectExportQ(i, startDate, endDate);
                double resProfit = resExport - resImport;
                row[i] = String.format("%,.0fđ", resProfit);
                total += resProfit;
            }
            row[5] = String.format("%,.0fđ", total);
            return row;
        }

    }
}
