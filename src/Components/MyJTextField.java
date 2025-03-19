package Components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class MyJTextField {
    public static JTextField GetJTextFieldLine(int style, int size) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Roboto", style, size));
        textField.setBorder(new MatteBorder(0,0,2,0, Color.BLACK));

        FocusListener focusListener = new FocusListener() {
            public void focusGained(FocusEvent e) {
                ((JTextField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#0099ff")));
            }
            public void focusLost(FocusEvent e) {
                ((JTextField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#000000")));
            }
        };

        textField.addFocusListener(focusListener);

        return textField;
    }
    public static JTextField GetJTextFieldInput(int style, int size, boolean editable) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Roboto", style, size));
        textField.setForeground(Color.decode("#000000"));
        textField.setEditable(editable);

        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#000000")),
                new EmptyBorder(5, 5, 5, 5)
        ));

        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#4A90E2"), 2),
                        new EmptyBorder(5, 5, 5, 5)
                ));
            }
            public void focusLost(FocusEvent e) {
                textField.setCaretPosition(0);
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#000000")),
                        new EmptyBorder(5, 5, 5, 5)
                ));
            }
        });
        return textField;
    }
}
