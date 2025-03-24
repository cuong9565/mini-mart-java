package GUI;

import Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnChangePassword extends JPanel {
    JLabel lbPassword = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mật khẩu hiện tại", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbNewPassword = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mật khẩu mới", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbConfirmPassword = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Xác nhận mật khẩu", SwingConstants.LEFT, SwingConstants.CENTER);
    JPasswordField pfPassword = new MyJPasswordFieldInput(Font.PLAIN, 14);
    JPasswordField pfNewPassword = new MyJPasswordFieldInput(Font.PLAIN, 14);
    JPasswordField pfConfirmPassword = new MyJPasswordFieldInput(Font.PLAIN, 14);
    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Đổi mật khẩu", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnChangePassword(JDialog dialog) {
        setBackground(MyColor.White);
        setLayout(null);

        lbPassword.setBounds(0,0,420,20);
        pfPassword.setBounds(0,20,420,30);
        lbNewPassword.setBounds(0,70,420,20);
        pfNewPassword.setBounds(0,90,420,30);
        lbConfirmPassword.setBounds(0,140,420,20);
        pfConfirmPassword.setBounds(0,160,420,30);
        btnSave.setBounds(50,220, 150, 40);
        btnEsc.setBounds(220,220, 150, 40);

        // region Event
        btnEsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean check = true;

                if(check) JOptionPane.showMessageDialog(dialog, "Đổi mật khẩu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
