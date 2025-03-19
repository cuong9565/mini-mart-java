package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyJButton {
    public static JButton GetJButton(int style, int size, String fColor, String bColor, String hoverColor, String text, int Hor, int Ver) {
        JButton button = new JButton();
        button.setFont(new Font("Roboto", style, size));
        button.setForeground(Color.decode(fColor));
        button.setBackground(Color.decode(bColor));
        button.setText(text);
        button.setHorizontalAlignment(Hor);
        button.setVerticalAlignment(Ver);
        button.setFocusPainted(false);
        button.setBorder(null);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                button.setBackground(Color.decode(hoverColor));
            }
            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                button.setBackground(Color.decode(bColor));
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

    public static JButton GetJButton(int style, int size, String fColor, String bColor, String text, int Hor, int Ver) {
        JButton button = new JButton();
        button.setFont(new Font("Roboto", style, size));
        button.setForeground(Color.decode(fColor));
        button.setBackground(Color.decode(bColor));
        button.setText(text);
        button.setHorizontalAlignment(Hor);
        button.setVerticalAlignment(Ver);
        button.setFocusPainted(false);
        button.setBorder(null);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        return button;
    }


    public static JButton GetJButton(int style, int size, Color fColor, Color bColor, Color hoverColor, String text, int Hor, int Ver) {
        JButton button = new JButton();
        button.setFont(new Font("Roboto", style, size));
        button.setForeground(fColor);
        button.setBackground(bColor);
        button.setText(text);
        button.setHorizontalAlignment(Hor);
        button.setVerticalAlignment(Ver);
        button.setFocusPainted(false);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                button.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                button.setBackground(bColor);
            }
        });

        if(Hor == SwingConstants.LEFT){
            button.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        }
        else {
            button.setBorder(null);
            button.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    button.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 2));
                }
                public void focusLost(FocusEvent e) {
                    button.setBorder(null);
                }
            });
        }

        return button;
    }

    public static JButton GetJButton(int style, int size, Color fColor, Color bColor, String text, int Hor, int Ver) {
        JButton button = new JButton();
        button.setFont(new Font("Roboto", style, size));
        button.setForeground(fColor);
        button.setBackground(bColor);
        button.setText(text);
        button.setHorizontalAlignment(Hor);
        button.setVerticalAlignment(Ver);
        button.setFocusPainted(false);
        if(Hor == SwingConstants.LEFT){
            button.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        }
        else button.setBorder(null);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        return button;
    }
}
