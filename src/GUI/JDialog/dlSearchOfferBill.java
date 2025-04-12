package GUI.JDialog;

import BUS.OfferBillBUS;
import Components.*;
import DTO.OfferBillDTO;
import GUI.JFrame.fManage;
import GUI.JPanel.pnSell;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class dlSearchOfferBill extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Tìm kiếm giảm giá hóa đơn", SwingConstants.CENTER, SwingConstants.CENTER);

    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String> cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá"}, 12);
    MyJTable tbOfferBill = new MyJTable(new String[]{"Mã số", "Ngày bắt đầu", "Ngày kết thúc", "Giảm giá"}, new int[]{}, new int[]{}, new int[]{});

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlSearchOfferBill(fManage parentFrame, pnSell parentPanel) {
        super(parentFrame, true);
        setTitle("Tìm kiếm giảm giá hóa đơn");
        setSize(1000,650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        loadOfferBill();

        // region setBounds
        pnMain.setBounds(0,0,1000,650);

        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,1000,60);

        pnSearch.setBounds(470,60,500,90);
        cbSearch.setBounds(485, 90, 150, 30);
        tfSearch.setBounds(645, 90, 200, 30);
        btnRefresh.setBounds(855,90,100,30);

        tbOfferBill.scrPn.setBounds(10, 160, 960, 330);
        btnSave.setBounds(340,520,150,40);
        btnEsc.setBounds(510,520,150,40);

        // endregion

        // region Event
        btnEsc.addActionListener(_ -> dialog.dispose());
        btnSave.addActionListener(_ -> {
            int i = tbOfferBill.getSelectedRow();
            if(i==-1) JOptionPane.showMessageDialog(dialog, "Vui lòng chọn thông tin giảm giá", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else {
                int idOfferBill = Integer.parseInt(tbOfferBill.getFirstColumn(i));
                parentPanel.updateOfferBill(idOfferBill);
                dialog.dispose();
            }
        });

        btnRefresh.addActionListener(_ -> {
            cbSearch.setSelectedIndex(0);
            tfSearch.setText("");
            loadOfferBill();
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
        add(tbOfferBill.scrPn);

        add(btnSave);
        add(btnEsc);

        add(lbHeader);
        add(pnSearch);
        add(pnMain);

        setVisible(true);
    }

    public void loadOfferBill(){
        OfferBillBUS.getInstance().getList();
        textChange();
    }

    public void textChange(){
        tbOfferBill.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(OfferBillDTO offerBill: OfferBillBUS.getInstance().getListBy(col, txt)){
            // Kiểm tra ngày nào áp dụng cho hôm nay
            MyDate today = MyDate.getCurrentDate();
            if(today.compareTo(offerBill.getOffer().getDateStart())>=0 && today.compareTo(offerBill.getOffer().getDateEnd())<=0)
                tbOfferBill.dftbModel.addRow(offerBill.getObjects());
        }
    }
}
