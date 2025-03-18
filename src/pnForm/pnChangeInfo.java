package pnForm;

import Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnChangeInfo extends JPanel {
    JLabel lbId = MyJLabel.GetJLabel(Font.PLAIN, 14, MyColor.Black, "Mã nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbUserName = MyJLabel.GetJLabel(Font.PLAIN, 14, MyColor.Black, "Tên tài khoản", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbFirstName = MyJLabel.GetJLabel(Font.PLAIN, 14, MyColor.Black, "Họ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbLastName = MyJLabel.GetJLabel(Font.PLAIN, 14, MyColor.Black, "Tên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhone = MyJLabel.GetJLabel(Font.PLAIN, 14, MyColor.Black, "Số điện thoại *", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbType = MyJLabel.GetJLabel(Font.PLAIN, 14, MyColor.Black, "Vai trò", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAddress = MyJLabel.GetJLabel(Font.PLAIN, 14, MyColor.Black, "Địa chỉ", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfId = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfUserName = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfFirstName = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfLastName = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPhone = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfType = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14 , false);
    JTextField tfAddress = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14 , true);
    JButton btnSave = MyJButton.GetJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Lưu thông tin", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = MyJButton.GetJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnChangeInfo(JDialog dialog) {
        setBackground(MyColor.White);
        setLayout(null);

        lbId.setBounds(0,0,200,20);
        tfId.setBounds(0,20,200,30);
        lbType.setBounds(220,0,200,20);
        tfType.setBounds(220,20,200,30);

        lbFirstName.setBounds(0,70,200,20);
        tfFirstName.setBounds(0,90,200,30);
        lbLastName.setBounds(220,70,200,20);
        tfLastName.setBounds(220,90,200,30);

        lbUserName.setBounds(0,140,200,20);
        tfUserName.setBounds(0,160,200,30);
        lbPhone.setBounds(220,140,200,20);
        tfPhone.setBounds(220,160,200,30);

        lbAddress.setBounds(0,210,420,20);
        tfAddress.setBounds(0,230,420,30);

        btnSave.setBounds(50,290, 150, 40);
        btnEsc.setBounds(220,290, 150, 40);

        // region Event
        btnEsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        // endregion
        add(lbId);
        add(lbUserName);
        add(lbFirstName);
        add(lbLastName);
        add(lbPhone);
        add(lbType);
        add(tfId);
        add(tfUserName);
        add(tfFirstName);
        add(tfLastName);
        add(tfPhone);
        add(tfType);
        add(lbAddress);
        add(tfAddress);
        add(btnSave);
        add(btnEsc);
    }
}
