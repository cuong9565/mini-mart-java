package GUI.JDialog;

import BUS.*;
import Components.*;
import DTO.*;
import GUI.JFrame.fManage;
import GUI.JPanel.pnProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class dlDetailProduct extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thông tin chi tiết", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbDetail = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Thông tin chi tiết", SwingConstants.LEFT, SwingConstants.CENTER);
    MyJTextArea taDetail = new MyJTextArea();

    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Thoát", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;
    int posCbDiscount = 0;
    public dlDetailProduct(fManage parentFrame, pnProduct parentPanel, ProductDTO productDTO) {
        super(parentFrame,true);
        setTitle("Thông tin chi tiết");
        setSize(300,440);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        taDetail.setText(productDTO.getDetail().getText());
        taDetail.setEditable(false);
        taDetail.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,300,60);
        pnMain.setBounds(0,0,300,440);
        lbDetail.setBounds(50,80,200,20);
        taDetail.sp.setBounds(50,100,200,170);
        btnEsc.setBounds(75,300,150,40);

        btnEsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        add(lbDetail);
        add(taDetail.sp);
        add(btnEsc);
        add(lbHeader);
        add(pnMain);

        setVisible(true);
    }
}
