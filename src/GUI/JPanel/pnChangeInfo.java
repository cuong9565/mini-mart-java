package GUI.JPanel;

import BUS.StaffBUS;
import Components.*;
import DTO.StaffDTO;
import GUI.JDialog.dlSettingAccount;
import GUI.JFrame.fManage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnChangeInfo extends JPanel {
    JLabel lbId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbGender = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giới tính", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbFirstName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên *", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbLastName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Họ *", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhone = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số điện thoại *", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbType = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Vai trò", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAddress = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Địa chỉ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSalary = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Lương", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfId = new MyJTextFieldInput(Font.PLAIN, 14, false);
    MyButtonGroup bgGender = new MyButtonGroup(new String[]{"Nam", "Nữ"});
    JTextField tfFirstName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfLastName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPhone = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfType = new MyJTextFieldInput(Font.PLAIN, 14 , false);
    JTextField tfAddress = new MyJTextFieldInput(Font.PLAIN, 14 , true);
    JTextField tfSalary = new MyJTextFieldInput(Font.PLAIN, 14 , false);
    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Lưu thông tin", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JPanel thisPanel = this;

    public pnChangeInfo(dlSettingAccount dialog, fManage parentFrame, StaffDTO accountLogin) {
        setBackground(MyColor.White);
        setLayout(null);

        // region setBounds
        lbId.setBounds(0,0,200,20);
        tfId.setBounds(0,20,200,30);
        lbType.setBounds(220,0,200,20);
        tfType.setBounds(220,20,200,30);

        lbLastName.setBounds(0,70,200,20);
        tfLastName.setBounds(0,90,200,30);
        lbFirstName.setBounds(220,70,200,20);
        tfFirstName.setBounds(220,90,200,30);

        lbPhone.setBounds(0,140,200,20);
        tfPhone.setBounds(0,160,200,30);
        lbGender.setBounds(220,140,200,20);
        bgGender.radioButtons[0].setBounds(220, 160, 100, 30);
        bgGender.radioButtons[1].setBounds(320, 160, 100, 30);

        lbAddress.setBounds(0,210,200,20);
        tfAddress.setBounds(0,230,200,30);
        lbSalary.setBounds(220,210,200,20);
        tfSalary.setBounds(220,230,200,30);

        btnSave.setBounds(50,290, 150, 40);
        btnEsc.setBounds(220,290, 150, 40);
        // endregion

        // region setText
        tfId.setText(String.valueOf(accountLogin.getId()));
        tfType.setText(accountLogin.getRole());
        tfFirstName.setText(accountLogin.getFirstName());
        tfLastName.setText(accountLogin.getLastName());
        tfPhone.setText(accountLogin.getPhone());
        tfAddress.setText(accountLogin.getAddress());
        if(accountLogin.getGender().equals("Nam")) bgGender.radioButtons[0].setSelected(true);
        else bgGender.radioButtons[1].setSelected(true);
        tfSalary.setText(accountLogin.getFormatSalary());
        // endregion

        // region Event
        btnEsc.addActionListener(_ -> dialog.dispose());
        btnSave.addActionListener(_ -> {
            StaffDTO staff = new StaffDTO(accountLogin.getId(), tfPhone.getText(), accountLogin.getPassword(), tfFirstName.getText(), tfLastName.getText(),(bgGender.radioButtons[0].isSelected()?"Nam":"Nữ"), tfAddress.getText(), tfType.getText(), accountLogin.getSalary(), accountLogin.getState());
            try {
                StaffBUS.getInstance().UpdateAccount(staff);
                JOptionPane.showMessageDialog(thisPanel, "Lưu thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentFrame.LoadThisAccount();
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // endregion

        // region Add
        add(lbId);
        add(lbGender);
        add(lbFirstName);
        add(lbLastName);
        add(lbPhone);
        add(lbType);
        add(tfId);
        for(JRadioButton rb: bgGender.radioButtons) add(rb);
        add(tfFirstName);
        add(tfLastName);
        add(tfPhone);
        add(tfType);
        add(lbAddress);
        add(tfAddress);
        add(lbSalary);
        add(tfSalary);
        add(btnSave);
        add(btnEsc);
        // endregion
    }
}
