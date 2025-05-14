package GUI.JPanel;

import BUS.ImportBUS;
import BUS.ImportInfoBUS;
import Components.*;
import DTO.ImportDTO;
import DTO.ImportInfoDTO;
import GUI.JFrame.fManage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class pnImportOrder extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDetail = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Chi tiết</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã HĐ", "Ngày tạo", "Thành tiền", "Trạng thái"}, 12);
    MyJTable tbImport = new MyJTable(new String[]{"Mã HĐ", "Ngày tạo", "Thành tiền", "Trạng thái"}, new int[]{100, 100, 150, 150}, new int[]{}, new int[]{});

    MyJTable tbImportInfo = new MyJTable(new String[]{"Mã SP", "Tên SP" , "Đơn giá", "Số lượng", "Đơn vị ", "Thành tiền"}, new int[]{50, 150, 125, 100, 100, 125}, new int[]{1, 5}, new int[]{});
    JPanel pnImport = new MyJPanel(MyColor.White, "Thông tin chi tiết hóa đơn");
    JLabel lbStaff = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Không có thông tin nhân viên</u></html>", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSupplier = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Không có thông tin nhà cung cấp</u></html>", SwingConstants.LEFT, SwingConstants.CENTER);

    pnImportOrder thisPanel = this;

    public pnImportOrder() {
        setLayout(null);
        setBackground(MyColor.White);

        // region setBounds
        pnHeader.setBounds(0,0,1170, 90);
        pnFunc.setBounds(0,0,230,90);
        btnDelete.setBounds(15,20,60,60);
        btnOut.setBounds(85,20,60,60);
        btnDetail.setBounds(155,20,60,60);
        pnSearch.setBounds(670,0,500,90);
        cbSearch.setBounds(685, 30, 150, 30);
        tfSearch.setBounds(845, 30, 200, 30);
        btnRefresh.setBounds(1055,30,100,30);
        pnFooter.setBounds(0,100,1170, 650);
        tbImport.scrPn.setBounds(0,100,500,650);

        pnImport.setBounds(520, 100, 650, 100);
        lbStaff.setBounds(530, 110, 630, 30);
        lbSupplier.setBounds(530, 140, 630, 30);

        tbImportInfo.scrPn.setBounds(520, 210, 650, 540);
        // endregion

        // region event
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {load();}
        });
        tbImport.getSelectionModel().addListSelectionListener(_ -> {
            int rowSelect = tbImport.getSelectedRow();
            if(rowSelect != -1) {
                int id = Integer.parseInt(tbImport.getFirstColumn(rowSelect));
                ImportDTO importDTO = ImportBUS.getInstance().getImportById(id);
                lbStaff.setText(
                        String.format("<html>Mã nhân viên: <b>%d</b> --- Tên nhân viên: <b>%s</b> --- Số điện thoại: <b>%s</b></html>", importDTO.getStaff().getId(), importDTO.getStaff().getLastName() + " " + importDTO.getStaff().getFirstName(), importDTO.getStaff().getPhone())
                );
                if(importDTO.getSupplier().getId()!=0)
                    lbSupplier.setText(
                            String.format("<html>Mã nhà cung cấp: <b>%d</b> --- Tên nhà cung cấp: <b>%s</b> --- Số điện thoại: <b>%s</b></html>", importDTO.getSupplier().getId(), importDTO.getSupplier().getName(), importDTO.getSupplier().getPhone())
                    );
                else lbSupplier.setText("<html><u>Không có thông tin nhà cung cấp</u></html>");

                tbImportInfo.dftbModel.setRowCount(0);
                for(ImportInfoDTO importInfoDTO: ImportInfoBUS.getInstance().loadByIdImport(id))
                    tbImportInfo.dftbModel.addRow(importInfoDTO.getSellObjects());
            }
            else {
                lbStaff.setText("<html><u>Không có thông tin nhân viên</u></html>");
                lbSupplier.setText("<html><u>Không có thông tin nhà cung cấp</u></html>");
                tbImportInfo.dftbModel.setRowCount(0);
            }
        });
        btnDelete.addActionListener(_ -> {
            int i = tbImport.getSelectedRow();
            if(i>=0){
                int id = Integer.parseInt(tbImport.getFirstColumn(i));
                try {
                    int res = JOptionPane.showConfirmDialog(thisPanel,"Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if(res==JOptionPane.YES_OPTION){
                        ImportBUS.getInstance().delete(id);
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        load();
                    }
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
        btnDetail.addActionListener(_ -> {
            int i = tbImport.getSelectedRow();
            if(i>=0){
                JOptionPane.showMessageDialog(thisPanel, "Tạo PDF đi!!!");
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn hóa đơn cần xuất thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
        btnRefresh.addActionListener(_ -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            load();
        });
        btnOut.addActionListener(_ -> tbImport.ExportExel("Danh sách đơn nhập hàng"));
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

        // region add
        add(lbSupplier);
        add(lbStaff);
        add(btnDelete);
        add(btnOut);
        add(btnDetail);
        add(pnFunc);
        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(pnSearch);
        add(pnHeader);
        add(tbImportInfo.scrPn);
        add(tbImport.scrPn);
        add(pnImport);
        add(pnFooter);
        // endregion
    }

    public void load()  {
        ImportBUS.getInstance().load();
        textChange();
    }

    public void textChange(){
        tbImport.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(ImportDTO bill: ImportBUS.getInstance().search(col, txt))
            tbImport.dftbModel.addRow(bill.getRowObjects());
    }
}
