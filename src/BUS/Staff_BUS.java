package BUS;
import javax.swing.table.TableRowSorter;
import DAO.Staff_DAO;
import DTO.Staff_DTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Staff_BUS {
    private Staff_DAO staffDAO;
    private DefaultTableModel model;
    public static List<Staff_DTO> staffList;

    public Staff_BUS(DefaultTableModel model) {
        this.staffDAO = new Staff_DAO();
        this.model = model;
        staffList = staffDAO.getAllStaff();
    }

    // Thêm nhân viên
    public boolean addStaff(Staff_DTO staff) {

        if (staff.getLastName().isEmpty() || staff.getFirstName().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Họ và tên không được để trống!");
            return false;
        }
        if (staff.getSalary() < 0) {
            JOptionPane.showMessageDialog(null, "Lương không được âm!");
            return false;
        }
        if (!staff.getPhone().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải là 10 chữ số!");
            return false;
        }
        return staffDAO.addStaff(staff);
    }

    // Cập nhật nhân viên
    public boolean updateStaff(Staff_DTO staff) {
        if (staff.getLastName().isEmpty() || staff.getFirstName().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Họ và tên không được để trống!");
            return false;
        }
        if (staff.getSalary() < 0) {
            JOptionPane.showMessageDialog(null, "Lương không được âm!");
            return false;
        }
        if (!staff.getPhone().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải là 10 chữ số!");
            return false;
        }
        return staffDAO.updateStaff(staff);
    }

    // Khóa nhân viên
    public boolean lockStaff(String id,String status){
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên để khóa!");
            return false;
        }
        if(status.equals("Inactive")){
            return  staffDAO.lockStaff(id, "Active");
             }
       else {
           return staffDAO.lockStaff(id,"Inactive");
        }
    }

    // Load dữ liệu từ database vào bảng
    public void loadStaffData() {
        model.setRowCount(0); // Xóa dữ liệu cũ
      List<Staff_DTO> staffList = staffDAO.getAllStaff();
        for (Staff_DTO staff : staffList) {
            model.addRow(new Object[]{
                    staff.getId(),staff.getPhone(),staff.getPassword() ,staff.getFirstName(),staff.getLastName(), staff.getAddress(),
                    staff.getSalary(),  staff.getRole(),
                    staff.getStatus(),staff.getGender()});
        }
    }

    // Hiển thị dialog thêm nhân viên
    public void showAddDialog(Component parent) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), "Thêm nhân viên", true);
        dialog.setLayout(new GridLayout(10, 2, 10, 10)); // 10 rows for 9 fields + buttons
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(parent);

        JTextField txtPhone = new JTextField();
        JPasswordField txtPass = new JPasswordField();
        JTextField txtFirstName = new JTextField();
        JTextField txtLastName = new JTextField();
        JTextField txtAddress = new JTextField();
        JTextField txtSalary = new JTextField();
        JComboBox<String> cbRole = new JComboBox<>(new String[]{"Nhân viên", "Quản lý"});
        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Đóng", "Mở"});
        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Nam", "Nữ"});

        dialog.add(new JLabel("Số điện thoại:"));
        dialog.add(txtPhone);
        dialog.add(new JLabel("Mật khẩu:"));
        dialog.add(txtPass);
        dialog.add(new JLabel("Tên:"));
        dialog.add(txtFirstName);
        dialog.add(new JLabel("Họ:"));
        dialog.add(txtLastName);
        dialog.add(new JLabel("Giới tính:"));
        dialog.add(cbGender);
        dialog.add(new JLabel("Địa chỉ:"));
        dialog.add(txtAddress);
        dialog.add(new JLabel("Loại:"));
        dialog.add(cbRole);
        dialog.add(new JLabel("Lương:"));
        dialog.add(txtSalary);
        dialog.add(new JLabel("Trạng thái:"));
        dialog.add(cbStatus);

        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(255, 69, 58));
        btnCancel.setForeground(Color.WHITE);
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(50, 168, 82));
        btnSave.setForeground(Color.WHITE);

        dialog.add(btnSave);
        dialog.add(btnCancel);

        btnSave.addActionListener(e -> {
            try {
                Staff_DTO staff = new Staff_DTO(
                        "",
                        txtPhone.getText(),
                        new String(txtPass.getPassword()),
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        (String) cbGender.getSelectedItem(),
                        txtAddress.getText(),
                        (String) cbRole.getSelectedItem(),
                        Double.parseDouble(txtSalary.getText()),
                        (String) cbStatus.getSelectedItem()
                );
                if (addStaff(staff)) {
                    JOptionPane.showMessageDialog(dialog, "Thêm nhân viên thành công!");
                    loadStaffData();
                    dialog.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Lương phải là số!");
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }
    // Hiển thị dialog sửa nhân viên
    public void showEditDialog(Component parent, int selectedRow) {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(parent, "Vui lòng chọn nhân viên để sửa!");
            return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), "Sửa nhân viên", true);
        dialog.setLayout(new GridLayout(10, 2, 10, 10));
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(parent);

        JTextField txtId = new JTextField((String) model.getValueAt(selectedRow, 0));
        JTextField txtPhone = new JTextField((String) model.getValueAt(selectedRow, 1));
        JTextField txtFirstName = new JTextField((String) model.getValueAt(selectedRow, 4));
        JTextField txtLastName = new JTextField((String) model.getValueAt(selectedRow, 3));
        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cbGender.setSelectedItem(model.getValueAt(selectedRow, 9));
        JTextField txtAddress = new JTextField((String) model.getValueAt(selectedRow, 5));
        JComboBox<String> cbRole = new JComboBox<>(new String[]{"Nhân viên", "Quản lý"});
        cbRole.setSelectedItem(model.getValueAt(selectedRow, 7));
        JTextField txtSalary = new JTextField(model.getValueAt(selectedRow, 6).toString());
        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Đóng", "Mở"});
        cbStatus.setSelectedItem(model.getValueAt(selectedRow, 8));

        dialog.add(new JLabel("Số điện thoại:"));
        dialog.add(txtPhone);
        dialog.add(new JLabel("Họ:"));
        dialog.add(txtLastName);
        dialog.add(new JLabel("Tên:"));
        dialog.add(txtFirstName);
        dialog.add(new JLabel("Giới tính:"));
        dialog.add(cbGender);
        dialog.add(new JLabel("Địa chỉ:"));
        dialog.add(txtAddress);
        dialog.add(new JLabel("Loại:"));
        dialog.add(cbRole);
        dialog.add(new JLabel("Lương:"));
        dialog.add(txtSalary);
        dialog.add(new JLabel("Trạng thái:"));
        dialog.add(cbStatus);


        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(255, 69, 58));
        btnCancel.setForeground(Color.WHITE);
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(50, 168, 82));
        btnSave.setForeground(Color.WHITE);
        dialog.add(btnCancel);
        dialog.add(btnSave);


        btnSave.addActionListener(e -> {
            try {
                Staff_DTO staff = new Staff_DTO(
                        txtId.getText(),
                        txtPhone.getText(),
                        (String) model.getValueAt(selectedRow,2),
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        (String) cbGender.getSelectedItem(),
                        txtAddress.getText(),
                        (String) cbRole.getSelectedItem(),
                        Double.parseDouble(txtSalary.getText()),
                        (String) cbStatus.getSelectedItem()
                );
                if (updateStaff(staff)) {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật nhân viên thành công!");
                    loadStaffData();
                    dialog.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Lương phải là số!");
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    // Làm mới bảng
    public void refreshTable(JTextField txtSearch, TableRowSorter<DefaultTableModel> sorter, JComboBox<String> sortComboBox) {
        txtSearch.setText("Nhập mã NV hoặc tên...");
        sorter.setRowFilter(null);
        sortComboBox.setSelectedIndex(0);
        loadStaffData();
    }



}