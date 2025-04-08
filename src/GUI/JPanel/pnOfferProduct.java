package GUI.JPanel;

import BUS.OfferProductBUS;
import BUS.SupplierBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddSupplier;
import GUI.JDialog.dlEditSupplier;
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
    int posSelectedCB = 0;

    public pnOfferProduct(pnDiscount parent) {
        setLayout(null);
        setBackground(MyColor.White);

        // region SET BOUNDS
        pnHeader.setBounds(0, 0, 970, 90);
        pnFunc.setBounds(0, 0, 370, 90);
        btnAdd.setBounds(15, 20, 60, 60);
        btnEdit.setBounds(85, 20, 60, 60);
        btnDelete.setBounds(155, 20, 60, 60);
        btnIn.setBounds(225, 20, 60, 60);
        btnOut.setBounds(295, 20, 60, 60);
        pnSearch.setBounds(460, 0, 500, 90);
        cbSearch.setBounds(475, 30, 150, 30);
        tfSearch.setBounds(640, 30, 200, 30);
        btnRefresh.setBounds(845, 30, 100, 30);
        pnFooter.setBounds(0, 100, 970, 650);
        tbOfferProduct.scrPn.setBounds(0, 100, 960, 610);
        // endregion

        // region EVENT CHO PANEL NÀY
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                btnRefresh.doClick();
            }
        });

        btnAdd.addActionListener(e -> {
            // new dlAddOfferProduct(frame, thisPanel);
        });

        btnEdit.addActionListener(e -> {
            // int i = tbOfferProduct.getSelectedRow();
            // if (i >= 0) {
            //     int id = Integer.parseInt(tbOfferProduct.getFirstColumn(i));
            //     OfferProductDTO offer = OfferProductBUS.getInstance().getById(id);
            //     new dlEditOfferProduct(frame, thisPanel, offer);
            // } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });

        btnDelete.addActionListener(e -> {
//            int i = tbOfferProduct.getSelectedRow();
//            if (i >= 0) {
//                int id = Integer.parseInt(tbOfferProduct.getFirstColumn(i));
//                OfferProductDTO offer = OfferProductBUS.getInstance().getById(id);
//                if (OfferProductBUS.getInstance().delete(offer)) {
//                    JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    loadOfferProduct();
//                    textChange();
//                } else JOptionPane.showMessageDialog(thisPanel, OfferProductBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
//            } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });

        btnRefresh.addActionListener(e -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadOfferProduct();
        });

        btnIn.addActionListener(e -> {
//            List<Object[]> list = tbOfferProduct.ImportExel(4);
//            if (list == null) return;
//            List<OfferProductDTO> offers = new ArrayList<>();
//            for (Object[] ob : list)
//                offers.add(new OfferProductDTO(-1, ob[0].toString(), ob[1].toString(), ob[2].toString(), ob[3].toString()));
//            if (OfferProductBUS.getInstance().addOffers(offers)) {
//                JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + OfferProductBUS.getInstance().getNumLine() + " khuyến mãi sản phẩm");
//                loadOfferProduct();
//            } else {
//                JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + OfferProductBUS.getInstance().getError());
//            }
        });

        btnOut.addActionListener(e -> tbOfferProduct.ExportExel("Danh sách khuyến mãi sản phẩm"));

        cbSearch.addActionListener(e -> {
            int i = cbSearch.getSelectedIndex();
            if (posSelectedCB != i) {
                tfSearch.setText("");
            }
            posSelectedCB = i;
        });

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                textChange();
            }

            public void removeUpdate(DocumentEvent e) {
                textChange();
            }

            public void changedUpdate(DocumentEvent e) {
                textChange();
            }
        });

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
        for (OfferProductDTO offer : OfferProductBUS.getInstance().getList()) {
            tbOfferProduct.dftbModel.addRow(offer.getObjects());
        }
    }

    public void textChange() {
//        tbOfferProduct.dftbModel.setRowCount(0);
//        int col = cbSearch.getSelectedIndex();
//        String txt = tfSearch.getText();
//        for (OfferProductDTO offer : OfferProductBUS.getInstance().getListBy(col, txt)) {
//            tbOfferProduct.dftbModel.addRow(offer.getObjects());
//        }
    }
}
