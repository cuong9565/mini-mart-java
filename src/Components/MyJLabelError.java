package Components;

import javax.swing.*;
import java.awt.*;

public class MyJLabelError extends JLabel {
    public MyJLabelError(int fontSize, String text) {
        super();
        setFont(new Font("Roboto", Font.PLAIN, fontSize));
        setForeground(MyColor.BrightRed);
        setText(text);
        setHorizontalAlignment(SwingConstants.LEFT);
        setVerticalAlignment(SwingConstants.CENTER);
        setOpaque(false);
        setVisible(false);
    }
}
