package GUI;
import Components.*;
import pnForm.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;


public class Manage extends JFrame {
    JPanel pnLeft = MyJPanel.GetJPanel(MyColor.White);
    JPanel pnRight = MyJPanel.GetJPanel(MyColor.White);
    JPanel pnNav = MyJPanel.GetJPanel(MyColor.White);
    JPanel pnMenu = MyJPanel.GetJPanel(MyColor.White);
    JLabel lbWelcome = MyJLabel.GetJLabel(Font.PLAIN, 16, MyColor.Black, "<html>Xin chào<br>USER<br>ROLE<br><hr></html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnStatistic = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Thống kê", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnImport = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Nhập hàng", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnProduct = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Sản phẩm", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnSell = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Bán hàng", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnTypeProduct = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Loại sản phẩm", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnCustomer = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Khách hàng", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnBill = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Hóa đơn", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnDiscount = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Giảm giá", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnStaff = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White,  "Nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnSupplier = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Nhà cung cấp", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnSettingAccount = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.HoverBlue, "Tài khoản", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnLogout = MyJButton.GetJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.HoverBlue, "Đăng xuất", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton[] lsBtn = new JButton[]{btnStatistic, btnImport, btnSell, btnProduct, btnTypeProduct, btnCustomer, btnBill, btnDiscount, btnStaff, btnSupplier};
    JPanel[] lsPn = new JPanel[]{new pnStatistic(), new pnImport(), new pnSell(), new pnProduct(), new pnTypeProduct(), new pnCustomer(), new pnBill(), new pnDiscount(), new pnStaff(), new pnSupplier()};

    int currCursor = 0;
    Manage currFrame = this;

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

        btnSettingAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SettingAccountFrame(currFrame);
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
                lsBtn[i].addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        lsBtn[I].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        lsBtn[I].setBackground(MyColor.HoverBlue);
                    }
                    public void mouseExited(MouseEvent e) {
                        lsBtn[I].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        if(I==currCursor) lsBtn[I].setBackground(MyColor.HoverBlue);
                        else lsBtn[I].setBackground(MyColor.White);
                    }
                });
                lsBtn[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        lsBtn[currCursor].setBackground(MyColor.White);
                        lsPn[currCursor].setVisible(false);
                        currCursor = I;
                        lsBtn[currCursor].setBackground(MyColor.HoverBlue);
                        lsPn[currCursor].setVisible(true);
                    }
                });
                pnNav.add(lsBtn[i]);
            }
            lsBtn[0].doClick();


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

