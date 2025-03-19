package pnForm;

import Components.MyColor;
import Components.MyJButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class pnStaff extends JPanel {
    private DefaultTableModel model = new DefaultTableModel();
    private JTable tbStaff;
    private JButton btnblock = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Khóa", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnfind = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Tìm", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnadd = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Thêm", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnfix = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Sửa", SwingConstants.CENTER, SwingConstants.CENTER);
    private JButton btnload = new MyJButton(Font.PLAIN, 14, MyColor.Black, MyColor.White, MyColor.White, "Tải lại", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField txtfiter = new JTextField("Nhập nội dung");

    // panel
    JPanel panelfunction = new JPanel();  // them sua block
    JPanel panelfind = new JPanel();     // tim kiem, loc thong tin
    JPanel panelheader = new JPanel();
    JPanel paneldisplay = new JPanel();  // hien danh sach

    public pnStaff() {
        setLayout(null);
        setBackground(Color.decode("#FFFFFF"));

        // panel function
        panelfunction.add(btnadd);
        panelfunction.add(btnfix);
        panelfunction.add(btnblock);
        panelfunction.setLayout(new GridLayout(1, 3, 10, 20));
        panelfunction.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        // Điều chỉnh kích thước button trong panelfunction
        btnadd.setPreferredSize(new Dimension(100, 35));
        btnfix.setPreferredSize(new Dimension(100, 35));
        btnblock.setPreferredSize(new Dimension(100, 35));

        // panel find
        panelfind.add(txtfiter);
        panelfind.add(btnfind);
        panelfind.add(btnload);
        Border border = BorderFactory.createLineBorder(Color.gray, 1);
        panelfind.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        //Set kích thước button và textfield trong panelfind
        txtfiter.setPreferredSize(new Dimension(200, 33));
        btnfind.setPreferredSize(new Dimension(80, 30));
        btnload.setPreferredSize(new Dimension(80, 30));

        // panel header
        panelheader.add(panelfunction);
        panelheader.add(panelfind);
        panelheader.setLayout(new GridLayout(1, 2, 15, 25));
        panelheader.setBounds(10, 54, 950, 80);

        // panel display
        String[] columns = {"ID", "Mã NV", "Họ-Lót", "Tên", "Địa chỉ", "Role", "Lương"};
        model.setColumnIdentifiers(columns);
        tbStaff = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tbStaff);
        paneldisplay.setBackground(Color.decode("#FFFFFF"));
        paneldisplay.setLayout(new GridLayout(1, 1));
        paneldisplay.add(scrollPane);
        paneldisplay.setBounds(10, 170, 950, 550);

        add(panelheader);
        add(paneldisplay);
    }


}