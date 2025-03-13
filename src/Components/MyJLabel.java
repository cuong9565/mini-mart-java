package Components;

import javax.swing.*;
import java.awt.*;

public class MyJLabel {
    public static JLabel GetJLabel(int style, int size, String color, String text, int Hor, int Ver){
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", style, size));
        label.setForeground(Color.decode(color));
        label.setText(text);
        label.setOpaque(false);
        label.setHorizontalAlignment(Hor);
        label.setVerticalAlignment(Ver);
        return label;
    }
}
