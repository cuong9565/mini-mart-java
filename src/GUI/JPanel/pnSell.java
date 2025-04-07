package GUI.JPanel;

import Components.MyColor;
import Components.MyJButton;
import Components.MyJLabel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class pnSell extends JPanel {
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model1 = new DefaultTableModel();
    JButton btnRefresh = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.White, "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnExportExcel = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.LightGray, "Nhập Excel", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.LightGray, "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.LightGray, "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbID = new MyJLabel(Font.PLAIN,12,MyColor.Black,"Mã phiếu nhập",SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbCreate = new MyJLabel(Font.PLAIN,12,MyColor.Black,"Người tạo phiếu",SwingConstants.LEFT, SwingConstants.CENTER);
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel tablePanel = new JPanel();
    JPanel tablePanel1 = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel quantityPanel = new JPanel();
    JTextField txtSearch = new JTextField();
    JTextField txtID = new JTextField();
    JTextField txtCreate = new JTextField();
    JTextField txtQuantity = new JTextField();

    JTable tbProduct;
    JTable tbImport;
    JPanel totalPanel = new JPanel();
    JLabel lbTotal = new MyJLabel(Font.BOLD, 16, Color.decode("#000000"), "Tổng tiền", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAmount = new MyJLabel(Font.PLAIN, 16, Color.decode("#000000"), "1.000.000đ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbQuantity = new MyJLabel(Font.PLAIN, 16, Color.decode("#000000"), "Số lượng", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnExport = new MyJButton(Font.PLAIN, 16, Color.decode("#FFFFFF"), Color.decode("#64a15c"), Color.decode("#00CC00"), "Xuất hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnAdd = new MyJButton(Font.PLAIN, 16, Color.decode("#FFFFFF"), Color.decode("#64a15c"), Color.decode("#00CC00"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);


    public pnSell() {
        setLayout(null);
        setBackground(MyColor.White);
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
        tablePanel1.setBounds(480,110,480,470);
        //-----------Button chức năng-----------------
        buttonPanel.setBackground(Color.decode("#FFFFFF"));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBounds(480, 590, 480, 50);

        btnExportExcel.setBorder(border);
        btnExportExcel.setPreferredSize(new Dimension(120, 36));
        btnDelete.setBorder(border);
        btnDelete.setPreferredSize(new Dimension(120, 36));
        btnEdit.setBorder(border);
        btnEdit.setPreferredSize(new Dimension(120, 36));

        buttonPanel.add(btnExportExcel);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnEdit);

        //----Xuất hàng---------
        totalPanel.setLayout(null);
        totalPanel.setBackground(Color.decode("#FFFFFF"));
        totalPanel.setBounds(480, 660, 480, 60);

        lbTotal.setBounds(60, 15, 100, 30);
        totalPanel.add(lbTotal);

        lbAmount.setBounds(180, 15, 100, 30);
        totalPanel.add(lbAmount);

        // btnExport.setBorder(BorderFactory.createLineBorder(Color.decode("#00CC00"), 1));
        btnExport.setBounds(310, 15, 120, 30);
        totalPanel.add(btnExport);

        //----Số lượng---------
        quantityPanel.setLayout(null);
        quantityPanel.setBackground(Color.decode("#FFFFFF"));
        quantityPanel.setBounds(50, 660, 450, 60);

        lbQuantity.setBounds(10, 15, 80, 30);
        quantityPanel.add(lbQuantity);

        txtQuantity.setFont(new Font("Arial", Font.PLAIN, 16));
        txtQuantity.setHorizontalAlignment(JTextField.CENTER);
        txtQuantity.setBounds(100, 15, 100, 30);
        txtQuantity.setBorder(border);
        quantityPanel.add(txtQuantity);

        btnAdd.setBorder(border);
        btnAdd.setBounds(250, 15, 120, 30);
        btnAdd.setPreferredSize(new Dimension(120, 36));
        quantityPanel.add(btnAdd);

        add(quantityPanel);
        add(totalPanel);
        add(buttonPanel);
        add(tablePanel1);
        add(tablePanel);
        add(btnRefresh);
        add(txtSearch);
        add(panel1);
        add(panel2);
    }
}
