package GUI.JDialog;

import Components.MyColor;
import Components.MyJLabel;
import Components.MyJPanel;
import Components.MyJTable;
import DAO.*;
import GUI.JFrame.fManage;
import GUI.JPanel.pnImport;

import javax.swing.*;
import java.awt.*;

public class dlImportDetail extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Danh sách đơn nhập hàng", SwingConstants.CENTER, SwingConstants.CENTER);
    MyJTable tbImportOrders = new MyJTable(new String[]{"Mã phiếu nhập", "Nhân viên", "Nhà cung cấp", "Ngày tạo", "Tổng tiền", "Chi tiết"}, new int[]{20, 100, 60, 300}, new int[]{1, 3, 4}, new int[]{});

    public dlImportDetail(fManage parentFrame, pnImport parentPanel) {
        super(parentFrame, true);
        setTitle("Xem danh sách nhập hàng");
        setSize(950, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Thiết lập header
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 950, 60);

        // Thiết lập bảng phiếu nhập
        tbImportOrders.scrPn.setBounds(0, 70, 950, 400);


        // Thêm các thành phần vào giao diện
        pnMain.setBounds(0, 0, 950, 500);
        pnMain.setLayout(null);
        pnMain.add(lbHeader);
        pnMain.add(tbImportOrders.scrPn);

        add(pnMain);
        setVisible(true);

    }
}