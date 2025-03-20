package pnForm;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnDiscount extends JPanel {
    private DefaultTableModel model = new DefaultTableModel();
    private JTable tbDiscountCodes;
    private TableRowSorter<DefaultTableModel> sorter;

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
    private JPanel panelFunction = new JPanel(); // Chứa các nút chức năng
    private JPanel panelSearch = new JPanel();   // Chứa tìm kiếm và sắp xếp
    private JPanel panelHeader = new JPanel();   // Chứa panelFunction và panelSearch
    private JPanel panelDisplay = new JPanel();  // Chứa bảng

    public pnDiscount() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F5F5")); // xám
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
        customizeButton(btnExportExcel, new Color(66, 133, 244));


        panelFunction.add(btnAdd);
        panelFunction.add(btnEdit);
        panelFunction.add(btnDelete);
        panelFunction.add(btnImportExcel); // Thêm nút Nhập Excel
        panelFunction.add(btnExportExcel);


        // Thiết lập panelSearch (Tìm kiếm và sắp xếp)
        panelSearch.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        panelSearch.setBorder(BorderFactory.createTitledBorder(border, "Tìm kiếm & Sắp xếp", 0, 0, new Font("Arial", Font.BOLD, 14)));
        panelSearch.setBackground(Color.WHITE);

        txtSearch.setPreferredSize(new Dimension(200, 30));
        txtSearch.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        txtSearch.setForeground(Color.GRAY);

        String[] sortOptions = {"STT (tăng dần)", "Hạn sử dụng", "Trạng thái"};
        sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.setPreferredSize(new Dimension(150, 30));
        sortComboBox.setBackground(Color.WHITE);
        sortComboBox.setForeground(Color.GRAY);
        sortComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Tùy chỉnh giao diện JComboBox
        sortComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new JButton("v");
                arrowButton.setBackground(Color.WHITE);
                arrowButton.setForeground(Color.GRAY);
                arrowButton.setBorder(BorderFactory.createEmptyBorder());
                arrowButton.setFocusPainted(false);
                arrowButton.setFont(new Font("Arial", Font.PLAIN, 12));
                return arrowButton;
            }
        });

        sortComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
                setForeground(Color.GRAY);
                setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                return this;
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

        // Thiết lập bảng
        String[] columns = {"STT", "ID mã", "Loại", "Hạn sử dụng", "Trạng thái"};
        model.setColumnIdentifiers(columns);
        tbDiscountCodes = new JTable(model);
        tbDiscountCodes.setRowHeight(30);
        tbDiscountCodes.setFont(new Font("Arial", Font.PLAIN, 14));
        tbDiscountCodes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tbDiscountCodes.getTableHeader().setBackground(new Color(66, 133, 244));
        tbDiscountCodes.getTableHeader().setForeground(Color.WHITE);

        // Điều chỉnh chiều rộng cột
        TableColumnModel columnModel = tbDiscountCodes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(100);

        // Thiết lập TableRowSorter
        sorter = new TableRowSorter<>(model);
        tbDiscountCodes.setRowSorter(sorter);

        // Thêm dữ liệu mẫu
        addSampleData();
        // Thêm sự kiện cho JComboBox
