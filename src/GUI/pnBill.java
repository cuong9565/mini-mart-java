package GUI;

import Components.MyColor;
import Components.MyJButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

public class pnBill extends JPanel {
    private DefaultTableModel model = new DefaultTableModel();
    private JTable tbInvoice;
    private JButton btnCancel = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnFind = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Tìm", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnAdd = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnEdit = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnReload = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Tải lại", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnLoad = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Load", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField txtFilter = new JTextField("Nhập nội dung");

    // panel
    JPanel panelFunction = new JPanel();  // them, sua, huy
    JPanel panelFind = new JPanel();     // tim kiem, loc thong tin
    JPanel panelHeader = new JPanel();
    JPanel panelDisplay = new JPanel();  // hien danh sach hoa don

    // Dữ liệu gốc để tải lại
    private Object[][] originalData;

    public pnBill() {
        setLayout(null);
        setBackground(Color.decode("#FFFFFF"));

        // panel function
        panelFunction.add(btnAdd);
        panelFunction.add(btnEdit);
        panelFunction.add(btnCancel);
        panelFunction.setLayout(new GridLayout(1, 3, 10, 20));
        panelFunction.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        btnAdd.setPreferredSize(new Dimension(100, 35));
        btnEdit.setPreferredSize(new Dimension(100, 35));
        btnCancel.setPreferredSize(new Dimension(100, 35));

        // panel find
        panelFind.add(txtFilter);
        panelFind.add(btnFind);
        panelFind.add(btnLoad);
        Border border = BorderFactory.createLineBorder(Color.gray, 1);
        panelFind.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txtFilter.setPreferredSize(new Dimension(200, 30));
        btnFind.setPreferredSize(new Dimension(80, 30));
        btnLoad.setPreferredSize(new Dimension(80, 30));

        // panel header
        panelHeader.add(panelFunction);
        panelHeader.add(panelFind);
        panelHeader.setLayout(new GridLayout(1, 2, 15, 25));
        panelHeader.setBounds(10, 54, 950, 80);

        // panel display
        String[] columns = {"STT", "Mã HĐ", "ID Khách", "Khách hàng", "Ngày lập", "Tổng tiền", "Trạng thái", "Chi tiết"};
        model.setColumnIdentifiers(columns);
        tbInvoice = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Chỉ cột "Chi tiết" có thể tương tác
            }
        };
        tbInvoice.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        tbInvoice.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox()));
        JScrollPane scrollPane = new JScrollPane(tbInvoice);
        panelDisplay.setBackground(Color.decode("#FFFFFF"));
        panelDisplay.setLayout(new GridLayout(1, 1));
        panelDisplay.add(scrollPane);
        panelDisplay.setBounds(10, 170, 950, 550);

        add(panelHeader);
        add(panelDisplay);

        // Sự kiện nhấp chuột cho txtFilter
        txtFilter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Nhấp chuột trái
                    txtFilter.setText("");
                }
            }
        });

        // Thêm sự kiện cho các button
        btnAdd.addActionListener(e -> addInvoice());
        btnEdit.addActionListener(e -> editInvoice());
        btnCancel.addActionListener(e -> cancelInvoice());
        btnFind.addActionListener(e -> findInvoice());
        btnReload.addActionListener(e -> reloadData());
        btnLoad.addActionListener(e -> loadOriginalData());
        // Tải dữ liệu mẫu ban đầu
        loadSampleData();
    }

    private void loadSampleData() {
        originalData = new Object[][]{
                {"HD001", "KH001", "Nguyễn Văn A", "2025-03-15", "1500000", "Hoàn thành"},
                {"HD002", "KH002", "Trần Thị B", "2025-03-16", "2500000", "Đang xử lý"},
                {"HD003", "KH003", "Lê Văn C", "2025-03-17", "800000", "Hoàn thành"},
                {"HD004", "KH004", "Phạm Thị D", "2025-03-17", "1200000", "Đã hủy"},
                {"HD005", "KH005", "Hoàng Văn E", "2025-03-18", "3000000", "Hoàn thành"}
        };
        updateTable(originalData);
    }

    private void updateTable(Object[][] data) {
        model.setRowCount(0); // Xóa dữ liệu hiện tại
        for (int i = 0; i < data.length; i++) {
            Object[] row = new Object[8];
            row[0] = String.valueOf(i + 1); // STT tự động tăng
            System.arraycopy(data[i], 0, row, 1, data[i].length);
            row[7] = "Xem"; // Nút trong cột Chi tiết
            model.addRow(row);
        }
    }

    private void addInvoice() {
        JTextField[] fields = {
                new JTextField(10), new JTextField(10), new JTextField(15),
                new JTextField(15), new JTextField(15), new JTextField(15)
        };
        String[] labels = {"Mã HĐ", "ID Khách", "Khách hàng", "Ngày lập", "Tổng tiền", "Trạng thái"};

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        for (int i = 0; i < labels.length; i++) {
            inputPanel.add(new JLabel(labels[i] + ":"));
            inputPanel.add(fields[i]);
        }

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Thêm hóa đơn mới",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Object[] newRow = new Object[6];
            for (int i = 0; i < fields.length; i++) {
                newRow[i] = fields[i].getText();
            }
            Object[][] newData = new Object[originalData.length + 1][6];
            System.arraycopy(originalData, 0, newData, 0, originalData.length);
            newData[originalData.length] = newRow;
            originalData = newData;
            updateTable(originalData);
        }
    }

    private void editInvoice() {
        int selectedRow = tbInvoice.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để sửa!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JTextField[] fields = new JTextField[6];
        String[] labels = {"Mã HĐ", "ID Khách", "Khách hàng", "Ngày lập", "Tổng tiền", "Trạng thái"};
        for (int i = 0; i < 6; i++) {
            fields[i] = new JTextField(model.getValueAt(selectedRow, i + 1).toString(), 15);
        }

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        for (int i = 0; i < labels.length; i++) {
            inputPanel.add(new JLabel(labels[i] + ":"));
            inputPanel.add(fields[i]);
        }

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Sửa thông tin hóa đơn",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            for (int i = 0; i < 6; i++) {
                model.setValueAt(fields[i].getText(), selectedRow, i + 1);
                originalData[selectedRow][i] = fields[i].getText();
            }
        }
    }

    private void cancelInvoice() {
        int selectedRow = tbInvoice.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để hủy!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn hủy hóa đơn này?", "Xác nhận hủy",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            model.setValueAt("Đã hủy", selectedRow, 6); // Cột Trạng thái là cột 6
            originalData[selectedRow][5] = "Đã hủy"; // Cột Trạng thái trong originalData
            JOptionPane.showMessageDialog(this, "Đã hủy hóa đơn!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void findInvoice() {
        String filterText = txtFilter.getText().trim().toLowerCase();
        if (filterText.equals("Nhập nội dung") || filterText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập nội dung tìm kiếm!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[][] filteredData = new Object[originalData.length][];
        int count = 0;
        for (Object[] row : originalData) {
            String customer = row[2].toString().toLowerCase(); // Khách hàng
            String invoiceId = row[0].toString().toLowerCase(); // Mã HĐ
            String customerId = row[1].toString().toLowerCase(); // ID Khách
            if (customer.contains(filterText) || invoiceId.contains(filterText) || customerId.contains(filterText)) {
                filteredData[count++] = row;
            }
        }

        if (count == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Object[][] resultData = new Object[count][];
        System.arraycopy(filteredData, 0, resultData, 0, count);
        updateTable(resultData);
    }

    private void reloadData() {
        updateTable(originalData);
        txtFilter.setText("Nhập nội dung");
    }

    private void loadOriginalData() {
        updateTable(originalData); // Tải lại danh sách ban đầu
        txtFilter.setText("Nhập nội dung");
    }

    // Renderer cho nút "Xem chi tiết"
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText((value == null) ? "Xem" : value.toString());
            return this;
        }
    }

    // Editor cho nút "Xem chi tiết"
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Chức năng xem chi tiết chưa được triển khai!");
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row) {
            label = (value == null) ? "Xem chi tiết" : value.toString();
            button.setText(label);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }
    }


}