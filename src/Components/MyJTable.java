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
        getTableHeader().setFont(new Font("Roboto", Font.BOLD, 14));
        getTableHeader().setBackground(MyColor.HoverBlue);
        getTableHeader().setForeground(MyColor.Black);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBorder(null);
        getTableHeader().setPreferredSize(new Dimension(this.getTableHeader().getWidth(), 30));

        // Set cho content
        setFont(new Font("Roboto", Font.PLAIN, 12));
        setBackground(MyColor.White);
        setForeground(MyColor.Black);
        setRowHeight(20);

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
}
