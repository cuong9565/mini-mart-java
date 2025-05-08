package GUI.JPanel;

import BUS.CustomerBUS;
import BUS.ProductBUS;
import BUS.ProductStatisticBUS;
import BUS.StaffBUS;
import Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class pnStatisticBill extends JPanel {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JPanel pnNumCustomer = new MyJPanel(MyColor.LightBlue);
    JPanel pnNumProduct = new MyJPanel(MyColor.Orange);
    JPanel pnNumStaff = new MyJPanel(MyColor.Purple);
    JPanel pnNumTotal = new MyJPanel(MyColor.Green);
    JLabel lbNumCustomer = new MyJLabel(Font.BOLD, 22, MyColor.White, "Khách hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbNumProduct = new MyJLabel(Font.BOLD, 22, MyColor.White, "Sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbNumStaff = new MyJLabel(Font.BOLD, 22, MyColor.White, "Nhân viên", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbNumTotal = new MyJLabel(Font.BOLD, 22, MyColor.White, "Doanh thu", SwingConstants.CENTER, SwingConstants.CENTER);
    JPanel pnFill = new MyJPanel(MyColor.White, "Lọc theo ngày");
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Từ: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Đến: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnFill = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Lọc", SwingConstants.CENTER, SwingConstants.CENTER);
    MyJSpinner spStartDate = new MyJSpinner(MyDate.getMinInMonth());
    MyJSpinner spEndDate = new MyJSpinner(MyDate.getMaxInMonth());

    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Tổng đơn nhập và đơn xuất", SwingConstants.CENTER, SwingConstants.CENTER);

    MyJTable tbStatistic = new MyJTable(new String[]{"Quý", "Quý 1", "Quý 2", "Quý 3", "Quý 4", "Tổng cộng"}, 16, new int[]{}, new int[]{}, new int[]{});

    public pnStatisticBill() {
        setLayout(null);

        // region setBounds
        pnMain.setBounds(0,0,1200, 800);
        pnNumCustomer.setBounds(0,0,200,200);
        lbNumCustomer.setBounds(0, 0, 200, 200);
        pnNumProduct.setBounds(330,0,200,200);
        lbNumProduct.setBounds(330,0,200,200);
        pnNumStaff.setBounds(640,0,200,200);
        lbNumStaff.setBounds(640,0,200,200);
        pnNumTotal.setBounds(970,0,200,200);
        lbNumTotal.setBounds(970,0,200,200);

        pnFill.setBounds(0, 200, 450, 90);
        lbStartDate.setBounds(10, 230, 30, 30);
        spStartDate.setBounds(37, 230, 100, 30);
        lbEndDate.setBounds(160, 230, 40, 30);
        spEndDate.setBounds(192, 230, 100, 30);
        btnFill.setBounds(330, 230, 100, 30);

        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 290, 1170, 50);
        tbStatistic.scrPn.setBounds(0, 340, 1170, 156);

        // endregion

        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {load();}
        });
        btnFill.addActionListener(_->load());

        tbStatistic.setRowHeight(40);

        add(lbHeader);
        add(tbStatistic.scrPn);
        add(lbStartDate);
        add(lbEndDate);
        add(spStartDate);
        add(spEndDate);
        add(btnFill);

        add(lbNumCustomer);
        add(lbNumProduct);
        add(lbNumStaff);
        add(lbNumTotal);

        add(pnFill);
        add(pnNumCustomer);
        add(pnNumProduct);
        add(pnNumStaff);
        add(pnNumTotal);
        add(pnMain);
    }

    public void load(){
        int numCustomer = CustomerBUS.getInstance().getNumberCustomer();
        int numProduct = ProductBUS.getInstance().getNumberProduct();
        int numStaff = StaffBUS.getInstance().getNumberStaff();
        double numTotal = ProductStatisticBUS.getInstance().getProfit();

        lbNumCustomer.setText(String.format("<html>%d<br>Khách hàng</html>", numCustomer));
        lbNumProduct.setText(String.format("<html>%d<br>Sản phẩm</html>", numProduct));
        lbNumStaff.setText(String.format("<html>%d<br>Nhân viên</html>", numStaff));
        lbNumTotal.setText(String.format("<html>%,.0fđ<br>Doanh thu</html>", numTotal));

        MyDate startDate = spStartDate.getMyDate();
        MyDate endDate = spEndDate.getMyDate();
        tbStatistic.dftbModel.setRowCount(0);
        tbStatistic.dftbModel.addRow(ProductStatisticBUS.getInstance().getRowObjectImport(startDate, endDate));
        tbStatistic.dftbModel.addRow(ProductStatisticBUS.getInstance().getRowObjectExport(startDate, endDate));
        tbStatistic.dftbModel.addRow(ProductStatisticBUS.getInstance().getRowObjectProfit(startDate, endDate));
    }
}
