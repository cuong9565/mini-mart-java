package GUI.JPanel;

import BUS.TypeProductBUS;
import Components.*;
import DTO.*;
import GUI.JDialog.dlAddTypeProduct;
import GUI.JDialog.dlEditTypeProduct;
import GUI.JFrame.fManage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class pnTypeProduct extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnAdd = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#4CAF50"), Color.decode("#7ED482"), "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnEdit = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#FF9800"), Color.decode("#FFD966"), "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnIn = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Nhập<br>Excel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Excel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã số", "Tên"}, 12);

    MyJTable tbTypeProduct = new MyJTable(new String[]{"Mã số", "Tên"}, new int[]{50}, new int[]{1}, new int[]{});

    pnTypeProduct thisPanel = this;

    public pnTypeProduct(fManage frame) {
        setLayout(null);
        setBackground(MyColor.White);

        // region SET BOUNDS
        pnHeader.setBounds(0,0,1170, 90);
        pnFunc.setBounds(0,0,370,90);
        btnAdd.setBounds(15,20,60,60);
        btnEdit.setBounds(85,20,60,60);
        btnDelete.setBounds(155,20,60,60);
        btnIn.setBounds(225,20,60,60);
        btnOut.setBounds(295,20,60,60);
        pnSearch.setBounds(670,0,500,90);
        cbSearch.setBounds(685, 30, 150, 30);
        tfSearch.setBounds(845, 30, 200, 30);
        btnRefresh.setBounds(1055,30,100,30);
        pnFooter.setBounds(0,100,1170, 650);
        tbTypeProduct.scrPn.setBounds(0,100,1170,650);
        // endregion
        // region EVENT CHO PANEL NÀY
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadTypeProduct();}
        });
        // endregion
        // region EVEN
        btnAdd.addActionListener(_ -> new dlAddTypeProduct(frame, thisPanel));
        btnEdit.addActionListener(_ -> {
            int i = tbTypeProduct.getSelectedRow();
            if (i >=0){
                int id = Integer.parseInt(tbTypeProduct.getFirstColumn(i));
                TypeProductDTO product = TypeProductBUS.getInstance().getItemById(id);
                new dlEditTypeProduct(frame, thisPanel, product);
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần sửa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });
        btnDelete.addActionListener(_ -> {
            int i = tbTypeProduct.getSelectedRow();
            if (i>=0){
                int choose = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if(choose == JOptionPane.YES_OPTION){
                    try {
                        int id = Integer.parseInt(tbTypeProduct.getFirstColumn(i));
                        TypeProductDTO product = TypeProductBUS.getInstance().getItemById(id);
                        TypeProductBUS.getInstance().delete(product);
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadTypeProduct();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(thisPanel, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        });
        btnRefresh.addActionListener(_ -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadTypeProduct();
        });
        btnIn.addActionListener(_ -> {
            List<Object[]> list = tbTypeProduct.ImportExel(1);
            if(list==null) return;
            int choose = JOptionPane.showConfirmDialog(thisPanel, "Bạn có chắc chắn muốn thêm?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(choose == JOptionPane.YES_OPTION){
                int success = 0;
                StringBuilder error = new StringBuilder();
                try {
                    for (Object[] ob : list){
                        try {
                            TypeProductBUS.getInstance().add(new TypeProductDTO(-1, ob[0].toString()));
                            success++;
                        } catch (Exception e) {
                            error.append(e.getMessage()).append("\n");
                        }
                    }
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                if(!error.isEmpty())
                    JOptionPane.showMessageDialog(thisPanel, error.toString(), "Lỗi", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(thisPanel, "Đã thêm " + success + " loại sản phẩm");
                loadTypeProduct();
            }
        });
        btnOut.addActionListener(_ -> tbTypeProduct.ExportExel("Danh sách loại sản phẩm"));
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
        // region ADD
        add(btnAdd);
        add(btnEdit);
        add(btnDelete);
        add(btnIn);
        add(btnOut);
        add(pnFunc);
        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(pnSearch);
        add(pnHeader);
        add(tbTypeProduct.scrPn);
        add(pnFooter);
        // endregion

    }

    public void loadTypeProduct()  {
        TypeProductBUS.getInstance().getList();
        textChange();
    }

    public void textChange(){
        tbTypeProduct.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(TypeProductDTO product: TypeProductBUS.getInstance().getListBy(col, txt))
            tbTypeProduct.dftbModel.addRow(product.getObjects());
    }
}
