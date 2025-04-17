package GUI.JDialog;

import BUS.OfferBUS;
import BUS.OfferBillBUS;
import Components.*;
import DTO.OfferDTO;
import DTO.OfferBillDTO;
import GUI.JPanel.pnOfferBill;

import javax.swing.*;
import java.awt.*;

public class dlAddOfferBill extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thêm chương trình giảm giá sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Phần trăm giảm giá*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Thời gian giảm giá*", SwingConstants.LEFT, SwingConstants.CENTER);
    JSpinner spDiscount = new MyJSpinner(1, 1, 100, 1);
    JComboBox<OfferDTO> cbTime = new MyJComboBox<>(new OfferDTO[]{}, 12);

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    public dlAddOfferBill(pnOfferBill parentPanel) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Thêm chương trình giảm giá sản phẩm");
        setSize(540, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region setBounds
        pnMain.setBounds(0, 0, 540, 300);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 540, 60);
        lbStartDate.setBounds(50, 80, 200, 20);
        spDiscount.setBounds(50, 100, 200, 30);
        lbEndDate.setBounds(270, 80, 200, 20);
        cbTime.setBounds(270, 100, 200, 30);
        btnSave.setBounds(100, 160, 150, 40);
        btnEsc.setBounds(270, 160, 150, 40);
        // endregion

        for(OfferDTO o: OfferBUS.getInstance().getList()) cbTime.addItem(o);
        btnEsc.addActionListener(_ -> dispose());
        btnSave.addActionListener(_ -> {
            OfferDTO offer = (OfferDTO) cbTime.getSelectedItem();
            OfferBillDTO offerProduct = new OfferBillDTO(-1, offer, Integer.parseInt(spDiscount.getValue().toString()));
            if (OfferBillBUS.getInstance().add(offerProduct)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadOfferBill();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, OfferBillBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // region ADD
        add(lbHeader);
        add(lbStartDate);
        add(spDiscount);
        add(lbEndDate);
        add(cbTime);
        add(btnSave);
        add(btnEsc);
        add(pnMain);
        // endregion

        setVisible(true);
    }
}
