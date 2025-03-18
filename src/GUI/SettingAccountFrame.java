package GUI;

import Components.MyJButton;
import Components.MyJPanel;
import pnForm.pnChangeInfo;
import pnForm.pnChangePassword;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class SettingAccountFrame extends JDialog {
    String LightGray = "#D3D3D3";
    String DarkBlue = "#0D47A1";
    JPanel pnMain = MyJPanel.GetJPanel("#FFFFFF");
    JPanel pnNav = MyJPanel.GetJPanel("#FFFFFF");
    JPanel pnContent = MyJPanel.GetJPanel("#FFFFFF");
    JButton btnChangeInfo = MyJButton.GetJButton(Font.PLAIN, 12, "#000000", LightGray, "Thay đổi thông tin", SwingConstants.CENTER,SwingConstants.CENTER);
    JButton btnChangePassword = MyJButton.GetJButton(Font.PLAIN, 12, "#000000", LightGray, "Thay đổi mật khẩu", SwingConstants.CENTER,SwingConstants.CENTER);
    JButton lsBtn[] = new JButton[]{btnChangeInfo, btnChangePassword};
    JPanel lsPn[] = new JPanel[]{new pnChangeInfo(), new pnChangePassword()};


    int currCursor = 0;

    public SettingAccountFrame(Manage parentFrame) {
        super(parentFrame,true);
        setTitle("Thông tin tài khoản");
        setSize(540,550);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        pnMain.setBounds(0,0,540,550);
        pnNav.setBounds(0,0,440, 30);
        pnContent.setBounds(50,50,440,520);
        btnChangeInfo.setMaximumSize(new Dimension(150, 30));
        btnChangeInfo.setMinimumSize(new Dimension(150, 30));
        btnChangeInfo.setPreferredSize(new Dimension(150, 30));
        btnChangePassword.setPreferredSize(new Dimension(150, 30));
        btnChangePassword.setMaximumSize(new Dimension(150, 30));
        btnChangePassword.setMinimumSize(new Dimension(150, 30));
        pnNav.setLayout(new BoxLayout(pnNav,BoxLayout.X_AXIS));
        pnContent.setLayout(new CardLayout());

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
                    lsBtn[currCursor].setBackground(Color.decode(LightGray));
                    lsBtn[currCursor].setBorder(BorderFactory.createEmptyBorder());
                    lsPn[currCursor].setVisible(false);
                    currCursor = I;
                    lsBtn[I].setBackground(Color.decode("#FFFFFF"));
                    lsBtn[I].setBorder(new MatteBorder(0,0,2,0,Color.decode(DarkBlue)));
                    lsPn[I].setVisible(true);
                }
            });
            pnNav.add(lsBtn[i]);
            pnContent.add(lsPn[i]);
        }

        lsBtn[currCursor].doClick();

        add(pnNav);
        add(pnContent);
        add(pnMain);

        setVisible(true);
    }
}
