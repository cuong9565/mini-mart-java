package GUI.JDialog;

import BUS.OfferBUS;
import BUS.OfferBillBUS;
import DTO.OfferBillDTO;
import DTO.OfferDTO;
import GUI.JPanel.pnOfferBill;
import Components.*;

import javax.swing.*;
import java.awt.*;

public class dlEditOfferBill extends JDialog {
    private JComboBox<Integer> cbOfferId;
    private JTextField tfDiscountPercent;
    private JButton btnSave, btnCancel;

    public dlEditOfferBill(pnOfferBill parentPanel, OfferBillDTO oldData) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Chỉnh sửa chương trình giảm giá");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Label
        JLabel lbOfferId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Chọn mã chương trình giảm giá", SwingConstants.LEFT, SwingConstants.CENTER);
        lbOfferId.setBounds(30, 30, 300, 20);
        add(lbOfferId);

        JLabel lbDiscount = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Phần trăm giảm giá (%)", SwingConstants.LEFT, SwingConstants.CENTER);
        lbDiscount.setBounds(30, 90, 300, 20);
        add(lbDiscount);

        // ComboBox Offer ID
        cbOfferId = new JComboBox<>();
        for (OfferDTO offer : OfferBUS.getInstance().getList()) {
            cbOfferId.addItem(offer.getId());
        }
        cbOfferId.setBounds(30, 55, 320, 30);
        cbOfferId.setSelectedItem(oldData.getOffer().getId());
        add(cbOfferId);

        // TextField Discount
        tfDiscountPercent = new JTextField(String.valueOf(oldData.getDiscount()));
        tfDiscountPercent.setBounds(30, 115, 320, 30);
        add(tfDiscountPercent);

        // Buttons
        btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Lưu", SwingConstants.CENTER, SwingConstants.CENTER);
        btnSave.setBounds(60, 170, 100, 35);
        add(btnSave);

        btnCancel = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);
        btnCancel.setBounds(220, 170, 100, 35);
        add(btnCancel);

        // Events
        btnCancel.addActionListener(e -> dispose());

        btnSave.addActionListener(e -> {
            try {
                int offerId = (Integer) cbOfferId.getSelectedItem();
                int discount = Integer.parseInt(tfDiscountPercent.getText());

                if (discount < 0 || discount > 100) {
                    JOptionPane.showMessageDialog(this, "Phần trăm giảm giá phải từ 0 đến 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                OfferDTO offer = OfferBUS.getInstance().getOfferById(offerId);
                if (offer == null) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy chương trình giảm giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                OfferBillDTO updated = new OfferBillDTO(oldData.getId(), discount, offer);
                boolean success = OfferBillBUS.getInstance().updateOfferBill(updated);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    parentPanel.loadOfferBill(); // Cập nhật bảng
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm giá không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
