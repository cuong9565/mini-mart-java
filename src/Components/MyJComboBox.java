package Components;

import javax.swing.*;
import java.awt.*;

public class MyJComboBox<E> extends JComboBox<E> {
    public MyJComboBox(E[] items, int fontSize) {
        super();
        setFont(new Font("Roboto", Font.PLAIN, fontSize));
        setBackground(MyColor.White);
        setForeground(MyColor.Black);
        setMaximumRowCount(5);
        setOpaque(false);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        for(E item : items)
            addItem(item);
        setBorder(null);
    }
}
