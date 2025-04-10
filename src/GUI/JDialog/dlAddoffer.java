package GUI.JDialog;

import BUS.OfferBUS;
import Components.*;
import DTO.OfferDTO;
import GUI.JPanel.pnOffer;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

public class dlAddoffer extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thêm chương trình giảm giá", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày áp dụng*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày kết thúc*", SwingConstants.LEFT, SwingConstants.CENTER);
    MyJSpinner spStartDate = new MyJSpinner(new Date());
    MyJSpinner spEndDate = new MyJSpinner(new Date());

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    public dlAddoffer(pnOffer parentPanel) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Thêm chương trình giảm giá");
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
        spStartDate.setBounds(50, 100, 200, 30);
        lbEndDate.setBounds(270, 80, 200, 20);
        spEndDate.setBounds(270, 100, 200, 30);
        btnSave.setBounds(100, 160, 150, 40);
        btnEsc.setBounds(270, 160, 150, 40);
        // endregion
        btnEsc.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            OfferDTO offer = new OfferDTO(-1, spStartDate.getSqlDate(), spEndDate.getSqlDate());
            if (OfferBUS.getInstance().add(offer)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadOffer();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: " + OfferBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });


        add(lbHeader);
        add(lbStartDate);
        add(spStartDate);
        add(lbEndDate);
        add(spEndDate);
        add(btnSave);
        add(btnEsc);
        add(pnMain);

        setVisible(true);
    }
}
