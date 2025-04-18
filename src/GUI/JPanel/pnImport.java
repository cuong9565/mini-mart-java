package GUI.JPanel;

import BUS.ProductBUS;
import BUS.SupplierBUS;
import Components.*;
import DAO.*;
import DAO.SupplierDAO;
import DTO.*;
import DTO.SupplierDTO;
import GUI.JDialog.dlImportDetail;
import GUI.JFrame.fManage;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class pnImport extends JPanel {
    // UI Components
    JPanel pnMain = new MyJPanel(MyColor.White);
    JPanel pnInfoSupplier = new MyJPanel(MyColor.White, "Thông tin nhà cung cấp");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm sản phẩm");
    JPanel pnInfoImport = new MyJPanel(MyColor.White, "Thông tin phiếu nhập");

    JLabel lbIdImport = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã phiếu nhập", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbNameStaff = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbNameSupplier = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên nhà cung cấp", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfIdImport = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfNameStaff = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfNameSupplier = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JButton btnSearchSupplier = new MyJButton(Font.BOLD, 12, MyColor.Black, MyColor.LightGray, "...", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRemoveSupplier = new MyJButton(Font.PLAIN, 12, MyColor.Black, MyColor.LightGray, "X", SwingConstants.CENTER, SwingConstants.CENTER);


    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã", "Tên", "Đơn giá", "Đơn vị", "Số lượng"}, 12);

    MyJTable tbProduct = new MyJTable(new String[]{"Mã", "Tên", "Đơn giá", "Đơn vị", "Số lượng"}, 12, new int[]{50, 150, 100, 50}, new int[]{1}, new int[]{});
    MyJTable tbInfoImport = new MyJTable(new String[]{"Mã", "Tên" , "Đơn giá", "Số lượng", "Đơn vị", "Thành tiền"}, 12, new int[]{10, 60, 50, 15, 15}, new int[]{1}, new int[]{});

    JLabel lbQuantity = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner snQuantity = new MyJSpinner(1, 1, 1000000000, 1);
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);

    JButton btnExportExcel = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Nhập Excel", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbQuantityFix = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner snQuantityFix = new MyJSpinner(1, 1, 1000000000, 1);

    JLabel lbTotal = new MyJLabel(Font.BOLD, 14, MyColor.Black, "Tổng tiền: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAmount = new MyJLabel(Font.BOLD, 14, MyColor.Black, "0đ", SwingConstants.LEFT, SwingConstants.CENTER);

    JButton btnCancel = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Hủy đơn", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnPay = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thanh toán", SwingConstants.CENTER, SwingConstants.CENTER);


    private final ProductDAO productDAO = ProductDAO.getInstance();
    private List<ProductDTO> selectedProducts = new ArrayList<>();
    pnImport thisPanel = this;

    StaffDTO staffLoginGlobal;


    public pnImport(fManage parentFrame, StaffDTO staffLogin) {
        setLayout(null);
        setBackground(MyColor.White);

        staffLoginGlobal = staffLogin;

        // region setBounds
        pnMain.setBounds(0, 0, 1170, 750);
        pnInfoSupplier.setBounds(0, 0, 1160, 70);
        pnInfoImport.setBounds(480,70,680, 135);
        pnSearch.setBounds(0, 70, 460, 60);

        lbIdImport.setBounds(500, 85, 200, 20);
        tfIdImport.setBounds(500, 105, 200, 30);
        lbNameStaff.setBounds(890,85,200,20);
        tfNameStaff.setBounds(890,105,200,30);

        lbNameSupplier.setBounds(890,145,200,20);
        tfNameSupplier.setBounds(890,165,200,30);

        cbSearch.setBounds(10, 90, 140, 30);
        tfSearch.setBounds(160, 90, 190, 30);
        btnRefresh.setBounds(360, 90, 90, 30);

        tbProduct.scrPn.setBounds(0, 140, 460, 550);
        tbInfoImport.scrPn.setBounds(480, 215, 680, 420);
        btnSearchSupplier.setBounds(1095, 174, 20, 20);
        btnRemoveSupplier.setBounds(1120, 174, 20, 20);


        lbQuantity.setBounds(0, 700, 70, 30);
        snQuantity.setBounds(70, 700, 100, 30);
        btnAdd.setBounds(180, 700, 100, 30);

        btnExportExcel.setBounds(480, 650, 100, 30);
        btnDelete.setBounds(480, 650, 100, 30);

        btnEdit.setBounds(1060, 650, 100, 30);
        lbQuantityFix.setBounds(880,650,100,30);
        snQuantityFix.setBounds(950,650,100,30);

        lbTotal.setBounds(480, 700, 80, 30);
        lbAmount.setBounds(560, 700, 190, 30);
        btnPay.setBounds(950, 700, 100, 30);
        btnCancel.setBounds(1060, 700, 100, 30);
        // endregion

        // setText
        tfNameStaff.setText(staffLogin.getLastName() + " " + staffLogin.getFirstName());

        // This panel
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                load();
            }
        });

        // Product
        btnRefresh.addActionListener(_ -> {
            cbSearch.setSelectedIndex(0);
            tfSearch.setText("");
            loadProduct();
        });
        cbSearch.addActionListener(_ -> loadProduct());
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textProductChange();}
            public void removeUpdate(DocumentEvent e) {textProductChange();}
            public void changedUpdate(DocumentEvent e) {textProductChange();}
        });

        // region add
        add(tfNameSupplier);
        add(btnSearchSupplier);
        add(btnRemoveSupplier);
        add(lbIdImport);
        add(tfIdImport);
        add(lbNameStaff);
        add(tfNameStaff);
        add(lbNameSupplier);
        add(cbSearch);
        add(btnRefresh);
        add(tfSearch);
        add(tbProduct.scrPn);
        add(tbInfoImport.scrPn);
        add(lbQuantity);
        add(snQuantity);
        add(btnAdd);
        add(btnDelete);
        add(btnEdit);
        add(btnPay);
        add(btnCancel);
        add(lbQuantityFix);
        add(snQuantityFix);
        add(lbTotal);
        add(lbAmount);
        add(pnInfoImport);
        add(pnSearch);
        add(pnInfoSupplier);
        add(pnMain);
        // endregion
    }

    public void load(){
        loadProduct();
    }

    private void loadSupplier() {
//        try {
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(),"Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
    }


    // Product
    private void loadProduct() {
        ProductBUS.getInstance().load();
        textProductChange();
    }

    public void textProductChange() {
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        tbProduct.dftbModel.setRowCount(0);
        for (ProductDTO p: ProductBUS.getInstance().getListSearchSell(col, txt))
            tbProduct.dftbModel.addRow(p.getRowObjectsSell());
    }





    private void loadImports() {
        tbInfoImport.dftbModel.setRowCount(0);
        int stt = 1;
        for (ProductDTO product : selectedProducts) {
            tbInfoImport.dftbModel.addRow(new Object[]{
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
        for (int i = 0; i < tbInfoImport.dftbModel.getRowCount(); i++) {
            String qty = tbInfoImport.dftbModel.getValueAt(i, 3).toString();
            String amountStr = tbInfoImport.dftbModel.getValueAt(i, 5).toString().replace("đ", "").replace(",", "");
            price = Integer.parseInt(qty) * Double.parseDouble(amountStr);
            total += price;
        }
        lbAmount.setText(String.format("%,.0fđ", total));
    }

    private void clearForm() {
        tfIdImport.setText("");
        tfNameStaff.setText("");
        snQuantity.setValue(1);
//        cboSupplier.setSelectedIndex(0);
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

        btnPay.addActionListener( e->{
            new dlImportDetail(frame, thisPanel);
        });

        btnRefresh.addActionListener(e -> {
//            loadProducts();
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
            int selectedRow = tbInfoImport.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    int productID = (int) tbInfoImport.dftbModel.getValueAt(selectedRow, 1);
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
            int selectedRow = tbInfoImport.getSelectedRow();
            if (selectedRow != -1) {
                int productId = (int) tbInfoImport.dftbModel.getValueAt(selectedRow, 1);
                int quantityTable = (int) tbInfoImport.dftbModel.getValueAt(selectedRow, 3);


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

        btnExportExcel.addActionListener(e -> {
            tbInfoImport.ExportExel("Import_List");
        });
    }
}