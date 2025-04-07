package GUI.JDialog;

import BUS.OfferBillBUS;
import Components.*;
import DTO.OfferBillDTO;
import DTO.OfferDTO;
import BUS.OfferBUS;
import GUI.JPanel.pnOfferBill;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class dlAddOfferBill extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Chỉnh sửa chương trình giảm giá hóa đơn", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbOfferId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Chọn chương trình giảm giá*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbDiscountPercent = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Phần trăm giảm giá*", SwingConstants.LEFT, SwingConstants.CENTER);
    JComboBox<Integer> cbOfferId;
    JTextField tfDiscountPercent = new MyJTextFieldInput(Font.PLAIN, 14, true);

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    public dlAddOfferBill(pnOfferBill parentPanel) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Thêm chương trình giảm giá hóa đơn");
        setSize(540, 440);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Khởi tạo JComboBox
        List<OfferDTO> offers = OfferBUS.getInstance().getList();
        Integer[] offerIds = new Integer[offers.size()];
        for (int i = 0; i < offers.size(); i++) {
            OfferDTO offer = offers.get(i);
            offerIds[i] = offer.getId();
        }
        cbOfferId = new JComboBox<>(offerIds);

        // region setBounds
        pnMain.setBounds(0, 0, 540, 440);
        lbOfferId.setBounds(50, 80, 200, 20);
        cbOfferId.setBounds(50, 100, 200, 30);
        lbDiscountPercent.setBounds(270, 80, 200, 20);
        tfDiscountPercent.setBounds(270, 100, 200, 30);
        btnSave.setBounds(100, 300, 150, 40);
        btnEsc.setBounds(270, 300, 150, 40);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 540, 60);
        // endregion

        btnEsc.addActionListener(e -> dispose());

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int offerId = (Integer) cbOfferId.getSelectedItem();
                    int discountPercent = Integer.parseInt(tfDiscountPercent.getText());

                    // Kiểm tra tính hợp lệ của phần trăm giảm giá
                    if (discountPercent < 0 || discountPercent > 100) {
                        JOptionPane.showMessageDialog(dlAddOfferBill.this, "Phần trăm giảm giá phải trong khoảng 0 đến 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    OfferDTO offer = OfferBUS.getInstance().getOfferById(offerId);

                    OfferBillDTO offerBill = new OfferBillDTO(-1, discountPercent, offer);
                   boolean success = OfferBillBUS.getInstance().addOfferBill(offerBill);
                    if (success) {
                        JOptionPane.showMessageDialog(dlAddOfferBill.this, "Thêm chương trình giảm giá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        parentPanel.loadOfferBill();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(dlAddOfferBill.this, "Thêm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dlAddOfferBill.this, "Vui lòng nhập số hợp lệ cho phần trăm giảm giá.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // endregion

        add(lbOfferId);
        add(cbOfferId);
        add(lbDiscountPercent);
        add(tfDiscountPercent);
        add(btnSave);
        add(btnEsc);
        add(lbHeader);
        add(pnMain);
        setVisible(true);
    }
}