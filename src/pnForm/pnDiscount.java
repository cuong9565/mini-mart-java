package pnForm;

import Components.MyColor;
import Components.MyJButton;

import javax.swing.*;
import java.awt.*;

public class pnDiscount extends JPanel {
    JButton button = new MyJButton(Font.PLAIN, 16, MyColor.Black, MyColor.White, MyColor.White, "Giảm giá", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnDiscount() {
        setLayout(null);
        setBackground(Color.decode("#FF00FF"));
        button.setBounds(10,10,100,100);
        add(button);
    }
}
