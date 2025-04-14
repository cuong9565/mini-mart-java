package GUI.JPanel;

import BUS.*;
import Components.*;
import DTO.*;
import GUI.JDialog.dlSearchCustomer;
import GUI.JDialog.dlSearchOfferBill;
import GUI.JFrame.fManage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class pnSell extends JPanel {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JPanel pnInfoCustomer = new MyJPanel(MyColor.White, "Thông tin khách hàng");
    JPanel pnInfoBill = new MyJPanel(MyColor.White, "Thông tin hóa đơn");
    JLabel lbIdBill = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã hóa đơn", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbInfoCustomer = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbOfferBill = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giảm giá hóa đơn", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbNameStaff = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhoneCustomer = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số điện thoại khách hàng", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfIdBill = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfOfferBill = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfNameStaff = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfPhoneCustomer = new MyJTextFieldInput(Font.PLAIN, 14, false);

    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã", "Tên", "Đơn giá", "Đơn vị", "Số lượng"}, 12);
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm sản phẩm");

    JButton btnSearchCustomer = new MyJButton(Font.BOLD, 12, MyColor.Black, MyColor.LightGray, "...", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRemoveCustomer = new MyJButton(Font.PLAIN, 12, MyColor.Black, MyColor.LightGray, "X", SwingConstants.CENTER, SwingConstants.CENTER);

    JButton btnSearchOfferBill = new MyJButton(Font.BOLD, 12, MyColor.Black, MyColor.LightGray, "...", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRemoveOfferBill = new MyJButton(Font.PLAIN, 12, MyColor.Black, MyColor.LightGray, "X", SwingConstants.CENTER, SwingConstants.CENTER);

    MyJTable tbProduct = new MyJTable(new String[]{"Mã", "Tên", "Đơn giá", "Đơn vị", "Số lượng"}, 12, new int[]{50, 150, 100, 50}, new int[]{1}, new int[]{});
    MyJTable tbBillInfo = new MyJTable(new String[]{"Mã", "Tên" , "Đơn giá", "Số lượng", "Giảm giá", "Đơn vị", "Thành tiền"}, 12, new int[]{10, 60, 50, 15, 10, 15}, new int[]{1}, new int[]{});

    JLabel lbQuantity = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner snQuantity = new MyJSpinner(1, 1, 1000000000, 1);
    JButton btnAddQuantity = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);

    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbQuantityFix = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner snQuantityFix = new MyJSpinner(1, 1, 1000000000, 1);

    JButton btnCancel = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Hủy đơn", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnPay = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thanh toán", SwingConstants.CENTER, SwingConstants.CENTER);

    JLabel lbTotal = new MyJLabel(Font.BOLD, 14, MyColor.Black, "Tổng tiền: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSum = new MyJLabel(Font.BOLD, 14, MyColor.Black, "0đ", SwingConstants.LEFT, SwingConstants.CENTER);

    StaffDTO staffLoginGlobal;
    pnSell thisPn = this;

    public pnSell(fManage parentFrame, StaffDTO staffLogin) {
        setLayout(null);
        setBackground(MyColor.White);

        staffLoginGlobal = staffLogin;

        // region setBounds
        pnMain.setBounds(0,0,1170, 750);
        pnInfoCustomer.setBounds(0,0,1160, 70);
        pnInfoBill.setBounds(480,70,680, 135);
        lbInfoCustomer.setBounds(20,20,1120, 30);

        lbIdBill.setBounds(500,85,200,20);
        tfIdBill.setBounds(500,105,200,30);
        lbNameStaff.setBounds(890,85,200,20);
        tfNameStaff.setBounds(890,105,200,30);

        lbOfferBill.setBounds(500, 145, 200,20);
        tfOfferBill.setBounds(500, 165, 200,30);
        btnSearchOfferBill.setBounds(705, 174, 20, 20);
        btnRemoveOfferBill.setBounds(730, 174, 20, 20);


        lbPhoneCustomer.setBounds(890,145,200,20);
        tfPhoneCustomer.setBounds(890,165,200,30);
        btnSearchCustomer.setBounds(1095, 174, 20, 20);
        btnRemoveCustomer.setBounds(1120, 174, 20, 20);

        pnSearch.setBounds(0,70,460,60);
        cbSearch.setBounds(10, 90, 140, 30);
        tfSearch.setBounds(160, 90, 190, 30);
        btnRefresh.setBounds(360,90,90,30);

        tbProduct.scrPn.setBounds(0,140,460,550);
        tbBillInfo.scrPn.setBounds(480, 215, 680, 420);

        lbQuantity.setBounds(0, 700, 70, 30);
        snQuantity.setBounds(70, 700, 100, 30);
        btnAddQuantity.setBounds(180, 700, 100, 30);

        btnDelete.setBounds(480, 650, 100, 30);
        lbQuantityFix.setBounds(700, 650, 70, 30);
        snQuantityFix.setBounds(770, 650, 100, 30);
        btnEdit.setBounds(880, 650, 100, 30);

        lbTotal.setBounds(480, 700, 80, 30);
        lbSum.setBounds(560, 700, 190, 30);
        btnPay.setBounds(950, 700, 100, 30);
        btnCancel.setBounds(1060, 700, 100, 30);
        // endregion

        // region setText
        tfNameStaff.setText(staffLogin.getLastName() + " " + staffLogin.getFirstName());
        // endregion

        // region setEvent
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {load();}
        });
            // Customer
        btnSearchCustomer.addActionListener(_-> new dlSearchCustomer(parentFrame, thisPn));
        btnRemoveCustomer.addActionListener(_-> {
            if(!tfIdBill.getText().isEmpty())
                updateCustomer(0);
        });
            // OfferBill
        btnSearchOfferBill.addActionListener(_-> new dlSearchOfferBill(parentFrame, thisPn));
        btnRemoveOfferBill.addActionListener(_-> {
            if(!tfOfferBill.getText().isEmpty())
                updateOfferBill(0);
        });
            // Bill
        btnPay.addActionListener(_-> {
            if(tfIdBill.getText().isEmpty()){
                JOptionPane.showMessageDialog(thisPn, "Chưa có thông tin hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(BillBUS.getInstance().Pay(Integer.parseInt(tfIdBill.getText()), Double.parseDouble(lbSum.getText().replace(",", "").replace(".", "").replace("đ", "")))){
                JOptionPane.showMessageDialog(thisPn, "Thanh toán hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                load();
            }
            else JOptionPane.showMessageDialog(thisPn, BillBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        });

        btnCancel.addActionListener(_-> {
            if(tfIdBill.getText().isEmpty()){
                JOptionPane.showMessageDialog(thisPn, "Chưa có thông tin hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(BillBUS.getInstance().Cancel(Integer.parseInt(tfIdBill.getText()))){
                JOptionPane.showMessageDialog(thisPn, "Hủy hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                load();
            }
            else JOptionPane.showMessageDialog(thisPn, BillBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);

        });
        btnAddQuantity.addActionListener(_-> {
            int i = tbProduct.getSelectedRow();
            if(i==-1) JOptionPane.showMessageDialog(thisPn, "Vui lòng chọn sản phẩm cần thêm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else {
                if(tfIdBill.getText().isEmpty()) addBill();
                int idBill = Integer.parseInt(tfIdBill.getText());
                int idProduct = Integer.parseInt(tbProduct.getFirstColumn(i));
                int quantity = Integer.parseInt(snQuantity.getValue().toString());
                if(BillInfoBUS.getInstance().addProduct(idBill, idProduct, quantity)){
                    loadBillInfo();
                    snQuantity.setValue(1);
                }
                else JOptionPane.showMessageDialog(thisPn, BillInfoBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
            // BillInfo
        btnDelete.addActionListener(_-> {
            int i = tbBillInfo.getSelectedRow();
            if(i>=0){
                int idBill = Integer.parseInt(tfIdBill.getText());
                int idProduct = Integer.parseInt(tbBillInfo.getFirstColumn(i));
                if(BillInfoBUS.getInstance().deleteProduct(idBill, idProduct)){
                    loadBillInfo();
                }
                else JOptionPane.showMessageDialog(thisPn, BillInfoBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            else JOptionPane.showMessageDialog(thisPn, "Vui lòng chọn mục cần xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
        btnEdit.addActionListener(_->{
            int i = tbBillInfo.getSelectedRow();
            if(i>=0){
                int idBill = Integer.parseInt(tfIdBill.getText());
                int idProduct = Integer.parseInt(tbBillInfo.getFirstColumn(i));
                int quantity = Integer.parseInt(snQuantityFix.getValue().toString());
                if(BillInfoBUS.getInstance().fixQuantityProduct(idBill, idProduct, quantity)){
                    loadBillInfo();
                    snQuantityFix.setValue(1);
                }
                else JOptionPane.showMessageDialog(thisPn, BillInfoBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            else JOptionPane.showMessageDialog(thisPn, "Vui lòng chọn mục cần sửa số lượng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
            // Product
        btnRefresh.addActionListener(_ -> {
            cbSearch.setSelectedIndex(0);
            tfSearch.setText("");
            loadProduct();
        });
        cbSearch.addActionListener(_ -> loadProduct());
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textProductChange();}
            public void removeUpdate(DocumentEvent e) {textProductChange();}
            public void changedUpdate(DocumentEvent e) {textProductChange();}
        });
        // endregion

        // region add
        add(btnSearchOfferBill);
        add(btnRemoveOfferBill);
        add(lbInfoCustomer);
        add(btnSearchCustomer);
        add(btnRemoveCustomer);
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
        add(snQuantity);
        add(btnAddQuantity);
        add(btnDelete);
        add(btnEdit);
        add(lbQuantityFix);
        add(snQuantityFix);
        add(btnCancel);
        add(lbTotal);
        add(lbSum);
        add(btnPay);
        add(pnSearch);
        add(pnInfoBill);
        add(pnInfoCustomer);
        add(pnMain);
        // endregion
    }

    public void load() {
        loadProduct();
        loadBill();
        loadBillInfo();
    }

    public void loadProduct(){
        ProductBUS.getInstance().getList();
        textProductChange();
    }

    public void textProductChange(){
        int i = cbSearch.getSelectedIndex();
        tbProduct.dftbModel.setRowCount(0);
        for(ProductDTO product : ProductBUS.getInstance().getListSearchSell(i, tfSearch.getText()))
            tbProduct.dftbModel.addRow(product.getRowObjectsSell());
    }

    public void addBill(){
        BillBUS.getInstance().addBill(staffLoginGlobal.getId());
        loadBill();
    }

    public void loadBill(){
        BillDTO billDTO = BillBUS.getInstance().getBillNotPaid(staffLoginGlobal.getId());
        if(billDTO.getId()!=0){
            tfIdBill.setText(billDTO.getId() + "");
            // Thông tin khách hàng
            if(billDTO.getCustomer().getId()==0){
                tfPhoneCustomer.setText("");
                lbInfoCustomer.setText("");
            }
            else {
                CustomerDTO customer = billDTO.getCustomer();
                tfPhoneCustomer.setText(customer.getPhone());
                lbInfoCustomer.setText(String.format(
                       """
                       <html>
                            Mã khách hàng: <b>%d</b> --- Họ và tên: <b>%s</b> --- Số điện thoại: <b>%s</b>
                       </html>
                       """, customer.getId(), customer.getLastName() + " " + customer.getFirstName(), customer.getPhone()
                ));
            }

            // Thông tin hóa đơn giảm giá
            if(billDTO.getOfferBill().getId() == 0){
                tfOfferBill.setText("");
            }
            else {
                OfferBillDTO offerBill = billDTO.getOfferBill();
                tfOfferBill.setText(offerBill.getDiscount() + "%");
            }
        }
        else {
            tfIdBill.setText("");
            tfPhoneCustomer.setText("");
            tfOfferBill.setText("");
        }
    }

    public void updateCustomer(int idCustomer){
        addBill();
        if(!BillBUS.getInstance().updateIdCustomer(Integer.parseInt(tfIdBill.getText()), idCustomer))
            JOptionPane.showMessageDialog(thisPn, BillBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        loadBill();
    }

    public void updateOfferBill(int idOfferBill){
        addBill();
        if(!BillBUS.getInstance().updateIdOfferBill(Integer.parseInt(tfIdBill.getText()), idOfferBill))
            JOptionPane.showMessageDialog(thisPn, BillBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        loadBill();
        loadBillInfo();
    }

    public void loadBillInfo(){
        double total = 0;
        tbBillInfo.dftbModel.setRowCount(0);
        if(!tfIdBill.getText().isEmpty()){
            for(BillInfoDTO billInfo : BillInfoBUS.getInstance().loadByIdBill(Integer.parseInt(tfIdBill.getText()))){
                total += billInfo.getTotal();
                tbBillInfo.dftbModel.addRow(billInfo.getSellObjects());
            }
        }
        if(!tfOfferBill.getText().isEmpty()) total = total * (100 - Integer.parseInt(tfOfferBill.getText().replace("%", ""))) / 100;
        lbSum.setText(String.format("%,.0fđ", total));
    }
}
