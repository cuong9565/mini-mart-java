package GUI.JPanel;

import BUS.ImportBUS;
import Components.*;
import DAO.*;
import DAO.SupplierDAO;
import DTO.*;
import DTO.SupplierDTO;
import GUI.JDialog.dlImportDetail;
import GUI.JFrame.fManage;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class pnImport extends JPanel {
    // UI Components
    JPanel pnMain = new MyJPanel(MyColor.White);
    JPanel pnInfoImport = new MyJPanel(MyColor.White, "Thông tin phiếu nhập");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm sản phẩm");

    JLabel lbID = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã phiếu nhập", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbCreate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Người tạo phiếu", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSupply = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Nhà cung cấp", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfID = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfCreate = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JComboBox<String> cboSupplier = new MyJComboBox<>(new String[]{}, 12);

    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã", "Tên", "Đơn giá", "Đơn vị", "Số lượng"}, 12);

    MyJTable tbProduct = new MyJTable(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn vị", "Đơn giá"}, 12, new int[]{50, 150, 100, 50}, new int[]{1}, new int[]{});
    MyJTable tbImport = new MyJTable(new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn vị", "Đơn giá"}, 12, new int[]{15, 15, 50, 15, 15}, new int[]{}, new int[]{});

    JLabel lbQuantity = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner snQuantity = new MyJSpinner(1, 1, 1000000000, 1);
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);

    JButton btnExportExcel = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Nhập Excel", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnImport = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Nhập hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnList = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Xem đơn nhập", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbQuantityFix = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner snQuantityFix = new MyJSpinner(1, 1, 1000000000, 1);

    JLabel lbTotal = new MyJLabel(Font.BOLD, 14, MyColor.Black, "Tổng tiền: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAmount = new MyJLabel(Font.BOLD, 14, MyColor.Black, "0đ", SwingConstants.LEFT, SwingConstants.CENTER);


    private final ImportBUS importBUS = ImportBUS.getInstance();
    private final ProductDAO productDAO = ProductDAO.getInstance();
    private final SupplierDAO supplierDAO = SupplierDAO.getInstance();
    private List<ProductDTO> selectedProducts = new ArrayList<>();
    fManage frame;
    pnImport thisPanel = this;

    StaffDTO staffLoginGlobal;


    public pnImport(StaffDTO staffLogin) {
        setLayout(null);
        setBackground(MyColor.White);

        staffLoginGlobal = staffLogin;

        // Set bounds
        pnMain.setBounds(0, 0, 1170, 750);
        pnInfoImport.setBounds(0, 0, 1160, 70);
        pnSearch.setBounds(0, 70, 460, 60);

        lbID.setBounds(480, 70, 200, 20);
        tfID.setBounds(480, 90, 200, 30);
        lbCreate.setBounds(700, 70, 200, 20);
        tfCreate.setBounds(700, 90, 200, 30);
        lbSupply.setBounds(920, 70, 200, 20);
        cboSupplier.setBounds(920, 90, 200, 30);

        cbSearch.setBounds(10, 90, 140, 30);
        tfSearch.setBounds(160, 90, 190, 30);
        btnRefresh.setBounds(360, 90, 90, 30);

        tbProduct.scrPn.setBounds(0, 140, 460, 550);
        tbImport.scrPn.setBounds(480, 140, 680, 500);

        lbQuantity.setBounds(0, 700, 70, 30);
        snQuantity.setBounds(70, 700, 100, 30);
        btnAdd.setBounds(180, 700, 100, 30);

        btnExportExcel.setBounds(480, 650, 100, 30);
        btnDelete.setBounds(480, 650, 100, 30);
        btnEdit.setBounds(880, 650, 100, 30);
        btnList.setBounds(810, 700, 120, 30);
        btnImport.setBounds(950, 700, 100, 30);
        lbQuantityFix.setBounds(700,650,100,30);
        snQuantityFix.setBounds(770,650,100,30);

        lbTotal.setBounds(480, 700, 80, 30);
        lbAmount.setBounds(560, 700, 190, 30);


        // Add components
        add(lbID);
        add(tfID);
        add(lbCreate);
        add(tfCreate);
        add(lbSupply);
        add(cboSupplier);
        add(cbSearch);
        add(btnRefresh);
        add(tfSearch);
        add(tbProduct.scrPn);
        add(tbImport.scrPn);
        add(lbQuantity);
        add(snQuantity);
        add(btnAdd);
        //add(btnExportExcel);
        add(btnDelete);
        add(btnEdit);
        add(btnImport);
        add(btnList);
        add(lbQuantityFix);
        add(snQuantityFix);
        add(lbTotal);
        add(lbAmount);
        add(pnSearch);
        add(pnInfoImport);
        add(pnMain);

        tfCreate.setText(staffLogin.getLastName() + " " + staffLogin.getFirstName());

        loadSuppliers();
        loadProducts();
        updateTotal();
        addListeners(frame, thisPanel);
    }

    private void loadSuppliers() {
        try {
            List<SupplierDTO> suppliers = supplierDAO.getListSupplier();
            cboSupplier.removeAllItems();
            cboSupplier.addItem("Select a supplier");
            for (SupplierDTO supplier : suppliers) {
                cboSupplier.addItem(supplier.getId() + " - " + supplier.getName());
            }
            cboSupplier.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            cboSupplier.removeAllItems();
            cboSupplier.addItem("Error loading suppliers");
        }
    }



    private void loadProducts() {
        List<ProductDTO> products = productDAO.getList();
        tbProduct.dftbModel.setRowCount(0);
        for (ProductDTO product : products) {
            tbProduct.dftbModel.addRow(new Object[]{
                    product.getId(),
                    product.getName(),
                    product.getQuantity(),
                    product.getUnit(),
                    String.format("%,.0fđ", product.getPrice())
            });
        }
    }

    private void loadImports() {
        tbImport.dftbModel.setRowCount(0);
        int stt = 1;
        for (ProductDTO product : selectedProducts) {
            tbImport.dftbModel.addRow(new Object[]{
                    stt++,
                    product.getId(),
                    product.getName(),
                    product.getQuantity(),
                    product.getUnit(),
                    String.format("%,.0fđ", product.getPrice())
            });
        }
    }


    private void updateTotal() {
        double price = 0;
        double total = 0;
        for (int i = 0; i < tbImport.dftbModel.getRowCount(); i++) {
            String qty = tbImport.dftbModel.getValueAt(i, 3).toString();
            String amountStr = tbImport.dftbModel.getValueAt(i, 5).toString().replace("đ", "").replace(",", "");
            price = Integer.parseInt(qty) * Double.parseDouble(amountStr);
            total += price;
        }
        lbAmount.setText(String.format("%,.0fđ", total));
    }

    private void clearForm() {
        tfID.setText("");
        tfCreate.setText("");
        snQuantity.setValue(1);
        cboSupplier.setSelectedIndex(0);
        selectedProducts.clear();
        updateTotal();
    }

    private void searchProducts(String searchText) {
        try {
            List<ProductDTO> products = productDAO.getList();
            tbProduct.dftbModel.setRowCount(0);
            for (ProductDTO product : products) {
                if (product.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                        String.valueOf(product.getId()).contains(searchText)) {
                    tbProduct.dftbModel.addRow(new Object[]{
                            product.getId(), product.getName(), product.getQuantity(),
                            product.getUnit(), String.format("%,.0fđ", product.getPrice())
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching products: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void addListeners(fManage frame, pnImport thisPanel) {

        btnList.addActionListener( e->{
            new dlImportDetail(frame, thisPanel);
        });

        btnRefresh.addActionListener(e -> {
            loadProducts();
            //loadImports();
            tfSearch.setText("");
            clearForm();
        });

        tfSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchProducts(tfSearch.getText().trim());
            }
        });


        btnAdd.addActionListener(e -> {
            int selectedRow = tbProduct.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    int qty = (Integer) snQuantity.getValue();
                    if (qty <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                        return;
                    }
                    if (tbProduct.dftbModel.getColumnCount() < 5) {
                        JOptionPane.showMessageDialog(this, "Dữ liệu sản phẩm không đầy đủ!");
                        return;
                    }

                    int productID = (int) tbProduct.dftbModel.getValueAt(selectedRow, 0);
                    String productName = (String) tbProduct.dftbModel.getValueAt(selectedRow, 1);
                    int productQuantity = (int) tbProduct.dftbModel.getValueAt(selectedRow, 2);
                    String productUnit = (String) tbProduct.dftbModel.getValueAt(selectedRow, 3);
                    String priceStr = tbProduct.dftbModel.getValueAt(selectedRow, 4).toString().replace("đ", "").replace(",", "");
                    double price = Double.parseDouble(priceStr);

                    int existingQuantity = 0;
                    for (ProductDTO product : selectedProducts) {
                        if (product.getId() == productID) {
                            existingQuantity = product.getQuantity();
                            break;
                        }
                    }
                    if (existingQuantity + qty > productQuantity) {
                        JOptionPane.showMessageDialog(this, "Tổng số lượng phải bé hơn số lượng sản phẩm hiện có: " + productQuantity);
                        return;
                    }

                    boolean productExists = false;
                    for (ProductDTO product : selectedProducts) {
                        if (product.getId() == productID) {
                            product.setQuantity(product.getQuantity() + qty); // Increase quantity
                            productExists = true;
                            break;
                        }
                    }

                    if (!productExists) {
                        ProductDTO product = new ProductDTO(productID, productName, qty, productUnit, price);
                        selectedProducts.add(product);
                    }

                    loadImports();
                    updateTotal();
                    snQuantity.setValue(1);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm sản phẩm: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm");
            }
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = tbImport.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    int productID = (int) tbImport.dftbModel.getValueAt(selectedRow, 1);
                    int newQuantity = (int) snQuantityFix.getValue();

                    if (newQuantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                        return;
                    }

                    ProductDTO productInStock = productDAO.getItemById(productID);
                    if (productInStock == null) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm trong kho!");
                        return;
                    }
                    for (ProductDTO product : selectedProducts) {
                        if (product.getId() == productID) {
                            product.setQuantity(newQuantity);
                            break;
                        }
                    }

                    loadImports();
                    updateTotal();
                    JOptionPane.showMessageDialog(this, "Cập nhật số lượng thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi chỉnh sửa: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong bảng nhập hàng!");
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = tbImport.getSelectedRow();
            if (selectedRow != -1) {
                int productId = (int) tbImport.dftbModel.getValueAt(selectedRow, 1);
                int quantityTable = (int) tbImport.dftbModel.getValueAt(selectedRow, 3);


                ProductDTO productRemove = null;
                for (ProductDTO product : selectedProducts) {
                    if (product.getId() == productId && product.getQuantity() == quantityTable) {
                        productRemove = product;
                        break;
                    }
                }

                if (productRemove != null) {
                    selectedProducts.remove(productRemove);
                    loadImports();
                    updateTotal();
                    snQuantity.setValue(0);
                    JOptionPane.showMessageDialog(this, "Đã xóa sản phẩm khỏi danh sách nhập hàng!");
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm để xóa!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong bảng nhập hàng để xóa!");
            }
        });
    /*
        btnImport.addActionListener(e -> {
            try {
                // Kiểm tra điều kiện đầu vào
                if (cboSupplier.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (selectedProducts.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Danh sách sản phẩm nhập hàng trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtCreate.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin người tạo phiếu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lấy thông tin từ giao diện
                String supplierStr = cboSupplier.getSelectedItem().toString();
                int idSupplier = Integer.parseInt(supplierStr.split(" - ")[0]); // Lấy ID nhà cung cấp từ JComboBox
                int idStaff = Integer.parseInt(txtCreate.getText().trim()); // Giả sử txtCreate chứa ID nhân viên
                double total = Double.parseDouble(lbAmount.getText().replace("đ", "").replace(",", "")); // Tổng tiền
                Timestamp dateCreate = new Timestamp(System.currentTimeMillis()); // Thời gian hiện tại

                // Tạo đối tượng ImportDTO
                ImportDTO importDTO = new ImportDTO(0, idStaff, idSupplier, total, dateCreate); // ID sẽ được tự động sinh

                // Thêm phiếu nhập vào cơ sở dữ liệu
                boolean importSuccess = importBUS.getInstance().getListImport().add(importDTO);
                if (!importSuccess) {
                    JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lấy ID phiếu nhập vừa tạo (giả sử ImportDAO đã cập nhật ID sau khi thêm)
                int importId = importDTO.getIdImport();

                // Thêm chi tiết phiếu nhập
                ImportDtlDAO importDtlDAO = ImportDtlDAO.getInstance();
                for (ProductDTO product : selectedProducts) {
                    ImportDtlDTO detailDTO = new ImportDtlDTO(
                            importId,           // ID phiếu nhập
                            product.getId(),    // ID sản phẩm
                            product.getQuantity(), // Số lượng
                            product.getPrice(), // Đơn giá
                            product.getUnit()   // Đơn vị
                    );
                    boolean detailSuccess = importDtlDAO.insert(detailDTO);
                    if (!detailSuccess) {
                        JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu nhập thất bại cho sản phẩm: " + product.getName(),
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Cập nhật số lượng sản phẩm trong kho (nếu cần)
                    ProductDAO productDAO = ProductDAO.getInstance();
                    ProductDTO updatedProduct = productDAO.getItemById(product.getId());
                    if (updatedProduct != null) {
                        int newQuantity = updatedProduct.getQuantity() + product.getQuantity();
                        updatedProduct.setQuantity(newQuantity);
                        productDAO.update(updatedProduct); // Giả sử ProductDAO có phương thức update
                    }
                }

                // Thông báo thành công và làm mới giao diện
                JOptionPane.showMessageDialog(this, "Nhập hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                clearForm(); // Xóa form
                loadProducts(); // Tải lại danh sách sản phẩm
                loadImports(); // Tải lại danh sách nhập hàng (nếu cần)

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi nhập hàng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }); */

        btnExportExcel.addActionListener(e -> {
            tbImport.ExportExel("Import_List");
        });
    }
}