package pnForm;

import Components.MyJButton;

import javax.swing.*;
import java.awt.*;

public class pnBill extends JPanel {
    JButton button = MyJButton.GetJButton(Font.PLAIN, 16, "#000000", "#FFFFFF", "#FFFFFF", "Hóa đơn", SwingConstants.CENTER, SwingConstants.CENTER);
    public pnBill() {
        setLayout(null);
        setBackground(Color.decode("#FF00FF"));
        button.setBounds(10,10,100,100);
        add(button);
    }
}
