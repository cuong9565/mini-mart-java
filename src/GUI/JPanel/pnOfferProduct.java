package GUI.JPanel;

import BUS.OfferBUS;
import BUS.OfferProductBUS;
import BUS.SupplierBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.*;
import GUI.JFrame.fManage;
import com.mysql.cj.protocol.Message;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class pnOfferProduct extends JPanel {
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
    MyJTable tbOfferProduct = new MyJTable(new String[]{"Mã số", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá"}, new int[]{}, new int[]{}, new int[]{});

    pnOfferProduct thisPanel = this;

    public pnOfferProduct(pnDiscount parent) {
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
        tbOfferProduct.scrPn.setBounds(0,100,1160,610);
        // endregion
        // region SET EVENT
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadOfferProduct();}
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dlAddOfferProduct(thisPanel);
            }
        });
        btnEdit.addActionListener(e -> {
            int i = tbOfferProduct.getSelectedRow();
            if (i >= 0) {
                int id = Integer.parseInt(tbOfferProduct.getFirstColumn(i));
                OfferProductDTO offer = OfferProductBUS.getInstance().getItemById(id);
                new dlEditOfferProduct(thisPanel, offer);
            } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });

        btnDelete.addActionListener(e -> {
            int i = tbOfferProduct.getSelectedRow();
            if (i >= 0) {
                int id = Integer.parseInt(tbOfferProduct.getFirstColumn(i));
                if(OfferProductBUS.getInstance().delete(id)){
                    JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadOfferProduct();
                }
                else JOptionPane.showMessageDialog(thisPanel, OfferProductBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        });

        btnRefresh.addActionListener(e -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadOfferProduct();
        });

        btnIn.addActionListener(e -> {
            List<Object[]> list = tbOfferProduct.ImportExel(3);
            if(list==null) return;
            String error = "";
            int success = 0;
            for (Object[] ob : list) {
                OfferDTO offer = OfferBUS.getInstance().getItemByDate(ob[0].toString(), ob[1].toString());
                OfferProductDTO offerProduct = new OfferProductDTO(-1, offer, Integer.parseInt(ob[2].toString().replace("%", "")));
                if(OfferProductBUS.getInstance().add(offerProduct)) success++;
                else {
                    error = (OfferProductBUS.getInstance().getError());
                }
            }
            JOptionPane.showMessageDialog(thisPanel, "Đã thêm thành công " + success + " thông tin");
            if(!error.isEmpty()) JOptionPane.showMessageDialog(thisPanel, error, "Lỗi", JOptionPane.ERROR_MESSAGE);
            loadOfferProduct();
        });
        btnOut.addActionListener(_ -> tbOfferProduct.ExportExel("Danh sách giảm giá sản phẩm"));
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
        add(tbOfferProduct.scrPn);
        add(pnFooter);
        // endregion
    }

    public void loadOfferProduct() {
        tbOfferProduct.dftbModel.setRowCount(0);
        for (OfferProductDTO offer : OfferProductBUS.getInstance().getList())
            tbOfferProduct.dftbModel.addRow(offer.getObjects());
        textChange();
    }
    public void textChange() {
        tbOfferProduct.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for (OfferProductDTO offer : OfferProductBUS.getInstance().getListBy(col, txt))
            tbOfferProduct.dftbModel.addRow(offer.getObjects());
    }
}
