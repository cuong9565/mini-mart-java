package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class pnProduct extends JPanel {
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public pnProduct() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F8F9FA")); // Màu nền

        // Tạo bảng với mô hình dữ liệu
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Trạng thái"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        productTable.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        // Tạo panel cho ô tìm kiếm
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchField.setToolTipText("Nhập nội dung để tìm kiếm");
        JButton searchButton = new JButton("Tìm");
        searchButton.addActionListener(e -> searchProducts());

        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Tạo panel cho các nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = createStyledButton("Thêm");
        JButton editButton = createStyledButton("Sửa");
        JButton deleteButton = createStyledButton("Xóa"); // Đổi tên từ "Hủy" thành "Xóa"

        // Thêm hành động cho các nút
        addButton.addActionListener(e -> addProduct());
        editButton.addActionListener(e -> editProduct());
        deleteButton.addActionListener(e -> deleteProduct()); // Đổi tên phương thức

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Bố trí các thành phần vào mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode("#F8F9FA"));
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Thêm panel chính vào JPanel chính
        add(mainPanel, BorderLayout.CENTER);

        // Thêm dữ liệu mẫu
        addSampleData();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#007BFF")); // Màu nền
        button.setForeground(Color.WHITE); // Màu chữ
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void addSampleData() {
        // Thêm dữ liệu mẫu
        tableModel.addRow(new Object[]{ "SP001", "coca", 100, "15.000đ", "Còn hàng"});
        tableModel.addRow(new Object[]{ "SP002", "bánh", 50, "12.000đ", "Hết hàng"});
        tableModel.addRow(new Object[]{ "SP003", "mì", 150, "3.500đ", "Còn hàng"});
    }

    private void searchProducts() {
        String searchText = searchField.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        productTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(searchText));
    }

    private void addProduct() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Mã sản phẩm:"));
        JTextField codeField = new JTextField();
        panel.add(codeField);
        panel.add(new JLabel("Tên sản phẩm:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("Số lượng:"));
        JTextField quantityField = new JTextField();
        panel.add(quantityField);
        panel.add(new JLabel("Giá:"));
        JTextField priceField = new JTextField();
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Thêm sản phẩm mới", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String code = codeField.getText();
            String name = nameField.getText();
            String quantity = quantityField.getText();
            String price = priceField.getText();

            if (!code.isEmpty() && !name.isEmpty() && !quantity.isEmpty() && !price.isEmpty()) {
                tableModel.addRow(new Object[]{code, name, Integer.parseInt(quantity), price, "Còn hàng"});
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Mã sản phẩm:"));
            JTextField codeField = new JTextField((String) tableModel.getValueAt(selectedRow, 0));
            panel.add(codeField);
            panel.add(new JLabel("Tên sản phẩm:"));
            JTextField nameField = new JTextField((String) tableModel.getValueAt(selectedRow, 1));
            panel.add(nameField);
            panel.add(new JLabel("Số lượng:"));
            JTextField quantityField = new JTextField(String.valueOf(tableModel.getValueAt(selectedRow, 2)));
            panel.add(quantityField);
            panel.add(new JLabel("Giá:"));
            JTextField priceField = new JTextField((String) tableModel.getValueAt(selectedRow, 3));
            panel.add(priceField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Sửa sản phẩm", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String code = codeField.getText();
                String name = nameField.getText();
                String quantity = quantityField.getText();
                String price = priceField.getText();

                if (!code.isEmpty() && !name.isEmpty() && !quantity.isEmpty() && !price.isEmpty()) {
                    tableModel.setValueAt(code, selectedRow, 0);
                    tableModel.setValueAt(name, selectedRow, 1);
                    tableModel.setValueAt(Integer.parseInt(quantity), selectedRow, 2);
                    tableModel.setValueAt(price, selectedRow, 3);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để sửa");
        }
    }

    private void deleteProduct() { // Đổi tên từ cancelProduct thành deleteProduct
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Đã xóa sản phẩm");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa");
        }
    }
}
