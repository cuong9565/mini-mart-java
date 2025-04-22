package Components;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyJTable extends JTable {
    public DefaultTableModel dftbModel;
    public JScrollPane scrPn;

    public MyJTable(String header[], int sizeColumns[], int leftColumns[], int rightColumns[]) {
        dftbModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(dftbModel);
        setBackground(MyColor.White);
        setBorder(BorderFactory.createLineBorder(MyColor.Black));

        // Set kich thuoc tung cot
        TableColumnModel columnModel = this.getColumnModel();
        for (int i = 0; i < sizeColumns.length; i++)
            columnModel.getColumn(i).setPreferredWidth(sizeColumns[i]);

        // Cach le 5 px
        DefaultTableCellRenderer paddedCenterRenderer = new DefaultTableCellRenderer();
        paddedCenterRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        paddedCenterRenderer.setBorder(new EmptyBorder(0, 5, 0, 5));
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(paddedCenterRenderer);
        }

        // Set trai tung cot
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        for(int x: leftColumns)
            columnModel.getColumn(x).setCellRenderer(leftRenderer);

        // Set phải tung cot
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        for(int x: rightColumns)
            columnModel.getColumn(x).setCellRenderer(rightRenderer);

        // Set cho header
        getTableHeader().setFont(new Font("Roboto", Font.BOLD, 14));
        getTableHeader().setBackground(MyColor.UnderLineBlue);
        getTableHeader().setForeground(MyColor.White);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBorder(null);
        getTableHeader().setPreferredSize(new Dimension(this.getTableHeader().getWidth(), 35));

        // Set cho content
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setBackground(MyColor.White);
        setForeground(MyColor.Black);
        setRowHeight(23);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setAutoCreateRowSorter(false);


        scrPn = new JScrollPane(this);
    }


    public MyJTable(String header[], int fontSize, int sizeColumns[], int leftColumns[], int rightColumns[]) {
        dftbModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(dftbModel);
        setBackground(MyColor.White);
        setBorder(BorderFactory.createLineBorder(MyColor.Black));


        // Set cho header
        getTableHeader().setFont(new Font("Roboto", Font.PLAIN, fontSize));
        getTableHeader().setBackground(MyColor.LightGray);
        getTableHeader().setForeground(MyColor.Black);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBorder(null);
        getTableHeader().setPreferredSize(new Dimension(this.getTableHeader().getWidth(), 33));

        // Set kich thuoc tung cot
        TableColumnModel columnModel = this.getColumnModel();
        for (int i = 0; i < sizeColumns.length; i++)
            columnModel.getColumn(i).setPreferredWidth(sizeColumns[i]);

        // Cach le 5 px
        DefaultTableCellRenderer paddedCenterRenderer = new DefaultTableCellRenderer();
        paddedCenterRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        paddedCenterRenderer.setBorder(new EmptyBorder(0, 5, 0, 5));
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(paddedCenterRenderer);
        }

        // Set trai tung cot
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        for(int x: leftColumns)
            columnModel.getColumn(x).setCellRenderer(leftRenderer);

        // Set phải tung cot
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        for(int x: rightColumns)
            columnModel.getColumn(x).setCellRenderer(rightRenderer);

        // Set cho content
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setBackground(MyColor.White);
        setForeground(MyColor.Black);
        setRowHeight(23);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setAutoCreateRowSorter(false);


        scrPn = new JScrollPane(this);
    }

    public List<Object[]> ImportExel(int col){
        List<Object[]> list = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn tệp để mở");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("Excel Files (*.xlsx, *.xls)", "xlsx", "xls"));

        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try(
                FileInputStream fileInput = new FileInputStream(file.getAbsoluteFile());
                Workbook wb = new XSSFWorkbook(fileInput);
            ){
                Sheet sheet = wb.getSheetAt(0);
                int n = sheet.getLastRowNum();

                for(int i=2; i<=n; i++){
                    Row row = sheet.getRow(i);
                    Object[] data = new Object[row.getLastCellNum()];
                    for(int j=0; j<col; j++)
                        data[j] = row.getCell(j).getStringCellValue();
                    list.add(data);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        else list = null;

        return list;
    }

    public void ExportExel(String name){
        int m = dftbModel.getRowCount();
        int n = dftbModel.getColumnCount();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Danh sách");
        Row rowTitle = sheet.createRow(0);
        rowTitle.createCell(0).setCellValue(name);

        Row rowHeader = sheet.createRow(1);
        for(int i = 0; i < n; i++) rowHeader.createCell(i).setCellValue(dftbModel.getColumnName(i));

        for(int i = 0; i < m; i++){
            Row row = sheet.createRow(i+2);
            for(int j = 0; j < n; j++)
                row.createCell(j).setCellValue(dftbModel.getValueAt(i,j).toString());
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn vị trí lưu file");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setSelectedFile(new File(name + ".xlsx"));

        int userChoice = chooser.showSaveDialog(null);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if(!filePath.endsWith(".xlsx")) filePath += ".xlsx";

            try(FileOutputStream fileOut = new FileOutputStream(filePath)){
                wb.write(fileOut);
                JOptionPane.showMessageDialog(null, "Xuất file thành công!!!");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Lỗi ghi file: " + e.getMessage());
            }
        }
    }

    public String getFirstColumn(int row){
        return dftbModel.getValueAt(row,0).toString();
    }
}
