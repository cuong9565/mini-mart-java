package GUI.JDialog;

import BUS.CustomerBUS;
import BUS.SupplierBUS;
import Components.*;
import DTO.CustomerDTO;
import DTO.SupplierDTO;
import GUI.JFrame.fManage;
import GUI.JPanel.pnCustomer;
import GUI.JPanel.pnSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dlEditCustomer extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Sửa thông tin khách hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã số", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbLastName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Họ*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbFirstName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbGender = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giới tính*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhone = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số điện thoại*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAddress = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Địa chỉ*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbState = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Trạng thái*", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfId = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfLastName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfFirstName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfAddress = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPhone = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfState = new MyJTextFieldInput(Font.PLAIN, 14, false);

    MyButtonGroup bgGender = new MyButtonGroup(new String[]{"Nam", "Nữ"});

    JButton btnState = new MyJButton(Font.BOLD, 12, MyColor.White, MyColor.LightRed, "", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlEditCustomer(fManage parentFrame, pnCustomer parentPanel, CustomerDTO customer) {
        super(parentFrame,true);
        setTitle("Sửa thông tin khách hàng");
        setSize(540,580);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region SET BOUNDS
        pnMain.setBounds(0,0,540,580);

        lbId.setBounds(50,80,420,20);
        tfId.setBounds(50,100,420,30);

        lbLastName.setBounds(50, 150, 200, 20);
        tfLastName.setBounds(50, 170, 200, 30);
        lbFirstName.setBounds(270, 150, 200, 20);
        tfFirstName.setBounds(270, 170, 200, 30);

        lbPhone.setBounds(50, 220, 200, 20);
        tfPhone.setBounds(50, 240, 200, 30);
        lbGender.setBounds(270, 220, 200, 20);
        bgGender.radioButtons[0].setBounds(270, 240, 100, 30);
        bgGender.radioButtons[1].setBounds(370, 240, 100, 30);

        lbAddress.setBounds(50, 290, 420, 20);
        tfAddress.setBounds(50, 310, 420, 30);

        lbState.setBounds(50, 360, 420, 20);
        tfState.setBounds(50, 380, 250, 30);
        btnState.setBounds(320, 380, 150, 30);

        btnSave.setBounds(100, 440, 150, 40);
        btnEsc.setBounds(270, 440, 150, 40);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,540,60);
        // endregion
        // region SET TEXT
        tfId.setText(customer.getId() + "");
        tfLastName.setText(customer.getLastName());
        tfFirstName.setText(customer.getFirstName());
        tfPhone.setText(customer.getPhone());
        if(customer.getGender().compareTo("Nam")==0) bgGender.radioButtons[0].setSelected(true);
        else bgGender.radioButtons[1].setSelected(true);
        tfAddress.setText(customer.getAddress());
        tfState.setText(customer.getState());
        if(customer.getState().compareTo("Đang hoạt động")==0){
            btnState.setText("Khóa tài khoản");
            btnState.setBackground(MyColor.LightRed);
        }
        else {
            btnState.setText("Mở tài khoản");
            btnState.setBackground(MyColor.LightBlue);
        }
        // endregion
        // region EVENT
        btnEsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerDTO customerNew = new CustomerDTO(customer.getId(), tfPhone.getText(), tfLastName.getText(), tfFirstName.getText(), tfAddress.getText(), ((bgGender.radioButtons[0].isSelected())?"Nam":"Nữ"), tfState.getText());
                if(CustomerBUS.getInstance().update(customerNew)){
                    JOptionPane.showMessageDialog(dialog, "Sửa thông tin khách hàng thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    parentPanel.loadCustomer();
                    parentPanel.textChange();
                    dialog.dispose();
                }
                else JOptionPane.showMessageDialog(dialog, CustomerBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnState.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        add(lbPhone);
        add(tfPhone);
        for(JRadioButton rb: bgGender.radioButtons) add(rb);
        add(lbGender);
        add(lbAddress);
        add(tfAddress);
        add(lbState);
        add(tfState);
        add(btnState);

        add(btnSave);
        add(btnEsc);

        add(lbHeader);
        add(pnMain);
        // endregion

        setVisible(true);
    }
}
