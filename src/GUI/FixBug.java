package GUI;

import javax.swing.*;
import java.awt.*;

public class FixBug extends JFrame {
    public FixBug() {
        setTitle("Chào mừng");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Tắt layout mặc định để dùng setBounds()

        JLabel lbWelcome = new JLabel("Chào mừng bạn đến với");
        lbWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lbWelcome.setForeground(Color.WHITE);
        lbWelcome.setOpaque(true);
        lbWelcome.setBackground(Color.BLACK);
//        lbWelcome.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbWelcome.setBounds(50, 50, 300, 50); // Đặt vị trí và kích thước hợp lý

        add(lbWelcome);
        setVisible(true);
    }
    public static void main(String[] args) {
        new FixBug();
    }
}
