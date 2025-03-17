package pnForm;

import Components.MyJButton;
import Components.MyJLabel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class pnImport extends JPanel{
    //JButton button = MyJButton.GetJButton(Font.PLAIN, 16, "#000000", "#FFFFFF", "#FFFFFF", "Nhập hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model1 = new DefaultTableModel();
    JButton btnRefresh = MyJButton.GetJButton(Font.PLAIN, 16, "#000000", "#FFFFFF", "#FFFFFF", "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbID = MyJLabel.GetJLabel(Font.PLAIN,12,"#000000","Mã phiếu nhập",SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbCreate = MyJLabel.GetJLabel(Font.PLAIN,12,"#000000","Người tạo phiếu",SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSupply = MyJLabel.GetJLabel(Font.PLAIN,12,"#000000","Nhà cung cấp",SwingConstants.LEFT, SwingConstants.CENTER);
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel tablePanel = new JPanel();
    JPanel tablePanel1 = new JPanel();
    JTextField txtSearch = new JTextField();
    JTextField txtID = new JTextField();
    JTextField txtCreate = new JTextField();
    JComboBox cboSupplier = new JComboBox<>();
    JTable tbProduct;
    JTable tbImport;

    public pnImport() {
        setLayout(null);
        setBackground(Color.decode("#FFFFFF"));
        Border border = BorderFactory.createLineBorder(Color.gray,1);
        panel1.setBorder(BorderFactory.createTitledBorder(border,"Tìm kiếm"));
        panel1.setBackground(Color.decode("#FFFFFF"));
        panel1.setBounds(10,10,450,80);

        txtSearch.setFont(new Font("Arial",Font.PLAIN,20));
        txtSearch.setHorizontalAlignment(JTextField.CENTER);
        txtSearch.setBounds(30,35,302,36);
        txtSearch.setBorder(border);

        btnRefresh.setBorder(border);
        btnRefresh.setBounds(342,35,100,36);

        panel2.setBackground(Color.decode("#FFFFFF"));
        panel2.setLayout(new GridLayout(3,2,10,10));
        panel2.add(lbID); panel2.add(txtID);
        panel2.add(lbCreate); panel2.add(txtCreate);
        panel2.add(lbSupply); panel2.add(cboSupplier);
        panel2.setBounds(490,10,430,100);

        String[] columns = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"};
        model.setColumnIdentifiers(columns);
        tbProduct = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tbProduct);
        //tbProduct.setBackground(Color.decode("#FFFFFF"));
        scrollPane.getViewport().setBackground(Color.decode("#FFFFFF"));
        tbProduct.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        tbProduct.setDefaultRenderer(Object.class, centerRenderer);
        tablePanel.setLayout(new GridLayout(1,1));
        tablePanel.add(scrollPane);
        tablePanel.setBounds(10,110,450,550);

        String[] columns1 = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"};
        model1.setColumnIdentifiers(columns1);
        tbImport = new JTable(model1);
        JScrollPane scrollPane1 = new JScrollPane(tbImport);
        //tbProduct.setBackground(Color.decode("#FFFFFF"));
        scrollPane1.getViewport().setBackground(Color.decode("#FFFFFF"));
        tbImport.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        tbImport.setDefaultRenderer(Object.class, centerRenderer);
        tablePanel1.setLayout(new GridLayout(1,1));
        tablePanel1.add(scrollPane1);
        tablePanel1.setBounds(480,150,480,430);


        add(tablePanel1);
        add(tablePanel);
        add(btnRefresh);
        add(txtSearch);
        add(panel1);
        add(panel2);

    }
}
