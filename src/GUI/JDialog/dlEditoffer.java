package GUI.JDialog;

import BUS.OfferBUS;
import Components.*;
import DTO.OfferDTO;
import GUI.JPanel.pnOffer;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class dlEditoffer extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Chỉnh sửa chương trình", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên chương trình*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày áp dụng*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày kết thúc*", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JSpinner spStartDate;
    JSpinner spEndDate;

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Cập nhật", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    public dlEditoffer(pnOffer parentPanel, OfferDTO offer) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Chỉnh sửa chương trình");
        setSize(540, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Spinner date model
        SpinnerDateModel modelStart = new SpinnerDateModel();
        SpinnerDateModel modelEnd = new SpinnerDateModel();
        spStartDate = new JSpinner(modelStart);
        spEndDate = new JSpinner(modelEnd);
        spStartDate.setEditor(new JSpinner.DateEditor(spStartDate, "yyyy-MM-dd"));
        spEndDate.setEditor(new JSpinner.DateEditor(spEndDate, "yyyy-MM-dd"));

        // region Set Bounds
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 540, 60);
        pnMain.setBounds(0, 0, 540, 400);
        lbName.setBounds(50, 80, 420, 20);
        tfName.setBounds(50, 100, 420, 30);
        lbStartDate.setBounds(50, 150, 200, 20);
        spStartDate.setBounds(50, 170, 200, 30);
        lbEndDate.setBounds(270, 150, 200, 20);
        spEndDate.setBounds(270, 170, 200, 30);
        btnSave.setBounds(100, 260, 150, 40);
        btnEsc.setBounds(270, 260, 150, 40);
        // endregion

        spStartDate.setValue(offer.getDateStart());
        spEndDate.setValue(offer.getDateEnd());

        btnEsc.addActionListener(e -> dispose());

        btnSave.addActionListener(e -> {
            String name = tfName.getText();
            Date startDate = new Date(((java.util.Date) spStartDate.getValue()).getTime());
            Date endDate = new Date(((java.util.Date) spEndDate.getValue()).getTime());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên chương trình!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            offer.setDateStart(startDate);
            offer.setDateEnd(endDate);

            boolean success = OfferBUS.getInstance().update(offer);

            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadOffer();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(lbName);
        add(tfName);
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
