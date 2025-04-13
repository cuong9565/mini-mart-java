package GUI.JPanel;

import BUS.Bill2BUS;
import BUS.BillBUS;
import Components.*;
import DTO.*;
import GUI.JFrame.fManage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class pnBill extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDetail = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Chi tiết</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã", "Tên nhân viên", "Giảm giá", "Tên khách hàng", "Ngày tạo", "Thành tiền", "Trạng thái"}, 12);

    MyJTable tbBill = new MyJTable(new String[]{"Mã", "Tên nhân viên", "Giảm giá", "Tên khách hàng", "Ngày tạo", "Thành tiền", "Trạng thái"}, new int[]{30, 100, 100, 100, 100, 30}, new int[]{1, 3}, new int[]{});

    pnBill thisPanel = this;

    public pnBill(fManage frame) {
        setLayout(null);
        setBackground(MyColor.White);
        // region SET BOUNDS
        pnHeader.setBounds(0,0,1170, 90);
        pnFunc.setBounds(0,0,300,90);
        btnEdit.setBounds(15,20,60,60);
        btnDelete.setBounds(85,20,60,60);
        btnOut.setBounds(155,20,60,60);
        btnDetail.setBounds(225,20,60,60);
        pnSearch.setBounds(670,0,500,90);
        cbSearch.setBounds(685, 30, 150, 30);
        tfSearch.setBounds(845, 30, 200, 30);
        btnRefresh.setBounds(1055,30,100,30);
        pnFooter.setBounds(0,100,1170, 650);
        tbBill.scrPn.setBounds(0,100,1170,650);
        // endregion
        // region EVENT CHO PANEL NÀY
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadBill();}
        });
        // endregion
        // region EVEN
//        btnAdd.addActionListener(_->new dlAddBill(frame, thisPanel));
//        btnEdit.addActionListener(_->{
//            int i = tbBill.getSelectedRow();
//            if (i>=0){
//                int id = Integer.parseInt(tbBill.getFirstColumn(i));
//                BillDTO bill = BillBUS.getInstance().getItemById(id);
//                new dlEditBill(frame, thisPanel, bill);
//            }
//            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//        });
//        btnDelete.addActionListener(_ -> {
//            int i = tbBill.getSelectedRow();
//            if(i>=0){
//                int id = Integer.parseInt(tbBill.getFirstColumn(i));
//                BillDTO bill = BillBUS.getInstance().getItemById(id);
//                if(BillBUS.getInstance().delete(bill)){
//                    JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    loadBill();
//                }
//                else JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + BillBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
//            }
//            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//
//        });
//        btnDetail.addActionListener(_ -> {
//            int i = tbBill.getSelectedRow();
//            if (i>=0){
//                int id = Integer.parseInt(tbBill.getFirstColumn(i));
//                BillDTO bill = BillBUS.getInstance().getItemById(id);
//                new dlDetailBill(frame, thisPanel, bill);
//            }
//            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xem!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//
//        });
        btnRefresh.addActionListener(_ -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadBill();
        });
//        btnIn.addActionListener(_ -> {
//            List<Object[]> list = tbBill.ImportExel(4);
//            if(list==null) return;
//            String error = null;
//            int success = 0;
//            for (Object[] ob : list) {
//                int idBillType = TypeBillBUS.getInstance().getItemByName(ob[0].toString()).getId();
//                String detail = "";
//                int idOfferBill = 0;
//                String name = ob[1].toString();
//                double price = Double.parseDouble(ob[2].toString().replace("đ", "").replace(",", ""));
//                String unit = ob[3].toString();
//
//                if(BillBUS.getInstance().add(idBillType, detail, idOfferBill, name, price, unit, 0)){
//                    success++;
//                }
//                else{
//                    error = BillBUS.getInstance().getError();
//                }
//            }
//
//            JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + success + " sản phẩm");
//            if(error!=null) JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + error);
//            loadBill();
//        });
        btnOut.addActionListener(_ -> tbBill.ExportExel("Danh sách hóa đơn"));
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
        add(btnEdit);
        add(btnDelete);
        add(btnOut);
        add(btnDetail);
        add(pnFunc);
        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(pnSearch);
        add(pnHeader);
        add(tbBill.scrPn);
        add(pnFooter);
        // endregion
    }

    public void loadBill()  {
        Bill2BUS.getInstance().load();
        textChange();
    }

    public void textChange(){
        tbBill.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(Bill2DTO bill: Bill2BUS.getInstance().search(col, txt))
            tbBill.dftbModel.addRow(bill.getRowObjects());
    }
}
