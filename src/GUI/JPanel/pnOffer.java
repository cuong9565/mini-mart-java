package GUI.JPanel;

import BUS.OfferBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddoffer;
import GUI.JDialog.dlEditoffer;
import GUI.JDialog.dlChooseProduct;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.Arrays;
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
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã số","Ngày bắt đầu", "Ngày kết thúc"}, 12);
    MyJTable tbOffer = new MyJTable(new String[]{"Mã số", "Tên chương trình","Ngày bắt đầu", "Ngày kết thúc","Áp dụng","Giảm giá","Trạng thái"}, new int[]{}, new int[]{}, new int[]{});
    pnOffer thisPanel = this;
    JButton btnchosse = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Chọn sp", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnOffer() {
        setLayout(null);
        setBackground(MyColor.White);
        // region SET BOUNDS
        pnHeader.setBounds(0,0,1170, 90);
        pnFunc.setBounds(0,0,470,90);
        btnAdd.setBounds(15,20,60,60);
        btnEdit.setBounds(85,20,60,60);
        btnDelete.setBounds(155,20,60,60);
        btnIn.setBounds(225,20,60,60);
        btnOut.setBounds(295,20,60,60);
        btnchosse.setBounds(365,20,90,60);
        pnSearch.setBounds(660,0,500,90);
        cbSearch.setBounds(675, 30, 150, 30);
        tfSearch.setBounds(840, 30, 200, 30);
        btnRefresh.setBounds(1045,30,100,30);
        pnFooter.setBounds(0,100,1170, 650);
        tbOffer.scrPn.setBounds(0,100,1160,610);
        // endregion
        // region SET EVENT
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadOffer();}
        });
        btnAdd.addActionListener(_ -> new dlAddoffer(pnOffer.this));
        btnEdit.addActionListener(_ -> {
            int i = tbOffer.getSelectedRow();
            if (i >= 0) {
                int id = Integer.parseInt(tbOffer.getFirstColumn(i));
                 OfferDTO offer = OfferBUS.getInstance().getOfferById(id);
                 new dlEditoffer(thisPanel, offer);
            } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });
        btnchosse.addActionListener(e -> {
            if (tbOffer.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn hóa đơn !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                String applyType = tbOffer.getValueAt(tbOffer.getSelectedRow(), 4).toString(); // cột "Áp dụng"

                if (applyType.equals("Giảm giá hóa đơn")) {
                    JOptionPane.showMessageDialog(thisPanel, "Chỉ dùng cho Giảm giá sản phẩm !!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int value = Integer.parseInt(tbOffer.getValueAt(tbOffer.getSelectedRow(), 0).toString());
                    OfferDTO of = OfferBUS.getInstance().getOfferById(value);
                    new dlChooseProduct(pnOffer.this, of);
                }
            }
        });

        btnDelete.addActionListener(_ -> {
            int i = tbOffer.getSelectedRow();
            if (i >= 0) {
                int choose = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc chắn muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                if(choose==JOptionPane.YES_OPTION) {
                    try {
                        int id = Integer.parseInt(tbOffer.getFirstColumn(i));
                        OfferBUS.getInstance().delete(id);
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!");
                        loadOffer();
                    }
                    catch(Exception e) {
                         JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });

        btnRefresh.addActionListener(_ -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadOffer();
        });

        btnIn.addActionListener(_ -> {
            List<Object[]> list = tbOffer.ImportExel(6);
            if(list == null) return;

            int choose = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc chắn muốn nhập?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(choose == JOptionPane.YES_OPTION) {
                int success = 0;
                StringBuilder error = new StringBuilder();
                for (Object[] ob : list) {
                    try {
                        if(ob.length < 6) {
                            error.append("Dòng thiếu dữ liệu\n");
                            continue;
                        }

                        // Xử lý phần trăm giảm giá
                        int discount = 0;
                        if(ob[5] != null) {
                            String discountStr = ob[5].toString().replace("%", "").trim();
                            try {
                                discount = Integer.parseInt(discountStr);
                            } catch (NumberFormatException e) {
                                error.append("Giá trị giảm giá không hợp lệ: ").append(ob[5].toString()).append("\n");
                                continue;
                            }
                        }

                        OfferDTO offer = new OfferDTO(
                                -1,
                                ob[1] != null ? ob[1].toString() : "",
                                ob[2] != null ? ob[2].toString() : "",
                                ob[3] != null ? ob[3].toString() : "",
                                ob[4] != null ? ob[4].toString() : "",
                                discount
                        );
                        OfferBUS.getInstance().add(offer);
                        success++;
                    } catch (Exception e) {
                        error.append("Lỗi: ").append(e.getMessage()).append("\n");
                    }
                }
                if(!error.isEmpty()) {
                    JOptionPane.showMessageDialog(thisPanel, error.toString(), "Lỗi", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + success + " chương trình khuyến mãi", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadOffer();
            }
        });

        btnOut.addActionListener(_ -> tbOffer.ExportExel("Danh sách khuyến mãi"));
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
        add(btnchosse);
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
