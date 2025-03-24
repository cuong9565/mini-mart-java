package GUI;

import Components.MyColor;
import Components.MyJLabel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class pnStatistic extends JPanel {
    DefaultTableModel model = new DefaultTableModel(); // Model cho bảng sản phẩm
    DefaultTableModel priceModel = new DefaultTableModel(); // Model cho bảng phiếu

    JLabel numBlue = new MyJLabel(Font.BOLD, 50, MyColor.White, "100", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbBlue = new MyJLabel(Font.BOLD, 16, MyColor.White, "Sản phẩm trong kho", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel numOrange = new MyJLabel(Font.BOLD, 50, MyColor.White, "100", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbOrange = new MyJLabel(Font.BOLD, 16, MyColor.White, "Nhà cung cấp", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel numGreen = new MyJLabel(Font.BOLD, 50, MyColor.White, "100", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbGreen = new MyJLabel(Font.BOLD, 16, MyColor.White, "Tài khoản", SwingConstants.CENTER, SwingConstants.CENTER);

    JPanel jPanelLayout = new JPanel();
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel(); // Tab "Sản phẩm"
    JPanel jPanel5 = new JPanel(); // Tab "Phiếu"
    JPanel jPanel6 = new JPanel(); // Tab "Tài khoản"
    JTabbedPane tabPane = new JTabbedPane();

    // Sản phẩm
    JPanel panelProduct = new JPanel();
    JTextField txtSearch1 = new JTextField();
    JPanel productTablePanel = new JPanel();
    JTable tbStatistic;

    // Phiếu
    JPanel panelPrice = new JPanel();
    JLabel lbMinPrice = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Từ", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbMaxPrice = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Đến", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField txtMinPrice = new JTextField();
    JTextField txtMaxPrice = new JTextField();
    JPanel priceTablePanel = new JPanel();
    JTable tbPrice;
    JPanel panelDate = new JPanel();
    JLabel lbFrom = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Từ", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbTo = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Đến", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField txtSearch2 = new JTextField();
    JTextField txtSearch3 = new JTextField();

    public pnStatistic() {
        setLayout(null);
        setBackground(Color.decode("#FFFFFF"));

        // --- 3 khung đầu ---
        jPanel1.setBackground(Color.decode("#45e7f3"));
        jPanel1.setLayout(null);
        numBlue.setBounds(115, 50, 100, 80);
        lbBlue.setBounds(110, 110, 200, 30);
        jPanel1.add(numBlue);
        jPanel1.add(lbBlue);

        jPanel2.setBackground(Color.decode("#f3a145"));
        jPanel2.setLayout(null);
        numOrange.setBounds(115, 50, 100, 80);
        lbOrange.setBounds(85, 110, 200, 30);
        jPanel2.add(numOrange);
        jPanel2.add(lbOrange);

        jPanel3.setBackground(Color.decode("#a1f345"));
        jPanel3.setLayout(null);
        numGreen.setBounds(115, 50, 100, 80);
        lbGreen.setBounds(70, 110, 200, 30);
        jPanel3.add(numGreen);
        jPanel3.add(lbGreen);

        jPanelLayout.setLayout(new GridLayout(1, 3, 10, 10));
        jPanelLayout.add(jPanel1);
        jPanelLayout.add(jPanel2);
        jPanelLayout.add(jPanel3);
        jPanelLayout.setBounds(10, 10, 950, 200);

        // --- Định nghĩa viền ---
        Border border = BorderFactory.createLineBorder(Color.gray, 1);

        // --- Tab "Sản phẩm" ---
        jPanel4.setLayout(null);
        jPanel4.setBackground(Color.decode("#FFFFFF"));

        // Panel tìm kiếm sản phẩm
        panelProduct.setBorder(BorderFactory.createTitledBorder(border, "Tìm kiếm"));
        panelProduct.setBackground(Color.decode("#FFFFFF"));
        panelProduct.setBounds(30, 10, 380, 90);
        panelProduct.setLayout(null);
        txtSearch1.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSearch1.setHorizontalAlignment(JTextField.LEFT);
        txtSearch1.setBounds(20, 30, 330, 36);
        txtSearch1.setBorder(BorderFactory.createCompoundBorder(
                border, BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panelProduct.add(txtSearch1);
        jPanel4.add(panelProduct);

        // Panel lọc theo ngày
        panelDate.setBorder(BorderFactory.createTitledBorder(border, "Lọc theo ngày"));
        panelDate.setBackground(Color.decode("#FFFFFF"));
        panelDate.setBounds(450, 10, 490, 90);
        panelDate.setLayout(null);
        lbFrom.setBounds(20, 30, 30, 30);
        panelDate.add(lbFrom);
        txtSearch2.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSearch2.setHorizontalAlignment(JTextField.LEFT);
        txtSearch2.setBounds(60, 30, 150, 30);
        txtSearch2.setBorder(BorderFactory.createCompoundBorder(
                border, BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panelDate.add(txtSearch2);
        lbTo.setBounds(240, 30, 40, 30);
        panelDate.add(lbTo);
        txtSearch3.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSearch3.setHorizontalAlignment(JTextField.LEFT);
        txtSearch3.setBounds(290, 30, 150, 30);
        txtSearch3.setBorder(BorderFactory.createCompoundBorder(
                border, BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panelDate.add(txtSearch3);
        jPanel4.add(panelDate);

        // Bảng sản phẩm
        String[] columns = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng nhập", "Số lượng xuất"};
        model.setColumnIdentifiers(columns);
        tbStatistic = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tbStatistic);
        productTablePanel.setBackground(Color.decode("#FFFFFF"));
        productTablePanel.setLayout(new GridLayout(1, 1));
        productTablePanel.add(scrollPane);
        productTablePanel.setBounds(10, 110, 930, 350);
        jPanel4.add(productTablePanel);

        // --- Tab "Phiếu" ---
        jPanel5.setLayout(null);
        jPanel5.setBackground(Color.decode("#FFFFFF"));

        // Panel "Lọc theo giá"
        panelPrice.setBorder(BorderFactory.createTitledBorder(border, "Lọc theo giá"));
        panelPrice.setBackground(Color.decode("#FFFFFF"));
        panelPrice.setBounds(30, 10, 380, 90);
        panelPrice.setLayout(null);
        lbMinPrice.setBounds(20, 30, 30, 30);
        panelPrice.add(lbMinPrice);
        txtMinPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMinPrice.setHorizontalAlignment(JTextField.LEFT);
        txtMinPrice.setBounds(50, 30, 120, 30);
        txtMinPrice.setBorder(BorderFactory.createCompoundBorder(
                border, BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panelPrice.add(txtMinPrice);
        lbMaxPrice.setBounds(190, 30, 40, 30);
        panelPrice.add(lbMaxPrice);
        txtMaxPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMaxPrice.setHorizontalAlignment(JTextField.LEFT);
        txtMaxPrice.setBounds(230, 30, 120, 30);
        txtMaxPrice.setBorder(BorderFactory.createCompoundBorder(
                border, BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panelPrice.add(txtMaxPrice);
        jPanel5.add(panelPrice);

        // Bảng phiếu
        String[] priceColumns = {"STT", "Mã phiếu", "Tổng giá", "Ngày tạo"};
        priceModel.setColumnIdentifiers(priceColumns);
        tbPrice = new JTable(priceModel);
        JScrollPane priceScrollPane = new JScrollPane(tbPrice);
        priceTablePanel.setBackground(Color.decode("#FFFFFF"));
        priceTablePanel.setLayout(new GridLayout(1, 1));
        priceTablePanel.add(priceScrollPane);
        priceTablePanel.setBounds(10, 110, 930, 350);
        jPanel5.add(priceTablePanel);

        // --- Tab "Tài khoản" ---
        jPanel6.setBackground(Color.decode("#FFFFFF"));

        // --- Thêm các tab vào TabPane ---
        tabPane.add("Sản phẩm", jPanel4);
        tabPane.add("Phiếu", jPanel5);
        tabPane.add("Tài khoản", jPanel6);
        tabPane.setBounds(10, 220, 950, 500); // Tăng chiều cao để chứa bảng

        // --- Thêm thành phần vào panel chính ---
        add(tabPane);
        add(jPanelLayout);
    }
}
