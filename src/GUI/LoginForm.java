package GUI;

import Components.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    JPanel pnLeft = MyJPanel.GetJPanel(MyColor.LightBlue);
    JPanel pnRight = MyJPanel.GetJPanel(MyColor.White);
    JLabel lbWelcome = MyJLabel.GetJLabel(Font.BOLD, 32, MyColor.White, "<html>Chào mừng bạn đến với<br>Hệ thống quản lý siêu thị mini</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbLogin = MyJLabel.GetJLabel(Font.PLAIN, 25, MyColor.Black, "<html>Đăng nhập tài khoản</html>", SwingConstants.CENTER, SwingConstants.TOP);
    JLabel lbUserName = MyJLabel.GetJLabel(Font.PLAIN, 14, MyColor.Black, "<html>Tên đăng nhập hoặc Email</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbPassword = MyJLabel.GetJLabel(Font.PLAIN, 14, MyColor.Black, "<html>Mật khẩu</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JTextField tfUserName = MyJTextField.GetJTextFieldLine(Font.PLAIN, 12);
    JPasswordField pfPassword = MyJPasswordField.GetJPasswordField(Font.PLAIN, 12);
    JButton btnLogin = MyJButton.GetJButton(Font.BOLD, 14, MyColor.White, MyColor.DarkBlue,MyColor.LightBlue,  "Đăng nhập", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbEmptyUserName = MyJLabel.GetJLabelError(10, "Vui lòng không để trống trường này!");
    JLabel lbEmptyPassword = MyJLabel.GetJLabelError(10, "Vui lòng không để trống trường này!");

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
        lbEmptyUserName.setBounds(580, 160, 280, 20);
        pfPassword.setBounds(580, 220, 280, 20);
        lbEmptyPassword.setBounds(580, 240, 280, 20);
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
        tfUserName.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                lbEmptyUserName.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {

            }
            public void changedUpdate(DocumentEvent e) {

            }
        });
        pfPassword.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                lbEmptyPassword.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {

            }
            public void changedUpdate(DocumentEvent e) {

            }
        });
        // endregion

        // region Add
        add(lbEmptyUserName);
        add(lbEmptyPassword);
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
        tfUserName.requestFocusInWindow();
    }

    public void Login() {
        boolean check = true;
        String userName = tfUserName.getText();
        String password = String.valueOf(pfPassword.getPassword());
        if(userName.isEmpty()) {
            lbEmptyUserName.setVisible(true);
            check = false;
        } else lbEmptyUserName.setVisible(false);

        if(password.isEmpty()){
            lbEmptyPassword.setVisible(true);
            check = false;
        } else lbEmptyPassword.setVisible(false);


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