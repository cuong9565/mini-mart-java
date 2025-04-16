package GUI.JDialog;

import BUS.BillInfoBUS;
import Components.*;
import DTO.BillDTO;
import DTO.BillInfoDTO;
import GUI.JFrame.fManage;
import GUI.JPanel.pnBill;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class dlDetailBill extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JPanel pnBill = new MyJPanel(MyColor.White, "Thông tin hóa đơn");
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thông tin chi tiết hóa đơn", SwingConstants.CENTER, SwingConstants.CENTER);

    JLabel lbBill = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Thông tin hóa đơn:</u></html>", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbStaff = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Thông tin nhân viên:</u></html>", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbCustomer = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Thông tin khách hàng:</u> Không có thông tin</html>", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbOfferBill = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Thông tin giảm giá:</u> Không có thông tin</html>", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbTotal = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "", SwingConstants.LEFT, SwingConstants.CENTER);

    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã sản phẩm", "Tên sản phẩm" , "Đơn giá", "Số lượng", "Giảm giá", "Đơn vị ", "Thành tiền"}, 12);

    MyJTable tbBillInfo = new MyJTable(new String[]{"Mã sản phẩm", "Tên sản phẩm" , "Đơn giá", "Số lượng", "Giảm giá", "Đơn vị ", "Thành tiền"}, 12, new int[]{100, 200, 100, 100, 100, 35}, new int[]{1, 5}, new int[]{});

    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Thoát", SwingConstants.CENTER, SwingConstants.CENTER);

    BillDTO parentBill;
    JDialog dialog = this;

    public dlDetailBill(fManage parentFrame, pnBill parentPanel, BillDTO bill) {
        super(parentFrame, true);
        setTitle("Thông tin chi tiết hóa đơn");
        setSize(1000,800);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        parentBill = bill;
        loadBillInfo();

        // region setBounds
        pnMain.setBounds(0,0,1000,800);

        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 1000, 60);

        pnBill.setBounds(10, 70, 960, 130);
        lbBill.setBounds(20, 80, 940, 30);
        lbStaff.setBounds(20, 110, 940, 30);
        lbCustomer.setBounds(20, 140, 940, 30);
        lbOfferBill.setBounds(20, 170, 940, 30);
        lbTotal.setBounds(10, 670, 500, 30);

        pnSearch.setBounds(470, 210, 500, 90);
        cbSearch.setBounds(485, 240, 150, 30);
        tfSearch.setBounds(645, 240, 200, 30);
        btnRefresh.setBounds(855, 240, 100, 30);
        tbBillInfo.scrPn.setBounds(10, 310, 960, 330);
        btnEsc.setBounds(810, 670, 150, 40);

        // endregion

        // region setText
        lbBill.setText(
                String.format("<html><u>Thông tin hóa đơn:</u> Mã hóa đơn: <b>%d</b> --- Ngày tạo: <b>%s</b></html>", bill.getId(), bill.getDateCreate())
        );
        lbStaff.setText(
                String.format("<html><u>Thông tin nhân viên:</u> Mã nhân viên: <b>%d</b> --- Tên nhân viên: <b>%s</b> --- Số điện thoại: <b>%s</b></html>", bill.getStaff().getId(), bill.getStaff().getLastName() + " " + bill.getStaff().getFirstName(), bill.getStaff().getPhone())
        );
        if(bill.getCustomer().getId()!=0)
            lbCustomer.setText(
                String.format("<html><u>Thông tin khách hàng:</u> Mã khách hàng: <b>%d</b> --- Tên khách hàng: <b>%s</b> --- Số điện thoại: <b>%s</b></html>", bill.getCustomer().getId(), bill.getCustomer().getLastName() + " " + bill.getCustomer().getFirstName(), bill.getCustomer().getPhone())
            );
        if(bill.getOfferBill().getId()!=0)
            lbOfferBill.setText(
                    String.format("<html><u>Thông tin giảm giá:</u> Mã giảm giá: <b>%d</b> --- Phần trăm giảm giá: <b>%s</b></html>", bill.getOfferBill().getId(), bill.getOfferBill().getDiscount() + "%")
            );
        lbTotal.setText(String.format("<html>Thành tiền: <b>%,.0fđ</b></html>", bill.getPrice()));
        // endregion

        btnEsc.addActionListener(_ -> dialog.dispose());
        btnRefresh.addActionListener(_ -> {
            cbSearch.setSelectedIndex(0);
            tfSearch.setText("");
            loadBillInfo();
        });
        cbSearch.addActionListener(_ -> textChange());
        tfSearch.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {textChange();}
        });
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textChange();}
            public void removeUpdate(DocumentEvent e) {textChange();}
            public void changedUpdate(DocumentEvent e) {textChange();}
        });
        // endregion

        add(lbTotal);
        add(lbBill);
        add(lbOfferBill);
        add(lbCustomer);
        add(lbStaff);
        add(pnBill);
        add(cbSearch);
        add(btnRefresh);
        add(tfSearch);
        add(tbBillInfo.scrPn);

        add(btnEsc);

        add(lbHeader);
        add(pnSearch);
        add(pnMain);

        setVisible(true);
    }

    public void loadBillInfo(){
        BillInfoBUS.getInstance().loadByIdBill(parentBill.getId());
        textChange();
    }

    public void textChange(){
        tbBillInfo.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(BillInfoDTO billInfo: BillInfoBUS.getInstance().Search(col, txt))
            tbBillInfo.dftbModel.addRow(billInfo.getSellObjects());
    }
}
