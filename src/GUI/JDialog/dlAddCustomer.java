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

public class dlAddCustomer extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thêm khách hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbLastName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Họ*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbFirstName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbGender = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giới tính*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhone = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số điện thoại*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAddress = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Địa chỉ*", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfLastName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfFirstName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfAddress = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPhone = new MyJTextFieldInput(Font.PLAIN, 14, true);

    MyButtonGroup bgGender = new MyButtonGroup(new String[]{"Nam", "Nữ"});

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlAddCustomer(fManage parentFrame, pnCustomer parentPanel) {
        super(parentFrame,true);
        setTitle("Thêm khách hàng");
        setSize(540,440);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region setBounds
        pnMain.setBounds(0,0,540,440);
        lbLastName.setBounds(50,80,200,20);
        tfLastName.setBounds(50,100,200,30);
        lbFirstName.setBounds(270,80,200,20);
        tfFirstName.setBounds(270,100,200,30);

        lbPhone.setBounds(50,150,200,20);
        tfPhone.setBounds(50,170,200,30);
        lbGender.setBounds(270,150,200,20);
        bgGender.radioButtons[0].setBounds(270, 170, 100, 30);
        bgGender.radioButtons[1].setBounds(370, 170, 100, 30);

        lbAddress.setBounds(50,220,420,20);
        tfAddress.setBounds(50,240,420,30);

        btnSave.setBounds(100,300,150,40);
        btnEsc.setBounds(270,300,150,40);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,540,60);
        // endregion

        // region Event
        btnEsc.addActionListener(_ -> dialog.dispose());
        btnSave.addActionListener(_ -> {
            CustomerDTO customer = new CustomerDTO(-1, tfPhone.getText(), tfLastName.getText(), tfFirstName.getText(), tfAddress.getText(), ((bgGender.radioButtons[0].isSelected())?"Nam":"Nữ"), "Mở");
            try{
                CustomerBUS.getInstance().add(customer);
                JOptionPane.showMessageDialog(dialog, "Thêm khách hàng thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadCustomer();
                dialog.dispose();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(dialog, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // endregion

        add(lbLastName);
        add(tfLastName);
        add(lbFirstName);
        add(tfFirstName);
        add(lbAddress);
        add(tfAddress);
        add(lbPhone);
        add(lbGender);
        add(tfPhone);
        for(JRadioButton rb: bgGender.radioButtons) add(rb);
        add(btnSave);
        add(btnEsc);
        add(lbHeader);
        add(pnMain);

        setVisible(true);
    }
}
