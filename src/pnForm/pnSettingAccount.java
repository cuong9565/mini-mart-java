package pnForm;

import Components.MyJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnSettingAccount extends JPanel {
    JButton button = MyJButton.GetJButton(Font.PLAIN, 16, "#000000", "#FFFFFF", "#FFFFFF", "Thiết lập tài khoản", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnSettingAccount() {
        setLayout(null);
        setBackground(Color.decode("#FF00FF"));
        button.setBounds(10,10,100,100);
        add(button);
    }
}
