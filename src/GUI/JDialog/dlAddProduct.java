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

public class dlAddProduct extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thêm sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);

    JLabel lbName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbType = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Loại*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbDiscount = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Thêm giảm giá", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPrice = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giá bán*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbUnit = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Đơn vị*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbDetail = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Mô tả sản phẩm", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbValueoffer = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Phần trăm", SwingConstants.LEFT, SwingConstants.CENTER);
    JTextField tfName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<TypeProductDTO> cbType = new MyJComboBox<>(new TypeProductDTO[]{}, 12);
    JComboBox<OfferProductDTO> cbDiscount = new MyJComboBox<>(new OfferProductDTO[]{},12);
    JComboBox<OfferDTO> cbOffer = new MyJComboBox<>(new OfferDTO[]{},12); //
    JSpinner snPrice = new MyJSpinner(100, 100, 1000000000, 100);
    JTextField tfUnit = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JTextField tfvalueoffer = new MyJTextFieldInput(Font.PLAIN, 14, true);
    MyJTextArea taDetail = new MyJTextArea();

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;
    public dlAddProduct(fManage parentFrame, pnProduct parentPanel) {
        super(parentFrame,true);
        setTitle("Thêm sản phẩm");
        setSize(760,440);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        tfvalueoffer.setEditable(false);
        OfferDTO chos = new OfferDTO(); chos.setName("Chọn đê");
        cbOffer.addItem(chos);
        for(OfferDTO offerDTO : OfferBUS.getInstance().getList()){
            if(!offerDTO.getStatus(offerDTO.getDateStart().getSqlDate(),offerDTO.getDateEnd().getSqlDate()).equals("Đã kết thúc")) {
                if (offerDTO.getCategory().equals("Giảm giá sản phẩm")) {
                    cbOffer.addItem(offerDTO);
                }
            }
        }
        cbOffer.addActionListener(e -> {
            if (cbOffer.getSelectedIndex()!=0) {
                OfferDTO selectedOffer = (OfferDTO) cbOffer.getSelectedItem();
                tfvalueoffer.setText(String.valueOf(selectedOffer.getValue())+"%");
            } else {
                tfvalueoffer.setText(""); // Clear nếu chọn option mặc định
            }
        });
        for(TypeProductDTO type: TypeProductBUS.getInstance().getList()) cbType.addItem(type);
        // region setBounds
        pnMain.setBounds(0,0,760,440);
        lbName.setBounds(50,80,200,20);
        tfName.setBounds(50,100,200,30);
        lbType.setBounds(270,80,200,20);
        cbType.setBounds(270,100,200,30);

        lbDiscount.setBounds(50,220,200,20);
        cbOffer.setBounds(50,240,200,30);
        lbValueoffer.setBounds(270,220,200,20);
        tfvalueoffer.setBounds(270,240,200,30);
        lbPrice.setBounds(50,150,200,20);
        lbUnit.setBounds(270,150,200,20);
        snPrice.setBounds(50,170,200,30);
        tfUnit.setBounds(270,170,200,30);

        lbDetail.setBounds(490,80,200,20);
        taDetail.sp.setBounds(490, 100, 200, 170);

        btnSave.setBounds(220,300,150,40);
        btnEsc.setBounds(390,300,150,40);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,760,60);
        // endregion

        // region setEvent

        btnEsc.addActionListener(_ -> dialog.dispose());
        btnSave.addActionListener(_ -> {
            TypeProductDTO typeSelected = (TypeProductDTO) cbType.getSelectedItem();
            OfferDTO offerSelected = (OfferDTO) cbOffer.getSelectedItem();
            int idoffer = offerSelected.getId();
            int idProductType = typeSelected.getId();
            String detail = taDetail.getText();
            String name = tfName.getText();
            double price = Double.parseDouble(snPrice.getValue().toString());
            String unit = tfUnit.getText();
            int quantity = 0;
            try {
                ProductBUS.getInstance().add(idProductType, detail,idoffer, name, price, unit, quantity);
                JOptionPane.showMessageDialog(dialog, "Thêm thông tin sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                parentPanel.loadProduct();
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(dialog, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        // endregion
        // region add
        add(lbName);
        add(tfName);
        add(lbType);
        add(cbType);
        add(lbPrice);
        add(lbUnit);
        add(snPrice);
        add(tfUnit);
       add(lbDiscount);
      add(cbOffer);
        add(lbValueoffer);
        add(tfvalueoffer);
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
