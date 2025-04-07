package GUI.JDialog;

import BUS.OfferBUS;
import Components.*;
import DTO.OfferDTO;
import GUI.JPanel.pnOffer;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class dlAddoffer extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thêm chương trình giảm giá", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên chương trình*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày áp dụng*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày kết thúc*", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JSpinner spStartDate = new JSpinner(new SpinnerDateModel());
    JSpinner spEndDate = new JSpinner(new SpinnerDateModel());

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    public dlAddoffer(pnOffer parentPanel) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Thêm chương trình giảm giá");
        setSize(540, 440);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(spStartDate, "yyyy-MM-dd");
        spStartDate.setEditor(startEditor);
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(spEndDate, "yyyy-MM-dd");
        spEndDate.setEditor(endEditor);

        // region setBounds
        pnMain.setBounds(0, 0, 540, 440);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 540, 60);
        lbName.setBounds(50, 80, 440, 20);
        tfName.setBounds(50, 100, 440, 30);
        lbStartDate.setBounds(50, 150, 200, 20);
        spStartDate.setBounds(50, 170, 200, 30);
        lbEndDate.setBounds(270, 150, 200, 20);
        spEndDate.setBounds(270, 170, 200, 30);
        btnSave.setBounds(100, 300, 150, 40);
        btnEsc.setBounds(270, 300, 150, 40);
        btnEsc.addActionListener(e -> dispose());
        // Sự kiện nút Xác nhận
        btnSave.addActionListener(e -> {
            String name = tfName.getText();
            Date startDateUtil = (Date) spStartDate.getValue();
            Date endDateUtil = (Date) spEndDate.getValue();

            if (name.isEmpty() || startDateUtil == null || endDateUtil == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Ép kiểu về java.sql.Date (dùng trong DTO)
            java.sql.Date startDate = new java.sql.Date(startDateUtil.getTime());
            java.sql.Date endDate = new java.sql.Date(endDateUtil.getTime());
            OfferDTO offer = new OfferDTO(-1, startDate, endDate);

            boolean success = OfferBUS.getInstance().add(offer);

            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadOffer();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });


        add(lbHeader);
        add(lbName);
        add(tfName);
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
