package GUI.JPanel;

import BUS.*;
import Components.*;
import DAO.*;
import DAO.SupplierDAO;
import DTO.*;
import DTO.SupplierDTO;
import GUI.JDialog.dlImportDetail;
import GUI.JDialog.dlSearchCustomer;
import GUI.JDialog.dlSearchSupplier;
import GUI.JFrame.fManage;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class pnImport extends JPanel {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JPanel pnInfoSupplier = new MyJPanel(MyColor.White, "Thông tin nhà cung cấp");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm sản phẩm");
    JPanel pnImportInfo = new MyJPanel(MyColor.White, "Thông tin phiếu nhập");

    JLabel lbIdImport = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã phiếu nhập", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbNameStaff = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbNameSupplier = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên nhà cung cấp", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbInfoSupplier = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfIdImport = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfNameStaff = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfNameSupplier = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JButton btnSearchSupplier = new MyJButton(Font.BOLD, 12, MyColor.Black, MyColor.LightGray, "...", SwingConstants.CENTER, SwingConstants.CENTER);

    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã", "Tên", "Đơn giá", "Đơn vị", "Số lượng"}, 12);

    MyJTable tbProduct = new MyJTable(new String[]{"Mã", "Tên", "Đơn giá", "Đơn vị", "Số lượng"}, 12, new int[]{50, 150, 100, 50}, new int[]{1}, new int[]{});
    MyJTable tbImportInfo = new MyJTable(new String[]{"Mã", "Tên" , "Đơn giá", "Số lượng", "Đơn vị", "Thành tiền"}, 12, new int[]{10, 60, 50, 15, 15}, new int[]{1}, new int[]{});

    JLabel lbQuantity = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner snQuantity = new MyJSpinner(1, 1, 1000000000, 1);
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);

    JButton btnExportExcel = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Nhập Excel", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnNewimport = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Tạo mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbQuantityFix = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner snQuantityFix = new MyJSpinner(1, 1, 1000000000, 1);

    JLabel lbTotal = new MyJLabel(Font.BOLD, 14, MyColor.Black, "Tổng tiền: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAmount = new MyJLabel(Font.BOLD, 14, MyColor.Black, "0đ", SwingConstants.LEFT, SwingConstants.CENTER);

    JButton btnCancel = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Hủy đơn", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnPay = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thanh toán", SwingConstants.CENTER, SwingConstants.CENTER);

    pnImport thisPanel = this;
    StaffDTO staffLoginGlobal;


    public pnImport(fManage parentFrame, StaffDTO staffLogin) {
        setLayout(null);
        setBackground(MyColor.White);

        staffLoginGlobal = staffLogin;

        // region setBounds
        pnMain.setBounds(0, 0, 1170, 750);
        pnInfoSupplier.setBounds(0, 0, 1160, 70);
        pnImportInfo.setBounds(480,70,680, 135);
        pnSearch.setBounds(0, 70, 460, 60);

        lbIdImport.setBounds(500, 85, 200, 20);
        tfIdImport.setBounds(500, 105, 200, 30);
        lbNameStaff.setBounds(890,85,200,20);
        tfNameStaff.setBounds(890,105,200,30);

        lbNameSupplier.setBounds(890,145,200,20);
        tfNameSupplier.setBounds(890,165,200,30);
        lbInfoSupplier.setBounds(20,20,1120, 30);


        cbSearch.setBounds(10, 90, 140, 30);
        tfSearch.setBounds(160, 90, 190, 30);
        btnRefresh.setBounds(360, 90, 90, 30);

        tbProduct.scrPn.setBounds(0, 140, 460, 550);
        tbImportInfo.scrPn.setBounds(480, 215, 680, 420);
        btnSearchSupplier.setBounds(1095, 174, 20, 20);

        lbQuantity.setBounds(0, 700, 70, 30);
        snQuantity.setBounds(70, 700, 100, 30);
        btnAdd.setBounds(180, 700, 100, 30);

        btnExportExcel.setBounds(480, 650, 100, 30);
        btnDelete.setBounds(480, 650, 100, 30);
        btnNewimport.setBounds(590,650,100,30);
        btnEdit.setBounds(1060, 650, 100, 30);
        lbQuantityFix.setBounds(880,650,100,30);
        snQuantityFix.setBounds(950,650,100,30);

        lbTotal.setBounds(480, 700, 80, 30);
        lbAmount.setBounds(560, 700, 190, 30);
        btnPay.setBounds(950, 700, 100, 30);
        btnCancel.setBounds(1060, 700, 100, 30);
        // endregion

        // This panel
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                load();
            }
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
        btnAdd.addActionListener(_->{
            int row = tbProduct.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn sản phẩm cần thêm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                addImport();
                int idImport = Integer.parseInt(tfIdImport.getText());
                int quantity = (int) snQuantity.getValue();
                int idProduct = Integer.parseInt(tbProduct.getFirstColumn(row));
                ProductDTO product = ProductBUS.getInstance().getItemById(idProduct);
                ImportInfoBUS.getInstance().addProduct(idImport, product, quantity);
                snQuantity.setValue(1);
                loadImportInfo();
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnEdit.addActionListener(_ -> {
            int row = tbImportInfo.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn mục cần sửa số lượng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int idImport = Integer.parseInt(tfIdImport.getText());
                int quantity = (int) snQuantityFix.getValue();
                int idProduct = Integer.parseInt(tbImportInfo.getFirstColumn(row));
                ProductDTO product = ProductBUS.getInstance().getItemById(idProduct);
                ImportInfoBUS.getInstance().updateProduct(idImport, product, quantity);
                snQuantityFix.setValue(1);
                loadImportInfo();
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnDelete.addActionListener(_->{
            int row = tbImportInfo.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn mục cần xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int idImport = Integer.parseInt(tfIdImport.getText());
                int idProduct = Integer.parseInt(tbImportInfo.getFirstColumn(row));
                ImportInfoBUS.getInstance().deleteProduct(idImport, idProduct);
                loadImportInfo();
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        });
        btnNewimport.addActionListener(e->{
            load();
        });
        // Supplier
        btnSearchSupplier.addActionListener(_-> new dlSearchSupplier(parentFrame, thisPanel));

        // ImportOrder
        btnCancel.addActionListener(_->{
            if(tfIdImport.getText().isEmpty())
                JOptionPane.showMessageDialog(thisPanel, "Chưa có thông tin đơn nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else {
                try {
                    int id = Integer.parseInt(tfIdImport.getText());
                    ImportBUS.getInstance().delete(id);
                    load();
                    JOptionPane.showMessageDialog(thisPanel, "Hủy đơn nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(Exception e) {
                    JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnPay.addActionListener(_->{
            if(tfIdImport.getText().isEmpty()){
                JOptionPane.showMessageDialog(thisPanel, "Chưa có thông tin hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(tfNameSupplier.getText().isEmpty()){
                JOptionPane.showMessageDialog(thisPanel, "Chưa có thông tin nhà cung cấp", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(tbImportInfo.getRowCount()==0){
                JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn sản phẩm!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(thisPanel, "Xác nhận thanh toán?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm != JOptionPane.YES_OPTION) return;
            try {
                ImportBUS.getInstance().Pay(Integer.parseInt(tfIdImport.getText()), Double.parseDouble(lbAmount.getText().replace(",", "").replace(".", "").replace("đ", "")));
                 loadProduct();
                JOptionPane.showMessageDialog(thisPanel, "Thanh toán hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // region add
        add(lbInfoSupplier);
        add(tfNameSupplier);
        add(btnSearchSupplier);
        add(lbIdImport);
        add(tfIdImport);
        add(lbNameStaff);
        add(tfNameStaff);
        add(lbNameSupplier);
        add(cbSearch);
        add(btnRefresh);
        add(tfSearch);
        add(tbProduct.scrPn);
        add(tbImportInfo.scrPn);
        add(lbQuantity);
        add(snQuantity);
        add(btnAdd);
        add(btnDelete);
        add(btnNewimport);
        add(btnEdit);
        add(btnPay);
        add(btnCancel);
        add(lbQuantityFix);
        add(snQuantityFix);
        add(lbTotal);
        add(lbAmount);
        add(pnImportInfo);
        add(pnSearch);
        add(pnInfoSupplier);
        add(pnMain);
        // endregion
    }

    public void load(){
        tfNameStaff.setText(staffLoginGlobal.getLastName() + " " + staffLoginGlobal.getFirstName());
        loadProduct();
        loadImport();
        loadImportInfo();
    }

    // Product
    public void loadProduct() {
        ProductBUS.getInstance().load();
        textProductChange();
    }
    public void textProductChange() {
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        tbProduct.dftbModel.setRowCount(0);
        for (ProductDTO p: ProductBUS.getInstance().getListSearchSell(col, txt))
            tbProduct.dftbModel.addRow(p.getRowObjectsSell());
    }

    // Import
    public void loadImport(){
        ImportDTO importDTO = ImportBUS.getInstance().getImportNotPaidByIdStaff(staffLoginGlobal.getId());
        if(importDTO.getId()==0){
            tfIdImport.setText("");
            tfNameSupplier.setText("");
            lbInfoSupplier.setText("");
        }
        else {
            tfIdImport.setText(String.valueOf(importDTO.getId()));
            if(importDTO.getSupplier().getId()==0){
                tfNameSupplier.setText("");
            }
            else {
                SupplierDTO supplier = importDTO.getSupplier();
                tfNameSupplier.setText(supplier.getName());
                lbInfoSupplier.setText(String.format(
                        """
                        <html>
                             <u>Thông tin nhà cung cấp:</u> Mã: <b>%d</b> --- Tên: <b>%s</b> --- Số điện thoại: <b>%s</b> --- Email: <b>%s</b>
                        </html>
                        """, supplier.getId(), supplier.getName(), supplier.getPhone(), supplier.getEmail()
                ));
            }
        }
    }
    public void addImport(){
        if(tfIdImport.getText().isEmpty())
            ImportBUS.getInstance().addImportByIdStaff(staffLoginGlobal.getId());
        loadImport();
    }
    public void updateSupplierById(int idSupplier){
        addImport();
        int idImport = Integer.parseInt(tfIdImport.getText());
        ImportBUS.getInstance().updateIdSupplier(idImport, idSupplier);
    }

    // ImportInfo
    public void loadImportInfo(){
        double total = 0;
        tbImportInfo.dftbModel.setRowCount(0);
        if (!tfIdImport.getText().isEmpty()){
            int idImport = Integer.parseInt(tfIdImport.getText());
            for(ImportInfoDTO importInfoDTO: ImportInfoBUS.getInstance().loadByIdImport(idImport)){
                tbImportInfo.dftbModel.addRow(importInfoDTO.getSellObjects());
                total += importInfoDTO.getTotal();
            }
        }
        lbAmount.setText(String.format("%,.0fđ", total));
    }
}