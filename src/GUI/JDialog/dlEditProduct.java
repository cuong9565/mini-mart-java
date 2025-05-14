package GUI.JDialog;

import BUS.*;
import Components.*;
import DTO.*;
import GUI.JFrame.fManage;
import GUI.JPanel.pnProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dlEditProduct extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Sửa thông tin sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);

    JLabel lbId = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mã sản phẩm*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbQuantity = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Số lượng", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbType = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Loại*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbDiscount = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Thêm giảm giá*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPrice = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giá bán*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbUnit = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Đơn vị*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbDetail = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Chi tiết sản phẩm", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbValueOffer = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Phần trăm", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfId = new MyJTextFieldInput(Font.PLAIN, 14, false);
    JSpinner snQuantity = new MyJSpinner(0, 0, 1000000000, 1);
    JTextField tfName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<TypeProductDTO> cbType = new MyJComboBox<>(new TypeProductDTO[]{}, 12);
    JComboBox<OfferDTO> cbOffer = new MyJComboBox<>(new OfferDTO[]{}, 12);
    JSpinner snPrice = new MyJSpinner(100, 100, 1000000000, 100);
    JTextField tfUnit = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfValueOffer = new MyJTextFieldInput(Font.PLAIN, 14, false);
    MyJTextArea taDetail = new MyJTextArea();

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;

    public dlEditProduct(fManage parentFrame, pnProduct parentPanel, ProductDTO productDTO) {
        super(parentFrame, true);
        setTitle("Sửa thông tin sản phẩm");
        setSize(760, 540);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region SET TEXT
        tfId.setText(String.valueOf(productDTO.getId()));
        snQuantity.setValue(productDTO.getQuantity());
        tfName.setText(productDTO.getName());
        tfUnit.setText(productDTO.getUnit());
        taDetail.setText(productDTO.getDetail().getText());
        snPrice.setValue((int) productDTO.getPrice());

        for (TypeProductDTO type : TypeProductBUS.getInstance().getList()) {
            cbType.addItem(type);
            if (type.getId() == productDTO.getType().getId()) cbType.setSelectedItem(type);
        }

        OfferDTO defaultOffer = new OfferDTO();
        defaultOffer.setName("Chọn đê");
        cbOffer.addItem(defaultOffer);
        for (OfferDTO offerDTO : OfferBUS.getInstance().getList()) {
            if (!offerDTO.getStatus(offerDTO.getDateStart().getSqlDate(), offerDTO.getDateEnd().getSqlDate()).equals("Đã kết thúc")) {
                if (offerDTO.getCategory().equals("Giảm giá sản phẩm")) {
                    cbOffer.addItem(offerDTO);
                    if (offerDTO.getId() == productDTO.getOfferProduct().getOffer().getId()) {
                        cbOffer.setSelectedItem(offerDTO);
                        tfValueOffer.setText(String.valueOf(offerDTO.getValue()) + "%");
                    }
                }
            }
        }

        // endregion
        // region SET BOUNDS
        pnMain.setBounds(0, 0, 760, 440);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0, 0, 760, 60);

        lbId.setBounds(50, 80, 200, 20);
        tfId.setBounds(50, 100, 200, 30);
        lbQuantity.setBounds(270, 80, 200, 20);
        snQuantity.setBounds(270, 100, 200, 30);

        lbName.setBounds(50, 150, 200, 20);
        tfName.setBounds(50, 170, 200, 30);
        lbType.setBounds(270, 150, 200, 20);
        cbType.setBounds(270, 170, 200, 30);

        lbDiscount.setBounds(50, 220, 200, 20);
        cbOffer.setBounds(50, 240, 200, 30);
        lbValueOffer.setBounds(270, 220, 200, 20);
        tfValueOffer.setBounds(270, 240, 200, 30);

        lbPrice.setBounds(50, 290, 200, 20);
        lbUnit.setBounds(270, 290, 200, 20);
        snPrice.setBounds(50, 310, 200, 30);
        tfUnit.setBounds(270, 310, 200, 30);

        lbDetail.setBounds(490, 80, 200, 20);
        taDetail.sp.setBounds(490, 100, 200, 170);

        btnSave.setBounds(220, 370, 150, 40);
        btnEsc.setBounds(390, 370, 150, 40);
        // endregion

        // region EVENT
        cbOffer.addActionListener(e -> {
            if (cbOffer.getSelectedIndex() != 0) {
                OfferDTO selectedOffer = (OfferDTO) cbOffer.getSelectedItem();
                tfValueOffer.setText(String.valueOf(selectedOffer.getValue()) + "%");
            } else {
                tfValueOffer.setText("");
            }
        });

        btnEsc.addActionListener(_ -> dialog.dispose());
        btnSave.addActionListener(_ -> {
            TypeProductDTO typeSelected = (TypeProductDTO) cbType.getSelectedItem();
            OfferDTO offerSelected = (OfferDTO) cbOffer.getSelectedItem();

            int id = productDTO.getId();
            int idProductType = typeSelected.getId();
            String detail = taDetail.getText();
            int idOffer = offerSelected.getId();
            String name = tfName.getText();
            double price = Double.parseDouble(snPrice.getValue().toString());
            String unit = tfUnit.getText();
            int quantity = Integer.parseInt(snQuantity.getValue().toString());

            try {
                ProductBUS.getInstance().update(id, idProductType,detail, idOffer, name, price, unit, quantity);
                JOptionPane.showMessageDialog(dialog, "Thay đổi thông tin sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadProduct();
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(dialog, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // endregion

        // region ADD
        add(lbId);
        add(tfId);
        add(lbQuantity);
        add(snQuantity);
        add(lbName);
        add(tfName);
        add(lbType);
        add(cbType);
        add(lbDiscount);
        add(cbOffer);
        add(lbValueOffer);
        add(tfValueOffer);
        add(lbPrice);
        add(snPrice);
        add(lbUnit);
        add(tfUnit);
        add(lbDetail);
        add(taDetail.sp);
        add(btnSave);
        add(btnEsc);
        add(lbHeader);
        add(pnMain);
        // endregion

        setVisible(true);
    }
}