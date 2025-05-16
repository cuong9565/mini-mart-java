package GUI.JPanel;

import BUS.ImportBUS;
import BUS.ImportInfoBUS;
import Components.*;
import DTO.ImportDTO;
import DTO.ImportInfoDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.List;


public class pnImportOrder extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Excel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDetail = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>In<br>PDF</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã HĐ", "Ngày tạo", "Thành tiền", "Trạng thái"}, 12);
    MyJTable tbImport = new MyJTable(new String[]{"Mã HĐ", "Ngày tạo", "Thành tiền", "Trạng thái"}, new int[]{100, 100, 150, 150}, new int[]{}, new int[]{});

    MyJTable tbImportInfo = new MyJTable(new String[]{"Mã SP", "Tên SP" , "Đơn giá", "Số lượng", "Đơn vị ", "Thành tiền"}, new int[]{50, 150, 125, 100, 100, 125}, new int[]{1, 5}, new int[]{});
    JPanel pnImport = new MyJPanel(MyColor.White, "Thông tin chi tiết hóa đơn");
    JLabel lbStaff = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Không có thông tin nhân viên</u></html>", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbSupplier = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Không có thông tin nhà cung cấp</u></html>", SwingConstants.LEFT, SwingConstants.CENTER);

    pnImportOrder thisPanel = this;

    public pnImportOrder() {
        setLayout(null);
        setBackground(MyColor.White);

        // region setBounds
        pnHeader.setBounds(0,0,1170, 90);
        pnFunc.setBounds(0,0,230,90);
        btnDelete.setBounds(15,20,60,60);
        btnOut.setBounds(85,20,60,60);
        btnDetail.setBounds(155,20,60,60);
        pnSearch.setBounds(670,0,500,90);
        cbSearch.setBounds(685, 30, 150, 30);
        tfSearch.setBounds(845, 30, 200, 30);
        btnRefresh.setBounds(1055,30,100,30);
        pnFooter.setBounds(0,100,1170, 650);
        tbImport.scrPn.setBounds(0,100,500,650);

        pnImport.setBounds(520, 100, 650, 100);
        lbStaff.setBounds(530, 110, 630, 30);
        lbSupplier.setBounds(530, 140, 630, 30);

        tbImportInfo.scrPn.setBounds(520, 210, 650, 540);
        // endregion

        // region event
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {load();}
        });
        tbImport.getSelectionModel().addListSelectionListener(_ -> {
            int rowSelect = tbImport.getSelectedRow();
            if(rowSelect != -1) {
                int id = Integer.parseInt(tbImport.getFirstColumn(rowSelect));
                ImportDTO importDTO = ImportBUS.getInstance().getImportById(id);
                lbStaff.setText(
                        String.format("<html>Mã nhân viên: <b>%d</b> --- Tên nhân viên: <b>%s</b> --- Số điện thoại: <b>%s</b></html>", importDTO.getStaff().getId(), importDTO.getStaff().getLastName() + " " + importDTO.getStaff().getFirstName(), importDTO.getStaff().getPhone())
                );
                if(importDTO.getSupplier().getId()!=0)
                    lbSupplier.setText(
                            String.format("<html>Mã nhà cung cấp: <b>%d</b> --- Tên nhà cung cấp: <b>%s</b> --- Số điện thoại: <b>%s</b></html>", importDTO.getSupplier().getId(), importDTO.getSupplier().getName(), importDTO.getSupplier().getPhone())
                    );
                else lbSupplier.setText("<html><u>Không có thông tin nhà cung cấp</u></html>");

                tbImportInfo.dftbModel.setRowCount(0);
                for(ImportInfoDTO importInfoDTO: ImportInfoBUS.getInstance().loadByIdImport(id))
                    tbImportInfo.dftbModel.addRow(importInfoDTO.getSellObjects());
            }
            else {
                lbStaff.setText("<html><u>Không có thông tin nhân viên</u></html>");
                lbSupplier.setText("<html><u>Không có thông tin nhà cung cấp</u></html>");
                tbImportInfo.dftbModel.setRowCount(0);
            }
        });
        btnDelete.addActionListener(_ -> {
            int i = tbImport.getSelectedRow();
            if(i>=0){
                int id = Integer.parseInt(tbImport.getFirstColumn(i));
                try {
                    int res = JOptionPane.showConfirmDialog(thisPanel,"Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if(res==JOptionPane.YES_OPTION){
                        ImportBUS.getInstance().delete(id);
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        load();
                    }
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
        btnDetail.addActionListener(_ -> {
            int i = tbImport.getSelectedRow();
            if (i >= 0) {
                try {
                    int importId = Integer.parseInt(tbImport.getFirstColumn(i));
                    ImportDTO importDTO = ImportBUS.getInstance().getImportById(importId);
                    List<ImportInfoDTO> importDetails = ImportInfoBUS.getInstance().loadByIdImport(importId);

                    // Cho phép người dùng chọn nơi lưu file
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Chọn nơi lưu đơn nhập hàng");
                    fileChooser.setSelectedFile(new File("DonNhapHang_" + importId + ".pdf"));
                    int userSelection = fileChooser.showSaveDialog(thisPanel);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        String fileName = fileToSave.getAbsolutePath();
                        if (!fileName.toLowerCase().endsWith(".pdf")) {
                            fileName += ".pdf";
                        }

                        // Tạo document PDF
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(fileName));
                        document.open();

                        // Font Unicode từ Arial
                        String fontPath = "lib/arial.ttf";
                        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                        // Các loại font
                        com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(baseFont, 18, com.itextpdf.text.Font.BOLD);
                        com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(baseFont, 14, com.itextpdf.text.Font.BOLD);
                        com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(baseFont, 12);
                        com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(baseFont, 12, com.itextpdf.text.Font.BOLD);

                        // Header đơn nhập hàng
                        Paragraph storeName = new Paragraph("MINI MART", fontTitle);
                        storeName.setAlignment(Element.ALIGN_CENTER);
                        document.add(storeName);

                        Paragraph invoiceTitle = new Paragraph("ĐƠN NHẬP HÀNG", fontHeader);
                        invoiceTitle.setAlignment(Element.ALIGN_CENTER);
                        document.add(invoiceTitle);

                        // Thông tin chung
                        Paragraph importInfo = new Paragraph();
                        importInfo.add(new Chunk("Mã đơn nhập: ", fontBold));
                        importInfo.add(new Chunk(String.valueOf(importDTO.getId()), fontNormal));
                        importInfo.add(Chunk.NEWLINE);
                        importInfo.add(new Chunk("Ngày nhập: ", fontBold));
                        importInfo.add(new Chunk(importDTO.getDateCreate().toString(), fontNormal));
                        importInfo.add(Chunk.NEWLINE);
                        importInfo.add(new Chunk("Nhân viên: ", fontBold));
                        importInfo.add(new Chunk(importDTO.getStaff().getLastName() + " " + importDTO.getStaff().getFirstName(), fontNormal));
                        importInfo.add(new Chunk(" - SĐT: " + importDTO.getStaff().getPhone(), fontNormal));

                        if (importDTO.getSupplier().getId() != 0) {
                            importInfo.add(Chunk.NEWLINE);
                            importInfo.add(new Chunk("Nhà cung cấp: ", fontBold));
                            importInfo.add(new Chunk(importDTO.getSupplier().getName(), fontNormal));
                            importInfo.add(new Chunk(" - SĐT: " + importDTO.getSupplier().getPhone(), fontNormal));
                        }

                        document.add(importInfo);

                        // Danh sách sản phẩm
                        Paragraph productTitle = new Paragraph("\nDANH SÁCH SẢN PHẨM", fontBold);
                        productTitle.setAlignment(Element.ALIGN_CENTER);
                        document.add(productTitle);

                        // Tạo bảng sản phẩm
                        PdfPTable table = new PdfPTable(5);
                        table.setWidthPercentage(100);
                        table.setWidths(new float[]{2f, 3f, 2f, 1f, 2f});
                        table.setSpacingBefore(10f);
                        table.setSpacingAfter(10f);

                        // Header table
                        PdfPCell cell;
                        String[] headers = {"Mã SP", "Tên SP", "Đơn giá", "SL", "Thành tiền"};
                        for (String header : headers) {
                            cell = new PdfPCell(new Phrase(header, fontBold));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBackgroundColor(new BaseColor(220, 220, 220));
                            table.addCell(cell);
                        }

                        // Content table
                        double totalAmount = 0;
                        for (ImportInfoDTO detail : importDetails) {
                            // Mã SP
                            cell = new PdfPCell(new Phrase(String.valueOf(detail.getIdProduct()), fontNormal));
                            cell.setPadding(5);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            // Tên SP
                            cell = new PdfPCell(new Phrase(detail.getNameProduct(), fontNormal));
                            cell.setPadding(5);
                            table.addCell(cell);

                            // Đơn giá
                            cell = new PdfPCell(new Phrase(String.format("%,.0f VNĐ", detail.getPrice()), fontNormal));
                            cell.setPadding(5);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            table.addCell(cell);

                            // Số lượng
                            cell = new PdfPCell(new Phrase(String.valueOf(detail.getQuantity()), fontNormal));
                            cell.setPadding(5);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            // Thành tiền
                            double itemTotal = detail.getPrice() * detail.getQuantity();
                            totalAmount += itemTotal;
                            cell = new PdfPCell(new Phrase(String.format("%,.0f VNĐ", itemTotal), fontNormal));
                            cell.setPadding(5);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            table.addCell(cell);
                        }

                        document.add(table);

                        // Tổng thanh toán
                        Paragraph total = new Paragraph();
                        total.add(new Chunk("\nTổng tiền: ", fontBold));
                        total.add(new Chunk(String.format("%,.0f VNĐ", totalAmount), fontBold));
                        total.setAlignment(Element.ALIGN_RIGHT);
                        document.add(total);

                        // Footer
                        Paragraph thankYou = new Paragraph("\n\nXIN CẢM ƠN", fontHeader);
                        thankYou.setAlignment(Element.ALIGN_CENTER);
                        document.add(thankYou);

                        document.close();
                        JOptionPane.showMessageDialog(thisPanel, "Xuất file PDF thành công:\n" + fileName);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(thisPanel,
                            "Lỗi khi tạo PDF: " + ex.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(thisPanel,
                        "Vui lòng chọn đơn nhập hàng để xuất",
                        "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnRefresh.addActionListener(_ -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            load();
        });
        btnOut.addActionListener(_ -> tbImport.ExportExel("Danh sách đơn nhập hàng"));
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

        // region add
        add(lbSupplier);
        add(lbStaff);
        add(btnDelete);
        add(btnOut);
        add(btnDetail);
        add(pnFunc);
        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(pnSearch);
        add(pnHeader);
        add(tbImportInfo.scrPn);
        add(tbImport.scrPn);
        add(pnImport);
        add(pnFooter);
        // endregion
    }

    public void load()  {
        ImportBUS.getInstance().load();
        textChange();
    }

    public void textChange(){
        tbImport.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(ImportDTO bill: ImportBUS.getInstance().search(col, txt))
            tbImport.dftbModel.addRow(bill.getRowObjects());
    }
}
