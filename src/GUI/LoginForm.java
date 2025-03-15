package GUI;

import Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    JPanel pnLeft = MyJPanel.GetJPanel("#66B2FF");
    JPanel pnRight = MyJPanel.GetJPanel("#FFFFFF");
    JLabel lbWelcome = MyJLabel.GetJLabel(Font.BOLD, 32, "#FFFFFF", "<html>Chào mừng bạn đến với<br>Hệ thống quản lý siêu thị mini</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbLogin = MyJLabel.GetJLabel(Font.PLAIN, 25, "#000000", "<html>Đăng nhập tài khoản</html>", SwingConstants.CENTER, SwingConstants.TOP);
    JLabel lbUserName = MyJLabel.GetJLabel(Font.PLAIN, 14, "#000000", "<html>Tên đăng nhập hoặc Email</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbPassword = MyJLabel.GetJLabel(Font.PLAIN, 14, "#000000", "<html>Mật khẩu</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JTextField tfUserName = MyJTextField.GetJTextFieldLine(Font.PLAIN, 12);
    JPasswordField pfPassword = MyJPasswordField.GetJPasswordField(Font.PLAIN, 12);
    JButton btnLogin = MyJButton.GetJButton(Font.BOLD, 14, "#FFFFFF", "#0099FF","#64B4FF",  "Đăng nhập", SwingConstants.CENTER, SwingConstants.CENTER);

    public LoginForm() {
        super("Phần mềm quản lý siêu thị mini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // region setBounds
        lbWelcome.setBounds(50, 100, 450, 100);
        lbLogin.setBounds(580, 50, 280, 30);
        lbUserName.setBounds(580, 120, 280, 20);
        lbPassword.setBounds(580, 201, 280, 20);
        tfUserName.setBounds(580, 140, 280, 20);
        pfPassword.setBounds(580, 220, 280, 20);
        btnLogin.setBounds(580, 300, 280, 30);
        pnLeft.setBounds(0,0,550, 500);
        pnRight.setBounds(550,0,350,500);
        // endregion

        // region Shortcut Key
        tfUserName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER, KeyEvent.VK_DOWN: pfPassword.requestFocus(); break;
                }
            }
        });

        pfPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN: btnLogin.requestFocus(); break;
                    case KeyEvent.VK_UP: tfUserName.requestFocus(); break;
                    case KeyEvent.VK_ENTER: Login(); break;
                }
            }
        });

        btnLogin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER: Login(); break;
                    case KeyEvent.VK_UP: pfPassword.requestFocus(); break;
                }
            }
        });


        // endregion

        // region Listener
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });
        // endregion

        // region Add
        add(lbWelcome);
        add(lbLogin);
        add(lbUserName);
        add(lbPassword);
        add(tfUserName);
        add(btnLogin);
        add(pfPassword);
        add(pnLeft);
        add(pnRight);
        // endregion

        setVisible(true);
    }

    public void Login() {
        boolean check = true;
        String userName = tfUserName.getText();
        String password = String.valueOf(pfPassword.getPassword());
        if(userName.isEmpty()){
//            JOptionPane.showMessageDialog(tfUserName, "Trống");

            check = false;
        }

        if(password.isEmpty()){
//            JOptionPane.showMessageDialog(pfPassword, "Trống");

            check = false;
        }
        if(!check){
            JOptionPane.showMessageDialog(pnRight, "Tên đăng nhập và mật khẩu không được để trống!!!");
        }
        if(check){
            new Manage(this);
            setVisible(false);
        }
    }
}
/*
* Còn thiếu kiểm tra điều kiện Login
* Thiếu phần báo lỗi (Một JLabel nằm bên dưới)
* */