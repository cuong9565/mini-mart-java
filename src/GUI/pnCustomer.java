package GUI;

import Components.MyColor;
import Components.MyJButton;

import javax.swing.*;
import java.awt.*;

public class pnCustomer extends JPanel {
    JButton button = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.White, "Khách hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnCustomer() {
        setLayout(null);
        setBackground(Color.decode("#FF00FF"));
        button.setBounds(10,10,100,100);
        add(button);
    }
}
