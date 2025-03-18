package pnForm;

import Components.MyJButton;
import Components.MyJLabel;
import Components.MyJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnChangePassword extends JPanel {
    String White = "#FFFFFF";
    String Black = "#000000";
    JLabel lbPassword = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Mật khẩu hiện tại", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbNewPassword = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Mật khẩu mới", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbConfirmPassword = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Xác nhận mật khẩu", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField pfPassword = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField pfNewPassword = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField pfConfirmPassword = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JButton btnSave = MyJButton.GetJButton(Font.BOLD, 14, White, "#00C800", "#64FF64", "Đổi mật khẩu", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = MyJButton.GetJButton(Font.BOLD, 14, White, "#DC0000", "#FF6464", "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnChangePassword(JDialog dialog) {
        setBackground(Color.decode(White));
        setLayout(null);

        lbPassword.setBounds(0,0,420,20);
        pfPassword.setBounds(0,20,420,30);

        lbNewPassword.setBounds(0,70,420,20);
        pfNewPassword.setBounds(0,90,420,30);

        lbConfirmPassword.setBounds(0,140,420,20);
        pfConfirmPassword.setBounds(0,160,420,30);

        btnSave.setBounds(50,210, 150, 40);
        btnEsc.setBounds(220,210, 150, 40);

        // region Event
        btnEsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        // endregion
        add(lbPassword);
        add(lbNewPassword);
        add(lbConfirmPassword);
        add(pfPassword);
        add(pfNewPassword);
        add(pfConfirmPassword);
        add(btnSave);
        add(btnEsc);
    }
}

/*
* Ẩn password
* */
