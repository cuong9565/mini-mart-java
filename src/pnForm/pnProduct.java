package pnForm;

import Components.MyJButton;

import javax.swing.*;
import java.awt.*;

public class pnProduct extends JPanel {
    JButton button = MyJButton.GetJButton(Font.PLAIN, 16, "#000000", "#FFFFFF", "#FFFFFF", "Sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnProduct() {
        setLayout(null);
        setBackground(Color.decode("#FF00FF"));
        button.setBounds(10,10,100,100);
        add(button);
    }
}
