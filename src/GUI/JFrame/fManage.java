package GUI.JFrame;
import BUS.StaffBUS;
import Components.*;
import DTO.StaffDTO;
import GUI.JDialog.dlSettingAccount;
import GUI.JPanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class fManage extends JFrame {
    JPanel pnLeft = new MyJPanel(MyColor.White);
    JPanel pnRight = new MyJPanel(MyColor.White);
    JPanel pnNav = new MyJPanel(MyColor.White);
    JPanel pnMenu = new MyJPanel(MyColor.White);
    JLabel lbWelcome = new MyJLabel(Font.PLAIN, 16, MyColor.Black, "", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnStatistic = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Thống kê", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnImport = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Nhập hàng", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnProduct = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Sản phẩm", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnSell = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Bán hàng", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnTypeProduct = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Loại sản phẩm", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnCustomer = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Khách hàng", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnBill = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Hóa đơn", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnDiscount = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Giảm giá", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnStaff = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White,  "Nhân viên", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnSupplier = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, "Nhà cung cấp", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnSettingAccount = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.LightGreen, "Tài khoản", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnLogout = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.LightRed, "Đăng xuất", SwingConstants.LEFT, SwingConstants.CENTER);

    int currCursor = 0;
    fManage currFrame = this;
    StaffDTO thisAccount;


    public fManage(fLogin loginForm, StaffDTO accountLogin) {
        setTitle("Phần mềm quản lý siêu thị mini");
        setSize(1400, 800);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setBackground(Color.decode("#FFFFFF"));

        thisAccount = accountLogin;
        LoadThisAccount();
        JButton[] lsBtn = new JButton[]{btnStatistic, btnImport, btnSell, btnProduct, btnTypeProduct, btnCustomer, btnBill, btnDiscount, btnStaff, btnSupplier};
        JPanel[] lsPn = new JPanel[]{
                new pnStatistic(),
                new pnImport(),
                new pnSell(currFrame, thisAccount),
                new pnProduct(currFrame),
                new pnTypeProduct(currFrame),
                new pnCustomer(currFrame),
                new pnBill(currFrame),
                new pnDiscount(currFrame),
                new pnStaff(currFrame),
                new pnSupplier(currFrame)};

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
        pnMenu.setBounds(210,10,1170, 750);
        pnLeft.setBounds(0,0,200,800);
        pnRight.setBounds(200,0,1200, 800);
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

        btnSettingAccount.addActionListener(_ -> new dlSettingAccount(currFrame, thisAccount));

        btnLogout.addActionListener(_ -> {
            dispose();
            loginForm.setVisible(true);
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
            lsBtn[i].addActionListener(_ -> {
                lsBtn[currCursor].setBackground(MyColor.White);
                lsPn[currCursor].setVisible(false);
                currCursor = I;
                lsBtn[currCursor].setBackground(MyColor.HoverBlue);
                lsPn[currCursor].setVisible(true);
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

    public void LoadThisAccount(){
        int id = thisAccount.getId();
        thisAccount = StaffBUS.getInstance().getStaffById(id);
        lbWelcome.setText(String.format("<html>Xin chào <b>%s</b><br><i>Vai trò: %s</i><br><hr></html>", thisAccount.getFirstName(), thisAccount.getRole()));
    }
}

