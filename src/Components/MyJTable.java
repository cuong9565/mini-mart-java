package Components;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyJTable extends JTable {
    public DefaultTableModel dftbModel;
    public JScrollPane scrPn;

    public MyJTable(String header[]) {
        dftbModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(dftbModel);

        // Set cho header
        getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
        getTableHeader().setBackground(MyColor.UnderLineBlue);
        getTableHeader().setForeground(MyColor.White);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBorder(null);
        getTableHeader().setPreferredSize(new Dimension(this.getTableHeader().getWidth(), 33));

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
        List<Object[]> list = new ArrayList<Object[]>();
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
                JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
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







    public MyJTable(String header[], Font style, Color colorFont, Color brheader, Color select) {
        dftbModel = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(dftbModel);
        setFont(new Font("Arial",0,16));
        setBackground(Color.WHITE);
        setRowHeight(28);
        getTableHeader().setPreferredSize(new Dimension(0, 30));

        // Tùy chỉnh header
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBorder(null);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(null);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBackground(brheader); // Màu nền header
                label.setForeground(colorFont); // Màu chữ header
                label.setFont(style); // Font của header
                return label;
            }
        });

        setIntercellSpacing(new Dimension(0, 1));
        setFocusable(false);
        setShowVerticalLines(false);
        setShowHorizontalLines(true);
        setGridColor(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder());

        // Căn giữa nội dung
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                ((JLabel) c).setBorder(null);
                if (isSelected) {
                    c.setBackground(select);
                    c.setForeground(Color.white);
                } else {
                    c.setBackground(Color.WHITE); // Màu nền mặc định
                    c.setForeground(Color.BLACK); // Màu chữ mặc định
                }
                return c;
            }
        };

        if (getColumnCount() > 0) {
            for (int i = 0; i < getColumnCount(); i++) {
                getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }
}
