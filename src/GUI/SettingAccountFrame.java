package GUI;

import Components.MyColor;
import Components.MyJButton;
import Components.MyJLabel;
import Components.MyJPanel;
import pnForm.pnChangeInfo;
import pnForm.pnChangePassword;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class SettingAccountFrame extends JDialog {
    JPanel pnMain = MyJPanel.GetJPanel(MyColor.White);
    JPanel pnNav = MyJPanel.GetJPanel(MyColor.LightGray);
    JPanel pnContent = MyJPanel.GetJPanel(MyColor.White);
    JButton btnChangeInfo = MyJButton.GetJButton(Font.PLAIN, 12, MyColor.Black, MyColor.LightGray, "Thay đổi thông tin", SwingConstants.CENTER,SwingConstants.CENTER);
    JButton btnChangePassword = MyJButton.GetJButton(Font.PLAIN, 12, MyColor.Black, MyColor.LightGray, "Thay đổi mật khẩu", SwingConstants.CENTER,SwingConstants.CENTER);
    JButton lsBtn[] = new JButton[]{btnChangeInfo, btnChangePassword};
    JPanel lsPn[] = new JPanel[]{new pnChangeInfo(this), new pnChangePassword(this)};
    JLabel lbHeader = MyJLabel.GetJLabel(Font.BOLD, 24, MyColor.White, "", SwingConstants.CENTER, SwingConstants.CENTER);

    int currCursor = 0;

    public SettingAccountFrame(Manage parentFrame) {
        super(parentFrame,true);
        setTitle("Thông tin tài khoản");
        setSize(540,550);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        pnMain.setBounds(0,0,540,550);
        pnNav.setBounds(0,0,540, 30);
        pnContent.setBounds(50,110,440,440);
        btnChangeInfo.setMaximumSize(new Dimension(150, 30));
        btnChangeInfo.setMinimumSize(new Dimension(150, 30));
        btnChangeInfo.setPreferredSize(new Dimension(150, 30));
        btnChangePassword.setPreferredSize(new Dimension(150, 30));
        btnChangePassword.setMaximumSize(new Dimension(150, 30));
        btnChangePassword.setMinimumSize(new Dimension(150, 30));
        pnNav.setLayout(new BoxLayout(pnNav,BoxLayout.X_AXIS));
        pnContent.setLayout(new CardLayout());

        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,30,540,60);

        // region Event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        // endregion

        for(int i=0; i<lsBtn.length; i++) {
            final int I = i;
            lsBtn[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lsBtn[currCursor].setBackground(MyColor.LightGray);
                    lsBtn[currCursor].setBorder(BorderFactory.createEmptyBorder());
                    lsPn[currCursor].setVisible(false);
                    currCursor = I;
                    lsBtn[I].setBackground(MyColor.White);
                    lsBtn[I].setBorder(new MatteBorder(0,0,2,0,MyColor.UnderLineBlue));
                    lsPn[I].setVisible(true);
                    switch (I){
                        case 0: lbHeader.setText("Thông tin tài khoản"); break;
                        case 1: lbHeader.setText("Đổi mật khẩu"); break;
                    }
                }
            });
            pnNav.add(lsBtn[i]);
            pnContent.add(lsPn[i]);
        }

        lsBtn[currCursor].doClick();

        add(lbHeader);
        add(pnNav);
        add(pnContent);
        add(pnMain);

        setVisible(true);
    }
}
