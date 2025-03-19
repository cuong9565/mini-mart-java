package Components;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyJPasswordFieldLine extends JPasswordField {
    public MyJPasswordFieldLine(int style, int size) {
        super();
        setFont(new Font("Roboto", style, size));
        setEchoChar('*');
        setBorder(new MatteBorder(0,0,2,0, Color.BLACK));

        addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                ((JPasswordField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#0099ff")));
            }
            public void focusLost(FocusEvent e) {
                ((JPasswordField)e.getSource()).setBorder(new MatteBorder(0,0,2,0, Color.decode("#000000")));
            }
        });
    }
}
