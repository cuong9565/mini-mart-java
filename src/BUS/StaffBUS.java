package BUS;

import DAO.StaffDAO;
import DTO.StaffDTO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StaffBUS {
    private static StaffBUS instance = null;
    private List<StaffDTO>list = null;
    private StaffBUS() {}
    public static StaffBUS getInstance() {
        if (instance == null) instance = new StaffBUS();
        return instance;
    }

    // List
    public List<StaffDTO> load() {
        list = StaffDAO.getInstance().load();
        return list;
    }
    // tìm kiếm theo trường tt và textfiled
    public List<StaffDTO>getStaffListBy(int col, String txt) {
        List<StaffDTO> ls = new ArrayList<>();
        for (StaffDTO staff : list) {
            switch (col) {
                case 0: if(String.valueOf(staff.getId()).contains(txt)) ls.add(staff); break; // contains có chứa nội dung đó là dc
                case 1: if(staff.getLastName().contains(txt)) ls.add(staff); break;             // equals giống 100 %
                case 2: if(staff.getFirstName().contains(txt)) ls.add(staff); break;
                case 3: if(staff.getGender().contains(txt)) ls.add(staff); break;
                case 4: if(staff.getPhone().contains(txt)) ls.add(staff); break;
                case 5: if(staff.getAddress().contains(txt)) ls.add(staff); break;
                case 6: if(staff.getRole().contains(txt)) ls.add(staff); break;
                case 7: if(String.format("%,.0f", staff.getSalary()).contains(txt)) ls.add(staff); break;
                case 8: if(staff.getState().contains(txt)) ls.add(staff); break;
                case 9: if(staff.getPassword().contains(txt)) ls.add(staff); break;
            }
        }
        return ls;
    }
    // Add
    public void add(StaffDTO staff){
        try {
            if (staff.getFirstName().isEmpty() || staff.getLastName().isEmpty() || staff.getPhone().isEmpty() || staff.getAddress().isEmpty() || staff.getPassword().isEmpty())
                throw new RuntimeException("Không được để trống thông tin!!!");

            if(!staff.getPhone().matches("^0[0-9]{8,10}$"))
                throw new RuntimeException("Số điện thoại định dạng không hợp lệ!!!");

            if(checkSamePhone(staff.getPhone()))
                throw new RuntimeException(String.format("Số điện thoại %s đã tồn tại!!!", staff.getPhone()));
            StaffDAO.getInstance().add(staff);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    // update
    public void UpdateAccount(StaffDTO staff){
        try {
            if(staff.getLastName().isEmpty())
                throw new RuntimeException("Họ khách hàng không được để trống!!!");

            if(staff.getFirstName().isEmpty())
                throw new RuntimeException("Tên khách hàng không được để trống!!!");

            if(staff.getPhone().isEmpty())
                throw new RuntimeException("Số điện thoại không được để trống!!!");

            if(staff.getPassword().isEmpty())
                throw new RuntimeException("Mật khẩu không được để trống!!!");

            if(!staff.getPhone().matches("^0[0-9]{8,10}$"))
                throw new RuntimeException("Số điện thoại định dạng không hợp lệ!!!");
            StaffDTO currStaff = StaffDAO.getInstance().GetStaffById(staff.getId());
            if (checkSamePhone(staff.getPhone()) && !currStaff.getPhone().equals(staff.getPhone()))
                throw new RuntimeException(String.format("Số điện thoại %s đã tồn tại!!!", staff.getPhone()));
            StaffDAO.getInstance().UpdateAccount(staff);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Delete
    public void delete(int id){
        try {
            StaffDAO.getInstance().delete(id);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Item
    public StaffDTO getStaffById(int id) {
        return StaffDAO.getInstance().GetStaffById(id);
    }

    // Check
    public boolean checkSamePhone(String phone){
        return StaffDAO.getInstance().checkSamePhone(phone);
    }
    public int getNumberStaff(){
        return StaffDAO.getInstance().getNumberStaff();
    }
    public StaffDTO Login(String phone, String password){
        StaffDTO staff;
        try {
            if(phone.isEmpty())
                throw new RuntimeException("Số điện thoại không được để trống!!!");

            if(password.isEmpty())
                throw new RuntimeException("Mật khẩu không được để trống!!!");

            if(!phone.matches("^0[0-9]{8,10}$")){
                throw new RuntimeException("Số điện thoại định dạng không hợp lệ!!!");
            }

            staff = StaffDAO.getInstance().Login(phone, password);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return staff;
    }

    public void UpdateStaffPassword(int id, String currPassword, String newPassword, String confirmPassword){
        try {
            if(currPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty())
                throw new RuntimeException("Không được để trống thông tin!!!\nVui lòng nhập lại");

            if(!newPassword.equals(confirmPassword))
                throw new RuntimeException("Mật khẩu mới và mật khẩu xác nhận không khớp nhau!!!\nVui lòng nhập lại");

            StaffDAO.getInstance().CheckPassword(id, currPassword);
            StaffDAO.getInstance().UpdateStaffPassword(id, newPassword);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
