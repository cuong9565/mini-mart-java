package GUI.JPanel;

import Components.*;
import GUI.JFrame.fManage;

import javax.swing.*;
import java.awt.*;

public class pnSell extends JPanel {
//    JPanel pnHeader = new MyJPanel(MyColor.White);
//    JPanel pnFooter = new MyJPanel(MyColor.White);
//    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
//    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
//    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
//    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
//    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
//    JButton btnIn = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Nhập<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
//    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
//    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
//    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
//    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Tên", "Mã số", "Số điện thoại", "Địa chỉ", "Email"}, 12);
//
//    MyJTable tbSupplier = new MyJTable(new String[]{"Mã số", "Tên", "Số điện thoại", "Địa chỉ", "Email"});
//
//    pnSell thisPanel = this;
//    int posSelectedCB = 0;

    JPanel pnMain = new MyJPanel(MyColor.White);
    JPanel pnInfoCustomer = new MyJPanel(MyColor.White, "Thông tin khách hàng");
    JPanel pnInfoBill = new MyJPanel(MyColor.White, "Thông tin hóa đơn");
    JLabel lbIdBill = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã hóa đơn", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbOfferBill = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giảm giá hóa đơn", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbNameStaff = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhoneCustomer = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số điện thoại khách hàng", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfIdBill = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfOfferBill = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfNameStaff = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfPhoneCustomer = new MyJTextFieldInput(Font.PLAIN, 14, true);

    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã", "Loại", "Giảm giá", "Tên", "Đơn giá", "Đơn vị", "Số lượng"}, 12);
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm sản phẩm");

    MyJTable tbProduct = new MyJTable(new String[]{"Mã", "Loại", "Giảm giá", "Tên", "Đơn giá", "Đơn vị", "Số lượng"}, 12);
    MyJTable tbBillInfo = new MyJTable(new String[]{"Mã", "Mã sản phẩm",  "Số lượng", "Đơn vị", "Thành tiền"}, 12);

    JLabel lbQuantity = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner snPrice = new MyJSpinner(1, 1, 1000000000, 1);
    JButton btnAddQuantity = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);

    JButton btnImportExel = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Nhập Exel", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnCancel = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Hủy đơn", SwingConstants.CENTER, SwingConstants.CENTER);


    JLabel lbTotal = new MyJLabel(Font.BOLD, 14, MyColor.Black, "Tổng tiền: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSum = new MyJLabel(Font.BOLD, 14, MyColor.Black, "0đ", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnCal = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thanh toán", SwingConstants.CENTER, SwingConstants.CENTER);

    public pnSell(fManage frame) {
        setLayout(null);
        setBackground(MyColor.White);

        pnMain.setBounds(0,0,970, 750);
        pnInfoCustomer.setBounds(0,0,960, 70);
        pnInfoBill.setBounds(480,70,480, 135);

        lbIdBill.setBounds(490,85,200,20);
        tfIdBill.setBounds(490,105,200,30);
        lbNameStaff.setBounds(730,85,200,20);
        tfNameStaff.setBounds(730,105,200,30);

        lbOfferBill.setBounds(490, 145, 200,20);
        tfOfferBill.setBounds(490, 165, 200,30);
        lbPhoneCustomer.setBounds(730,145,200,20);
        tfPhoneCustomer.setBounds(730,165,200,30);

        pnSearch.setBounds(0,70,460,60);
        cbSearch.setBounds(10, 90, 140, 30);
        tfSearch.setBounds(160, 90, 190, 30);
        btnRefresh.setBounds(360,90,90,30);

        tbProduct.scrPn.setBounds(0,140,460,550);
        tbBillInfo.scrPn.setBounds(480, 215, 480, 420);

        lbQuantity.setBounds(0, 700, 70, 30);
        snPrice.setBounds(70, 700, 100, 30);
        btnAddQuantity.setBounds(180, 700, 100, 30);

        btnImportExel.setBounds(480, 650, 100, 30);
        btnDelete.setBounds(590, 650, 100, 30);
        btnEdit.setBounds(700, 650, 100, 30);

        lbTotal.setBounds(480, 700, 80, 30);
        lbSum.setBounds(560, 700, 190, 30);
        btnCal.setBounds(750, 700, 100, 30);
        btnCancel.setBounds(860, 700, 100, 30);

        add(lbIdBill);
        add(tfIdBill);
        add(lbNameStaff);
        add(tfNameStaff);
        add(lbOfferBill);
        add(tfOfferBill);
        add(lbPhoneCustomer);
        add(tfPhoneCustomer);
        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(tbProduct.scrPn);
        add(tbBillInfo.scrPn);
        add(lbQuantity);
        add(snPrice);
        add(btnAddQuantity);
        add(btnImportExel);
        add(btnDelete);
        add(btnEdit);
        add(btnCancel);
        add(lbTotal);
        add(lbSum);
        add(btnCal);
        add(pnSearch);
        add(pnInfoBill);
        add(pnInfoCustomer);
        add(pnMain);
    }

}
