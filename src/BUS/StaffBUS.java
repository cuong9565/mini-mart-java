package BUS;

import DAO.StaffDAO;
import DTO.StaffDTO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StaffBUS {
    private static StaffBUS instance = null;
    private List<StaffDTO>list;
    private String error = null;
    private int NumLine = 0;

    private StaffBUS() {}
    public static StaffBUS getInstance() {
        if (instance == null) instance = new StaffBUS();
        return instance;
    }

    public StaffDTO Login(String phone, String password){
        StaffDTO staff;
        if(phone.isEmpty()){
            error = "Số điện thoại không được để trống!!!";
            return new StaffDTO();
        }
        if(password.isEmpty()){
            error = "Mật khẩu không được để trống!!!";
            return new StaffDTO();
        }
        if(!phone.matches("^0[0-9]{8,10}$")){
            error = "Số điện thoại định dạng không hợp lệ!!!";
            return new StaffDTO();
        }
        try {
            staff = StaffDAO.getInstance().Login(phone, password);
        }
        catch(Exception e){
            error = e.getMessage();
            return new StaffDTO();
        }
        return staff;
    }

    public boolean UpdateStaffPassword(int id, String currPassword, String newPassword, String confirmPassword){
        if(currPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()){
            error = "Không được để trống thông tin!!!\nVui lòng nhập lại";
            return false;
        }
        if(!newPassword.equals(confirmPassword)){
            error = "Mật khẩu mới và mật khẩu xác nhận không khớp nhau!!!\nVui lòng nhập lại";
            return false;
        }
        try {
            StaffDAO.getInstance().CheckPassword(id, currPassword);
        }
        catch (Exception e){
            error = e.getMessage();
            return false;
        }
        try {
            StaffDAO.getInstance().UpdateStaffPassword(id, newPassword);
        }
        catch (Exception e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean UpdateAccount(StaffDTO staff){
        if(staff.getLastName().isEmpty()){
            error = "Họ khách hàng không được để trống!!!";
            return false;
        }
        if(staff.getFirstName().isEmpty()){
            error = "Tên khách hàng không được để trống!!!";
            return false;
        }
        if(staff.getPhone().isEmpty()){
            error = "Số điện thoại không được để trống!!!";
            return false;
        }
        if(staff.getPassword().isEmpty()){
            error = "Mật khẩu không được để trống!!!";
            return false;
        }
        if(!staff.getPhone().matches("^0[0-9]{8,10}$")){
            error = "Số điện thoại định dạng không hợp lệ!!!";
            return false;
        }
        try {
            StaffDAO.getInstance().UpdateAccount(staff);
        }
        catch(Exception e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public List<StaffDTO> getList() {
        list = StaffDAO.getInstance().getList();
        return list;
    }

    public StaffDTO getStaffById(int id) {
        StaffDTO staff;
        try {
            staff = StaffDAO.getInstance().GetStaffById(id);
        }
        catch(Exception e){
            error = e.getMessage();
            staff = new StaffDTO();
        }
        return staff;
    }

    public List<StaffDTO>getStaffListBy(int col, String txt) {
        List<StaffDTO> ls = new ArrayList<>();
        for (StaffDTO staff : list) {
            switch (col) {
                case 0: if(String.valueOf(staff.getId()).contains(txt)) ls.add(staff); break;
                case 1: if(staff.getLastName().contains(txt)) ls.add(staff); break;
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

    public boolean add(StaffDTO staff){
        if (staff.getFirstName().isEmpty() || staff.getLastName().isEmpty() || staff.getPhone().isEmpty() || staff.getAddress().isEmpty() || staff.getPassword().isEmpty()){
            error = "Không được để trống thông tin!!!";
            return false;
        }
        if(!staff.getPhone().matches("^0[0-9]{8,10}$")){
            error = "Số điện thoại định dạng không hợp lệ!!!";
            return false;
        }
        try {
            StaffDAO.getInstance().add(staff);
        }
        catch (Exception e) {
            error = "Lỗi: " + e.getMessage();
            return false;
        }
        return true;
    }


public boolean update(StaffDTO staff){
        if (staff.getFirstName().isEmpty() || staff.getLastName().isEmpty() || staff.getPhone().isEmpty() || staff.getAddress().isEmpty() || staff.getPassword().isEmpty()){
            error = "Không được để trống thông tin!!!";
            return false;
        }
        if(!staff.getPhone().matches("^0[0-9]{8,10}$")){
            error = "Số điện thoại định dạng không hợp lệ!!!";
            return false;
        }
        try {
            StaffDAO.getInstance().update(staff);
        }
        catch (Exception e) {
            error = "Lỗi: " + e.getMessage();
            return false;
        }
        return true;
    }

    public boolean delete(int id){
        try {
            StaffDAO.getInstance().delete(id);
        }
        catch (Exception e) {
            error = "Lỗi: " + e.getMessage();
            return false;
        }
        return true;
    }

    public String getError() {return error;}
    public int getNumLine() {return NumLine;}
}
