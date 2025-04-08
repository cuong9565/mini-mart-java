package GUI.JPanel;

import BUS.CustomerBUS;
import BUS.SupplierBUS;
import Components.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import Components.MyColor;
import Components.MyJButton;
import DTO.CustomerDTO;
import DTO.SupplierDTO;
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
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Tên", "Mã số", "Số điện thoại", "Họ", "Địa chỉ", "Giới tính", "Trạng thái"}, 12);

    MyJTable tbCustomer = new MyJTable(new String[]{"Mã số", "Số điện thoại" , "Họ", "Tên", "Địa chỉ", "Giới tính", "Trạng thái"}, new int[]{}, new int[]{}, new int[]{});
    int currPosCB = 0;
    pnCustomer thisPanel = this;

    int posSelectedCB = 0;

    public pnCustomer(fManage fmanage) {
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
        pnSearch.setBounds(470,0,500,90);
        cbSearch.setBounds(485, 30, 150, 30);
        tfSearch.setBounds(645, 30, 200, 30);
        btnRefresh.setBounds(855,30,100,30);
        pnFooter.setBounds(0,100,970, 650);
        tbCustomer.scrPn.setBounds(0,100,970,650);
        // endregion
        // region ADD EVENT CHO FORM NÀY
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                btnRefresh.doClick();
            }
        });
        // endregion
        // region EVENT
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dlAddCustomer(fmanage, thisPanel);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tbCustomer.getSelectedRow();
                if(i==-1) JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn tài khoản để sửa thông tin!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                else {
                    int id = Integer.parseInt(tbCustomer.getFirstColumn(i));
                    CustomerDTO customer = CustomerBUS.getInstance().getItemById(id);
                    new dlEditCustomer(fmanage, thisPanel, customer);
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tbCustomer.getSelectedRow();
                if(i==-1) JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn khách hàng cần xóa!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                else {
                    int id = Integer.parseInt(tbCustomer.getFirstColumn(i));
                    CustomerDTO customer = CustomerBUS.getInstance().getItemById(id);
                    if(CustomerBUS.getInstance().delete(customer)){
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadCustomer();
                        textChange();
                    }
                    else JOptionPane.showMessageDialog(thisPanel, CustomerBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbSearch.setSelectedIndex(0);
                tfSearch.setText("");
                loadCustomer();
            }
        });
        btnOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbCustomer.ExportExel("Danh sách khách hàng");
            }
        });
        btnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Object[]> list = tbCustomer.ImportExel(6);
                if(list==null) return;
                List<CustomerDTO> customers = new ArrayList<>();
                for (Object[] ob : list)
                    customers.add(new CustomerDTO(-1, ob[0].toString(), ob[1].toString(), ob[2].toString(), ob[3].toString(), ob[4].toString(), ob[5].toString()));
                if(CustomerBUS.getInstance().adds(customers)){
                    JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + CustomerBUS.getInstance().getNumLine() + " khách hàng");
                    loadCustomer();
                }
                else {
                    JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + CustomerBUS.getInstance().getError());
                }
            }
        });
        cbSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = cbSearch.getSelectedIndex();
                if(posSelectedCB !=i){
                    posSelectedCB = i;
                    tfSearch.setText("");
                }
            }
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
        tbCustomer.dftbModel.setRowCount(0);
        for(CustomerDTO customer: CustomerBUS.getInstance().getAllList())
            tbCustomer.dftbModel.addRow(customer.getObjects());
    }

    public void textChange(){
        tbCustomer.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(CustomerDTO customer: CustomerBUS.getInstance().getSupplierListBy(col, txt))
            tbCustomer.dftbModel.addRow(customer.getObjects());
    }
}