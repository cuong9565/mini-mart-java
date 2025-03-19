package Components;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyJPanel {
    public static JPanel GetJPanel(Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());
        panel.setBackground(color);
        panel.setBorder(null);
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                panel.requestFocus();
            }
        });
        return panel;
    }
    public static JPanel GetJPanelTitle(Color color, String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());
        panel.setBackground(color);
        panel.setBorder(null);
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                panel.requestFocus();
            }
        });

        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MyColor.UnderLineBlue),
                title,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_JUSTIFICATION,
                new Font("Roboto", Font.BOLD, 12),
                MyColor.UnderLineBlue
        ));
        return panel;
    }
}
