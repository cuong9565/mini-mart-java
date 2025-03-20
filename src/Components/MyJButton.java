package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyJButton extends JButton {
    public MyJButton(int style, int size, Color fColor, Color bColor, Color hoverColor, String text, int Hor, int Ver) {
        super();
        setFocusPainted(false);
        setFont(new Font("Roboto", style, size));
        setForeground(fColor);
        setBackground(bColor);
        setText(text);
        setHorizontalAlignment(Hor);
        setVerticalAlignment(Ver);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                setBackground(bColor);
            }
        });

        if(Hor == SwingConstants.LEFT){
            setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        }
        else {
            setBorder(null);
            addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 2));
                }
                public void focusLost(FocusEvent e) {
                    setBorder(null);
                }
            });
        }
    }


    public MyJButton(int style, int size, Color fColor, Color bColor, String text, int Hor, int Ver) {
        super();
        setFocusPainted(false);
        setFont(new Font("Roboto", style, size));
        setForeground(fColor);
        setBackground(bColor);
        setText(text);
        setHorizontalAlignment(Hor);
        setVerticalAlignment(Ver);

        if(Hor == SwingConstants.LEFT){
            setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        }
        else setBorder(null);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
    }

}
