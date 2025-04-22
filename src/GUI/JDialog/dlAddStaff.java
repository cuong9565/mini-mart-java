package GUI.JDialog;

import BUS.StaffBUS;
import Components.*;
import DTO.StaffDTO;
import DTO.TypeProductDTO;
import GUI.JFrame.fManage;
import GUI.JPanel.pnStaff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dlAddStaff extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thêm nhân viên", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbLastName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Họ*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbFirstName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbGender = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giới tính*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhone = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số điện thoại*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAddress = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Địa chỉ*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbRole = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Vai trò*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSalary = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Lương*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPassword = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mật khẩu*", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfLastName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfFirstName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfAddress = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPhone = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPassword = new MyJTextFieldInput(Font.PLAIN, 14, true);

    MyButtonGroup bgGender = new MyButtonGroup(new String[]{"Nam", "Nữ"});

    JComboBox<String> cbRole = new MyJComboBox<>(new String[]{"Quản trị viên", "Quản lý", "Thu ngân", "Nhân viên bán hàng", "Nhân viên kho"}, 12);
    JSpinner snSalary = new MyJSpinner(100, 100, 1000000000, 100);


    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlAddStaff(fManage parentFrame, pnStaff parentPanel) {
        super(parentFrame,true);
        setTitle("Thêm nhân viên");
        setSize(540,510);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region setBounds
        pnMain.setBounds(0,0,540,510);
        lbLastName.setBounds(50,80,200,20);
        tfLastName.setBounds(50,100,200,30);
        lbFirstName.setBounds(270,80,200,20);
        tfFirstName.setBounds(270,100,200,30);

        lbPhone.setBounds(50,150,200,20);
        tfPhone.setBounds(50,170,200,30);
        lbGender.setBounds(270,150,200,20);
        bgGender.radioButtons[0].setBounds(270, 170, 100, 30);
        bgGender.radioButtons[1].setBounds(370, 170, 100, 30);

        lbAddress.setBounds(50,220,200,20);
        tfAddress.setBounds(50,240,200,30);
        lbRole.setBounds(270,220,200,20);
        cbRole.setBounds(270, 240, 200, 30);

        lbSalary.setBounds(50,290,200,20);
        snSalary.setBounds(50, 310, 200, 30);
        lbPassword.setBounds(270,290,200,20);
        tfPassword.setBounds(270,310,200,30);

        btnSave.setBounds(100,370,150,40);
        btnEsc.setBounds(270,370,150,40);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,540,60);
        // endregion
        // region Event
        btnEsc.addActionListener(_ -> dialog.dispose());
        btnSave.addActionListener(_ -> {
            StaffDTO staff = new StaffDTO(-1, tfPhone.getText(), tfPassword.getText(), tfFirstName.getText(), tfLastName.getText(), (bgGender.radioButtons[0].isSelected()?"Nam":"Nữ"), tfAddress.getText(), (String) cbRole.getSelectedItem(), Double.parseDouble(snSalary.getValue().toString()), "");
            try {
                StaffBUS.getInstance().add(staff);
                JOptionPane.showMessageDialog(dialog, "Thêm nhân viên thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadStaff();
                dialog.dispose();

            }catch (Exception e){
                JOptionPane.showMessageDialog(dialog, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);

            }
        });
        // endregion
        // region Add
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
        for(JRadioButton rb: bgGender.radioButtons) add(rb);
        add(btnSave);
        add(btnEsc);
        add(lbHeader);
        add(pnMain);
        // endregion

        setVisible(true);
    }
}
