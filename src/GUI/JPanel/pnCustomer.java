package GUI.JPanel;

import BUS.CustomerBUS;
import Components.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import Components.MyColor;
import Components.MyJButton;
import DTO.CustomerDTO;
import GUI.JDialog.dlAddCustomer;
import GUI.JDialog.dlEditCustomer;
import GUI.JFrame.fManage;

public class pnCustomer extends JPanel {
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
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Số điện thoại" , "Họ", "Tên", "Địa chỉ", "Giới tính", "Trạng thái"}, 12);

    MyJTable tbCustomer = new MyJTable(new String[]{"Mã số", "Số điện thoại" , "Họ", "Tên", "Địa chỉ", "Giới tính", "Trạng thái"}, new int[]{50, 100, 100, 100, 300, 35}, new int[]{2, 3, 4}, new int[]{});
    pnCustomer thisPanel = this;

    public pnCustomer(fManage fmanage) {
        setLayout(null);
        setBackground(MyColor.White);

        // region setBounds
        pnHeader.setBounds(0,0,1170, 90);
        pnFunc.setBounds(0,0,370,90);
        btnAdd.setBounds(15,20,60,60);
        btnEdit.setBounds(85,20,60,60);
        btnDelete.setBounds(155,20,60,60);
        btnIn.setBounds(225,20,60,60);
        btnOut.setBounds(295,20,60,60);

        pnSearch.setBounds(670,0,500,90);
        cbSearch.setBounds(685, 30, 150, 30);
        tfSearch.setBounds(845, 30, 200, 30);
        btnRefresh.setBounds(1055,30,100,30);
        pnFooter.setBounds(0,100,1170, 650);
        tbCustomer.scrPn.setBounds(0,100,1170,650);
        // endregion
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadCustomer();}
        });
        // region EVENT
        btnAdd.addActionListener(_ -> new dlAddCustomer(fmanage, thisPanel));
        btnEdit.addActionListener(_ -> {
            int i = tbCustomer.getSelectedRow();
            if(i==-1) JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn tài khoản để sửa thông tin!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            else {
                int id = Integer.parseInt(tbCustomer.getFirstColumn(i));
                CustomerDTO customer = CustomerBUS.getInstance().getItemById(id);
                new dlEditCustomer(fmanage, thisPanel, customer);
            }
        });
        btnDelete.addActionListener(_ -> {
            int i = tbCustomer.getSelectedRow();
            if(i==-1) JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn khách hàng cần xóa!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            else {
                int choose = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choose == JOptionPane.YES_OPTION) {
                    try {
                        int id = Integer.parseInt(tbCustomer.getFirstColumn(i));
                        CustomerDTO customer = CustomerBUS.getInstance().getItemById(id);
                        CustomerBUS.getInstance().delete(customer);
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadCustomer();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnOut.addActionListener(_ -> tbCustomer.ExportExel("Danh sách khách hàng"));
        btnIn.addActionListener(_ -> {
            List<Object[]> list = tbCustomer.ImportExel(5);
            if(list==null) return;
            int choose = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc chắn muốn thêm?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                int success = 0;
                StringBuilder error = new StringBuilder();
                for (Object[] ob : list){
                    try {
                        CustomerDTO customer = new CustomerDTO(-1, ob[0].toString(), ob[1].toString(), ob[2].toString(), ob[3].toString(), ob[4].toString(), "");
                        CustomerBUS.getInstance().add(customer);
                        success++;
                    } catch (Exception e) {
                        error.append(e.getMessage()).append("\n");
                    }
                }
                if(!error.isEmpty())
                    JOptionPane.showMessageDialog(thisPanel, error.toString(), "Lỗi", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + success + " khách hàng");
                loadCustomer();
            }
        });
        btnRefresh.addActionListener(_ -> {
            cbSearch.setSelectedIndex(0);
            tfSearch.setText("");
            loadCustomer();
        });
        cbSearch.addActionListener(_ -> textChange());
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
        add(tbCustomer.scrPn);
        add(pnFooter);
        // endregion
    }

    public void loadCustomer(){
        CustomerBUS.getInstance().getAllList();
        textChange();
    }

    public void textChange(){
        tbCustomer.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(CustomerDTO customer: CustomerBUS.getInstance().getSupplierListBy(col, txt))
            tbCustomer.dftbModel.addRow(customer.getObjects());
    }
}