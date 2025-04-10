package GUI.JDialog;

import BUS.OfferBUS;
import BUS.OfferProductBUS;
import Components.*;
import DTO.OfferDTO;
import DTO.OfferProductDTO;
import GUI.JPanel.pnOfferProduct;

import javax.swing.*;
import java.awt.*;

public class dlEditOfferProduct extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Sửa thông tin giảm giá sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);
    JLabel lbId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã số*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbStartDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Phần trăm giảm giá*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbEndDate = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Thời gian giảm giá*", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfId = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JSpinner spDiscount = new MyJSpinner(1, 1, 100, 1);
    JComboBox<OfferDTO> cbTime = new MyJComboBox<>(new OfferDTO[]{}, 12);

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    public dlEditOfferProduct(pnOfferProduct parentPanel, OfferProductDTO parentOfferProduct) {
        super((JFrame) SwingUtilities.getWindowAncestor(parentPanel), true);
        setTitle("Sửa thông tin giảm giá sản phẩm");
        setSize(540, 370);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region setBounds
        pnMain.setBounds(0, 0, 540, 370);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 540, 60);
        lbId.setBounds(50, 80, 420, 20);
        tfId.setBounds(50, 100, 420, 30);
        lbStartDate.setBounds(50, 150, 200, 20);
        spDiscount.setBounds(50, 170, 200, 30);
        lbEndDate.setBounds(270, 150, 200, 20);
        cbTime.setBounds(270, 170, 200, 30);
        btnSave.setBounds(100, 230, 150, 40);
        btnEsc.setBounds(270, 230, 150, 40);
        // endregion
        // region setText
        tfId.setText(String.valueOf(parentOfferProduct.getId()));
        spDiscount.setValue(parentOfferProduct.getDiscount());
        for(OfferDTO o: OfferBUS.getInstance().getList()){
            cbTime.addItem(o);
            if(o.getId()==parentOfferProduct.getOffer().getId())
                cbTime.setSelectedItem(o);
        }
        // endregion
        btnEsc.addActionListener(_ -> dispose());
        btnSave.addActionListener(_ -> {
            OfferDTO offer = (OfferDTO) cbTime.getSelectedItem();
            OfferProductDTO offerProduct = new OfferProductDTO(parentOfferProduct.getId(), offer, Integer.parseInt(spDiscount.getValue().toString()));
            if (OfferProductBUS.getInstance().update(offerProduct)) {
                JOptionPane.showMessageDialog(this, "Sửa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadOfferProduct();
            } else {
                JOptionPane.showMessageDialog(this, OfferProductBUS.getInstance().getError(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // region ADD
        add(lbHeader);
        add(lbId);
        add(tfId);
        add(lbStartDate);
        add(spDiscount);
        add(lbEndDate);
        add(cbTime);
        add(btnSave);
        add(btnEsc);
        add(pnMain);
        // endregion

        setVisible(true);
    }
}
