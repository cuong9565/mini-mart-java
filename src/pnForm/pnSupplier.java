package pnForm;

import Components.MyColor;
import Components.MyJButton;
import Components.MyJPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class pnSupplier extends JPanel {
    JPanel pnHeader = MyJPanel.GetJPanel(MyColor.White);
    JPanel pnFunc = MyJPanel.GetJPanelTitle(MyColor.White, "Chức năng");
    JButton btnAdd = MyJButton.GetJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = MyJButton.GetJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = MyJButton.GetJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
//    JButton btnSearch = MyJButton.GetJButton()

    public pnSupplier(JFrame frame) {
        setLayout(null);
        setBackground(MyColor.LightGray);

        pnHeader.setBounds(0,0,970, 100);
        pnFunc.setBounds(0,0,260,100);
        btnAdd.setBounds(15,19,70,70);
        btnEdit.setBounds(95,19,70,70);
        btnDelete.setBounds(175,19,70,70);

        add(btnAdd);
        add(btnEdit);
        add(btnDelete);
        add(pnFunc);
        add(pnHeader);
    }
}
