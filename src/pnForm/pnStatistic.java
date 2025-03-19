package pnForm;

import Components.MyColor;
import Components.MyJButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class pnStatistic extends JPanel {
    DefaultTableModel model = new DefaultTableModel();
    //ArrayList<Thongke> list = new ArrayList<>();
    JButton button = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.White, "Thống kê", SwingConstants.CENTER, SwingConstants.CENTER);
    JPanel jPanelLayout = new JPanel();
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    JPanel jPanel5 = new JPanel();
    JPanel jPanel6 = new JPanel();
    JPanel tablePanel = new JPanel();
    JTabbedPane tabPane = new JTabbedPane();
    JTable tbStatistic;


    public pnStatistic() {
        setLayout(null);
        setBackground(Color.decode("#FFFFFF"));
        jPanel1.setBackground(Color.decode("#45e7f3"));
        jPanel2.setBackground(Color.decode("#f3a145"));
        jPanel3.setBackground(Color.decode("#a1f345"));
        jPanel4.setBackground(Color.decode("#FFFFFF"));
        jPanel5.setBackground(Color.decode("#FFFFFF"));
        jPanel6.setBackground(Color.decode("#FFFFFF"));

        jPanelLayout.setLayout(new GridLayout(1,3, 10,10));
        //jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        tabPane.add("Sản phẩm",jPanel4);
        tabPane.add("Phiếu",jPanel5);
        tabPane.add("Tài khoản",jPanel6);
        tabPane.setBounds(10,220,950,150);

        jPanelLayout.add(jPanel1);
        jPanelLayout.add(jPanel2);
        jPanelLayout.add(jPanel3);
        jPanelLayout.setBounds(10,10,950,200);

        //tablePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        String[] columns = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng nhập", "Số lượng xuất"};
        model.setColumnIdentifiers(columns);
        tbStatistic = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tbStatistic);
        tablePanel.setBackground(Color.decode("#FFFFFF"));
        tablePanel.setLayout(new GridLayout(1,1));
        tablePanel.add(scrollPane);
        tablePanel.setBounds(10,380,950,350);

        add(tablePanel);
        add(tabPane);
        add(jPanelLayout);
    }
}
