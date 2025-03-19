package Components;

import javax.swing.*;
import java.awt.*;

public class MyJLabel {
    public static JLabel GetJLabel(int style, int size, String color, String text, int Hor, int Ver){
        JLabel label = new JLabel();
        label.setFont(new Font("Roboto", style, size));
        label.setForeground(Color.decode(color));
        label.setText(text);
        label.setOpaque(false);
        label.setHorizontalAlignment(Hor);
        label.setVerticalAlignment(Ver);
        return label;
    }

    public static JLabel GetJLabel(int style, int size, Color color, String text, int Hor, int Ver){
        JLabel label = new JLabel();
        label.setFont(new Font("Roboto", style, size));
        label.setForeground(color);
        label.setText(text);
        label.setOpaque(false);
        label.setHorizontalAlignment(Hor);
        label.setVerticalAlignment(Ver);
        return label;
    }

    public static JLabel GetJLabelError(int size, String text){
        JLabel label = new JLabel();
        label.setFont(new Font("Roboto", Font.PLAIN, size));
        label.setForeground(MyColor.BrightRed);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setOpaque(false);
        label.setVisible(false);
        return label;
    }
}
