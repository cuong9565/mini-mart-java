package GUI.JPanel;

import BUS.OfferBUS;
import BUS.OfferBillBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class pnOfferBill extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnIn = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Nhập<br>Excel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Excel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);

    JComboBox<String> cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá"}, 12);
    MyJTable tbOfferBill = new MyJTable(new String[]{"Mã số", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá"}, new int[]{}, new int[]{}, new int[]{});

    pnOfferBill thisPanel = this;

    public pnOfferBill() {
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
        tbOfferBill.scrPn.setBounds(0,100,1160,610);
        // endregion
        // region SET EVENT
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadOfferBill();}
        });
        btnAdd.addActionListener(_ -> new dlAddOfferBill(thisPanel));
        btnEdit.addActionListener(_ -> {
            int i = tbOfferBill.getSelectedRow();
            if (i >= 0) {
                int id = Integer.parseInt(tbOfferBill.getFirstColumn(i));
                OfferBillDTO offer = OfferBillBUS.getInstance().getItemById(id);
                new dlEditOfferBill(thisPanel, offer);
            } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });

        btnDelete.addActionListener(_ -> {
            int i = tbOfferBill.getSelectedRow();
            if (i >= 0) {
                int choose = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if(choose == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(tbOfferBill.getFirstColumn(i));
                    try {
                        OfferBillBUS.getInstance().delete(id);
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                    loadOfferBill();
                }
            } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        });

        btnRefresh.addActionListener(_ -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadOfferBill();
        });

        btnIn.addActionListener(_ -> {
            List<Object[]> list = tbOfferBill.ImportExel(3);
            if(list==null) return;
            int choose = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc chắn muốn thêm?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(choose == JOptionPane.YES_OPTION){
                int success = 0;
                StringBuilder error = new StringBuilder();
                for (Object[] ob : list) {
                    try{
                        OfferDTO offer = OfferBUS.getInstance().getItemByDate(new MyDate(ob[0].toString()), new MyDate(ob[1].toString()));
                        OfferBillDTO offerProduct = new OfferBillDTO(-1, offer, Integer.parseInt(ob[2].toString().replace("%", "")));
                        OfferBillBUS.getInstance().add(offerProduct);
                        success++;
                    }
                    catch (Exception e){
                        error.append(e.getMessage()).append("\n");
                    }

                }
                if(!error.isEmpty())
                    JOptionPane.showMessageDialog(thisPanel, error.toString(), "Lỗi", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(thisPanel, "Đã thêm thành công " + success + " thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadOfferBill();
            }
        });
        btnOut.addActionListener(_ -> tbOfferBill.ExportExel("Danh sách giảm giá hóa đơn"));
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
        for (OfferBillDTO offer : OfferBillBUS.getInstance().getList())
            tbOfferBill.dftbModel.addRow(offer.getObjects());
        textChange();
    }
    public void textChange() {
        tbOfferBill.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for (OfferBillDTO offer : OfferBillBUS.getInstance().getListBy(col, txt))
            tbOfferBill.dftbModel.addRow(offer.getObjects());
    }
}