//        sortComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String selectedOption = (String) sortComboBox.getSelectedItem();
//                switch (selectedOption) {
//                    case "STT (tăng dần)":
//                        sorter.setSortKeys(java.util.Collections.singletonList(
//                                new RowSorter.SortKey(0, SortOrder.ASCENDING)));
//                        break;
//                    case "Hạn sử dụng":
//                        sorter.setSortKeys(java.util.Collections.singletonList(
//                                new RowSorter.SortKey(3, SortOrder.ASCENDING)));
//                        break;
//                    case "Trạng thái":
//                        sorter.setSortKeys(java.util.Collections.singletonList(
//                                new RowSorter.SortKey(4, SortOrder.ASCENDING)));
//                        break;
//                }
//            }
//        });

        // Thêm sự kiện tìm kiếm
        txtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = txtSearch.getText().trim().toLowerCase();
                if (searchText.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 1, 2)); // Tìm kiếm ở cột ID mã và Loại
                }
            }
        });

        // Thêm sự kiện cho các nút
        btnAdd.addActionListener(e -> showAddDialog());
        btnEdit.addActionListener(e -> showEditDialog());
        btnDelete.addActionListener(e -> deleteSelectedRow());
        btnRefresh.addActionListener(e -> refreshTable());
        btnImportExcel.addActionListener(e -> importFromExcel()); // Sự kiện Nhập Excel
        btnExportExcel.addActionListener(e -> exportToExcel());

        JScrollPane scrollPane = new JScrollPane(tbDiscountCodes);
        panelDisplay.setLayout(new BorderLayout());
        panelDisplay.add(scrollPane, BorderLayout.CENTER);
        panelDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Thêm các panel vào giao diện chính
        add(panelHeader, BorderLayout.NORTH);
        add(panelDisplay, BorderLayout.CENTER);
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

    // Thêm dữ liệu mẫu
    private void addSampleData() {
        model.addRow(new Object[]{1, "CODE001", "Giảm giá sản phẩm", "2025-12-31", "Hoạt động"});
        model.addRow(new Object[]{2, "CODE002", "Giảm giá hóa đơn", "2024-11-30", "Hết hạn"});
        model.addRow(new Object[]{3, "CODE003", "Giảm giá sản phẩm", "2025-06-30", "Hoạt động"});
    }

    // Hiển thị dialog để thêm mã giảm giá
    private void showAddDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm mã giảm giá", true);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setSize(300, 250);
        dialog.setLocationRelativeTo(this);

        JTextField txtId = new JTextField();
        JComboBox<String> cbType = new JComboBox<>(new String[]{"Giảm giá sản phẩm", "Giảm giá hóa đơn"});
        JTextField txtExpiry = new JTextField("YYYY-MM-DD");
        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Hoạt động", "Hết hạn"});

        dialog.add(new JLabel("ID mã:"));
        dialog.add(txtId);
        dialog.add(new JLabel("Loại:"));
        dialog.add(cbType);
        dialog.add(new JLabel("Hạn sử dụng:"));
        dialog.add(txtExpiry);
        dialog.add(new JLabel("Trạng thái:"));
        dialog.add(cbStatus);

        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(50, 168, 82));
        btnSave.setForeground(Color.WHITE);
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(255, 69, 58));
        btnCancel.setForeground(Color.WHITE);

        dialog.add(btnSave);
        dialog.add(btnCancel);

        btnSave.addActionListener(e -> {
            String id = txtId.getText();
            String type = (String) cbType.getSelectedItem();
            String expiry = txtExpiry.getText();
            String status = (String) cbStatus.getSelectedItem();

            if (id.isEmpty() || expiry.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!");
                return;
            }

            int stt = model.getRowCount() + 1;
            model.addRow(new Object[]{stt, id, type, expiry, status});
            dialog.dispose();
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Hiển thị dialog để sửa mã giảm giá
    private void showEditDialog() {
        int selectedRow = tbDiscountCodes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã giảm giá để sửa!");
            return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Sửa mã giảm giá", true);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setSize(300, 250);
        dialog.setLocationRelativeTo(this);

        JTextField txtId = new JTextField((String) model.getValueAt(selectedRow, 1));
        JComboBox<String> cbType = new JComboBox<>(new String[]{"Giảm giá sản phẩm", "Giảm giá hóa đơn"});
        cbType.setSelectedItem(model.getValueAt(selectedRow, 2));
        JTextField txtExpiry = new JTextField((String) model.getValueAt(selectedRow, 3));
        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Hoạt động", "Hết hạn"});
        cbStatus.setSelectedItem(model.getValueAt(selectedRow, 4));

        dialog.add(new JLabel("ID mã:"));
        dialog.add(txtId);
        dialog.add(new JLabel("Loại:"));
        dialog.add(cbType);
        dialog.add(new JLabel("Hạn sử dụng:"));
        dialog.add(txtExpiry);
        dialog.add(new JLabel("Trạng thái:"));
        dialog.add(cbStatus);

        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(50, 168, 82));
        btnSave.setForeground(Color.WHITE);
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(255, 69, 58));
        btnCancel.setForeground(Color.WHITE);

        dialog.add(btnSave);
        dialog.add(btnCancel);

        btnSave.addActionListener(e -> {
            String id = txtId.getText();
            String type = (String) cbType.getSelectedItem();
            String expiry = txtExpiry.getText();
            String status = (String) cbStatus.getSelectedItem();

            if (id.isEmpty() || expiry.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!");
                return;
            }

            model.setValueAt(id, selectedRow, 1);
            model.setValueAt(type, selectedRow, 2);
            model.setValueAt(expiry, selectedRow, 3);
            model.setValueAt(status, selectedRow, 4);
            dialog.dispose();
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Xóa dòng được chọn
    private void deleteSelectedRow() {
        int selectedRow = tbDiscountCodes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã giảm giá để xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa mã này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.removeRow(selectedRow);
            // Cập nhật lại STT
            for (int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0);
            }
        }
    }
    private void exportToExcel() {

    }

    private void importFromExcel() {

    }
    // Làm mới bảng
    private void refreshTable() {
        txtSearch.setText("Nhập ID mã hoặc loại...");
        sorter.setRowFilter(null);
        sortComboBox.setSelectedIndex(0);
    }

}