package pnForm;

import Components.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class pnSupplier extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Tất cả", "1", "2", "3", "4", "5"}, 12);

    MyJTable tbSupplier = new MyJTable(new String[]{"1", "2", "3", "4", "5"});

    public pnSupplier(JFrame frame) {
        setLayout(null);
        setBackground(MyColor.LightGray);

        pnHeader.setBounds(0,0,970, 100);

        pnFunc.setBounds(0,0,260,100);
        btnAdd.setBounds(15,19,70,70);
        btnEdit.setBounds(95,19,70,70);
        btnDelete.setBounds(175,19,70,70);

        pnSearch.setBounds(455,0,515,100);
        cbSearch.setBounds(475, 30, 150, 40);
        tfSearch.setBounds(645, 30, 200, 40);
        btnRefresh.setBounds(855,30,100,40);

        pnFooter.setBounds(0,110,970, 630);
        tbSupplier.scrPn.setBounds(0,110,970,630);

        tbSupplier.addRow(new Object[]{"1", "2", "3", "4", "5"});
        tbSupplier.addRow(new Object[]{"1", "2", "3", "4", "5"});
        tbSupplier.addRow(new Object[]{"1", "2", "3", "4", "5"});
        tbSupplier.addRow(new Object[]{"1", "2", "3", "4", "5"});
        tbSupplier.addRow(new Object[]{"1", "2", "3", "4", "5"});

        add(btnAdd);
        add(btnEdit);
        add(btnDelete);
        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(pnFunc);
        add(pnSearch);
        add(pnHeader);

        add(tbSupplier.scrPn);
        add(pnFooter);
    }
}
