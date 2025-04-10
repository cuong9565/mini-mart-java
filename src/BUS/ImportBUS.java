package BUS;

import DAO.*;
import DTO.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ImportBUS {
    private static ImportBUS instance = null;
    private static String error = null;
    private static int numLine = 0;
    private static List<ImportDTO> ImportList;

    private ImportBUS() {}

    public static ImportBUS getInstance() {
        if (instance == null) {
            instance = new ImportBUS();
        }
        return instance;
    }

    public List<ImportDTO> getListImport() {
        ImportList = ImportDAO.getInstance().getListImport();
        return ImportList;
    }

    public ImportDTO getImportByRow(int row) { return ImportList.get(row); }

    public List<ImportDTO> getImportListBy (String whr, String str) {
        return ImportDAO.getInstance().getListImportBy(whr, str);
    }
}