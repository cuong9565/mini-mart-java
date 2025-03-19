package Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MyJTextFieldInput extends JTextField {
    public MyJTextFieldInput(int style, int size, boolean editable) {
        super();
        setFont(new Font("Roboto", style, size));
        setForeground(Color.decode("#000000"));
        setEditable(editable);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#000000")),
                new EmptyBorder(5, 5, 5, 5)
        ));

        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#4A90E2"), 2),
                        new EmptyBorder(5, 5, 5, 5)
                ));
            }
            public void focusLost(FocusEvent e) {
                setCaretPosition(0);
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#000000")),
                        new EmptyBorder(5, 5, 5, 5)
                ));
            }
        });
    }
}
