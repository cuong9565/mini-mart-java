package Components;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyJTextFieldLine extends JTextField {
    public MyJTextFieldLine(int style, int size) {
        super();
        setFont(new Font("Roboto", style, size));
        setBorder(new MatteBorder(0,0,2,0, Color.BLACK));

        FocusListener focusListener = new FocusListener() {
            public void focusGained(FocusEvent e) {
                ((JTextField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#0099ff")));
            }
            public void focusLost(FocusEvent e) {
                ((JTextField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#000000")));
            }
        };

        addFocusListener(focusListener);
    }
}
