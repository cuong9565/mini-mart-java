package GUI;

import BUS.SupplierBUS;
import Components.*;
import DTO.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnSupplier extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnIn = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Nhập<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Tất cả", "Mã số", "Tên", "Số điện thoại", "Địa chỉ", "Email"}, 12);

    MyJTable tbSupplier = new MyJTable(new String[]{"Mã số", "Tên", "Số điện thoại", "Địa chỉ", "Email"});

    pnSupplier thisPanel = this;

    public pnSupplier(Manage frame) {
        setLayout(null);
        setBackground(MyColor.LightGray);

        pnHeader.setBounds(0,0,970, 90);

        pnFunc.setBounds(0,0,370,90);
        btnAdd.setBounds(15,20,60,60);
        btnEdit.setBounds(85,20,60,60);
        btnDelete.setBounds(155,20,60,60);
        btnIn.setBounds(225,20,60,60);
        btnOut.setBounds(295,20,60,60);

        pnSearch.setBounds(470,0,500,90);
        cbSearch.setBounds(485, 30, 150, 30);
        tfSearch.setBounds(645, 30, 200, 30);
        btnRefresh.setBounds(855,30,100,30);

        pnFooter.setBounds(0,100,970, 650);
        tbSupplier.scrPn.setBounds(0,100,970,650);

        // region EVEN
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dlAddSupplier(frame, thisPanel);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tbSupplier.getSelectedRow();
                if (i >=0){
                    SupplierDTO supplier = new SupplierDTO(tbSupplier.getRowObject(i));
                    new dlEditSupplier(frame, thisPanel, supplier);
                }
                else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin dòng cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tbSupplier.getSelectedRow();
                if (i >=0){
                    SupplierDTO supplierNew = new SupplierDTO(tbSupplier.getRowObject(i));
                    if(SupplierBUS.getInstance().deleteSupplier(supplierNew)){
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadSupplier();
                    }
                    else JOptionPane.showMessageDialog(thisPanel, SupplierBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin dòng cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });

        // endregion

        loadSupplier();

        add(btnAdd);
        add(btnEdit);
        add(btnDelete);
        add(btnIn);
        add(btnOut);
        add(pnFunc);

        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(pnSearch);
        add(pnHeader);

        add(tbSupplier.scrPn);
        add(pnFooter);
    }

    public void loadSupplier()  {
        tbSupplier.dftbModel.setRowCount(0);
        for(SupplierDTO provider: SupplierBUS.getInstance().getListSupplier())
            tbSupplier.addRow(provider.getObjects());
    }
}
