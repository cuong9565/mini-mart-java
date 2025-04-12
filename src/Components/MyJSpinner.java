package Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;

public class MyJSpinner extends JSpinner {
    public MyJSpinner(int value, int min, int max, int step) {
        super(new SpinnerNumberModel(value, min, max, step));
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setBorder(null);
        setBackground(MyColor.White);

        JComponent editor = getEditor();
        JFormattedTextField tf = ((DefaultEditor) editor).getTextField();
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

    public MyJSpinner(MyDate currDate){
        super(new SpinnerDateModel(currDate.getUtilDate(), MyDate.getMinDate(), MyDate.getMaxDate(), Calendar.DAY_OF_MONTH));
        setEditor(new JSpinner.DateEditor(this, "dd/MM/yyyy"));
    }

    public MyDate getMyDate(){
        java.util.Date utilDate = (java.util.Date) getValue();
        return new MyDate(utilDate);
    }
}
