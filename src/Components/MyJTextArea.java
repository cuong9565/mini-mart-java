package Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MyJTextArea extends JTextArea {
    public JScrollPane sp = new JScrollPane(this);
    public MyJTextArea() {
        super();
        setFont(new Font("Roboto", Font.PLAIN, 12));
        setForeground(MyColor.Black);
        setBackground(MyColor.White);
        setLineWrap(true);
        setWrapStyleWord(true);

        sp.setBackground(MyColor.White);
        sp.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#000000")),
                new EmptyBorder(5, 5, 5, 5)
        ));

        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                sp.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#4A90E2"), 2),
                        new EmptyBorder(5, 5, 5, 5)
                ));
            }
            public void focusLost(FocusEvent e) {
                setCaretPosition(0);
                sp.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#000000")),
                        new EmptyBorder(5, 5, 5, 5)
                ));
            }
        });

    }
}
