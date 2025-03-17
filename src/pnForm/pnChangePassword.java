package pnForm;

import Components.MyJButton;
import Components.MyJLabel;
import Components.MyJTextField;

import javax.swing.*;
import java.awt.*;

public class pnChangePassword extends JPanel {
    String White = "#FFFFFF";
    String Black = "#000000";
    JLabel lbTitle = MyJLabel.GetJLabel(Font.BOLD, 20, Black, "Đổi mật khẩu", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbPassword = MyJLabel.GetJLabel(Font.PLAIN, 12, Black, "Mật khẩu hện tại", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbNewPassword = MyJLabel.GetJLabel(Font.PLAIN, 12, Black, "Mật khẩu mới", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbConfirmPassword = MyJLabel.GetJLabel(Font.PLAIN, 12, Black, "Xác nhận mật khẩu", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField pfPassword = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField pfNewPassword = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField pfConfirmPassword = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JButton btnSave = MyJButton.GetJButton(Font.BOLD, 12, White, "#0099FF", "#64B4FF", "Đổi mật khẩu", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnChangePassword() {
        setBackground(Color.decode(White));
        setLayout(null);

        lbTitle.setBounds(0,0,480,25);
        lbPassword.setBounds(0,50,480,20);
        pfPassword.setBounds(0,70,480,20);
        lbNewPassword.setBounds(0,110,480,20);
        pfNewPassword.setBounds(0,130,480,20);
        lbConfirmPassword.setBounds(0,170,480,20);
        pfConfirmPassword.setBounds(0,190,480,20);
        btnSave.setBounds(180,250, 120, 40);

        add(lbTitle);
        add(lbPassword);
        add(lbNewPassword);
        add(lbConfirmPassword);
        add(pfPassword);
        add(pfNewPassword);
        add(pfConfirmPassword);
        add(btnSave);
    }
}

/*
* Ẩn password
* */
