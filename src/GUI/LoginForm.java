package GUI;

import Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    ImageIcon iconEyeOpen = new ImageIcon(".\\src\\img\\eye_icon.png");
    Image imgEyeOpen = iconEyeOpen.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon img = new ImageIcon(imgEyeOpen);
//    Icon eyeClose = new ImageIcon(".\\src\\img\\hidden_eye_icon.png");

    String White = "#FFFFFF";
    String Black = "#000000";
    JPanel pnLeft = MyJPanel.GetJPanel("#66B2FF");
    JPanel pnRight = MyJPanel.GetJPanel(White);
    JLabel lbWelcome = MyJLabel.GetJLabel(Font.BOLD, 32, White, "<html>Chào mừng bạn đến với<br>Hệ thống quản lý siêu thị mini</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbLogin = MyJLabel.GetJLabel(Font.PLAIN, 25, Black, "<html>Đăng nhập tài khoản</html>", SwingConstants.CENTER, SwingConstants.TOP);
    JLabel lbUserName = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "<html>Tên đăng nhập hoặc Email</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbPassword = MyJLabel.GetJLabel(Font.PLAIN, 14, Black, "<html>Mật khẩu</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JTextField tfUserName = MyJTextField.GetJTextFieldLine(Font.PLAIN, 12);
    JPasswordField pfPassword = MyJPasswordField.GetJPasswordField(Font.PLAIN, 12);
    JButton btnLogin = MyJButton.GetJButton(Font.BOLD, 14, White, "#0099FF","#64B4FF",  "Đăng nhập", SwingConstants.CENTER, SwingConstants.CENTER);

    public LoginForm() {
        super("Phần mềm quản lý siêu thị mini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JButton btnEye = MyJButton.GetJButtonICon(img);

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


//        btnEye.setBackground(Color.BLACK);

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
        add(btnEye);
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