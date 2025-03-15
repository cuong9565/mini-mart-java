package pnForm;

import Components.MyJButton;

import javax.swing.*;
import java.awt.*;

public class pnSupplier extends JPanel {
    JButton button = MyJButton.GetJButton(Font.PLAIN, 16, "#000000", "#FFFFFF", "#FFFFFF", "Nhà cung cấp", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnSupplier() {
        setLayout(null);
        setBackground(Color.decode("#FF00FF"));
        button.setBounds(10,10,100,100);
        add(button);
    }
}
