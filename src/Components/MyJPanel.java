package Components;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyJPanel extends JPanel {

    public MyJPanel(Color color) {
        super();
        setLayout(new CardLayout());
        setBackground(color);
        setBorder(null);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                requestFocus();
            }
        });
    }

    public MyJPanel(Color color, String title) {
        super();
        setLayout(new CardLayout());
        setBackground(color);
        setBorder(null);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                requestFocus();
            }
        });

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MyColor.UnderLineBlue),
                title,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_JUSTIFICATION,
                new Font("Roboto", Font.BOLD, 12),
                MyColor.UnderLineBlue
        ));
    }
}
