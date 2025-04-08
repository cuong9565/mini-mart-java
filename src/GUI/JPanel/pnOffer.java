package GUI.JPanel;

import BUS.OfferBUS;
import BUS.SupplierBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddSupplier;
import GUI.JDialog.dlAddoffer;
import GUI.JDialog.dlEditSupplier;
import GUI.JDialog.dlEditoffer;
import GUI.JFrame.fManage;
import com.mysql.cj.protocol.Message;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class pnOffer extends JPanel {
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
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Ngày bắt đầu", "Ngày kết thúc"}, 12);

    MyJTable tbOffer = new MyJTable(new String[]{"Mã số", "Ngày bắt đầu", "Ngày kết thúc"}, new int[]{}, new int[]{}, new int[]{});
    pnOffer thisPanel = this;
    int posSelectedCB = 0;

    public pnOffer(pnDiscount parent) {
        setLayout(null);
        setBackground(MyColor.White);
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
        tbOffer.scrPn.setBounds(0,100,960,610);
        // endregion

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                btnRefresh.doClick();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dlAddoffer(pnOffer.this);
            }
        });


        btnEdit.addActionListener(e -> {
            int i = tbOffer.getSelectedRow();
            if (i >= 0) {
                int id = Integer.parseInt(tbOffer.getFirstColumn(i));
                 OfferDTO offer = OfferBUS.getInstance().getOfferById(id);
                 new dlEditoffer( thisPanel, offer);
            } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });

        btnDelete.addActionListener(e -> {
            int i = tbOffer.getSelectedRow();
            if (i >= 0) {
                int id = Integer.parseInt(tbOffer.getFirstColumn(i));
                 OfferDTO offer = OfferBUS.getInstance().getOfferById(id);
                 if (OfferBUS.getInstance().delete(offer)) {
                     JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!");
                     loadOffer();
                 } else {
                     JOptionPane.showMessageDialog(thisPanel, OfferBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                 }
            } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });

        btnRefresh.addActionListener(e -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadOffer();
        });

        btnIn.addActionListener(e -> {
            List<Object[]> list = tbOffer.ImportExel(4);
            if (list == null) return;
            List<SupplierDTO> suppliers = new ArrayList<>();
            for (Object[] ob : list)
                suppliers.add(new SupplierDTO(-1, ob[0].toString(), ob[1].toString(), ob[2].toString(), ob[3].toString()));
            if (SupplierBUS.getInstance().addSuppliers(suppliers)) {
                JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + SupplierBUS.getInstance().getNumLine() + " nhà cung cấp");
                loadOffer();
            } else {
                JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + SupplierBUS.getInstance().getError());
            }
        });

        btnOut.addActionListener(e -> {
            tbOffer.ExportExel("Danh sách khuyến mãi");
        });

        cbSearch.addActionListener(e -> {
            int i = cbSearch.getSelectedIndex();
            if (posSelectedCB != i) {
                tfSearch.setText("");
                posSelectedCB = i;
            }
        });

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { textChange(); }
            public void removeUpdate(DocumentEvent e) { textChange(); }
            public void changedUpdate(DocumentEvent e) { textChange(); }
        });

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
        add(tbOffer.scrPn);
        add(pnFooter);
    }

    public void loadOffer() {
        tbOffer.dftbModel.setRowCount(0);
        for (OfferDTO offer : OfferBUS.getInstance().getList()) {
            tbOffer.dftbModel.addRow(offer.getObjects());
        }
    }

    public void textChange() {
        tbOffer.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for (SupplierDTO supplier : SupplierBUS.getInstance().getSupplierListBy(col, txt)) {
            tbOffer.dftbModel.addRow(supplier.getObjects());
        }
    }
}
