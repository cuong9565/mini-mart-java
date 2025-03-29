package GUI;

import BUS.SupplierBUS;
import Components.*;
import DTO.SupplierDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dlEditSupplier extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thay đổi thông tin nhà cung cấp", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên nhà cung cấp*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPhone = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số điện thoại*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbAddress = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Địa chỉ*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEmail = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Email*", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfPhone = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfAddress = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfEmail = new MyJTextFieldInput(Font.PLAIN, 14, true);

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlEditSupplier(Manage parentFrame, pnSupplier parentPanel, SupplierDTO supplier) {
        super(parentFrame,true);
        setTitle("Thay đổi thông tin nhà cung cấp");
        setSize(540,440);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region setBounds
        pnMain.setBounds(0,0,540,440);
        lbName.setBounds(50,80,200,20);
        tfName.setBounds(50,100,200,30);
        lbPhone.setBounds(270,80,200,20);
        tfPhone.setBounds(270,100,200,30);
        lbAddress.setBounds(50,150,420,20);
        tfAddress.setBounds(50,170,420,30);
        lbEmail.setBounds(50,220,420,20);
        tfEmail.setBounds(50,240,420,30);
        btnSave.setBounds(100,300,150,40);
        btnEsc.setBounds(270,300,150,40);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,540,60);
        // endregion
        // region setText
        tfName.setText(supplier.getName());
        tfPhone.setText(supplier.getPhone());
        tfAddress.setText(supplier.getAddress());
        tfEmail.setText(supplier.getEmail());
        // endregion
        // region Event
        btnEsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SupplierDTO supplierNew = new SupplierDTO(supplier.getId(), tfName.getText(), tfPhone.getText(), tfAddress.getText(), tfEmail.getText());
                boolean check = SupplierBUS.getInstance().editSupplier(supplierNew);

                if(check){
                    JOptionPane.showMessageDialog(dialog, "Thay đổi thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    parentPanel.loadSupplier();
                    dialog.dispose();
                }
                else JOptionPane.showMessageDialog(dialog, SupplierBUS.getInstance().getError(), "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        // endregion11

        add(lbName);
        add(tfName);
        add(lbPhone);
        add(tfPhone);
        add(lbAddress);
        add(tfAddress);
        add(lbEmail);
        add(tfEmail);
        add(btnSave);
        add(btnEsc);

        add(lbHeader);
        add(pnMain);

        setVisible(true);
    }
}
