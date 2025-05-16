package GUI.JDialog;

import BUS.OfferBUS;
import Components.*;
import DTO.OfferDTO;
import GUI.JPanel.pnOffer;

import javax.swing.*;
import java.awt.*;

public class dlEditoffer extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Chỉnh sửa chương trình", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã số*", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfId = new MyJTextFieldInput(Font.PLAIN, 14, false);
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

    public dlEditoffer(pnOffer parentPanel, OfferDTO offer) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Chỉnh sửa chương trình");
        setSize(540, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region setText
        tfId.setText(String.valueOf(offer.getId()));
        spStartDate = new MyJSpinner(offer.getDateStart());
        spEndDate = new MyJSpinner(offer.getDateEnd());
        tfname.setText(offer.getName());
        tfvalue.setText(String.valueOf(offer.getValue()));
        category.setSelectedItem(offer.getCategory());
        // endregion

        // region setBounds
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 540, 60);
        pnMain.setBounds(0, 0, 540, 400);
// Dòng đầu: ID và Tên ưu đãi
        lbId.setBounds(50, 80, 200, 20);
        tfId.setBounds(50, 100, 200, 30);

        lbnameopffer.setBounds(270, 80, 200, 20);
        tfname.setBounds(270, 100, 200, 30);

// Dòng 2: Ngày bắt đầu và kết thúc
        lbStartDate.setBounds(50, 140, 200, 20);
        spStartDate.setBounds(50, 160, 200, 30);

        lbEndDate.setBounds(270, 140, 200, 20);
        spEndDate.setBounds(270, 160, 200, 30);

// Dòng 3: Loại và Giá trị
        lbcategory.setBounds(50, 200, 200, 20);
        category.setBounds(50, 220, 200, 30);

        lbvalue.setBounds(270, 200, 200, 20);
        tfvalue.setBounds(270, 220, 200, 30);
// Nút
        btnSave.setBounds(100, 290, 150, 40);
        btnEsc.setBounds(270, 290, 150, 40);

        // endregion

        btnEsc.addActionListener(_ -> dispose());
        btnSave.addActionListener(_ -> {
            OfferDTO offerNew = new OfferDTO(Integer.parseInt(tfId.getText()),tfname.getText(), spStartDate.getMyDate(), spEndDate.getMyDate(),(String) category.getSelectedItem(),Integer.parseInt(tfvalue.getText()));
            try{
                OfferBUS.getInstance().update(offerNew);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadOffer();
                dispose();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(lbHeader);
        add(lbId);
        add(tfId);
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
        setVisible(true);
    }
}
