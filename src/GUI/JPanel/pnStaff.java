package GUI.JPanel;

import BUS.ProductBUS;
import BUS.StaffBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddStaff;
import GUI.JDialog.dlEditStaff;
import GUI.JFrame.fManage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class pnStaff extends JPanel {
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
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Họ", "Tên", "Giới tính", "Số điện thoại", "Địa chỉ", "Vai trò", "Lương", "Trạng thái", "Mật khẩu"}, 12);

    MyJTable tbStaff = new MyJTable(new String[]{"Mã số", "Họ", "Tên", "Giới tính", "Số điện thoại", "Địa chỉ", "Vai trò", "Lương", "Trạng thái", "Mật khẩu"}, new int[]{20, 75, 50, 35, 75, 200, 100}, new int[]{1, 2, 5, 6}, new int[]{});

    pnStaff thisPanel = this;

    public pnStaff(fManage frame) {
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
        tbStaff.scrPn.setBounds(0,100,1160,640);
        // endregion
        // region EVENT CHO PANEL NÀY
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadStaff();
            }
        });
        // endregion
        // region EVEN
        btnAdd.addActionListener(_ -> new dlAddStaff(frame, thisPanel));
        btnEdit.addActionListener(_ -> {
            int i = tbStaff.getSelectedRow();
            if (i >=0){
                int id = Integer.parseInt(tbStaff.getFirstColumn(i));
                StaffDTO staff = StaffBUS.getInstance().getStaffById(id);
                new dlEditStaff(frame, thisPanel, staff);
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });
        btnDelete.addActionListener(_ -> {
            int i = tbStaff.getSelectedRow();
            if (i >=0){
                int choose = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc chắn muốn xoá?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (choose == JOptionPane.YES_OPTION) {
                    try {
                        int id = Integer.parseInt(tbStaff.getFirstColumn(i));
                        StaffBUS.getInstance().delete(id);
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadStaff();
                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });
        btnRefresh.addActionListener(_ -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadStaff();
        });
        btnIn.addActionListener(_ -> {
            List<Object[]> list = tbStaff.ImportExel(8);
            if(list==null) return;
            int success = 0;
            int select = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc chắn muốn thêm?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (select == JOptionPane.YES_OPTION) {
                try {
                    for (Object[] ob : list) {
                        String lastName = (String) ob[0];
                        String firstName = (String) ob[1];
                        String gender = (String) ob[2];
                        String phone = (String) ob[3];
                        String address = (String) ob[4];
                        String role = (String) ob[5];
                        Double salary = Double.parseDouble(String.valueOf(ob[6]).replace(",", "").replace("đ", ""));
                        String password = (String) ob[7];
                        StaffDTO staff = new StaffDTO(-1, phone, password, firstName, lastName, gender, address, role, salary, "");
                        StaffBUS.getInstance().add(staff);
                        success++;
                    }
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(thisPanel,e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + success + " nhân viên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadStaff();
            }
        });
        btnOut.addActionListener(_ -> tbStaff.ExportExel("Danh sách nhân viên"));
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
        add(tbStaff.scrPn);
        add(pnFooter);
        // endregion
    }

    public void loadStaff()  {
        tbStaff.dftbModel.setRowCount(0);
        for (StaffDTO staff: StaffBUS.getInstance().load())
            tbStaff.dftbModel.addRow(staff.getObjects());
        textChange();
    }

    public void textChange(){
        tbStaff.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(StaffDTO supplier: StaffBUS.getInstance().getStaffListBy(col, txt))
            tbStaff.dftbModel.addRow(supplier.getObjects());
    }
}
