package GUI.JDialog;

import BUS.ProductBUS;
import Components.*;
import DTO.OfferDTO;
import DTO.ProductDTO;
import GUI.JPanel.pnOffer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class dlChooseProduct extends JDialog {
    // Panel chính
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Chọn sản phẩm áp dụng mã giảm giá", SwingConstants.CENTER, SwingConstants.CENTER);

    // Panel tìm kiếm
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JPanel pnInfo = new MyJPanel(MyColor.White, "Thông tin giảm giá");
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String> cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Tên sản phẩm", "Loại sản phẩm"}, 12);

    // Bảng sản phẩm (bên trái)
    MyJTable tbProduct = new MyJTable(new String[]{"Chọn", "Mã số", "Tên sản phẩm", "Loại", "Giá"},
            new int[]{45,65, 180, 150, 90}, new int[]{0}, new int[]{}) {
        @Override
        public Class<?> getColumnClass(int column) {
            return column == 0 ? Boolean.class : super.getColumnClass(column);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 0;
        }
    };

    // Bảng sản phẩm đã chọn (bên phải)
    MyJTable tbSelected = new MyJTable(new String[]{"Mã số", "Tên sản phẩm", "Loại", "Giá"},
            new int[]{80, 200, 150, 100}, new int[]{}, new int[]{});

    // Các nút chức năng
    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnCancel = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnSelectAll = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.DarkBlue, MyColor.LightBlue, "Chọn tất cả", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnClearAll = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Orange, MyColor.LightGray, "Bỏ chọn tất cả", SwingConstants.CENTER, SwingConstants.CENTER);

    // Data structures
    private List<ProductDTO> selectedProducts = new ArrayList<>();
    private Map<Integer, ProductDTO> productMap = new HashMap<>();
    private Map<Integer, Integer> selectedProductRowMap = new HashMap<>();
    private boolean isUpdating = false;
    private Set<Integer> displayedProductIds = new HashSet<>();

    public dlChooseProduct(pnOffer parentPanel, OfferDTO offer) {
        super();
        setTitle("Chọn sản phẩm áp dụng mã giảm giá");
        setSize(1000, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Configure table renderers/editors
        tbProduct.getColumnModel().getColumn(0).setCellRenderer(tbProduct.getDefaultRenderer(Boolean.class));
        tbProduct.getColumnModel().getColumn(0).setCellEditor(tbProduct.getDefaultEditor(Boolean.class));

        loadProducts(offer.getId());
        setupUI(offer);
        setupEvents(parentPanel, offer.getId());
        setVisible(true);
    }

    private void setupUI(OfferDTO offer) {
        // Header
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 1000, 50);

        // Panel tìm kiếm
        pnSearch.setBounds(10, 60, 480, 80);
        cbSearch.setBounds(30, 90, 140, 30);
        tfSearch.setBounds(180, 90, 200, 30);
        btnRefresh.setBounds(390, 90, 90, 30);

        // Panel thông tin giảm giá
        pnInfo.setBounds(500, 60, 480, 80);
        pnInfo.setLayout(null);
        JLabel lbOfferCode = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã giảm giá: " + offer.getId(),SwingConstants.CENTER, SwingConstants.CENTER);
        JLabel lbDiscount = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giảm giá: " + offer.getValue() + "%",SwingConstants.CENTER, SwingConstants.CENTER);
        JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Bắt đầu: " + offer.getDateStart(),SwingConstants.CENTER, SwingConstants.CENTER);
        JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Kết thúc: " + offer.getDateEnd(),SwingConstants.CENTER, SwingConstants.CENTER);
        lbOfferCode.setBounds(10, 30, 200, 20);
        lbDiscount.setBounds(10, 50, 200, 20);
        lbStartDate.setBounds(220, 30, 250, 20);
        lbEndDate.setBounds(220, 50, 250, 20);
        pnInfo.add(lbOfferCode);
        pnInfo.add(lbDiscount);
        pnInfo.add(lbStartDate);
        pnInfo.add(lbEndDate);

        // Bảng sản phẩm (bên trái)
        JPanel pnLeft = new MyJPanel(MyColor.White, "Danh sách sản phẩm");
        pnLeft.setBounds(10, 150, 480, 350);
        tbProduct.scrPn.setBounds(10, 30, 460, 310);
        pnLeft.add(tbProduct.scrPn);

        // Bảng sản phẩm đã chọn (bên phải)
        JPanel pnRight = new MyJPanel(MyColor.White, "Sản phẩm đã chọn");
        pnRight.setBounds(500, 150, 480, 350);
        tbSelected.scrPn.setBounds(10, 30, 460, 310);
        pnRight.add(tbSelected.scrPn);

        // Các nút chức năng
        btnSelectAll.setBounds(100, 510, 150, 35);
        btnClearAll.setBounds(260, 510, 150, 35);
        btnSave.setBounds(700, 510, 120, 35);
        btnCancel.setBounds(830, 510, 120, 35);

        // Thêm components vào dialog
        add(cbSearch);
        add(tfSearch);
        add(btnRefresh);
        add(pnSearch);
        add(pnInfo);
        add(pnLeft);
        add(pnRight);
        add(btnSelectAll);
        add(btnClearAll);
        add(btnSave);
        add(btnCancel);
        add(lbHeader);
    }

    private void setupEvents(pnOffer parentPanel, int idOffer) {
        btnCancel.addActionListener(_ -> dispose());
        tbProduct.getModel().addTableModelListener(e -> {
            if (e.getColumn() == 0) {
                updateSelectedProducts();
            }
        });

        btnSave.addActionListener(_ -> {
            if (selectedProducts.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                updateoffer(selectedProducts, idOffer);
                dispose();
            }
        });

        btnSelectAll.addActionListener(_ -> {
            isUpdating = true;
            for (int i = 0; i < tbProduct.getRowCount(); i++) {
                tbProduct.setValueAt(true, i, 0);
            }
            isUpdating = false;
            updateAllSelectedProducts();
        });

        btnClearAll.addActionListener(_ -> {
            isUpdating = true;
            for (int i = 0; i < tbProduct.getRowCount(); i++) {
                tbProduct.setValueAt(false, i, 0);
            }
            tbSelected.dftbModel.setRowCount(0);
            selectedProducts.clear();
            selectedProductRowMap.clear();
            isUpdating = false;
        });

        btnRefresh.addActionListener(_ -> {
            cbSearch.setSelectedIndex(0);
            tfSearch.setText("");
            loadProducts(idOffer);
        });

        cbSearch.addActionListener(_ -> filterProducts());
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterProducts(); }
            public void removeUpdate(DocumentEvent e) { filterProducts(); }
            public void changedUpdate(DocumentEvent e) { filterProducts(); }
        });

        tbSelected.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tbSelected.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        removeSelectedProduct(row);
                    }
                }
            }
        });
    }

    private void updateoffer(List<ProductDTO> selectedProducts, int idOffer) {
        for (ProductDTO pro : selectedProducts) {
            ProductBUS.getInstance().updateoffer(pro.getId(), idOffer);
        }
    }

    private void loadProducts(int offer) {
        tbProduct.dftbModel.setRowCount(0);
        tbSelected.dftbModel.setRowCount(0);
        productMap.clear();
        selectedProducts.clear();
        selectedProductRowMap.clear();
        displayedProductIds.clear();

        List<ProductDTO> products = ProductBUS.getInstance().load();
        List<ProductDTO> preSelectedProducts = offer != 0 ?
                ProductBUS.getInstance().getListofoffer(offer) : new ArrayList<>();

        for (ProductDTO product : products) {
            if (displayedProductIds.contains(product.getId())) {
                continue;
            }
            displayedProductIds.add(product.getId());
            productMap.put(product.getId(), product);

            boolean isSelected = preSelectedProducts.stream()
                    .anyMatch(p -> p.getId() == product.getId());

            tbProduct.dftbModel.addRow(new Object[]{
                    isSelected,
                    product.getId(),
                    product.getName(),
                    product.getType().getName(),
                    product.getPrice()
            });

            if (isSelected) {
                selectedProducts.add(product);
                tbSelected.dftbModel.addRow(new Object[]{
                        product.getId(),
                        product.getName(),
                        product.getType().getName(),
                        product.getPrice()
                });
                selectedProductRowMap.put(product.getId(), tbSelected.getRowCount() - 1);
            }
        }
    }

    private void filterProducts() {
        tbProduct.dftbModel.setRowCount(0);
        displayedProductIds.clear();

        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText().toLowerCase();

        productMap.values().stream()
                .filter(product -> {
                    switch (col) {
                        case 0:
                            return String.valueOf(product.getId()).toLowerCase().contains(txt);
                        case 1:
                            return product.getName().toLowerCase().contains(txt);
                        case 2:
                            return product.getType().getName().toLowerCase().contains(txt);
                        default:
                            return true;
                    }
                })
                .forEach(product -> {
                    if (displayedProductIds.contains(product.getId())) {
                        return;
                    }
                    displayedProductIds.add(product.getId());

                    boolean isSelected = selectedProducts.stream()
                            .anyMatch(p -> p.getId() == product.getId());

                    tbProduct.dftbModel.addRow(new Object[]{
                            isSelected,
                            product.getId(),
                            product.getName(),
                            product.getType().getName(),
                            product.getPrice()
                    });
                });
    }

    private void updateSelectedProducts() {
        if (isUpdating) return;

        selectedProducts.clear();
        tbSelected.dftbModel.setRowCount(0);
        selectedProductRowMap.clear();

        Set<Integer> addedProductIds = new HashSet<>();

        for (int i = 0; i < tbProduct.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tbProduct.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                int id = Integer.parseInt(tbProduct.getValueAt(i, 1).toString());
                if (addedProductIds.contains(id)) {
                    continue;
                }
                addedProductIds.add(id);

                ProductDTO product = productMap.get(id);
                if (product != null) {
                    selectedProducts.add(product);
                    tbSelected.dftbModel.addRow(new Object[]{
                            product.getId(),
                            product.getName(),
                            product.getType().getName(),
                            product.getPrice()
                    });
                    selectedProductRowMap.put(product.getId(), tbSelected.getRowCount() - 1);
                }
            }
        }
    }

    private void updateAllSelectedProducts() {
        selectedProducts.clear();
        tbSelected.dftbModel.setRowCount(0);
        selectedProductRowMap.clear();

        Set<Integer> addedProductIds = new HashSet<>();

        for (int i = 0; i < tbProduct.getRowCount(); i++) {
            int id = Integer.parseInt(tbProduct.getValueAt(i, 1).toString());
            if (addedProductIds.contains(id)) {
                continue;
            }
            addedProductIds.add(id);

            ProductDTO product = productMap.get(id);
            if (product != null) {
                selectedProducts.add(product);
                tbSelected.dftbModel.addRow(new Object[]{
                        product.getId(),
                        product.getName(),
                        product.getType().getName(),
                        product.getPrice()
                });
                selectedProductRowMap.put(product.getId(), tbSelected.getRowCount() - 1);
            }
        }
    }

    private void removeSelectedProduct(int row) {
        try {
            int productId = Integer.parseInt(tbSelected.getValueAt(row, 0).toString());

            for (int i = 0; i < tbProduct.getRowCount(); i++) {
                if (Integer.parseInt(tbProduct.getValueAt(i, 1).toString()) == productId) {
                    tbProduct.setValueAt(false, i, 0);
                    break;
                }
            }

            selectedProducts.removeIf(p -> p.getId() == productId);
            selectedProductRowMap.remove(productId);
            updateSelectedProducts();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}