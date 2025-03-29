package GUI.JPanel;


import Components.*;
import javax.swing.*;
import java.awt.*;


public class pnProduct extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnIn = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Nhập</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Tất cả", "1", "2", "3", "4", "5"}, 12);

    MyJTable tbSupplier = new MyJTable(new String[]{"stt", "mã sản phẩm", "tên sản phẩm", "số lượng", "giá"});


    public pnProduct() {
        setLayout(null);
        setBackground(MyColor.LightGray);

        pnHeader.setBounds(0,0,970, 90);

        pnFunc.setBounds(0,0,370,60);
        btnAdd.setBounds(15,20,60,30);
        btnEdit.setBounds(85,20,60,30);
        btnDelete.setBounds(155,20,60,30);
        btnIn.setBounds(225,20,60,30);
        btnOut.setBounds(295,20,60,30);

        pnSearch.setBounds(470,0,500,90);
        cbSearch.setBounds(485, 30, 150, 30);
        tfSearch.setBounds(645, 30, 200, 30);
        btnRefresh.setBounds(855,30,100,30);

        pnFooter.setBounds(0,100,970, 650);
        tbSupplier.scrPn.setBounds(0,100,970,650);

        tbSupplier.addRow(new Object[]{"1", "SP001", "coca", "15.000 đ", "100"});
        tbSupplier.addRow(new Object[]{"2", "SP002", "snack", "15.000 đ", "100"});
        tbSupplier.addRow(new Object[]{"3", "SP003", "nước suối", "15.000 đ", "100"});
        tbSupplier.addRow(new Object[]{"4", "SP004", "...", "15.000 đ", "100"});
        tbSupplier.addRow(new Object[]{"5", "SP005", "...", "15.000 đ", "100"});

        add(btnAdd);
        add(btnEdit);
        add(btnDelete);
        add(btnIn);
        add(btnOut);
        add(pnFunc);

        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(pnSearch);
        add(pnHeader);

        add(tbSupplier.scrPn);
        add(pnFooter);
    }
}
