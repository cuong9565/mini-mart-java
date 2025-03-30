package BUS;

import DAO.Discount_DAO;
import DTO.Discount_DTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Discount_BUS {
    private Discount_DAO discountDao;
    private DefaultTableModel model;
    private List<Discount_DTO> discountlist;

    public Discount_BUS(DefaultTableModel model) {
        this.model = model;
        this.discountDao = new Discount_DAO();
        this.discountlist = discountDao.GetDiscountData();
    }

    public void loadDiscountData() {
        model.setRowCount(0);
        List<Discount_DTO> disList = discountDao.GetDiscountData();
        int stt =1 ;
        for (Discount_DTO dis : disList) {
            model.addRow(new Object[]{
                    stt++,
                    dis.getId(),
                    dis.getType(),
                    dis.getDatecreate(),
                    dis.getDatedue(),
                    dis.getQuanty(),
                    dis.getStatus()
            });
        }
    }
    //
    public void ShowAddDig(Component parent) {
        JDialog adddig = new JDialog(SwingUtilities.getWindowAncestor(parent), "Thêm Giảm Giá", Dialog.ModalityType.APPLICATION_MODAL);
        adddig.setLayout(new GridLayout(7, 2, 10, 10));
        adddig.setSize(300, 400);
        adddig.setLocationRelativeTo(parent);
        JTextField txttg = new JTextField();
        JTextField txtsl = new JTextField();
        JComboBox<String> cbtype = new JComboBox<>(new String[]{"Giảm giá bill", "Giảm giá sản phẩm"});
        JComboBox<String> cbactive = new JComboBox<>(new String[]{"Áp dụng", "Khóa"});
        adddig.add(new JLabel("Loại giảm giá:"));
        adddig.add(cbtype);
        adddig.add(new JLabel("Thời gian giảm giá (ngày):"));
        adddig.add(txttg);
        adddig.add(new JLabel("Số lượng:"));
        adddig.add(txtsl);
        adddig.add(new JLabel("Trạng thái:"));
        adddig.add(cbactive);
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(255, 69, 58));
        btnCancel.setForeground(Color.WHITE);
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(50, 168, 82));
        btnSave.setForeground(Color.WHITE);
        adddig.add(btnSave);
        adddig.add(btnCancel);

        // Xử lý sự kiện
        btnSave.addActionListener(e -> {
            try {
                if (txttg.getText().trim().isEmpty() || txtsl.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(adddig, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                int soNgay = Integer.parseInt(txttg.getText().trim());
                int soLuong = Integer.parseInt(txtsl.getText().trim());

                if (soNgay < 0 || soLuong < 0) {
                    JOptionPane.showMessageDialog(adddig, "Thời gian và số lượng phải là số dương!");
                    return;
                }
                Date datecreate = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(datecreate);
                cal.add(Calendar.DAY_OF_MONTH, soNgay);
                Date datedue = cal.getTime();

                Discount_DTO disdto = new Discount_DTO(
                        "",
                        datecreate,
                        datedue,
                        (String) cbtype.getSelectedItem(),
                      Integer.parseInt(txtsl.getText()),
                        (String) cbactive.getSelectedItem()
                );
                discountDao.addDiscount(disdto);
                JOptionPane.showMessageDialog(adddig, "Lưu thành công!");

                // Đóng hộp thoại sau khi lưu
                adddig.dispose();
                loadDiscountData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adddig, "Thời gian và số lượng phải là số nguyên!");
            }
        });

        btnCancel.addActionListener(e -> adddig.dispose());
        adddig.setVisible(true);
    }



    public void ShowEditDig(Component parent, int isRow) {
        if (isRow < 0) {
            JOptionPane.showMessageDialog(parent, "Vui lòng chọn mã để sửa");
            return;
        }

        JDialog editdig = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), "Sửa mã giảm giá", true);
        editdig.setLayout(new GridLayout(9, 2, 10, 10));
        editdig.setSize(400, 500);
        editdig.setLocationRelativeTo(parent);

        // Chọn ngày bằng JSpinner
        SpinnerDateModel startModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateStartSpinner = new JSpinner(startModel);
        dateStartSpinner.setEditor(new JSpinner.DateEditor(dateStartSpinner, "dd/MM/yyyy"));

        SpinnerDateModel endModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateEndSpinner = new JSpinner(endModel);
        dateEndSpinner.setEditor(new JSpinner.DateEditor(dateEndSpinner, "dd/MM/yyyy"));

        JTextField txtsl = new JTextField();
        JTextField txtExtendDays = new JTextField();

        JComboBox<String> cbtype = new JComboBox<>(new String[]{"Giảm giá bill", "Giảm giá sản phẩm"});
        JComboBox<String> cbactive = new JComboBox<>(new String[]{"Áp dụng", "Khóa"});

        editdig.add(new JLabel("Loại giảm giá:"));
        editdig.add(cbtype);
        editdig.add(new JLabel("Ngày bắt đầu:"));
        editdig.add(dateStartSpinner);
        editdig.add(new JLabel("Ngày kết thúc:"));
        editdig.add(dateEndSpinner);
        editdig.add(new JLabel("Gia hạn (số ngày):"));
        editdig.add(txtExtendDays);
        editdig.add(new JLabel("Số lượng:"));
        editdig.add(txtsl);
        editdig.add(new JLabel("Trạng thái:"));
        editdig.add(cbactive);

        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(255, 69, 58));
        btnCancel.setForeground(Color.WHITE);
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(50, 168, 82));
        btnSave.setForeground(Color.WHITE);

        editdig.add(btnSave);
        editdig.add(btnCancel);

        // Xử lý sự kiện lưu
        btnSave.addActionListener(e -> {
            try {
                Date startDate = (Date) dateStartSpinner.getValue();
                Date endDate = (Date) dateEndSpinner.getValue();
                int soLuong = Integer.parseInt(txtsl.getText().trim());

                if (startDate.after(endDate)) {
                    JOptionPane.showMessageDialog(editdig, "Ngày kết thúc phải sau ngày bắt đầu!");
                    return;
                }

                if (soLuong < 0) {
                    JOptionPane.showMessageDialog(editdig, "Số lượng phải là số dương!");
                    return;
                }

                int extendDays = 0;
                if (!txtExtendDays.getText().trim().isEmpty()) {
                    extendDays = Integer.parseInt(txtExtendDays.getText().trim());
                    if (extendDays < 0) {
                        JOptionPane.showMessageDialog(editdig, "Số ngày gia hạn phải >= 0!");
                        return;
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(endDate);
                    cal.add(Calendar.DAY_OF_MONTH, extendDays);
                    endDate = cal.getTime();
                }

                // Lưu dữ liệu
                Discount_DTO disdto = new Discount_DTO(
                        "",
                        startDate,
                        endDate,
                        (String) cbtype.getSelectedItem(),
                        soLuong,
                        (String) cbactive.getSelectedItem()
                );

                discountDao.updateDis(disdto);

                JOptionPane.showMessageDialog(editdig, "Lưu thành công!");
                editdig.dispose();
                loadDiscountData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editdig, "Số lượng và số ngày gia hạn phải là số nguyên hợp lệ!");
            }
        });


        btnCancel.addActionListener(e -> editdig.dispose());
        editdig.setVisible(true);
    }

    public void DeleDig(Component parent, int row) {
        if (row < 0) {
            JOptionPane.showMessageDialog(parent, "Vui lòng chọn mã giảm giá để xóa!");
            return;
        }

        String id = (String) model.getValueAt(row, 1);
        int confirm = JOptionPane.showConfirmDialog(parent,
                "Bạn có chắc chắn muốn xóa mã giảm giá này?", "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            discountDao.Deledis(id);
            loadDiscountData();
            JOptionPane.showMessageDialog(parent, "Xóa thành công!");
        }
    }

}
