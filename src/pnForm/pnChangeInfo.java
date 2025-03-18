package pnForm;

import Components.MyJButton;
import Components.MyJLabel;
import Components.MyJPanel;
import Components.MyJTextField;

import javax.swing.*;
import java.awt.*;

public class pnChangeInfo extends JPanel {
    String White = "#FFFFFF";
    String Black = "#000000";
    JLabel lbTitle = MyJLabel.GetJLabel(Font.BOLD, 20, Black, "Thông tin tài khoản", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbId = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Mã nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbUserName = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Tên tài khoản", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbFirstName = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Họ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbLastName = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Tên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhone = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Số điện thoại *", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbType = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Vai trò", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAddress = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "Địa chỉ", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfId = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfUserName = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfFirstName = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfLastName = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPhone = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfType = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14 , false);
    JTextField tfAddress = MyJTextField.GetJTextFieldInput(Font.PLAIN, 14 , true);
    JButton btnSave = MyJButton.GetJButton(Font.BOLD, 14, White, "#00C800", "#64FF64", "Lưu thông tin", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = MyJButton.GetJButton(Font.BOLD, 14, White, "#DC0000", "#FF6464", "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnChangeInfo() {
        setBackground(Color.decode(White));
        setLayout(null);

        lbTitle.setBounds(0,0,420,25);

        lbId.setBounds(0,50,200,20);
        tfId.setBounds(0,70,200,30);
        lbType.setBounds(220,50,200,20);
        tfType.setBounds(220,70,200,30);

        lbFirstName.setBounds(0,120,200,20);
        tfFirstName.setBounds(0,140,200,30);
        lbLastName.setBounds(220,120,200,20);
        tfLastName.setBounds(220,140,200,30);

        lbUserName.setBounds(0,190,200,20);
        tfUserName.setBounds(0,210,200,30);
        lbPhone.setBounds(220,190,200,20);
        tfPhone.setBounds(220,210,200,30);

        lbAddress.setBounds(0,260,420,20);
        tfAddress.setBounds(0,280,420,30);

        btnSave.setBounds(70,340, 120, 40);
        btnEsc.setBounds(220,340, 120, 40);

        add(lbTitle);
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
