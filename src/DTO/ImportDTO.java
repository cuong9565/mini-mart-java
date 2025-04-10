package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ImportDTO {
    private int idImport, idStaff, idSupplier;
    private double total;
    private Timestamp date;

    public ImportDTO() {}

    public ImportDTO(Object[] obj) {
        this.idImport = (int) obj[0];
        this.idStaff = (int) obj[1];
        this.idSupplier =  (int) obj[2];
        this.total = (double) obj[3];
        this.date = (Timestamp) obj[4];
    }
    public ImportDTO(ResultSet rs) {
        try {
            this.idImport = Integer.parseInt(rs.getString("id"));
            this.idStaff= Integer.parseInt(rs.getString("idStaff"));
            this.idSupplier = Integer.parseInt(rs.getString("idProvider"));
            this.total = Integer.parseInt(rs.getString("total"));
            this.date = Timestamp.valueOf(rs.getString("date"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ImportDTO(int idImport, int idStaff, int idSupplier, double total, Timestamp date) {
        this.idImport = idImport;
        this.idStaff = idStaff;
        this.idSupplier = idSupplier;
        this.total = total;
        this.date = date;
    }

    public int getIdImport() {return idImport;}
    public void setIdImport(int idImport) {this.idImport = idImport;}
    public int getIdStaff() {return idStaff;}
    public void setIdStaff(int idStaff) {this.idStaff = idStaff;}
    public int getIdSupplier() {return idSupplier;}
    public void setIdSupplier(int idSupplier) {this.idSupplier = idSupplier;}
    public double getTotal() {return total;}
    public void setTotal(double total) {this.total = total;}
    public Timestamp getDate() {return date;}
    public void setDate(Timestamp date) {this.date = date;}
    public Object[] getObjects() {return new Object[]{idImport,idStaff,idSupplier,date,total};} //co the bi loi
}
