package Components;

import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {
    public MyJLabel(int style, int size, Color color, String text, int Hor, int Ver) {
        super();
        setFont(new Font("Roboto", style, size));
        setForeground(color);
        setText(text);
        setOpaque(false);
        setHorizontalAlignment(Hor);
        setVerticalAlignment(Ver);
    }
}
