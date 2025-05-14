package GUI.JDialog;

import BUS.OfferBUS;
import Components.*;
import DTO.OfferDTO;
import GUI.JPanel.pnOffer;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;

public class dlAddoffer extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thêm chương trình giảm giá", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày áp dụng*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Ngày kết thúc*", SwingConstants.LEFT, SwingConstants.CENTER);
    MyJSpinner spStartDate = new MyJSpinner(MyDate.getCurrentDate());
    MyJSpinner spEndDate = new MyJSpinner(MyDate.getCurrentDate());
    JLabel lbnameopffer = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên chương trình", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbcategory = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Áp dụng cho*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbvalue = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giá trị giảm(%)", SwingConstants.LEFT, SwingConstants.CENTER);
    JComboBox<String> category = new MyJComboBox<>(new String[]{"Giảm giá sản phẩm","Giảm giá hóa đơn"},12);
    JTextField tfvalue = new JTextField();
    JTextField tfname = new JTextField();
    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    public dlAddoffer(pnOffer parentPanel) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Thêm chương trình giảm giá");
        setSize(540, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // region setBounds
        pnMain.setBounds(0, 0, 540, 400);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 540, 60);
        lbStartDate.setBounds(50, 80, 200, 20);
        spStartDate.setBounds(50, 100, 200, 30);
        lbEndDate.setBounds(270, 80, 200, 20);
        spEndDate.setBounds(270, 100, 200, 30);
        lbcategory.setBounds(50,140,200,20);
        category.setBounds(50,160,200,30);
        lbvalue.setBounds(270,140,200,20);
        tfvalue.setBounds(270,160,200,30);
        lbnameopffer.setBounds(50,200,200,20);
        tfname.setBounds(50,220,200,30);
        btnSave.setBounds(100, 290, 150, 40);
        btnEsc.setBounds(270, 290, 150, 40);
        // endregion
        btnEsc.addActionListener(_ -> dispose());
        btnSave.addActionListener(_ -> {
            OfferDTO offer = new OfferDTO(-1,tfname.getText() ,spStartDate.getMyDate(), spEndDate.getMyDate(),(String) category.getSelectedItem(),Integer.parseInt(tfvalue.getText()));
            try {
                OfferBUS.getInstance().add(offer);
                JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadOffer();
                dispose();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // region ADD
        add(lbHeader);
        add(lbStartDate);
        add(spStartDate);
        add(lbEndDate);
        add(spEndDate);
        add(lbcategory);
        add(lbvalue);
        add(category);
        add(tfvalue);
        add(tfname);
        add(lbnameopffer);
        add(btnSave);
        add(btnEsc);
        add(pnMain);
        // endregion

        setVisible(true);
    }
}
