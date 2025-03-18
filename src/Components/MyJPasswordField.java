package Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyJPasswordField {
    public static JPasswordField GetJPasswordField(int style, int size) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Roboto", style, size));
//        passwordField.setEchoChar((char) 0);
        passwordField.setEchoChar('*');
        passwordField.setBorder(new MatteBorder(0,0,2,0, Color.BLACK));

        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                ((JPasswordField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#0099ff")));
            }
            public void focusLost(FocusEvent e) {
                ((JPasswordField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#000000")));
            }
        });

        return passwordField;
    }
    public static JPasswordField GẹtJPasswordFieldInput(int style, int size) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Roboto", style, size));
        passwordField.setForeground(Color.decode("#000000"));
        passwordField.setEchoChar('*');

        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#000000")),
                new EmptyBorder(5, 5, 5, 5)
        ));

        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                passwordField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#4A90E2"), 2),
                        new EmptyBorder(5, 5, 5, 5)
                ));
            }
            public void focusLost(FocusEvent e) {
                passwordField.setCaretPosition(0);
                passwordField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#000000")),
                        new EmptyBorder(5, 5, 5, 5)
                ));
            }
        });
        return passwordField;
    }
}
