package GUI.JPanel;

import BUS.ProductBUS;
import BUS.TypeProductBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddProduct;
import GUI.JDialog.dlDetailProduct;
import GUI.JDialog.dlEditProduct;
import GUI.JFrame.fManage;
import com.mysql.cj.protocol.Message;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class pnProduct extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnIn = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Nhập<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDetail = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Chi tiết</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Tên", "Mã", "Loại", "Giảm giá", "Giá bán", "Đơn vị", "Số lượng"}, 12);

    MyJTable tbProduct = new MyJTable(new String[]{"Mã", "Loại", "Giảm giá", "Tên sản phẩm", "Giá bán", "Đơn vị", "Số lượng"});

    pnProduct thisPanel = this;
    int posSelectedCB = 0;

    public pnProduct(fManage frame) {
        setLayout(null);
        setBackground(MyColor.LightGray);
        // region SET BOUNDS
        pnHeader.setBounds(0,0,970, 90);
        pnFunc.setBounds(0,0,440,90);
        btnAdd.setBounds(15,20,60,60);
        btnEdit.setBounds(85,20,60,60);
        btnDelete.setBounds(155,20,60,60);
        btnIn.setBounds(225,20,60,60);
        btnOut.setBounds(295,20,60,60);
        btnDetail.setBounds(365, 20, 60,60);
        pnSearch.setBounds(470,0,500,90);
        cbSearch.setBounds(485, 30, 150, 30);
        tfSearch.setBounds(645, 30, 200, 30);
        btnRefresh.setBounds(855,30,100,30);
        pnFooter.setBounds(0,100,970, 650);
        tbProduct.scrPn.setBounds(0,100,970,650);
        // endregion
        // region EVENT CHO PANEL NÀY
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                btnRefresh.doClick();
            }
        });
        // endregion
        // region EVEN
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dlAddProduct(frame, thisPanel);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tbProduct.getSelectedRow();
                if (i>=0){
                    int id = Integer.parseInt(tbProduct.getFirstColumn(i));
                    ProductDTO product = ProductBUS.getInstance().getItemById(id);
                    new dlEditProduct(frame, thisPanel, product);
                }
                else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tbProduct.getSelectedRow();
                if(i>=0){
                    int id = Integer.parseInt(tbProduct.getFirstColumn(i));
                    ProductDTO product = ProductBUS.getInstance().getItemById(id);
                    if(ProductBUS.getInstance().delete(product)){
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadProduct();
                        textChange();
                    }
                    else JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + ProductBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);

            }
        });
        btnDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tbProduct.getSelectedRow();
                if (i>=0){
                    int id = Integer.parseInt(tbProduct.getFirstColumn(i));
                    ProductDTO product = ProductBUS.getInstance().getItemById(id);
                    new dlDetailProduct(frame, thisPanel, product);
                }
                else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xem!!", "Thông báo", JOptionPane.ERROR_MESSAGE);

            }
        });
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfSearch.setText("");
                cbSearch.setSelectedIndex(0);
                loadProduct();
            }
        });
        btnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object[]> list = tbProduct.ImportExel(4);
                if(list==null) return;
                String error = null;
                int success = 0;
                for (Object[] ob : list) {
                    int idProductType = TypeProductBUS.getInstance().getItemByName(ob[0].toString()).getId();
                    String detail = "";
                    int idOfferProduct = 0;
                    String name = ob[1].toString();
                    double price = Double.parseDouble(ob[2].toString().replace("đ", "").replace(",", ""));
                    String unit = ob[3].toString();

                    if(ProductBUS.getInstance().add(idProductType, detail, idOfferProduct, name, price, unit, 0)){
                        success++;
                    }
                    else error += ("\n" + ProductBUS.getInstance().getError());
                }

                JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + success + " sản phẩm");
                if(error!=null) JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + error);
                loadProduct();
                textChange();
            }
        });
        btnOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbProduct.ExportExel("Danh sách sản phẩm");
            }
        });
        cbSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = cbSearch.getSelectedIndex();
                if(posSelectedCB !=i){
                    tfSearch.setText("");
                }
            }
        });
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textChange();}
            public void removeUpdate(DocumentEvent e) {textChange();}
            public void changedUpdate(DocumentEvent e) {textChange();}
        });
        // endregion
        // region ADD
        add(btnAdd);
        add(btnEdit);
        add(btnDelete);
        add(btnIn);
        add(btnOut);
        add(btnDetail);
        add(pnFunc);
        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(pnSearch);
        add(pnHeader);
        add(tbProduct.scrPn);
        add(pnFooter);
        // endregion
    }

    public void loadProduct()  {
        tbProduct.dftbModel.setRowCount(0);
        for(ProductDTO product: ProductBUS.getInstance().getList())
            tbProduct.dftbModel.addRow(product.getRowObjects());
    }

    public void textChange(){
        tbProduct.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(ProductDTO product: ProductBUS.getInstance().getListSearch(col, txt))
            tbProduct.dftbModel.addRow(product.getRowObjects());
    }
}
