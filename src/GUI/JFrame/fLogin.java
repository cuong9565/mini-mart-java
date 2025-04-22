package GUI.JFrame;

import BUS.StaffBUS;
import Components.*;
import DTO.StaffDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class fLogin extends JFrame {
    JPanel pnLeft = new MyJPanel(MyColor.LightBlue);
    JPanel pnRight = new MyJPanel(MyColor.White);
    JLabel lbWelcome = new MyJLabel(Font.BOLD, 32, MyColor.White, "<html>Chào mừng bạn đến với<br>Hệ thống quản lý siêu thị mini</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbLogin = new MyJLabel(Font.PLAIN, 25, MyColor.Black, "<html>Đăng nhập tài khoản</html>", SwingConstants.CENTER, SwingConstants.TOP);
    JLabel lbPhone = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html>Số điện thoại</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbPassword = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html>Mật khẩu</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JTextField tfPhone = new MyJTextFieldLine(Font.PLAIN, 12);
    JPasswordField pfPassword = new MyJPasswordFieldLine(Font.PLAIN, 12);
    JButton btnLogin = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.DarkBlue,MyColor.LightBlue,  "Đăng nhập", SwingConstants.CENTER, SwingConstants.CENTER);

    public fLogin() {
        super("Phần mềm quản lý siêu thị mini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // region setBounds
        lbWelcome.setBounds(50, 100, 450, 100);
        lbLogin.setBounds(580, 50, 280, 30);
        lbPhone.setBounds(580, 120, 280, 20);
        lbPassword.setBounds(580, 201, 280, 20);
        tfPhone.setBounds(580, 140, 280, 20);
        pfPassword.setBounds(580, 220, 280, 20);
        btnLogin.setBounds(580, 300, 280, 30);
        pnLeft.setBounds(0,0,550, 500);
        pnRight.setBounds(550,0,350,500);
        // endregion

        // region code xong nhớ xóa
        tfPhone.setText("0397969307");
        pfPassword.setText("admin");
        // endregion

        // region Shortcut Key
        tfPhone.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    pfPassword.requestFocus();
            }
        });

        pfPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN: btnLogin.requestFocus(); break;
                    case KeyEvent.VK_UP: tfPhone.requestFocus(); break;
                }
            }
        });

        btnLogin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) pfPassword.requestFocus();
            }
        });

        getRootPane().setDefaultButton(btnLogin);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC");
        getRootPane().getActionMap().put("ESC", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        // endregion

        btnLogin.addActionListener(_-> Login());

        // region Add
        add(lbWelcome);
        add(lbLogin);
        add(lbPhone);
        add(lbPassword);
        add(tfPhone);
        add(btnLogin);
        add(pfPassword);
        add(pnLeft);
        add(pnRight);
        // endregion

        setVisible(true);
        tfPhone.requestFocusInWindow();

    }

    public void Login() {
        try {
            String phone = tfPhone.getText();
            String password = String.valueOf(pfPassword.getPassword());
            StaffDTO accountLogin = StaffBUS.getInstance().Login(phone, password);
            if(accountLogin.getId()!=0){
                new fManage(this, accountLogin);
                setVisible(false);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}