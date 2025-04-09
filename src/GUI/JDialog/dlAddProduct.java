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
import java.util.Objects;

public class dlAddProduct extends JDialog {
    JPanel pnMain = new MyJPanel(MyColor.White);
    JLabel lbHeader = new MyJLabel(Font.BOLD, 24, MyColor.White, "Thêm sản phẩm", SwingConstants.CENTER, SwingConstants.CENTER);

    JLabel lbName = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Tên*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbType = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Loại*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbDiscount = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Phần trăm giảm giá*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbDiscountTime = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Thời gian giảm giá*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbPrice = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Giá bán*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbUnit = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Đơn vị*", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbDetail = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "Thông tin chi tiết", SwingConstants.LEFT, SwingConstants.CENTER);

    JTextField tfName = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<TypeProductDTO> cbType = new MyJComboBox<>(new TypeProductDTO[]{}, 12);
    JComboBox<OfferProductDTO> cbDiscount = new MyJComboBox<>(new OfferProductDTO[]{},12);
    JComboBox<OfferDTO> cbOffer = new MyJComboBox<>(new OfferDTO[]{},12);
    JSpinner snPrice = new MyJSpinner(100, 100, 1000000000, 100);
    JTextField tfUnit = new MyJTextFieldInput(Font.PLAIN, 14, true);
    MyJTextArea taDetail = new MyJTextArea();

    JButton btnSave = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Green, MyColor.LightGreen, "Xác nhận", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEsc = new MyJButton(Font.BOLD, 14, MyColor.White, MyColor.Red, MyColor.LightRed, "Hủy", SwingConstants.CENTER, SwingConstants.CENTER);

    JDialog dialog = this;
    int posCbDiscount = 0;
    public dlAddProduct(fManage parentFrame, pnProduct parentPanel) {
        super(parentFrame,true);
        setTitle("Thêm sản phẩm");
        setSize(760,440);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // region SET TEXT
        for(TypeProductDTO type: TypeProductBUS.getInstance().getList()) cbType.addItem(type);
        for(OfferProductDTO offerProduct: OfferProductBUS.getInstance().getListDiscount()) cbDiscount.addItem(offerProduct);
        for(OfferDTO offer: OfferBUS.getInstance().getListByOfferProduct((OfferProductDTO) cbDiscount.getSelectedItem())) cbOffer.addItem(offer);
        // endregion
        // region SET BOUNDS
        pnMain.setBounds(0,0,760,440);
        lbName.setBounds(50,80,200,20);
        tfName.setBounds(50,100,200,30);
        lbType.setBounds(270,80,200,20);
        cbType.setBounds(270,100,200,30);

        lbDiscount.setBounds(50,150,200,20);
        cbDiscount.setBounds(50,170,200,30);
        lbDiscountTime.setBounds(270,150,200,20);
        cbOffer.setBounds(270, 170, 200, 30);

        lbPrice.setBounds(50,220,200,20);
        lbUnit.setBounds(270,220,200,20);
        snPrice.setBounds(50,240,200,30);
        tfUnit.setBounds(270,240,200,30);

        lbDetail.setBounds(490,80,200,20);
        taDetail.sp.setBounds(490, 100, 200, 170);

        btnSave.setBounds(220,300,150,40);
        btnEsc.setBounds(390,300,150,40);
        lbHeader.setOpaque(true);
        lbHeader.setBackground(MyColor.DarkBlue);
        lbHeader.setBounds(0,0,760,60);
        // endregion
        // region Event
        cbDiscount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = cbDiscount.getSelectedIndex();
                if(i!=posCbDiscount) {
                    posCbDiscount = i;
                    cbOffer.removeAllItems();
                    for(OfferDTO offer: OfferBUS.getInstance().getListByOfferProduct((OfferProductDTO) cbDiscount.getSelectedItem()))
                        cbOffer.addItem(offer);
                }
            }
        });
        btnEsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TypeProductDTO typeSelected = (TypeProductDTO) cbType.getSelectedItem();
                OfferProductDTO offerProductSelected = (OfferProductDTO) cbDiscount.getSelectedItem();
                OfferDTO offerSelected = (OfferDTO) cbOffer.getSelectedItem();

                int idProductType = typeSelected.getId();
                String detail = taDetail.getText();
                int idOfferProduct = (offerProductSelected.getId()==0?0:OfferProductBUS.getInstance().getIdBy(offerProductSelected.getDiscount(), offerSelected.getId()));
                String name = tfName.getText();
                double price = Double.parseDouble(snPrice.getValue().toString());
                String unit = tfUnit.getText();
                int quantity = 0;
                if(ProductBUS.getInstance().add(idProductType, detail, idOfferProduct, name, price, unit, quantity)){
                    JOptionPane.showMessageDialog(dialog, "Thêm thông tin sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    parentPanel.loadProduct();
                    dispose();
                }
                else JOptionPane.showMessageDialog(dialog, "Lỗi: " + ProductBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        // endregion11
        // region ADD
        add(lbName);
        add(tfName);
        add(lbType);
        add(cbType);
        add(lbPrice);
        add(lbUnit);
        add(snPrice);
        add(tfUnit);
        add(lbDiscount);
        add(lbDiscountTime);
        add(cbDiscount);
        add(cbOffer);
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
