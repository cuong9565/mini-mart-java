package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyJPanel {
    public static JPanel GetJPanel(String color) {
        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());
        panel.setBackground(Color.decode(color));
        panel.setBorder(null);
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                panel.requestFocus();
            }
        });
        return panel;
    }
}
