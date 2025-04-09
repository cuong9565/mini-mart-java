package GUI.JPanel;

import BUS.OfferBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddoffer;
import GUI.JDialog.dlEditoffer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
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

    public pnOffer(pnDiscount parent) {
        setLayout(null);
        setBackground(MyColor.White);

        // region SET BOUNDS
        pnHeader.setBounds(0,0,1170, 90);
        pnFunc.setBounds(0,0,370,90);
        btnAdd.setBounds(15,20,60,60);
        btnEdit.setBounds(85,20,60,60);
        btnDelete.setBounds(155,20,60,60);
        btnIn.setBounds(225,20,60,60);
        btnOut.setBounds(295,20,60,60);
        pnSearch.setBounds(660,0,500,90);
        cbSearch.setBounds(675, 30, 150, 30);
        tfSearch.setBounds(840, 30, 200, 30);
        btnRefresh.setBounds(1045,30,100,30);
        pnFooter.setBounds(0,100,1170, 650);
        tbOffer.scrPn.setBounds(0,100,1160,610);
        // endregion
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadOffer();}
        });
        // region SET EVENT
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
            List<Object[]> list = tbOffer.ImportExel(2);
            if(list==null) return;
            String error = null;
            int success = 0;
            for (Object[] ob : list) {
                OfferDTO offer = new OfferDTO(-1, ob[0].toString(), ob[1].toString());
                if(OfferBUS.getInstance().add(offer)) success++;
                else error += ("\n" + OfferBUS.getInstance().getError());
            }
            JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + success + " thời gian giảm giá");
            if(error!=null) JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + error);
            loadOffer();
        });

        btnOut.addActionListener(_ -> tbOffer.ExportExel("Danh sách khuyến mãi"));
        cbSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {textChange();}
        });
        tfSearch.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {textChange();}
        });
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textChange();}
            public void removeUpdate(DocumentEvent e) {textChange();}
            public void changedUpdate(DocumentEvent e) {textChange();}
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
        add(tbOffer.scrPn);
        add(pnFooter);
        // endregion
    }

    public void loadOffer() {
        tbOffer.dftbModel.setRowCount(0);
        for (OfferDTO offer : OfferBUS.getInstance().getList())
            tbOffer.dftbModel.addRow(offer.getObjects());
        textChange();
    }
    public void textChange() {
        tbOffer.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for (OfferDTO offer : OfferBUS.getInstance().getListBy(col, txt))
            tbOffer.dftbModel.addRow(offer.getObjects());
    }
}
