package pnForm;

import Components.MyJButton;
import Components.MyJLabel;
import Components.MyJTextField;

import javax.swing.*;
import java.awt.*;

public class pnChangeInfo extends JPanel {
    String White = "#FFFFFF";
    String Black = "#000000";
    JLabel lbTitle = MyJLabel.GetJLabel(Font.BOLD, 20, Black, "Thông tin tài khoản", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbId = MyJLabel.GetJLabel(Font.PLAIN, 12, Black, "Mã nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbUserName = MyJLabel.GetJLabel(Font.PLAIN, 12, Black, "Tên tài khoản", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbFirstName = MyJLabel.GetJLabel(Font.PLAIN, 12, Black, "Họ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbLastName = MyJLabel.GetJLabel(Font.PLAIN, 12, Black, "Tên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAddress = MyJLabel.GetJLabel(Font.PLAIN, 12, Black, "Địa chỉ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbType = MyJLabel.GetJLabel(Font.PLAIN, 12, Black, "Vai trò", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfId = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfUserName = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfFirstName = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfLastName = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfAddress = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfType = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14 , false);
    JButton btnSave = MyJButton.GetJButton(Font.BOLD, 12, White, "#0099FF", "#64B4FF", "Lưu thông tin", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnChangeInfo() {
        setBackground(Color.decode(White));
        setLayout(null);

        lbTitle.setBounds(0,0,480,25);
        lbId.setBounds(0,50,480,20);
        tfId.setBounds(0,70,480,20);
        lbUserName.setBounds(0,110,480,20);
        tfUserName.setBounds(0,130,480,20);
        lbFirstName.setBounds(0,170,480,20);
        tfFirstName.setBounds(0,190,480,20);
        lbLastName.setBounds(0,230,480,20);
        tfLastName.setBounds(0,250,480,20);
        lbAddress.setBounds(0,290,480,20);
        tfAddress.setBounds(0,310,480,20);
        lbType.setBounds(0,350,480,20);
        tfType.setBounds(0,370,480,20);
        btnSave.setBounds(180,430, 120, 40);

        add(lbTitle);
        add(lbId);
        add(lbUserName);
        add(lbFirstName);
        add(lbLastName);
        add(lbAddress);
        add(lbType);
        add(tfId);
        add(tfUserName);
        add(tfFirstName);
        add(tfLastName);
        add(tfAddress);
        add(tfType);
        add(btnSave);
    }
}
