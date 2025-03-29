package GUI.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
public class pnTypeProduct extends JPanel {
    private JTable productTable;
    private DefaultTableModel tableModel;

    public pnTypeProduct() {
        setLayout(new BorderLayout()); // Sử dụng BorderLayout

        // Tạo panel cho các nút loại sản phẩm
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 10)); // 1 hàng, 4 cột

        // Tạo các nút cho từng loại sản phẩm
        String[] types = {"Nước", "Mì", "Bánh", "Vệ sinh"};

        for (String type : types) {
            JButton button = new JButton(type);
            button.setFont(new Font("Arial", Font.BOLD, 14)); // Kích thước chữ
            button.setPreferredSize(new Dimension(100, 50)); // Kích thước nút
            button.addActionListener((ActionEvent e) -> showProducts(type));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.NORTH); // Thêm panel chứa nút vào phía Bắc

        // Tạo bảng sản phẩm
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Giá"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        productTable.setFillsViewportHeight(true);

        // Thêm bảng sản phẩm vào giữa
        add(scrollPane, BorderLayout.CENTER);
    }

    private void showProducts(String type) {
        // Làm sạch bảng hiện tại
        tableModel.setRowCount(0); // Xóa tất cả các hàng hiện có

        // Tạo dữ liệu mẫu cho từng loại sản phẩm
        Object[][] data;
        switch (type) {
            case "Nước":
                data = new Object[][]{
                        {"SP001", "Nước khoáng", "10.000đ"},
                        {"SP002", "Nước ngọt", "15.000đ"},
                        {"SP003", "Nước tinh khiết", "8.000đ"}
                };
            case "Mì":
                data = new Object[][]{
                        {"SP004", "Mì tôm", "5.000đ"},
                        {"SP005", "Mì gói", "7.000đ"},
                        {"SP006", "Mì Hàn Quốc", "12.000đ"}
                };
                break;
            case "Bánh":
                data = new Object[][]{
                        {"SP007", "Bánh quy", "20.000đ"},
                        {"SP008", "Bánh mì", "15.000đ"},
                        {"SP009", "Bánh ngọt", "25.000đ"}
                };
                break;
            case "Vệ sinh":
                data = new Object[][]{
                        {"SP010", "Khăn giấy", "10.000đ"},
                        {"SP011", "Xà phòng", "15.000đ"},
                        {"SP012", "Nước rửa tay", "20.000đ"}
                };
                break;
            default:
                data = new Object[0][0]; // Không có dữ liệu
                break;
        }

        // Thêm dữ liệu vào bảng
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}
