package GUI;

import Components.MyColor;
import Components.MyJButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnStaff extends JPanel {
    private DefaultTableModel model = new DefaultTableModel();
    private JTable tbStaff;
    private JButton btnblock = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Khóa", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnfind = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Tìm", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnadd = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnfix = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnload = new MyJButton(Font.PLAIN, 14, MyColor.White, MyColor.DarkBlue, MyColor.HoverBlue, "Tải lại", SwingConstants.CENTER, SwingConstants.CENTER);
    // Thêm hai nút mới
    private JButton btnImportExcel = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Nhập Excel", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnExportExcel = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Xuất Excel", SwingConstants.CENTER, SwingConstants.CENTER);

    private JComboBox<String> sortComboBox;
    private TableRowSorter<DefaultTableModel> sorter;

    JTextField txtfiter = new JTextField("Nhập nội dung");

    // panel
    JPanel panelfunction = new JPanel();  // them sua block
    JPanel panelfind = new JPanel();     // tim kiem, loc thong tin
    JPanel panelheader = new JPanel();
    JPanel paneldisplay = new JPanel();  // hien danh sach
    JPanel panelexcel = new JPanel();
    public pnStaff() {
        setLayout(null);
        setBackground(Color.decode("#FFFFFF"));
        Border border = BorderFactory.createLineBorder(Color.gray, 1);

        // panel function
        panelfunction.add(btnadd);
        panelfunction.add(btnfix);
        panelfunction.add(btnblock);
        panelexcel.add(btnImportExcel);
        panelexcel.add(btnExportExcel);

        // JComboBox sắp xếp
        String[] sortOptions = {"ID (tăng dần)", "Giới tính", "Lương"};
        sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.setPreferredSize(new Dimension(150, 30));
        //
        sortComboBox.setBackground(Color.WHITE);
        sortComboBox.setForeground(Color.GRAY);
        sortComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Viền xám

        sortComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new JButton("v");
                arrowButton.setBackground(Color.WHITE);
                arrowButton.setForeground(Color.GRAY);
                arrowButton.setBorder(BorderFactory.createEmptyBorder());
                arrowButton.setFocusPainted(false);
                arrowButton.setFont(new Font("Arial", Font.PLAIN, 12)); // Điều chỉnh kích thước chữ
                return arrowButton;
            }

            @Override
            protected void installDefaults() {
                super.installDefaults();
                comboBox.setOpaque(true);
            }
        });

        // Tùy chỉnh renderer để danh sách thả xuống cũng có giao diện tương tự
        sortComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE); // Nền khi chọn
                setForeground(Color.GRAY); // Chữ xám nhạt
                setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                return this;
            }
        });

        panelfunction.add(sortComboBox);

        panelfunction.setLayout(new FlowLayout());
        panelfunction.setBorder(BorderFactory.createTitledBorder(border, "Chức năng"));

        // Điều chỉnh kích thước button trong panelfunction
        btnadd.setPreferredSize(new Dimension(80, 35));
        btnfix.setPreferredSize(new Dimension(100, 35));
        btnblock.setPreferredSize(new Dimension(100, 35));
        btnImportExcel.setPreferredSize(new Dimension(80, 35));
        btnExportExcel.setPreferredSize(new Dimension(80, 35));

        // Thêm sự kiện cho nút Nhập Excel
        btnImportExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(pnStaff.this, "Chức năng Nhập Excel được gọi!");
                // Thêm logic nhập Excel tại đây
            }
        });

        // Thêm sự kiện cho nút Xuất Excel
        btnExportExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(pnStaff.this, "Chức năng Xuất Excel được gọi!");
                // Thêm logic xuất Excel tại đây
            }
        });

        // panel find
        panelfind.add(txtfiter);
        panelfind.add(btnfind);
        panelfind.add(btnload);
        panelfind.setBorder(BorderFactory.createTitledBorder(border, "Tìm kiếm"));

        // Set kích thước button và textfield trong panelfind
        txtfiter.setPreferredSize(new Dimension(200, 33));
        btnfind.setPreferredSize(new Dimension(80, 30));
        btnload.setPreferredSize(new Dimension(80, 30));

        // panel header
        panelheader.add(panelfunction);
        panelheader.add(panelfind);
        panelheader.setLayout(new GridLayout(1, 2, 15, 25));
        panelheader.setBounds(10, 20, 950, 80);
        panelheader.setBackground(Color.decode("#FFFFFF"));
        panelexcel.setBounds(785,110,175,45 );

        // panel display
        String[] columns = {"STT", "Mã NV", "Họ-Lót", "Tên", "Giới tính", "Địa chỉ", "Role", "Lương", "Trạng thái"};
        model.setColumnIdentifiers(columns);
        tbStaff = new JTable(model);

        // Thiết lập TableRowSorter
        sorter = new TableRowSorter<>(model);
        tbStaff.setRowSorter(sorter);

        // Điều chỉnh chiều rộng cột
        TableColumnModel columnModel = tbStaff.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // Cột "STT"
        columnModel.getColumn(1).setPreferredWidth(80);  // Cột "Mã NV"
        columnModel.getColumn(2).setPreferredWidth(100); // Cột "Họ-Lót"
        columnModel.getColumn(3).setPreferredWidth(80);  // Cột "Tên"
        columnModel.getColumn(4).setPreferredWidth(80);  // Cột "Giới tính"
        columnModel.getColumn(5).setPreferredWidth(150); // Cột "Địa chỉ"
        columnModel.getColumn(6).setPreferredWidth(80);  // Cột "Role"
        columnModel.getColumn(7).setPreferredWidth(100); // Cột "Lương"
        columnModel.getColumn(8).setPreferredWidth(80);  // Cột "Trạng thái"

//        // Thêm sự kiện cho JComboBox
//        sortComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String selectedOption = (String) sortComboBox.getSelectedItem();
//                switch (selectedOption) {
//                    case "ID (tăng dần)":
//                        sorter.setSortKeys(java.util.Collections.singletonList(
//                                new RowSorter.SortKey(0, SortOrder.ASCENDING)));
//                        break;
//                    case "Giới tính":
//                        sorter.setSortKeys(java.util.Collections.singletonList(
//                                new RowSorter.SortKey(4, SortOrder.ASCENDING)));
//                        break;
//                    case "Lương":
//                        sorter.setSortKeys(java.util.Collections.singletonList(
//                                new RowSorter.SortKey(7, SortOrder.ASCENDING)));
//                        break;
//                }
//            }
//        });

        JScrollPane scrollPane = new JScrollPane(tbStaff);
        paneldisplay.setBackground(Color.decode("#FFFFFF"));
        paneldisplay.setLayout(new GridLayout(1, 1));
        paneldisplay.add(scrollPane);
        paneldisplay.setBounds(10, 170, 950, 550);

        add(panelheader);
        add(panelexcel);
        add(paneldisplay);
    }
}