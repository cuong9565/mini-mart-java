package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyJButton {
    public static JButton GetJButton(int style, int size, String fColor, String bColor, String text, int Hor, int Ver) {
        JButton button = new JButton();
        button.setFont(new Font("Arial", style, size));
        button.setForeground(Color.decode(fColor));
        button.setBackground(Color.decode(bColor));
        button.setText(text);
        button.setHorizontalTextPosition(Hor);
        button.setVerticalTextPosition(Ver);
        button.setFocusPainted(false);
        button.setBorder(null);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        button.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                button.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 2));
            }
            public void focusLost(FocusEvent e) {
                button.setBorder(null);
            }
        });
        return button;
    }
}
