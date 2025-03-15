package GUI;
import Components.MyJButton;
import Components.MyJLabel;
import Components.MyJPanel;
import pnForm.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manage extends JFrame {
    JPanel pnLeft = MyJPanel.GetJPanel("#FFFFFF");
    JPanel pnRight = MyJPanel.GetJPanel("#00FF00");
    JPanel pnNav = MyJPanel.GetJPanel("#FFFFFF");
    JPanel pnMenu = MyJPanel.GetJPanel("#00FFFF");
    JLabel lbWelcome = MyJLabel.GetJLabel(Font.PLAIN, 16, "#000000", "<html>Xin chào<br>USER<br>ROLE<br><hr></html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnStatistic = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Thống kê", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnImport = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Nhập hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnSell = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Bán hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnProduct = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnTypeProduct = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Loại sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnCustomer = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Khách hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnBill = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Hóa đơn", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDiscount = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Giảm giá", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnStaff = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Nhân viên", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnSupplier = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Nhà cung cấp", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnSettingAccount = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Tài khoản", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnLogout = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Đăng xuất", SwingConstants.CENTER, SwingConstants.CENTER);

    public Manage(LoginForm loginForm) {
        setTitle("Phần mềm quản lý siêu thị mini");
        setSize(1200, 800);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setBackground(Color.decode("#FFFFFF"));

        // region setBounds + MaximumSize Button
        lbWelcome.setBounds(10, 0, 180, 150);
        btnStatistic.setMaximumSize(new Dimension(180, 30));
        btnImport.setMaximumSize(new Dimension(180, 30));
        btnSell.setMaximumSize(new Dimension(180, 30));
        btnProduct.setMaximumSize(new Dimension(180, 30));
        btnTypeProduct.setMaximumSize(new Dimension(180, 30));
        btnCustomer.setMaximumSize(new Dimension(180, 30));
        btnBill.setMaximumSize(new Dimension(180, 30));
        btnDiscount.setMaximumSize(new Dimension(180, 30));
        btnStaff.setMaximumSize(new Dimension(180, 30));
        btnSupplier.setMaximumSize(new Dimension(180, 30));
        btnSettingAccount.setBounds(10, 650, 180, 30);
        btnLogout.setBounds(10, 680, 180, 30);
        pnNav.setBounds(10,150,180, 500);
        pnMenu.setBounds(210,10,970, 750);
        pnLeft.setBounds(0,0,200,800);
        pnRight.setBounds(200,0,1000, 800);
        // endregion

        JButton[] lsBtn = new JButton[]{btnStatistic, btnImport, btnSell, btnProduct, btnTypeProduct, btnCustomer, btnBill, btnDiscount, btnStaff, btnSupplier, btnSettingAccount};
        JPanel[] lsPn = new JPanel[]{new pnStatistic(), new pnImport(), new pnSell(), new pnProduct(), new pnTypeProduct(), new pnCustomer(), new pnBill(), new pnDiscount(), new pnStaff(), new pnSupplier(), new pnSettingAccount()};
        MouseListener[] listener = new MouseListener[lsBtn.length];
        for (int i = 0; i < lsBtn.length; i++) {
            final int I = i;
            listener[i] = new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    lsBtn[I].setBackground(Color.decode("00FFFF"));
                }
                public void mouseExited(MouseEvent e) {
                    lsBtn[I].setBackground(Color.decode("FFFFFF"));
                }
            };
        }


        pnNav.setLayout(new BoxLayout(pnNav, BoxLayout.Y_AXIS));
        pnMenu.setLayout(new CardLayout());
        pnLeft.setLayout(null);
        pnRight.setLayout(null);


        // region Listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                loginForm.setVisible(true);
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                loginForm.setVisible(true);
            }
        });
        // endregion

        // region Add
            // add pnMenu
            for(JPanel jPanel : lsPn) pnMenu.add(jPanel);

            // add pnNav
            for(int i=0; i<lsBtn.length; i++){
                final int I = i;
                lsBtn[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for(int j=0; j<lsPn.length; j++){
                            if(I==j){
                                lsPn[j].setVisible(true);
                            }
                            else{
                                lsPn[j].setVisible(false);
                            }
                        }
                    }
                });
                pnNav.add(lsBtn[i]);
            }


            // add Frame
            add(lbWelcome);
            add(btnSettingAccount);
            add(btnLogout);
            add(pnNav);
            add(pnMenu);
            add(pnLeft);
            add(pnRight);
        // endregion

        setVisible(true);
    }
}
/*
* Chỉnh sửa MouseListener lại
* */