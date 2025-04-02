package GUI.JPanel;

import BUS.Discount_BUS;
import Components.MyJTable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class pnDiscount extends JPanel {
    private MyJTable tbDiscountCodes = new MyJTable(
            new String[]{"STT", "ID mã", "Loại", "Ngày tạo", "Hạn sử dụng", "Số lượng", "Trạng thái"},
            new Font("Roboto", Font.BOLD, 16),
            new Color(159, 32, 243), // Màu chữ
            new Color(159, 242, 115), // Màu nền header
            new Color(255, 0, 239) // Màu khi chọn hàng
    );
    private TableRowSorter<DefaultTableModel> sorter;
    private Discount_BUS discountBus;
    // Các thành phần giao diện
    private JButton btnAdd = new JButton("Thêm");
    private JButton btnEdit = new JButton("Sửa");
    private JButton btnDelete = new JButton("Xóa");
    private JButton btnRefresh = new JButton("Tải lại");
    private JTextField txtSearch = new JTextField("Nhập ID mã hoặc loại...");
    private JButton btnImportExcel = new JButton("Nhập Excel");
    private JButton btnExportExcel = new JButton("Xuất Excel");
    private JComboBox<String> sortComboBox;
    // Panels
    private JPanel panelFunction = new JPanel();
    private JPanel panelSearch = new JPanel();
    private JPanel panelHeader = new JPanel();
    private JPanel panelDisplay = new JPanel();

    public pnDiscount() {
//        discountBus = new Discount_BUS((DefaultTableModel) tbDiscountCodes.getModel());
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F5F5"));
        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        // panelFunction
        panelFunction.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelFunction.setBorder(BorderFactory.createTitledBorder(border, "Chức năng", 0, 0, new Font("Arial", Font.BOLD, 14)));
        panelFunction.setBackground(Color.WHITE);
        // Tùy chỉnh giao diện các nút
        customizeButton(btnAdd, new Color(50, 168, 82));    // Xanh lá
        customizeButton(btnEdit, new Color(255, 165, 0));   // Cam
        customizeButton(btnDelete, new Color(255, 69, 58)); // Đỏ
        customizeButton(btnRefresh, new Color(66, 133, 244)); // Xanh dương
        customizeButton(btnImportExcel, new Color(66, 133, 244)); // Xanh dương
        customizeButton(btnExportExcel, new Color(66, 133, 244)); // Xanh dương

        panelFunction.add(btnAdd);
        panelFunction.add(btnEdit);
        panelFunction.add(btnDelete);
        panelFunction.add(btnImportExcel);
        panelFunction.add(btnExportExcel);

        // Thiết lập panelSearch (Tìm kiếm và sắp xếp)
        panelSearch.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        panelSearch.setBorder(BorderFactory.createTitledBorder(border, "Tìm kiếm & Sắp xếp", 0, 0, new Font("Arial", Font.BOLD, 14)));
        panelSearch.setBackground(Color.WHITE);

        txtSearch.setPreferredSize(new Dimension(200, 30));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        txtSearch.setForeground(Color.GRAY);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));

        String[] sortOptions = {"STT (tăng dần)", "Hạn sử dụng", "Trạng thái"};
        sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.setPreferredSize(new Dimension(150, 30));
        sortComboBox.setBackground(Color.WHITE);
        sortComboBox.setForeground(Color.GRAY);
        sortComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        sortComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new JButton("V");
                arrowButton.setBackground(Color.WHITE);
                arrowButton.setForeground(Color.GRAY);
                arrowButton.setBorder(BorderFactory.createEmptyBorder());
                arrowButton.setFocusPainted(false);
                return arrowButton;
            }
        });

        panelSearch.add(txtSearch);
        panelSearch.add(sortComboBox);
        panelSearch.add(btnRefresh);

        // panelHeader
        panelHeader.setLayout(new BorderLayout());
        panelHeader.add(panelFunction, BorderLayout.WEST);
        panelHeader.add(panelSearch, BorderLayout.EAST);
        panelHeader.setBackground(Color.WHITE);
        panelHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Điều chỉnh chiều rộng cột
        TableColumnModel columnModel = tbDiscountCodes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // STT
        columnModel.getColumn(1).setPreferredWidth(100); // ID mã
        columnModel.getColumn(2).setPreferredWidth(150); // Loại
        columnModel.getColumn(3).setPreferredWidth(120); // Hạn sử dụng
        columnModel.getColumn(4).setPreferredWidth(100); // Trạng thái

        // Thiết lập TableRowSorter
        sorter = new TableRowSorter<>((DefaultTableModel) tbDiscountCodes.getModel());
        tbDiscountCodes.setRowSorter(sorter);

//        discountBus.loadDiscountData();

        // Thêm sự kiện tìm kiếm
        txtSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    txtSearch.setText("");
                }
            }
        });

        txtSearch.addActionListener(e -> {
            String searchText = txtSearch.getText().trim().toLowerCase();
            if (searchText.isEmpty() || searchText.equals("nhập id mã hoặc loại...")) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 1, 2)); // Tìm kiếm ở cột ID mã và Loại
            }
        });

        JScrollPane scrollPane = new JScrollPane(tbDiscountCodes);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);

        panelDisplay.setLayout(new BorderLayout());
        panelDisplay.add(scrollPane, BorderLayout.CENTER);
        panelDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelDisplay.setBackground(Color.WHITE);
        add(panelHeader, BorderLayout.NORTH);
        add(panelDisplay, BorderLayout.CENTER);

        btnAdd.addActionListener(event -> discountBus.ShowAddDig(this));
        btnEdit.addActionListener(event -> discountBus.ShowEditDig(this, tbDiscountCodes.getSelectedRow()));
        btnDelete.addActionListener(event -> discountBus.DeleDig(this, tbDiscountCodes.getSelectedRow()));
        btnRefresh.addActionListener(e -> refreshTable());
        btnExportExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbDiscountCodes.ExportExel("Danh sách mã giảm giá");
            }
        });
        btnImportExcel.addActionListener((e ->importFromExcel()));
    }

    // Tùy chỉnh giao diện nút
    private void customizeButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void refreshTable() {
        txtSearch.setText("Nhập ID mã hoặc loại...");
        sorter.setRowFilter(null);
        sortComboBox.setSelectedIndex(0);
    }

    private void importFromExcel() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Excel files", "xlsx", "xls"));
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(file);
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0);

                DefaultTableModel model = (DefaultTableModel) tbDiscountCodes.getModel();
                model.setRowCount(0);
                Iterator<Row> rowIterator = sheet.iterator();
                if (rowIterator.hasNext()) rowIterator.next();

                // Đọc dữ liệu
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Object[] rowData = new Object[7];

                    for (int i = 0; i < 7; i++) {
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData[i] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                rowData[i] = String.valueOf((int) cell.getNumericCellValue());
                                break;
                            default:
                                rowData[i] = "";
                        }
                    }
                    model.addRow(rowData);
                }

                workbook.close();
                fis.close();

                JOptionPane.showMessageDialog(this,
                        "Nhập dữ liệu từ Excel thành công!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi nhập Excel: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}