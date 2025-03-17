package Components;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class MyJTextField {
    public static JTextField GetJTextFieldLine(int style, int size) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", style, size));
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
    public static JTextField GetJTextFieldInput(int style, int size,String text, int hor) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", style, size));
        textField.setForeground(Color.decode("#000000"));
        textField.setHorizontalAlignment(hor);
        textField.setText(text);

        return textField;
    }
    public static JTextField GetJTextFieldInput(int style, int size, boolean editable) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", style, size));
        textField.setForeground(Color.decode("#000000"));
        textField.setEditable(editable);
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 1));

        return textField;
    }
}
