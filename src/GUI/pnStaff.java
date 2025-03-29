package GUI;

import BUS.Staff_BUS;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

public class pnStaff extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tbStaff;
    private final TableRowSorter<DefaultTableModel> sorter;
    private final Staff_BUS staffBUS;

    // Các thành phần giao diện
    private final JButton btnAdd = new JButton("Thêm");
    private final JButton btnEdit = new JButton("Sửa");
    private final JButton btnBlock = new JButton("Khóa");
    private final JButton btnExportExcel = new JButton("Xuất Excel");
    private final JButton btnRefresh = new JButton("Tải lại");
    private final JTextField txtSearch = new JTextField("Nhập mã NV hoặc tên...");
    private final JComboBox<String> sortComboBox;

    // Panels
    private final JPanel panelFunction = new JPanel();
    private final JPanel panelSearch = new JPanel();
    private final JPanel panelHeader = new JPanel();
    private final JPanel panelDisplay = new JPanel();

    public pnStaff() {
        staffBUS = new Staff_BUS(model); // Truyền model vào BUS
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F5F5"));
        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        customizeButton(btnAdd, new Color(50, 168, 82));
        customizeButton(btnEdit, new Color(255, 165, 0));
        customizeButton(btnBlock, new Color(255, 69, 58));
        JButton btnImportExcel = new JButton("Nhập Excel");
        customizeButton(btnImportExcel, new Color(66, 133, 244));
        customizeButton(btnExportExcel, new Color(66, 133, 244));
        customizeButton(btnRefresh, new Color(66, 133, 244));

        // Thiết lập panelFunction
        panelFunction.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelFunction.setBorder(BorderFactory.createTitledBorder(border, "Chức năng", 0, 0, new Font("Arial", Font.BOLD, 14)));
        panelFunction.setBackground(Color.WHITE);
        panelFunction.add(btnAdd);
        panelFunction.add(btnEdit);
        panelFunction.add(btnBlock);
        panelFunction.add(btnImportExcel);
        panelFunction.add(btnExportExcel);

        // Thiết lập panelSearch
        panelSearch.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        panelSearch.setBorder(BorderFactory.createTitledBorder(border, "Tìm kiếm & Sắp xếp", 0, 0, new Font("Arial", Font.BOLD, 14)));
        panelHeader.setBackground(new Color(230, 240, 255));
        txtSearch.setPreferredSize(new Dimension(200, 30));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        txtSearch.setForeground(Color.GRAY);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));

        // Cập nhật sortComboBox với các tùy chọn sắp xếp nâng cao
        String[] sortOptions = {
                "Lương (tăng dần)",
                "Lương (giảm dần)",
                "NAM",
                "NỮ",
                "Quản lý",
                "Nhân Viên"
        };
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
        panelHeader.setBackground(Color.decode("#09D1C7"));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Thiết lập bảng
        String[] columns = {"Mã NV", "Số điện thoại", "Pass", "Họ", "Tên", "Địa chỉ", "Lương", "Role", "Trạng thái", "Giới tính"};
        model.setColumnIdentifiers(columns);
        tbStaff = new JTable(model);
        tbStaff.setRowHeight(28);
        tbStaff.setFont(new Font("Arial", Font.PLAIN, 14));
        tbStaff.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 14));
        tbStaff.getTableHeader().setPreferredSize(new Dimension(0, 28));
        tbStaff.getTableHeader().setBackground(new Color(178, 236, 255));
        tbStaff.getTableHeader().setForeground(Color.BLACK);
        tbStaff.getTableHeader().setReorderingAllowed(false);
        tbStaff.setBorder(BorderFactory.createEmptyBorder());
        tbStaff.setShowGrid(false);
        tbStaff.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(null);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBackground(new Color(66, 133, 244));
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Roboto", Font.BOLD, 16));
                return label;
            }
        });
        tbStaff.setIntercellSpacing(new Dimension(0, 1));
        tbStaff.setFocusable(false);
        tbStaff.setShowVerticalLines(false);
        tbStaff.setShowHorizontalLines(true);
        tbStaff.setGridColor(new Color(240, 240, 240));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tbStaff.getColumnCount(); i++) {
            tbStaff.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tbStaff);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);
        TableColumnModel columnModel = tbStaff.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(60);
        columnModel.getColumn(4).setPreferredWidth(60);
        columnModel.getColumn(5).setPreferredWidth(170);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(95);
        columnModel.getColumn(8).setPreferredWidth(100);
        columnModel.getColumn(9).setPreferredWidth(70);

        sorter = new TableRowSorter<>(model);
        tbStaff.setRowSorter(sorter);

        // Xử lý cột Lương (nếu là String, cần Comparator để sắp xếp số)
        sorter.setComparator(6, (o1, o2) -> {
            try {
                Double d1 = Double.parseDouble(o1.toString());
                Double d2 = Double.parseDouble(o2.toString());
                return d1.compareTo(d2);
            } catch (NumberFormatException e) {
                return o1.toString().compareTo(o2.toString());
            }
        });

        // Load dữ liệu ban đầu
        staffBUS.loadStaffData();

        // Sự kiện tìm kiếm
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
            if (searchText.isEmpty() || searchText.equals("nhập nội dung tìm kiếm ...")) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(searchText)));
            }
        });

        // Sự kiện sắp xếp cho sortComboBox
        sortComboBox.addActionListener(e -> {
            int selectedIndex = sortComboBox.getSelectedIndex();
            switch (selectedIndex) {
                case 0: // Lương (tăng dần)
                    sorter.setSortKeys(java.util.Collections.singletonList(
                            new RowSorter.SortKey(6, SortOrder.ASCENDING)
                    ));
                    break;

                case 1: // Lương (giảm dần)
                    sorter.setSortKeys(java.util.Collections.singletonList(
                            new RowSorter.SortKey(6, SortOrder.DESCENDING)
                    ));
                    break;
                case 2: // Nam
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)^Nam$", 9));
                    break;
                case 3: // Nữ
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)^Nữ$", 9));
                    sorter.setSortKeys(null); // Xóa sắp xếp
                    break;
                case 4: // Quản lý
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)^Quản lý$", 7));
                    sorter.setSortKeys(null);
                    break;
                case 5: // Nhân viên
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)^Nhân viên$", 7));
                    sorter.setSortKeys(null);
                    break;
                default:
                    sorter.setSortKeys(null);
                    break;
            }
            tbStaff.repaint();
        });

        // Sự kiện cho các nút
        btnAdd.addActionListener(e -> staffBUS.showAddDialog(this));
        btnEdit.addActionListener(e -> staffBUS.showEditDialog(this, tbStaff.getSelectedRow()));
        tbStaff.getSelectedRow();
        btnBlock.addActionListener(e -> {
            int selectedRow = tbStaff.getSelectedRow();
            String status = (String) tbStaff.getValueAt(selectedRow,8);
            if (selectedRow >= 0) {
                String id = (String) model.getValueAt(selectedRow, 0);
                if (staffBUS.lockStaff(id,status)) {
                    if (status.equals("Active")) {
                        JOptionPane.showMessageDialog(this, "Đã khóa tài khoản!");
                    }
                    else {
                        JOptionPane.showMessageDialog(this,"Đã mở khóa tài khoản!");
                    }
                    staffBUS.loadStaffData();
                }
            }
        });
        // btnImportExcel.addActionListener(e -> staffBUS.importExcel(this));
        btnExportExcel.addActionListener(e -> staffBUS.exportExcel(this));
        btnRefresh.addActionListener(e -> staffBUS.refreshTable(txtSearch, sorter, sortComboBox));

        panelDisplay.setLayout(new BorderLayout());
        panelDisplay.add(scrollPane, BorderLayout.CENTER);
        panelDisplay.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panelDisplay.setBackground(Color.WHITE);

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
        Color hoverColor = backgroundColor.brighter(); // Sáng hơn 20%
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });
    }
}