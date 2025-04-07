package Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MyJSpinner extends JSpinner {
    public MyJSpinner(int value, int min, int max, int step) {
        super(new SpinnerNumberModel(value, min, max, step));
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setBorder(null);
        setBackground(MyColor.White);

        JComponent editor = getEditor();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
        tf.setBackground(MyColor.White);
        tf.setForeground(MyColor.Black);

        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#000000")),
                new EmptyBorder(0, 5, 0, 5)
        ));

        tf.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#4A90E2"), 2),
                        new EmptyBorder(0, 5, 0, 5)
                ));
            }
            public void focusLost(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#000000")),
                        new EmptyBorder(0, 5, 0, 5)
                ));
            }
        });
    }
}
