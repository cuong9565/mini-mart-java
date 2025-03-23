//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pnForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class pnProduct extends JPanel {
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public pnProduct() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#F8F9FA"));
        String[] columnNames = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Trạng thái"};
        this.tableModel = new DefaultTableModel(columnNames, 0);
        this.productTable = new JTable(this.tableModel);
        this.productTable.setFillsViewportHeight(true);
        this.productTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(this.productTable);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(0));
        this.searchField = new JTextField(20);
        this.searchField.setToolTipText("Nhập nội dung để tìm kiếm");
        JButton searchButton = new JButton("Tìm");
        searchButton.addActionListener((e) -> this.searchProducts());
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(this.searchField);
        searchPanel.add(searchButton);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(0));
        JButton addButton = this.createStyledButton("Thêm");
        JButton editButton = this.createStyledButton("Sửa");
        JButton deleteButton = this.createStyledButton("Xóa");
        addButton.addActionListener((e) -> this.addProduct());
        editButton.addActionListener((e) -> this.editProduct());
        deleteButton.addActionListener((e) -> this.deleteProduct());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode("#F8F9FA"));
        mainPanel.add(searchPanel, "North");
        mainPanel.add(scrollPane, "Center");
        mainPanel.add(buttonPanel, "South");
        this.add(mainPanel, "Center");
        this.addSampleData();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#007BFF"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", 1, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void addSampleData() {
        this.tableModel.addRow(new Object[]{"SP001", "coca", 100, "15.000đ", "Còn hàng"});
        this.tableModel.addRow(new Object[]{"SP002", "bánh", 50, "12.000đ", "Hết hàng"});
        this.tableModel.addRow(new Object[]{"SP003", "mì", 150, "3.500đ", "Còn hàng"});
    }

    private void searchProducts() {
        String searchText = this.searchField.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(this.tableModel);
        this.productTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(searchText, new int[0]));
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
        int result = JOptionPane.showConfirmDialog(this, panel, "Thêm sản phẩm mới", 2);
        if (result == 0) {
            String code = codeField.getText();
            String name = nameField.getText();
            String quantity = quantityField.getText();
            String price = priceField.getText();
            if (!code.isEmpty() && !name.isEmpty() && !quantity.isEmpty() && !price.isEmpty()) {
                this.tableModel.addRow(new Object[]{code, name, Integer.parseInt(quantity), price, "Còn hàng"});
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", 0);
            }
        }

    }

    private void editProduct() {
        int selectedRow = this.productTable.getSelectedRow();
        if (selectedRow >= 0) {
            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Mã sản phẩm:"));
            JTextField codeField = new JTextField((String)this.tableModel.getValueAt(selectedRow, 0));
            panel.add(codeField);
            panel.add(new JLabel("Tên sản phẩm:"));
            JTextField nameField = new JTextField((String)this.tableModel.getValueAt(selectedRow, 1));
            panel.add(nameField);
            panel.add(new JLabel("Số lượng:"));
            JTextField quantityField = new JTextField(String.valueOf(this.tableModel.getValueAt(selectedRow, 2)));
            panel.add(quantityField);
            panel.add(new JLabel("Giá:"));
            JTextField priceField = new JTextField((String)this.tableModel.getValueAt(selectedRow, 3));
            panel.add(priceField);
            int result = JOptionPane.showConfirmDialog(this, panel, "Sửa sản phẩm", 2);
            if (result == 0) {
                String code = codeField.getText();
                String name = nameField.getText();
                String quantity = quantityField.getText();
                String price = priceField.getText();
                if (!code.isEmpty() && !name.isEmpty() && !quantity.isEmpty() && !price.isEmpty()) {
                    this.tableModel.setValueAt(code, selectedRow, 0);
                    this.tableModel.setValueAt(name, selectedRow, 1);
                    this.tableModel.setValueAt(Integer.parseInt(quantity), selectedRow, 2);
                    this.tableModel.setValueAt(price, selectedRow, 3);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi", 0);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để sửa");
        }

    }

    private void deleteProduct() {
        int selectedRow = this.productTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận", 0);
            if (confirm == 0) {
                this.tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Đã xóa sản phẩm");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa");
        }

    }
}
