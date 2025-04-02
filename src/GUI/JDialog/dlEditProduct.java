package GUI.JDialog;

import BUS.ProductBUS;
import Components.*;
import DTO.ProductDTO;
import DTO.ProductDTO;
import GUI.JFrame.fManage;
import GUI.JPanel.pnProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dlEditProduct extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Sửa thông tin loại sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã số*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên loại sản phẩm*", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfId = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JTextField tfName = new MyJTextFieldInput(Font.PLAIN, 14, true);

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlEditProduct(fManage parentFrame, pnProduct parentPanel, ProductDTO product) {
        super(parentFrame,true);
        setTitle("Sửa thông tin loại sản phẩm");
        setSize(540,370);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region SET BOUNDS
        pnMain.setBounds(0,0,540,370);
        lbId.setBounds(50,80,420,20);
        tfId.setBounds(50,100,420,30);
        lbName.setBounds(50,150,420,20);
        tfName.setBounds(50,170,420,30);
        btnSave.setBounds(100,230,150,40);
        btnEsc.setBounds(270,230,150,40);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,540,60);
        // endregion
        // region SET TEXT
        tfId.setText(product.getId() + "");
        tfName.setText(product.getName());
        // endregion
        // region EVENT
        btnEsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                ProductDTO productNew = new ProductDTO(product.getId(), tfName.getText());
//                boolean check = ProductBUS.getInstance().edit(productNew);
//
//                if(check){
//                    JOptionPane.showMessageDialog(dialog, "Sửa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    parentPanel.loadProduct();
//                    dialog.dispose();
//                }
//                else JOptionPane.showMessageDialog(dialog, ProductBUS.getInstance().getError(), "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        // endregion11
        // region ADD
        add(lbId);
        add(tfId);
        add(lbName);
        add(tfName);
        add(btnSave);
        add(btnEsc);
        add(lbHeader);
        add(pnMain);
        // endregion

        setVisible(true);
    }
}
