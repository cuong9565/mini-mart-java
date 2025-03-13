package GUI;

import Components.MyJButton;
import Components.MyJLabel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm extends JFrame {
    JPanel pnLeft = new JPanel();
    JPanel pnRight = new JPanel();
    JLabel lbWelcome = MyJLabel.GetJLabel(Font.BOLD, 32, "#FFFFFF", "<html>Chào mừng bạn đến với<br>Hệ thống quản lý siêu thị mini</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbLogin = MyJLabel.GetJLabel(Font.PLAIN, 25, "#000000", "<html>Đăng nhập tài khoản</html>", SwingConstants.CENTER, SwingConstants.TOP);
    JLabel lbUserName = MyJLabel.GetJLabel(Font.PLAIN, 14, "#000000", "<html>Tên đăng nhập hoặc Email</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JLabel lbPassword = MyJLabel.GetJLabel(Font.PLAIN, 14, "#000000", "<html>Mật khẩu</html>", SwingConstants.LEFT, SwingConstants.TOP);
    JTextField tfUserName = new JTextField();
    JPasswordField pfPassword = new JPasswordField();
    JButton btnLogin = MyJButton.GetJButton(Font.BOLD, 14, "#FFFFFF", "#0099FF", "Đăng nhập", SwingConstants.CENTER, SwingConstants.CENTER);

    public LoginForm() {
        super("Phần mềm quản lý siêu thị mini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        lbWelcome.setBounds(50, 100, 450, 100);
        lbLogin.setBounds(580, 50, 280, 30);
        lbUserName.setBounds(580, 120, 280, 20);
        lbPassword.setBounds(580, 200, 280, 20);
        tfUserName.setBounds(580, 140, 280, 20);
        pfPassword.setBounds(580, 220, 280, 20);
        btnLogin.setBounds(580, 300, 280, 30);
        tfUserName.setFont(new Font("Arial", Font.PLAIN, 12));
        tfUserName.setBorder(new MatteBorder(0,0,2,0, Color.BLACK));
        pfPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        pfPassword.setBorder(new MatteBorder(0,0,2,0, Color.BLACK));

        // Set Panel Left
        pnLeft.setLayout(new CardLayout());
        pnLeft.setBackground(Color.decode("#66B2FF"));
        pnLeft.setBounds(0,0,550, 500);

        // Set Panel Right
        pnRight.setLayout(null);
        pnRight.setBackground(Color.decode("#FFFFFF"));
        pnRight.setBounds(550,0,350,500);

        FocusListener focusListener = new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(e.getSource() instanceof JTextField)
                    ((JTextField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#0099ff")));
                else if(e.getSource() instanceof JPasswordField)
                    ((JPasswordField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#0099ff")));
            }
            public void focusLost(FocusEvent e) {
                if(e.getSource() instanceof JTextField)
                    ((JTextField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#000000")));
                else if(e.getSource() instanceof JPasswordField)
                    ((JPasswordField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#000000")));
            }
        };
        pnLeft.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pnLeft.requestFocus();
            }
        });
        pnRight.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pnRight.requestFocus();
            }
        });

        // add Focus
        tfUserName.addFocusListener(focusListener);
        pfPassword.addFocusListener(focusListener);

        // add into JFrame
        add(lbWelcome);
        add(lbLogin);
        add(lbUserName);
        add(lbPassword);
        add(tfUserName);
        add(btnLogin);
        add(pfPassword);
        add(pnLeft);
        add(pnRight);

        setVisible(true);
    }
}
