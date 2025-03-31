package Components;

import javax.swing.*;
import java.awt.*;

public class MyButtonGroup extends ButtonGroup {
    public JRadioButton[] radioButtons = null;
    public MyButtonGroup() {}
    public MyButtonGroup(String[] items) {
        super();
        radioButtons = new JRadioButton[items.length];
        for (int i = 0; i < items.length; i++) {
            radioButtons[i] = new JRadioButton(items[i]);
            radioButtons[i].setBorderPainted(false);
            radioButtons[i].setFocusPainted(false);
            radioButtons[i].setFont(new Font("Roboto", Font.PLAIN, 14));
            radioButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);
    }
}
