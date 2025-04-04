package GUI.JPanel;

import DAO.*;
import Components.*;
import DTO.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class pnImport extends JPanel {
    JButton btnRefresh = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.White, "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnExportExcel = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.LightGray, "Nhập Excel", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.LightGray, "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.LightGray, "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnList = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.LightGray, "Xem chi tiết", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbID = new MyJLabel(Font.PLAIN, 12, MyColor.Black, "Mã phiếu nhập", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbCreate = new MyJLabel(Font.PLAIN, 12, MyColor.Black, "Người tạo phiếu", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSupply = new MyJLabel(Font.PLAIN, 12, MyColor.Black, "Nhà cung cấp", SwingConstants.LEFT, SwingConstants.CENTER);
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel quantityPanel = new JPanel();
    JTextField txtSearch = new JTextField();
    JTextField txtID = new JTextField();
    JTextField txtCreate = new JTextField();
    JTextField txtQuantity = new JTextField();
    JComboBox<Object> cboSupplier = new JComboBox<>();
    MyJTable tbProduct = new MyJTable(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn vị", "Đơn giá"});
    MyJTable tbImport = new MyJTable(new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn vị", "Đơn giá"});
    JPanel totalPanel = new JPanel();
    JLabel lbTotal = new MyJLabel(Font.BOLD, 16, Color.decode("#000000"), "Tổng tiền", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAmount = new MyJLabel(Font.PLAIN, 16, Color.decode("#000000"), "0đ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbQuantity = new MyJLabel(Font.PLAIN, 16, Color.decode("#000000"), "Số lượng", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnImport = new MyJButton(Font.PLAIN, 16, Color.decode("#FFFFFF"), Color.decode("#64a15c"), Color.decode("#00CC00"), "Nhập hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnAdd = new MyJButton(Font.PLAIN, 16, Color.decode("#FFFFFF"), Color.decode("#64a15c"), Color.decode("#00CC00"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);

    private ProductDAO productDAO = ProductDAO.getInstance();
    private ImportDAO importDAO = ImportDAO.getInstance();
    private SupplierDAO supplierDAO = SupplierDAO.getInstance();
    private ImportDtlDAO detailDAO = ImportDtlDAO.getInstance();
    private List<ProductDTO> selectedProducts = new ArrayList<>();

    public pnImport() {
        initComponents();
        loadSuppliers();
        loadProducts();
        addListeners();
    }

    private void initComponents() {
        setLayout(null);
        setBackground(Color.decode("#FFFFFF"));
        Border border = BorderFactory.createLineBorder(Color.gray, 1);
        panel1.setBorder(BorderFactory.createTitledBorder(border, "Tìm kiếm"));
        panel1.setBackground(Color.decode("#FFFFFF"));
        panel1.setBounds(10, 10, 450, 80);

        txtSearch.setFont(new Font("Arial", Font.PLAIN, 20));
        txtSearch.setHorizontalAlignment(JTextField.CENTER);
        txtSearch.setBounds(30, 35, 302, 36);
        txtSearch.setBorder(border);

        btnRefresh.setBorder(border);
        btnRefresh.setBounds(342, 35, 100, 36);

        panel2.setBackground(Color.decode("#FFFFFF"));
        panel2.setLayout(new GridLayout(3, 2, 10, 10));
        panel2.add(lbID); panel2.add(txtID);
        panel2.add(lbCreate); panel2.add(txtCreate);
        panel2.add(lbSupply); panel2.add(cboSupplier);
        panel2.setBounds(490, 10, 430, 100);

        tbProduct.scrPn.setBounds(10, 110, 450, 550);
        tbImport.scrPn.setBounds(490, 120, 450, 450);

        buttonPanel.setBackground(Color.decode("#FFFFFF"));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBounds(480, 590, 480, 50);

        btnExportExcel.setBorder(border);
        btnExportExcel.setPreferredSize(new Dimension(100, 36));
        btnDelete.setBorder(border);
        btnDelete.setPreferredSize(new Dimension(100, 36));
        btnEdit.setBorder(border);
        btnEdit.setPreferredSize(new Dimension(100, 36));
        btnList.setBorder(border);
        btnList.setPreferredSize(new Dimension(100, 36));

        buttonPanel.add(btnExportExcel);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnList);

        totalPanel.setLayout(null);
        totalPanel.setBackground(Color.decode("#FFFFFF"));
        totalPanel.setBounds(480, 660, 480, 60);

        lbTotal.setBounds(60, 15, 100, 30);
        totalPanel.add(lbTotal);

        lbAmount.setBounds(180, 15, 100, 30);
        totalPanel.add(lbAmount);

        btnImport.setBounds(310, 15, 120, 30);
        totalPanel.add(btnImport);

        quantityPanel.setLayout(null);
        quantityPanel.setBackground(Color.decode("#FFFFFF"));
        quantityPanel.setBounds(50, 660, 450, 60);

        lbQuantity.setBounds(10, 15, 80, 30);
        quantityPanel.add(lbQuantity);

        txtQuantity.setFont(new Font("Arial", Font.PLAIN, 16));
        txtQuantity.setHorizontalAlignment(JTextField.CENTER);
        txtQuantity.setBounds(100, 15, 100, 30);
        txtQuantity.setBorder(border);
        quantityPanel.add(txtQuantity);

        btnAdd.setBorder(border);
        btnAdd.setBounds(250, 15, 120, 30);
        btnAdd.setPreferredSize(new Dimension(120, 36));
        quantityPanel.add(btnAdd);

        add(quantityPanel);
        add(totalPanel);
        add(buttonPanel);
        add(tbImport.scrPn);
        add(tbProduct.scrPn);
        add(btnRefresh);
        add(txtSearch);
        add(panel1);
        add(panel2);
    }

    private void loadSuppliers() {
        try {
            List<SupplierDTO> suppliers = supplierDAO.getListSupplier();
            cboSupplier.removeAllItems();
            for (SupplierDTO supplier : suppliers) {
                cboSupplier.addItem(supplier.getId() + " - " + supplier.getName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadProducts() {
        try {
            List<ProductDTO> products = productDAO.getList();
            tbProduct.dftbModel.setRowCount(0);
            for (ProductDTO product : products) {
                tbProduct.dftbModel.addRow(new Object[]{
                        product.getId(), product.getName(), product.getQuantity(),
                        product.getUnit(), product.getPrice()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadImports() {
        try {
            List<ImportDTO> imports = importDAO.getAll();
            tbImport.dftbModel.setRowCount(0);
            int stt = 1;
            for (ImportDTO imp : imports) {
                tbImport.dftbModel.addRow(new Object[]{stt++, "N/A", "N/A", "N/A", "N/A", imp.getTotal()});
            }
            updateTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading imports: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTotal() {
        double total = 0;
        for (int i = 0; i < tbImport.dftbModel.getRowCount(); i++) {
            total += Double.parseDouble(tbImport.dftbModel.getValueAt(i, 5).toString());
        }
        lbAmount.setText(String.format("%,.0fđ", total));
    }

    private void addListeners() {
        btnRefresh.addActionListener(e -> {
            loadProducts();
            loadImports();
            txtSearch.setText("");
            selectedProducts.clear();
            tbImport.dftbModel.setRowCount(0);
            updateTotal();
        });

        btnAdd.addActionListener(e -> {
            int row = tbProduct.getSelectedRow();
            if (row >= 0) {
                try {
                    int quantity = Integer.parseInt(txtQuantity.getText());
                    int productId = (int) tbProduct.dftbModel.getValueAt(row, 0);
                    ProductDTO product = productDAO.getItemById(productId);
                    product.setQuantity(quantity);
                    selectedProducts.add(product);

                    tbImport.dftbModel.addRow(new Object[]{
                            tbImport.dftbModel.getRowCount() + 1, product.getId(), product.getName(),
                            quantity, product.getUnit(), product.getPrice()
                    });
                    updateTotal();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error adding product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a product to add!");
            }
        });

        btnImport.addActionListener(e -> {
            try {
                int idStaff = Integer.parseInt(txtCreate.getText());
                int idProvider = Integer.parseInt(cboSupplier.getSelectedItem().toString().split(" - ")[0]);
                double total = Double.parseDouble(lbAmount.getText().replace("đ", "").replace(",", ""));
                String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                ImportDTO importDTO = new ImportDTO(0, idStaff, idProvider, total, dateStr);
                importDAO.insert(importDTO);

                for (ProductDTO product : selectedProducts) {
                    ImportDtlDTO detail = new ImportDtlDTO(0, product.getId(),
                            product.getQuantity(), product.getPrice(), product.getUnit());
                    detailDAO.insert(detail);
                }

                selectedProducts.clear();
                tbImport.dftbModel.setRowCount(0);
                loadImports();
                updateTotal();
                JOptionPane.showMessageDialog(this, "Import added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding import: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            int row = tbImport.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = Integer.parseInt(txtID.getText());
                    importDAO.delete(id);
                    loadImports();
                    JOptionPane.showMessageDialog(this, "Import deleted!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting import: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an import to delete!");
            }
        });

        btnExportExcel.addActionListener(e -> {
            tbImport.ExportExel("Import_List");
        });

        btnEdit.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Edit functionality not implemented yet.");
        });

        btnList.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "View details functionality not implemented yet.");
        });

        txtSearch.addActionListener(e -> {
            String searchText = txtSearch.getText().trim().toLowerCase();
            tbProduct.dftbModel.setRowCount(0);
            try {
                List<ProductDTO> products = productDAO.getList();
                for (ProductDTO product : products) {
                    if (product.getName().toLowerCase().contains(searchText) ||
                            String.valueOf(product.getId()).contains(searchText)) {
                        tbProduct.dftbModel.addRow(new Object[]{
                                product.getId(), product.getName(), product.getQuantity(),
                                product.getUnit(), product.getPrice()
                        });
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error searching products: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}