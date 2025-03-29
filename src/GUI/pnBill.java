package GUI;

import Components.MyColor;
import Components.MyJButton;
import Components.MyJTable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

public class pnBill extends JPanel {
    private JTable tbInvoice = new  MyJTable(new String[]{"STT", "Mã HĐ", "ID Khách", "Khách hàng", "Ngày lập", "Tổng tiền", "Trạng thái", "Chi tiết"},
            new Font("Roboto", Font.BOLD, 14),
            new Color(159, 32, 243), // Màu chữ
            new Color(159, 242, 115), // Màu nền header
            new Color(255, 0, 239));
    private JButton btnCancel = new MyJButton(Font.BOLD, 16, MyColor.White, new Color(220, 53, 69), new Color(255, 99, 132), "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnFind = new MyJButton(Font.BOLD, 16, MyColor.White, new Color(0, 123, 255), new Color(51, 153, 255), "Tìm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnAdd = new MyJButton(Font.BOLD, 16, MyColor.White, new Color(40, 167, 69), new Color(72, 201, 95), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnEdit = new MyJButton(Font.BOLD, 14, MyColor.White, new Color(108, 117, 125), new Color(150, 150, 150), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnReload = new MyJButton(Font.BOLD, 14, MyColor.White, new Color(23, 162, 184), new Color(60, 179, 211), "Tải lại", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnLoad = new MyJButton(Font.BOLD, 14, MyColor.White, new Color(23, 162, 184), new Color(60, 179, 211), "Load", SwingConstants.CENTER, SwingConstants.CENTER);

    JTextField txtFilter = new JTextField("Nhập nội dung");
    // panel
    JPanel panelFunction = new JPanel();
    JPanel panelFind = new JPanel();
    JPanel panelHeader = new JPanel();
    JPanel panelDisplay = new JPanel();  // hien danh sach hoa don

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

        tbInvoice.getTableHeader().setReorderingAllowed(false);
        tbInvoice.setRowHeight(30);
        tbInvoice.getTableHeader().setBackground(Color.cyan);
        tbInvoice.getTableHeader().setFont(new Font("Arial",1,16));
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
                if (e.getButton() == MouseEvent.BUTTON1) { // chuột trái
                    txtFilter.setText("");
                }
            }
        });

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