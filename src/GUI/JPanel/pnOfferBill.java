package GUI.JPanel;

import BUS.OfferBUS;
import BUS.OfferBillBUS;
import BUS.SupplierBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddOfferBill;
import GUI.JDialog.dlEditOfferBill;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class pnOfferBill extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnIn = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Nhập<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);

    JComboBox<String> cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá"}, 12);
    MyJTable tbOfferBill = new MyJTable(new String[]{"Mã số", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá"}, new int[]{}, new int[]{}, new int[]{});

    pnOfferBill thisPanel = this;
    int posSelectedCB = 0;

    public pnOfferBill(pnDiscount parent) {
        setLayout(null);
        setBackground(MyColor.White);
        pnOfferBill thisPanel = this;
        // region SET BOUNDS
        pnHeader.setBounds(0,0,970, 90);
        pnFunc.setBounds(0,0,370,90);
        btnAdd.setBounds(15,20,60,60);
        btnEdit.setBounds(85,20,60,60);
        btnDelete.setBounds(155,20,60,60);
        btnIn.setBounds(225,20,60,60);
        btnOut.setBounds(295,20,60,60);
        pnSearch.setBounds(460,0,500,90);
        cbSearch.setBounds(475, 30, 150, 30);
        tfSearch.setBounds(640, 30, 200, 30);
        btnRefresh.setBounds(845,30,100,30);
        pnFooter.setBounds(0,100,970, 650);
        tbOfferBill.scrPn.setBounds(0,100,960,610);
        // endregion

        // region EVENT CHO PANEL NÀY
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                btnRefresh.doClick();
            }
        });
        // endregion

        // region EVENT
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dlAddOfferBill(thisPanel);
            }
        });
        btnEdit.addActionListener(e -> {
             int i = tbOfferBill.getSelectedRow();
             if (i >=0){
                 int id = Integer.parseInt(tbOfferBill.getFirstColumn(i));
               OfferBillDTO of = OfferBillBUS.getInstance().getById(id);
                 new dlEditOfferBill(thisPanel,of);
             } else {
                 JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
             }
        });
        btnDelete.addActionListener(e -> {
            int i = tbOfferBill.getSelectedRow();
            if (i >= 0) {
                int id = Integer.parseInt(tbOfferBill.getFirstColumn(i));
             OfferBillDTO offer = OfferBillBUS.getInstance().getById(id);
                if (OfferBillBUS.getInstance().delete(offer)) {
                    JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!");
                    loadOfferBill();
                } else {
                    JOptionPane.showMessageDialog(thisPanel, SupplierBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRefresh.addActionListener(e -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadOfferBill();
        });

        btnIn.addActionListener(e -> {
            List<Object[]> list = tbOfferBill.ImportExel(4);
            if (list == null) return;
            List<SupplierDTO> suppliers = new ArrayList<>();
            for (Object[] ob : list)
                suppliers.add(new SupplierDTO(-1, ob[0].toString(), ob[1].toString(), ob[2].toString(), ob[3].toString()));
            if (SupplierBUS.getInstance().addSuppliers(suppliers)) {
                JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + SupplierBUS.getInstance().getNumLine() + " nhà cung cấp");
                loadOfferBill();
            } else {
                JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + SupplierBUS.getInstance().getError());
            }
        });

        btnOut.addActionListener(e -> {
            tbOfferBill.ExportExel("Danh sách nhà cung cấp");
        });

        cbSearch.addActionListener(e -> {
            int i = cbSearch.getSelectedIndex();
            if (posSelectedCB != i) {
                tfSearch.setText("");
            }
        });

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { textChange(); }
            public void removeUpdate(DocumentEvent e) { textChange(); }
            public void changedUpdate(DocumentEvent e) { textChange(); }
        });
        // endregion

        // region ADD
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
        add(tbOfferBill.scrPn);
        add(pnFooter);
        // endregion
    }
    public void loadOfferBill() {
        tbOfferBill.dftbModel.setRowCount(0);
        for (OfferBillDTO o : OfferBillBUS.getInstance().getList())
            tbOfferBill.dftbModel.addRow(o.getObjects());
    }
    public void textChange() {
        tbOfferBill.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for (SupplierDTO supplier : SupplierBUS.getInstance().getSupplierListBy(col, txt))
            tbOfferBill.dftbModel.addRow(supplier.getObjects());
    }
}
