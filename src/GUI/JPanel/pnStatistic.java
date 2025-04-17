package GUI.JPanel;

import Components.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import Components.MyColor;
import Components.MyJButton;
import GUI.JFrame.fManage;

public class pnStatistic extends JPanel {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JPanel pnNav = new MyJPanel(MyColor.LightGray);
    JPanel pnContent = new MyJPanel(MyColor.White);
    JButton btnOffer = new MyJButton(Font.PLAIN, 12, MyColor.Black, MyColor.LightGray, "Thống kê theo hóa đơn", SwingConstants.CENTER,SwingConstants.CENTER);
    JButton btnOfferProduct = new MyJButton(Font.PLAIN, 12, MyColor.Black, MyColor.LightGray, "Thống kê theo sản phẩm", SwingConstants.CENTER,SwingConstants.CENTER);
    JButton btnOfferBill = new MyJButton(Font.PLAIN, 12, MyColor.Black, MyColor.LightGray, "Thống kê theo khách hàng", SwingConstants.CENTER,SwingConstants.CENTER);
    JButton[] lsBtn = new JButton[]{btnOffer, btnOfferProduct, btnOfferBill};
    JPanel[] lsPn = new JPanel[]{new pnStatisticBill(), new pnStatisticProduct(), new pnStatisticCustomer()};

    int currCursor = 0;

    public pnStatistic(fManage fmanage) {
        setLayout(null);
        setBackground(MyColor.White);

        pnMain.setBounds(0,0,1170,800);
        pnNav.setBounds(0,0,1170, 30);
        pnContent.setBounds(0,30,1170,800);

        btnOffer.setMaximumSize(new Dimension(150, 30));
        btnOffer.setMinimumSize(new Dimension(150, 30));
        btnOffer.setPreferredSize(new Dimension(150, 30));
        btnOfferProduct.setPreferredSize(new Dimension(150, 30));
        btnOfferProduct.setMaximumSize(new Dimension(150, 30));
        btnOfferProduct.setMinimumSize(new Dimension(150, 30));
        btnOfferBill.setPreferredSize(new Dimension(150, 30));
        btnOfferBill.setMaximumSize(new Dimension(150, 30));
        btnOfferBill.setMinimumSize(new Dimension(150, 30));

        pnNav.setLayout(new BoxLayout(pnNav,BoxLayout.X_AXIS));
        pnContent.setLayout(new CardLayout());

        for(int i=0; i<lsBtn.length; i++) {
            final int I = i;
            lsBtn[i].addActionListener(_ -> {
                lsBtn[currCursor].setBackground(MyColor.LightGray);
                lsBtn[currCursor].setBorder(BorderFactory.createEmptyBorder());
                lsPn[currCursor].setVisible(false);
                currCursor = I;
                lsBtn[I].setBackground(MyColor.White);
                lsBtn[I].setBorder(new MatteBorder(0,0,2,0,MyColor.UnderLineBlue));
                lsPn[I].setVisible(true);
            });
            pnNav.add(lsBtn[i]);
            pnContent.add(lsPn[i]);
        }

        lsBtn[currCursor].doClick();

        add(pnNav);
        add(pnContent);
        add(pnMain);
    }
}