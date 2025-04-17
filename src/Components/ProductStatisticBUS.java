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
}
