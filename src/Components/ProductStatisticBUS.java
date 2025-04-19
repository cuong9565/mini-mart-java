package Components;

import DAO.ProductStatisticDAO;
import DTO.ProductStatisticDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductStatisticBUS {
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
                currId = p.getId();
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

    public double getProfit(){
        return ProductStatisticDAO.getInstance().getProfit();
    }
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
