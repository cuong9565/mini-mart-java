package GUI.JPanel;

import Components.*;
import DTO.ProductStatisticDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class pnStatisticProduct extends JPanel {
    JPanel pnMain = new MyJPanel(MyColor.White);

    JLabel lbHeaderImport = new MyJLabel(Font.BOLD, 24, MyColor.White, "Đơn nhập", SwingConstants.CENTER, SwingConstants.CENTER);
    JPanel pnFill = new MyJPanel(MyColor.White, "Lọc theo ngày");
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Từ: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Đến: ", SwingConstants.LEFT, SwingConstants.CENTER);
    JButton btnFill = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Lọc", SwingConstants.CENTER, SwingConstants.CENTER);
    MyJSpinner spStartDate = new MyJSpinner(MyDate.getMinInMonth());
    MyJSpinner spEndDate = new MyJSpinner(MyDate.getMaxInMonth());
    JLabel lbTotalImport = new MyJLabel(Font.BOLD, 14, MyColor.Black, "Tổng cộng: 0đ", SwingConstants.LEFT, SwingConstants.CENTER);

    JLabel lbHeaderExport = new MyJLabel(Font.BOLD, 24, MyColor.White, "Đơn xuất", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbTotalExport = new MyJLabel(Font.BOLD, 14, MyColor.Black, "Tổng doanh thu: 0đ", SwingConstants.LEFT, SwingConstants.CENTER);

    JLabel lbTotal = new MyJLabel(Font.BOLD, 14, MyColor.Black, "Tổng cộng: 0đ", SwingConstants.LEFT, SwingConstants.CENTER);


    MyJTable tbImport = new MyJTable(new String[]{"Mã sp", "Tên sp", "Q1", "Q2", "Q3", "Q4", "Tổng cộng"}, 12, new int[]{}, new int[]{1}, new int[]{});
    MyJTable tbExport = new MyJTable(new String[]{"Mã sp", "Tên sp", "Q1", "Q2", "Q3", "Q4", "Tổng cộng"}, 12, new int[]{}, new int[]{1}, new int[]{});
    public pnStatisticProduct() {
        setLayout(null);

        // region setBounds
        pnMain.setBounds(0, 0, 1200, 800);

        pnFill.setBounds(0, 0, 1170, 60);
        lbStartDate.setBounds(10, 20, 30, 30);
        spStartDate.setBounds(40, 20, 100, 30);
        lbEndDate.setBounds(150, 20, 40, 30);
        spEndDate.setBounds(190, 20, 100, 30);
        btnFill.setBounds(310, 20, 100, 30);

        lbHeaderImport.setOpaque(true);
        lbHeaderImport.setBackground(MyColor.DarkBlue);
        lbHeaderImport.setBounds(0, 60, 570, 50);
        tbImport.scrPn.setBounds(0, 110, 570, 460);
        lbTotalImport.setBounds(0, 600, 570, 30);

        lbHeaderExport.setOpaque(true);
        lbHeaderExport.setBackground(MyColor.DarkBlue);
        lbHeaderExport.setBounds(600, 60, 570, 50);
        tbExport.scrPn.setBounds(600, 110, 570, 460);
        lbTotalExport.setBounds(600, 600, 570, 30);

        lbTotal.setBounds(0, 630, 570, 30);
        // endregion

        // region event
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {load();}
        });
        btnFill.addActionListener(_ -> load());
        // endregion

        // region add
        add(lbTotal);

        add(btnFill);
        add(lbTotalExport);
        add(tbExport.scrPn);
        add(lbHeaderExport);

        add(lbTotalImport);
        add(tbImport.scrPn);
        add(lbStartDate);
        add(lbEndDate);
        add(spStartDate);
        add(spEndDate);
        add(pnFill);
        add(lbHeaderImport);
        add(pnMain);
        // endregion
    }

    public void load(){
        loadExport();
        loadImport();

        double totalImport = Double.parseDouble(lbTotalImport.getText().replace("Tổng cộng: ","").replace(",","").replace(".","").replace("đ",""));
        double totalExport = Double.parseDouble(lbTotalExport.getText().replace("Tổng cộng: ","").replace(",","").replace(".","").replace("đ",""));

        lbTotal.setText(String.format("Tổng doanh thu: %,.0fđ", totalExport - totalImport));
    }

    public void loadImport(){
        double total = 0;
        tbImport.dftbModel.setRowCount(0);
        for(ProductStatisticDTO p: ProductStatisticBUS.getInstance().loadImportByDate(spStartDate.getMyDate(), spEndDate.getMyDate())){
            tbImport.dftbModel.addRow(p.getRRowObject());
            total+=p.getTotal();
        }
        lbTotalImport.setText(String.format("Tổng cộng: %,.0fđ", total));
    }

    public void loadExport(){
        double total = 0;
        tbExport.dftbModel.setRowCount(0);
        for(ProductStatisticDTO p: ProductStatisticBUS.getInstance().loadExportByDate(spStartDate.getMyDate(), spEndDate.getMyDate())){
            tbExport.dftbModel.addRow(p.getRRowObject());
            total+=p.getTotal();
        }
        lbTotalExport.setText(String.format("Tổng cộng: %,.0fđ", total));
    }
}
