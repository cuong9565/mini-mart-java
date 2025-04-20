package GUI.JPanel;

import BUS.CustomerStatisticBUS;
import Components.*;
import DTO.CustomerStatisticDTO;
import DTO.ProductStatisticDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class pnStatisticCustomer extends JPanel {
    JPanel pnMain = new MyJPanel(MyColor.White);

    JPanel pnFill = new MyJPanel(MyColor.White, "Lọc theo ngày");
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Từ: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Đến: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnFill = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Lọc", SwingConstants.CENTER, SwingConstants.CENTER);
    MyJSpinner spStartDate = new MyJSpinner(MyDate.getMinInMonth());
    MyJSpinner spEndDate = new MyJSpinner(MyDate.getMaxInMonth());

    JLabel lbHeaderCustomer = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thống kê số tiền mua hàng của từng khách hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbTotalCustomer = new MyJLabel(Font.BOLD, 14, MyColor.Black, "Tổng doanh thu: 0đ", SwingConstants.LEFT, SwingConstants.CENTER);
    MyJTable tbCustomer = new MyJTable(new String[]{"Mã khách hàng", "Họ", "Tên", "Số điện thoại", "Q1", "Q2", "Q3", "Q4", "Tổng cộng"}, 12, new int[]{}, new int[]{1}, new int[]{});
    public pnStatisticCustomer() {
        setLayout(null);

        // region setBounds
        pnMain.setBounds(0, 0, 1200, 800);

        pnFill.setBounds(0, 0, 1170, 60);
        lbStartDate.setBounds(10, 20, 30, 30);
        spStartDate.setBounds(40, 20, 100, 30);
        lbEndDate.setBounds(150, 20, 40, 30);
        spEndDate.setBounds(190, 20, 100, 30);
        btnFill.setBounds(310, 20, 100, 30);

        pnFill.setBounds(0, 0, 450, 90);
        lbStartDate.setBounds(10, 30, 30, 30);
        spStartDate.setBounds(37, 30, 100, 30);
        lbEndDate.setBounds(160, 30, 40, 30);
        spEndDate.setBounds(192, 30, 100, 30);
        btnFill.setBounds(330, 30, 100, 30);

        lbHeaderCustomer.setOpaque(true);
        lbHeaderCustomer.setBackground(MyColor.DarkBlue);
        lbHeaderCustomer.setBounds(0, 90, 1170, 50);
        tbCustomer.scrPn.setBounds(0, 140, 1170, 430);
        lbTotalCustomer.setBounds(0, 600, 1170, 30);

        // endregion

        // region event
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {load();}
        });
        btnFill.addActionListener(_ -> {
            load();
        });
        // endregion

        // region add
        add(btnFill);
        add(lbTotalCustomer);
        add(tbCustomer.scrPn);
        add(lbStartDate);
        add(lbEndDate);
        add(spStartDate);
        add(spEndDate);
        add(pnFill);
        add(lbHeaderCustomer);
        add(pnMain);
        // endregion
    }

    public void load(){
        double res = 0;
        tbCustomer.dftbModel.setRowCount(0);
        for (CustomerStatisticDTO customer: CustomerStatisticBUS.getInstance().loadByDate(spStartDate.getMyDate(), spEndDate.getMyDate())){
            res += customer.getTotal();
            tbCustomer.dftbModel.addRow(customer.getRowObjects());
        }
        lbTotalCustomer.setText(String.format("Tổng cộng: %,.0fđ", res));
    }
}
