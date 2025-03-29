package Components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyJTable extends JTable {
    public DefaultTableModel dftbModel;
    public JScrollPane scrPn;
    public MyJTable(String header[]) {
        dftbModel = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(dftbModel);

        // Set cho header
        getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
        getTableHeader().setBackground(MyColor.UnderLineBlue);
        getTableHeader().setForeground(MyColor.White);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBorder(null);
        getTableHeader().setPreferredSize(new Dimension(this.getTableHeader().getWidth(), 33));

        // Set cho content
        setFont(new Font("Roboto", Font.PLAIN, 14));
        setBackground(MyColor.White);
        setForeground(MyColor.Black);
        setRowHeight(23);

        scrPn = new JScrollPane(this);
    }


    public void addRow(Object[] rows) {
        dftbModel.addRow(rows);
    }
    public Object[] getRowObject(int row) {
        Object[] rowObjects = new Object[dftbModel.getColumnCount()];
        for(int i = 0; i < dftbModel.getColumnCount(); i++) {
            rowObjects[i] = dftbModel.getValueAt(row, i);
        }
        return rowObjects;
    }


    public MyJTable(String header[], Font style, Color colorFont, Color brheader, Color select) {
        dftbModel = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(dftbModel);
        setFont(new Font("Arial",0,16));
        setBackground(Color.WHITE);
        setRowHeight(28);
        getTableHeader().setPreferredSize(new Dimension(0, 28));

        // Tùy chỉnh header
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBorder(null);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(null);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBackground(brheader); // Màu nền header
                label.setForeground(colorFont); // Màu chữ header
                label.setFont(style); // Font của header
                return label;
            }
        });

        setIntercellSpacing(new Dimension(0, 1));
        setFocusable(false);
        setShowVerticalLines(false);
        setShowHorizontalLines(true);
        setGridColor(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder());

        // Căn giữa nội dung
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                ((JLabel) c).setBorder(null);
                if (isSelected) {
                    c.setBackground(select);
                    c.setForeground(Color.white);
                } else {
                    c.setBackground(Color.WHITE); // Màu nền mặc định
                    c.setForeground(Color.BLACK); // Màu chữ mặc định
                }
                return c;
            }
        };

        if (getColumnCount() > 0) {
            for (int i = 0; i < getColumnCount(); i++) {
                getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }
}
