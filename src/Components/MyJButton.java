package Components;

import javax.swing.*;
import java.awt.*;
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
        button.setBorderPainted(false);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {}
        });
        return button;
    }
}
