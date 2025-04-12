package GUI.JDialog;

import BUS.OfferBUS;
import Components.*;
import DTO.OfferDTO;
import GUI.JPanel.pnOffer;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class dlEditoffer extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Chỉnh sửa chương trình", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã số*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày áp dụng*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày kết thúc*", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfId = new MyJTextFieldInput(Font.PLAIN, 14, false);
    MyJSpinner spStartDate = new MyJSpinner(MyDate.getCurrentDate());
    MyJSpinner spEndDate = new MyJSpinner(MyDate.getCurrentDate());

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Cập nhật", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    public dlEditoffer(pnOffer parentPanel, OfferDTO offer) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Chỉnh sửa chương trình");
        setSize(540, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region Set Bounds
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 540, 60);
        pnMain.setBounds(0, 0, 540, 400);
        lbId.setBounds(50, 80, 420, 20);
        tfId.setBounds(50, 100, 420, 30);
        lbStartDate.setBounds(50, 150, 200, 20);
        spStartDate.setBounds(50, 170, 200, 30);
        lbEndDate.setBounds(270, 150, 200, 20);
        spEndDate.setBounds(270, 170, 200, 30);
        btnSave.setBounds(100, 260, 150, 40);
        btnEsc.setBounds(270, 260, 150, 40);
        // endregion
        tfId.setText(String.valueOf(offer.getId()));
        spStartDate.setValue(offer.getDateStart());
        spEndDate.setValue(offer.getDateEnd());

        btnEsc.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            OfferDTO offerNew = new OfferDTO(offer.getId(), spStartDate.getMyDate(), spEndDate.getMyDate());
            if (OfferBUS.getInstance().update(offerNew)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadOffer();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + OfferBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(lbId);
        add(tfId);
        add(lbStartDate);
        add(spStartDate);
        add(lbEndDate);
        add(spEndDate);
        add(btnSave);
        add(btnEsc);
        add(lbHeader);
        add(pnMain);

        setVisible(true);
    }
}
