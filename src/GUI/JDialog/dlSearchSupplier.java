package GUI.JDialog;

import BUS.SupplierBUS;
import Components.*;
import DTO.SupplierDTO;
import GUI.JFrame.fManage;
import GUI.JPanel.pnImport;
import GUI.JPanel.pnSell;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class dlSearchSupplier extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Tìm kiếm nhà cung cấp", SwingConstants.CENTER, SwingConstants.CENTER);

    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Tên", "Số điện thoại", "Địa chỉ", "Email"}, 12);

    MyJTable tbSupplier = new MyJTable(new String[]{"Mã số", "Tên", "Số điện thoại", "Địa chỉ", "Email"}, new int[]{20, 100, 60, 300}, new int[]{1, 3, 4}, new int[]{});

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlSearchSupplier(fManage parentFrame, pnImport parentPanel) {
        super(parentFrame, true);
        setTitle("Tìm kiếm nhà cung cấp");
        setSize(800,650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        loadSupplier();

        // region setBounds
        pnMain.setBounds(0,0,800,650);

        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,800,60);

        pnSearch.setBounds(270,60,500,90);
        cbSearch.setBounds(285, 90, 150, 30);
        tfSearch.setBounds(445, 90, 200, 30);
        btnRefresh.setBounds(655,90,100,30);

        tbSupplier.scrPn.setBounds(10, 160, 760, 330);
        btnSave.setBounds(240,520,150,40);
        btnEsc.setBounds(410,520,150,40);
        // endregion

        // region Event
        btnEsc.addActionListener(_ -> dialog.dispose());
        btnSave.addActionListener(_ -> {
            int i = tbSupplier.getSelectedRow();
            if(i==-1) JOptionPane.showMessageDialog(dialog, "Vui lòng chọn thông tin nhà cung cấp", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else {
                int idSupplier = Integer.parseInt(tbSupplier.getFirstColumn(i));
                parentPanel.updateSupplierById(idSupplier);
                parentPanel.loadImport();
                dialog.dispose();
            }
        });

        btnRefresh.addActionListener(_ -> {
            cbSearch.setSelectedIndex(0);
            tfSearch.setText("");
            loadSupplier();
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

        add(cbSearch);
        add(btnRefresh);
        add(tfSearch);
        add(tbSupplier.scrPn);

        add(btnSave);
        add(btnEsc);

        add(lbHeader);
        add(pnSearch);
        add(pnMain);

        setVisible(true);
    }

    public void loadSupplier(){
        SupplierBUS.getInstance().load();
        textChange();
    }

    public void textChange(){
        tbSupplier.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(SupplierDTO customer: SupplierBUS.getInstance().getSupplierListBy(col, txt))
            tbSupplier.dftbModel.addRow(customer.getObjects());
    }
}
