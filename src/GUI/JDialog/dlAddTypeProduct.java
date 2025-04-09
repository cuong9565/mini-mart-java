package GUI.JDialog;

import BUS.TypeProductBUS;
import Components.*;
import DTO.TypeProductDTO;
import GUI.JFrame.fManage;
import GUI.JPanel.pnTypeProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dlAddTypeProduct extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thêm loại sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên loại sản phẩm*", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfName = new MyJTextFieldInput(Font.PLAIN, 14, true);

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlAddTypeProduct(fManage parentFrame, pnTypeProduct parentPanel) {
        super(parentFrame,true);
        setTitle("Thêm loại sản phẩm");
        setSize(540,300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region setBounds
        pnMain.setBounds(0,0,540,440);
        lbName.setBounds(50,80,420,20);
        tfName.setBounds(50,100,420,30);
        btnSave.setBounds(100,160,150,40);
        btnEsc.setBounds(270,160,150,40);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,540,60);
        // endregion

        // region Event
        btnEsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TypeProductDTO product = new TypeProductDTO(-1, tfName.getText());
                boolean check = TypeProductBUS.getInstance().add(product);

                if(check){
                    JOptionPane.showMessageDialog(dialog, "Thêm thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    parentPanel.loadTypeProduct();
                    dialog.dispose();
                }
                else JOptionPane.showMessageDialog(dialog, TypeProductBUS.getInstance().getError(), "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        // endregion11

        add(lbName);
        add(tfName);
        add(btnSave);
        add(btnEsc);
        add(lbHeader);
        add(pnMain);

        setVisible(true);
    }
}
