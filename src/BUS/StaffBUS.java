package BUS;

import DAO.StaffDAO;
import DTO.StaffDTO;

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
        StaffDTO staff = new StaffDTO(0);
        if(phone.isEmpty()){
            error = "Số điện thoại không được để trống!!!";
            return new StaffDTO(0);
        }
        if(password.isEmpty()){
            error = "Mật khẩu không được để trống!!!";
            return new StaffDTO(0);
        }
        if(!phone.matches("^0[0-9]{8,10}$")){
            error = "Số điện thoại định dạng không hợp lệ!!!";
            return new StaffDTO(0);
        }
        try {
            staff = StaffDAO.getInstance().Login(phone, password);
        }
        catch(Exception e){
            error = e.getMessage();
            return new StaffDTO(0);
        }
        return staff;
    }

    public List<StaffDTO> getList() {
        list = StaffDAO.getInstance().getList();
        return list;
    }

    public StaffDTO getStaffById(int id) {
        for (StaffDTO staffDTO : list)
            if (staffDTO.getId() == id)
                return staffDTO;
        return null;
    }

    public List<StaffDTO> getStaffListBy(int col, String txt) {
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
