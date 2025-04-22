package GUI.JPanel;

import BUS.ProductBUS;
import BUS.TypeProductBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddProduct;
import GUI.JDialog.dlDetailProduct;
import GUI.JDialog.dlEditProduct;
import GUI.JFrame.fManage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class pnProduct extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JPanel pnSearchadvance = new MyJPanel(MyColor.White, "Tìm kiếm Nâng Cao");
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnIn = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Nhập<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Exel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDetail = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Chi tiết</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã", "Loại", "Giảm giá", "Tên sản phẩm", "Giá bán", "Đơn vị", "Số lượng"}, 12);
    JComboBox<String>cbSearchad1 = new MyJComboBox<>(new String[]{"Giá từ","0-99k","Trên 99k",}, 12);
    JComboBox<String>cbSearchad2 = new MyJComboBox<>(new String[]{"Giảm giá","Có Giảm Giá","Không giảm giá"}, 12);
    JComboBox<String>cbSearchad3 = new MyJComboBox<>(new String[]{"Số Lượng","Sắp hết hàng(<10)","Còn nhiều(>50)"}, 12);
    JButton btnsearch = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Lọc", SwingConstants.CENTER, SwingConstants.CENTER);
    MyJTable tbProduct = new MyJTable(new String[]{"Mã", "Loại", "Giảm giá", "Tên sản phẩm", "Giá bán", "Đơn vị", "Số lượng"}, new int[]{30, 100, 100, 100, 100, 30}, new int[]{1, 2, 3}, new int[]{});
    pnProduct thisPanel = this;

    public pnProduct(fManage frame) {
        setLayout(null);
        setBackground(MyColor.White);
        // region SET BOUNDS
        pnHeader.setBounds(0,0,1170, 90);
        pnFunc.setBounds(0,0,440,90);
        btnAdd.setBounds(15,20,60,60);
        btnEdit.setBounds(85,20,60,60);
        btnDelete.setBounds(155,20,60,60);
        btnIn.setBounds(225,20,60,60);
        btnOut.setBounds(295,20,60,60);
        btnDetail.setBounds(365, 20, 60,60);
        pnSearch.setBounds(660,0,500,90);
        cbSearch.setBounds(675, 30, 150, 30);
        tfSearch.setBounds(835, 30, 200, 30);
        btnRefresh.setBounds(1045,30,100,30);
        pnFooter.setBounds(0,100,1170, 650);
        tbProduct.scrPn.setBounds(0,180,1170,550);

        pnSearchadvance.setBounds(0,90,440,90);
        cbSearchad1.setBounds(10, 120, 90, 35);
        cbSearchad2.setBounds(110, 120, 90, 35);
        cbSearchad3.setBounds(210, 120, 110, 35);
        btnsearch.setBounds(330,120,90,35);

        // endregion
        // region EVENT CHO PANEL NÀY
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadProduct();}
        });
        // endregion
        // region EVEN
        btnAdd.addActionListener(_->new dlAddProduct(frame, thisPanel));
        btnEdit.addActionListener(_->{
            int i = tbProduct.getSelectedRow();
            if (i>=0){
                int id = Integer.parseInt(tbProduct.getFirstColumn(i));
                ProductDTO product = ProductBUS.getInstance().getItemById(id);
                new dlEditProduct(frame, thisPanel, product);
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });
        btnDelete.addActionListener(_ -> {
            int i = tbProduct.getSelectedRow();
            if(i>=0){
                int id = Integer.parseInt(tbProduct.getFirstColumn(i));
                ProductDTO product = ProductBUS.getInstance().getItemById(id);
                if(ProductBUS.getInstance().delete(product)){
                    JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadProduct();
                }
                else JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + ProductBUS.getInstance().getError(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);

        });
        btnDetail.addActionListener(_ -> {
            int i = tbProduct.getSelectedRow();
            if (i>=0){
                int id = Integer.parseInt(tbProduct.getFirstColumn(i));
                ProductDTO product = ProductBUS.getInstance().getItemById(id);
                new dlDetailProduct(frame, thisPanel, product);
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xem!!", "Thông báo", JOptionPane.ERROR_MESSAGE);

        });
        btnRefresh.addActionListener(_ -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadProduct();
        });
        btnIn.addActionListener(_ -> {
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
                else{
                    error = ProductBUS.getInstance().getError();
                }
            }

            JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + success + " sản phẩm");
            if(error!=null) JOptionPane.showMessageDialog(thisPanel, "Lỗi: " + error);
            loadProduct();
        });
        btnOut.addActionListener(_ -> tbProduct.ExportExel("Danh sách sản phẩm"));
        cbSearch.addActionListener(_ -> textChange());
        tfSearch.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {textChange();}
        });
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textChange();}
            public void removeUpdate(DocumentEvent e) {textChange();}
            public void changedUpdate(DocumentEvent e) {textChange();}
        });
        btnsearch.addActionListener(e->{
         List<ProductDTO> newproduct = ProductBUS.getInstance().SearchAd(cbSearchad1.getSelectedIndex(),cbSearchad2.getSelectedIndex(),cbSearchad3.getSelectedIndex());
         if(newproduct.isEmpty()){
             JOptionPane.showMessageDialog(this,"Không có sản phẩm nào phù hợp !","Thông Báo", JOptionPane.INFORMATION_MESSAGE);
         }
         else {
             tbProduct.dftbModel.setRowCount(0);
             for (ProductDTO item : newproduct){
                 tbProduct.dftbModel.addRow(item.getRowObjects());
             }
         }
        });
        // endregion
        // region ADD
        add(cbSearchad1);
        add(cbSearchad2);
        add(cbSearchad3);
        add(btnsearch);
        add(pnSearchadvance);
        add(tbProduct.scrPn);
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
        add(pnFooter);
        // endregion
    }

    public void loadProduct()  {
        ProductBUS.getInstance().load();
        textChange();
    }

    public void textChange(){
        tbProduct.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(ProductDTO product: ProductBUS.getInstance().getListSearch(col, txt))
            tbProduct.dftbModel.addRow(product.getRowObjects());
    }
}
