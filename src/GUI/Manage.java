package GUI;
import Components.*;
import pnForm.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;


public class Manage extends JFrame {
    JPanel pnLeft = MyJPanel.GetJPanel("#FFFFFF");
    JPanel pnRight = MyJPanel.GetJPanel("#FFFFFF");
    JPanel pnNav = MyJPanel.GetJPanel("#FFFFFF");
    JPanel pnMenu = MyJPanel.GetJPanel("#00FFFF");
    JLabel lbWelcome = MyJLabel.GetJLabel(Font.PLAIN, 16, "#000000", "<html>Xin chào<br>USER<br>ROLE<br><hr></html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnStatistic = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "Thống kê", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnImport = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "Nhập hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnProduct = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "Sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnSell = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "Bán hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnTypeProduct = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "Loại sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnCustomer = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "Khách hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnBill = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "Hóa đơn", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDiscount = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "Giảm giá", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnStaff = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF",  "Nhân viên", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnSupplier = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "Nhà cung cấp", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnSettingAccount = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF", "#00FFFF", "Tài khoản", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnLogout = MyJButton.GetJButton(Font.PLAIN, 14, "#000000", "#FFFFFF","#00FFFF", "Đăng xuất", SwingConstants.CENTER, SwingConstants.CENTER);
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
                        lsBtn[I].setBackground(Color.decode("#00FFFF"));
                    }
                    public void mouseExited(MouseEvent e) {
                        lsBtn[I].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        if(I==currCursor) lsBtn[I].setBackground(Color.decode("#00FFFF"));
                        else lsBtn[I].setBackground(Color.decode("#FFFFFF"));
                    }
                });
                lsBtn[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        currCursor = I;
                        for(int j=0; j<lsPn.length; j++){
                            if(I==j){
                                lsBtn[j].setBackground(Color.decode("#00FFFF"));
                                lsPn[j].setVisible(true);
                            }
                            else{
                                lsBtn[j].setBackground(Color.decode("#FFFFFF"));
                                lsPn[j].setVisible(false);
                            }
                        }
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