package GUI.JPanel;

import BUS.SupplierBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddSupplier;
import GUI.JDialog.dlEditSupplier;
import GUI.JFrame.fManage;
import com.mysql.cj.protocol.Message;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Tên", "Số điện thoại", "Địa chỉ", "Email"}, 12);

    MyJTable tbSupplier = new MyJTable(new String[]{"Mã số", "Tên", "Số điện thoại", "Địa chỉ", "Email"}, new int[]{20, 100, 60, 300}, new int[]{1, 3, 4}, new int[]{});

    pnSupplier thisPanel = this;

    public pnSupplier(fManage frame) {
        setLayout(null);
        setBackground(MyColor.White);

        // region SET BOUNDS
        pnHeader.setBounds(0,0,970, 90);
        pnFunc.setBounds(0,0,370,90);
        btnAdd.setBounds(15,20,60,60);
        btnEdit.setBounds(85,20,60,60);
        btnDelete.setBounds(155,20,60,60);
        btnIn.setBounds(225,20,60,60);
        btnOut.setBounds(295,20,60,60);
        pnSearch.setBounds(660,0,500,90);
        cbSearch.setBounds(675, 30, 150, 30);
        tfSearch.setBounds(835, 30, 200, 30);
        btnRefresh.setBounds(1045,30,100,30);
        pnFooter.setBounds(0,100,970, 650);
        tbSupplier.scrPn.setBounds(0,100,1160,640);
        // endregion
        // region EVENT CHO PANEL NÀY
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadSupplier();}
        });
        // endregion
        // region EVENT
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
                    int id = Integer.parseInt(tbSupplier.getFirstColumn(i));
                    SupplierDTO supplier = SupplierBUS.getInstance().getSupplierById(id);
                    new dlEditSupplier(frame, thisPanel, supplier);
                }
                else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tbSupplier.getSelectedRow();
                if (i >=0){
                    int id = Integer.parseInt(tbSupplier.getFirstColumn(i));
                    SupplierDTO supplierNew = SupplierBUS.getInstance().getSupplierById(id);
                    if(SupplierBUS.getInstance().deleteSupplier(supplierNew)){
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadSupplier();
                    }
                    else JOptionPane.showMessageDialog(thisPanel, SupplierBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfSearch.setText("");
                cbSearch.setSelectedIndex(0);
                loadSupplier();
            }
        });
        btnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object[]> list = tbSupplier.ImportExel(4);
                if(list==null) return;
                List<SupplierDTO> suppliers = new ArrayList<>();
                for (Object[] ob : list)
                    suppliers.add(new SupplierDTO(-1, ob[0].toString(), ob[1].toString(), ob[2].toString(), ob[3].toString()));
                if(SupplierBUS.getInstance().addSuppliers(suppliers)){
                    JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + SupplierBUS.getInstance().getNumLine() + " nhà cung cấp");
                    loadSupplier();
                }
                else {
                    JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + SupplierBUS.getInstance().getError());
                }
            }
        });
        btnOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbSupplier.ExportExel("Danh sách nhà cung cấp");
            }
        });
        cbSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {textChange();}
        });
        tfSearch.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {textChange();}
        });
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textChange();}
            public void removeUpdate(DocumentEvent e) {textChange();}
            public void changedUpdate(DocumentEvent e) {textChange();}
        });
        // endregion
        // region ADD
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
        // endregion

    }

    public void loadSupplier()  {
        tbSupplier.dftbModel.setRowCount(0);
        for(SupplierDTO supplier: SupplierBUS.getInstance().load())
            tbSupplier.dftbModel.addRow(supplier.getObjects());
        textChange();
    }

    public void textChange(){
        tbSupplier.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(SupplierDTO supplier: SupplierBUS.getInstance().getSupplierListBy(col, txt))
            tbSupplier.dftbModel.addRow(supplier.getObjects());
    }
}
