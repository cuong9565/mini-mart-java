package GUI.JDialog;

import BUS.StaffBUS;
import Components.*;
import DTO.StaffDTO;
import GUI.JFrame.fManage;
import GUI.JPanel.pnStaff;

import javax.swing.*;
import java.awt.*;

public class dlEditStaff extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Sửa thông tin nhân viên", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã nhân viên*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbLastName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Họ*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbFirstName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbGender = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giới tính*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhone = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số điện thoại*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAddress = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Địa chỉ*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbRole = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Vai trò*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSalary = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Lương*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPassword = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mật khẩu*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbState = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Trạng thái*", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfId = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfLastName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfFirstName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfAddress = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPhone = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPassword = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfState = new MyJTextFieldInput(Font.PLAIN, 14, false);

    JButton btnState = new MyJButton(Font.BOLD, 12, MyColor.White, MyColor.LightRed, "", SwingConstants.CENTER, SwingConstants.CENTER);

    MyButtonGroup bgGender = new MyButtonGroup(new String[]{"Nam", "Nữ"});

    JComboBox<String> cbRole = new MyJComboBox<>(new String[]{"Quản trị viên", "Quản lý", "Thu ngân", "Nhân viên bán hàng", "Nhân viên kho"}, 12);
    JSpinner snSalary = new MyJSpinner(100, 100, 1000000000, 100);

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlEditStaff(fManage parentFrame, pnStaff parentPanel, StaffDTO staff) {
        super(parentFrame,true);
        setTitle("Sửa thông tin nhân viên");
        setSize(540,650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region setBounds
        pnMain.setBounds(0,0,540,650);

        lbId.setBounds(50,80,420,20);
        tfId.setBounds(50,100,420,30);

        lbLastName.setBounds(50,150,200,20);
        tfLastName.setBounds(50,170,200,30);
        lbFirstName.setBounds(270,150,200,20);
        tfFirstName.setBounds(270,170,200,30);

        lbPhone.setBounds(50,220,200,20);
        tfPhone.setBounds(50,240,200,30);
        lbGender.setBounds(270,220,200,20);
        bgGender.radioButtons[0].setBounds(270, 240, 100, 30);
        bgGender.radioButtons[1].setBounds(370, 240, 100, 30);

        lbAddress.setBounds(50,290,200,20);
        tfAddress.setBounds(50,310,200,30);
        lbRole.setBounds(270,290,200,20);
        cbRole.setBounds(270, 310, 200, 30);

        lbSalary.setBounds(50,360,200,20);
        snSalary.setBounds(50, 380, 200, 30);
        lbPassword.setBounds(270,360,200,20);
        tfPassword.setBounds(270,380,200,30);

        lbState.setBounds(50, 430, 420, 20);
        tfState.setBounds(50, 450, 250, 30);
        btnState.setBounds(320, 450, 150, 30);

        btnSave.setBounds(100,510,150,40);
        btnEsc.setBounds(270,510,150,40);

        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,540,60);
        // endregion1
        // region SET TEXT
        tfId.setText(String.valueOf(staff.getId()));
        tfLastName.setText(staff.getLastName());
        tfFirstName.setText(staff.getFirstName());
        tfPhone.setText(staff.getPhone());
        tfAddress.setText(staff.getAddress());
        tfPassword.setText(staff.getPassword());
        if(staff.getGender().equals("Nam")) bgGender.radioButtons[0].setSelected(true);
        else bgGender.radioButtons[1].setSelected(true);
        tfState.setText(staff.getState());
        cbRole.setSelectedItem(staff.getRole());
        snSalary.setValue(staff.getSalary());
        if(staff.getState().compareTo("Đang hoạt động")==0){
            btnState.setText("Khóa tài khoản");
            btnState.setBackground(MyColor.LightRed);
        }
        else {
            btnState.setText("Mở tài khoản");
            btnState.setBackground(MyColor.LightBlue);
        }
        // endregion
        // region Event
        btnState.addActionListener(_ -> {
            if(tfState.getText().compareTo("Đang hoạt động")==0){
                btnState.setText("Mở tài khoản");
                btnState.setBackground(MyColor.LightBlue);
                tfState.setText("Đã bị khóa");
            }
            else {
                btnState.setText("Khóa tài khoản");
                btnState.setBackground(MyColor.LightRed);
                tfState.setText("Đang hoạt động");
            }
        });
        btnEsc.addActionListener(_ -> dialog.dispose());
        btnSave.addActionListener(_ -> {
            StaffDTO staffNew = new StaffDTO(staff.getId(), tfPhone.getText(), tfPassword.getText(), tfFirstName.getText(), tfLastName.getText(), (bgGender.radioButtons[0].isSelected()?"Nam":"Nữ"), tfAddress.getText(), (String) cbRole.getSelectedItem(), Double.parseDouble(snSalary.getValue().toString()), tfState.getText());
            try {
                StaffBUS.getInstance().UpdateAccount(staffNew);
                JOptionPane.showMessageDialog(dialog, "Sửa thông tin nhân viên thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadStaff();
                dialog.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(dialog, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // endregion11
        // region ADD
        add(lbId);
        add(tfId);
        add(lbLastName);
        add(tfLastName);
        add(lbFirstName);
        add(tfFirstName);
        add(lbAddress);
        add(tfAddress);
        add(lbRole);
        add(cbRole);
        add(lbSalary);
        add(snSalary);
        add(lbPassword);
        add(tfPassword);
        add(lbPhone);
        add(lbGender);
        add(tfPhone);
        add(lbState);
        add(tfState);
        add(btnState);
        for(JRadioButton rb: bgGender.radioButtons) add(rb);
        add(btnSave);
        add(btnEsc);
        add(lbHeader);
        add(pnMain);
        // endregion

        setVisible(true);
    }
}
