package GUI.JPanel;
import BUS.BillBUS;
import BUS.BillInfoBUS;
import Components.*;
import DTO.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.util.List;

public class pnBill extends JPanel {
    JPanel pnHeader = new MyJPanel(MyColor.White);
    JPanel pnFooter = new MyJPanel(MyColor.White);
    JPanel pnFunc = new MyJPanel(MyColor.White, "Chức năng");
    JPanel pnSearch = new MyJPanel(MyColor.White, "Tìm kiếm");
    JButton btnDelete = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#F44336"), Color.decode("#FF7568"), "Xóa", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnOut = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Excel</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnPDF = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>In<br>PDF</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnDetail = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "<html>Xuất<br>Chi tiết</html>", SwingConstants.CENTER, SwingConstants.CENTER);
    JButton btnRefresh = new MyJButton(Font.BOLD, 16, Color.decode("#FFFFFF"), Color.decode("#2196F3"), Color.decode("#64B5F6"), "Làm mới", SwingConstants.CENTER, SwingConstants.CENTER);
    JTextField tfSearch = new MyJTextFieldInput(Font.PLAIN, 14, true);
    JComboBox<String>cbSearch = new MyJComboBox<>(new String[]{"Mã HĐ", "Ngày tạo", "Thành tiền", "Trạng thái"}, 12);
    MyJTable tbBill = new MyJTable(new String[]{"Mã HĐ", "Ngày tạo", "Thành tiền", "Trạng thái"}, new int[]{100, 100, 150, 150}, new int[]{}, new int[]{});
    MyJTable tbBillInfo = new MyJTable(new String[]{"Mã SP", "Tên SP" , "Đơn giá", "Số lượng", "Giảm giá", "Đơn vị ", "Thành tiền"}, new int[]{50, 125, 125, 75, 75, 75, 125}, new int[]{1, 5}, new int[]{});
    JPanel pnBill = new MyJPanel(MyColor.White, "Thông tin chi tiết hóa đơn");
    JLabel lbStaff = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Không có thông tin nhân viên</u></html>", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbCustomer = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Không có thông tin khách hàng</u></html>", SwingConstants.LEFT, SwingConstants.CENTER);
    JLabel lbOfferBill = new MyJLabel(Font.PLAIN, 14, MyColor.Black, "<html><u>Không có thông tin giảm giá</u></html>", SwingConstants.LEFT, SwingConstants.CENTER);

    pnBill thisPanel = this;

    public pnBill() {
        setLayout(null);
        setBackground(MyColor.White);

        // region setBounds
        pnHeader.setBounds(0,0,1170, 90);
        pnFunc.setBounds(0,0,230,90);
        btnDelete.setBounds(15,20,60,60);
        btnOut.setBounds(85,20,60,60);
//        btnDetail.setBounds(155,20,60,60);
        btnPDF.setBounds(155,20,60,60);
        pnSearch.setBounds(670,0,500,90);
        cbSearch.setBounds(685, 30, 150, 30);
        tfSearch.setBounds(845, 30, 200, 30);
        btnRefresh.setBounds(1055,30,100,30);
        pnFooter.setBounds(0,100,1170, 650);
        tbBill.scrPn.setBounds(0,100,500,650);

        pnBill.setBounds(520, 100, 650, 100);
        lbStaff.setBounds(530, 110, 630, 30);
        lbCustomer.setBounds(530, 140, 630, 30);
        lbOfferBill.setBounds(530, 170, 630, 30);

        tbBillInfo.scrPn.setBounds(520, 210, 650, 540);
        // endregion

        // region event
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {loadBill();}
        });
        tbBill.getSelectionModel().addListSelectionListener(_ -> {
            int rowSelect = tbBill.getSelectedRow();
            if(rowSelect != -1) {
                int id = Integer.parseInt(tbBill.getFirstColumn(rowSelect));
                BillDTO bill = BillBUS.getInstance().getBillById(id);
                lbStaff.setText(
                        String.format("<html>Mã nhân viên: <b>%d</b> --- Tên nhân viên: <b>%s</b> --- Số điện thoại: <b>%s</b></html>", bill.getStaff().getId(), bill.getStaff().getLastName() + " " + bill.getStaff().getFirstName(), bill.getStaff().getPhone())
                );
                if(bill.getCustomer().getId()!=0)
                    lbCustomer.setText(
                            String.format("<html>Mã khách hàng: <b>%d</b> --- Tên khách hàng: <b>%s</b> --- Số điện thoại: <b>%s</b></html>", bill.getCustomer().getId(), bill.getCustomer().getLastName() + " " + bill.getCustomer().getFirstName(), bill.getCustomer().getPhone())
                    );
                else lbCustomer.setText("<html><u>Không có thông tin khách hàng</u></html>");
                if(bill.getOfferBill().getOffer().getId()!=0)
                    lbOfferBill.setText(
                            String.format("<html>Mã giảm giá: <b>%d</b> --- Phần trăm giảm giá: <b>%s</b></html>", bill.getOfferBill().getOffer().getId(), bill.getOfferBill().getOffer().getValue() + "%")
                    );
                else lbOfferBill.setText("<html><u>Không có thông tin giảm giá</u></html>");
                tbBillInfo.dftbModel.setRowCount(0);
                for(BillInfoDTO billInfo: BillInfoBUS.getInstance().loadByIdBill(id))
                    tbBillInfo.dftbModel.addRow(billInfo.getSellObjects());
            }
            else {
                lbStaff.setText("<html><u>Không có thông tin nhân viên</u></html>");
                lbCustomer.setText("<html><u>Không có thông tin khách hàng</u></html>");
                lbOfferBill.setText("<html><u>Không có thông tin giảm giá</u></html>");
                tbBillInfo.dftbModel.setRowCount(0);
            }
        });
        btnDelete.addActionListener(_ -> {
            int i = tbBill.getSelectedRow();
            if(i>=0){
                int id = Integer.parseInt(tbBill.getFirstColumn(i));
                try {
                    int res = JOptionPane.showConfirmDialog(thisPanel,"Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if(res==JOptionPane.YES_OPTION){
                        BillBUS.getInstance().delete(id);
                        JOptionPane.showMessageDialog(thisPanel, "Xóa thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadBill();
                    }
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(thisPanel, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn thông tin cần xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
        btnDetail.addActionListener(_ -> {
            int i = tbBill.getSelectedRow();
            if(i>=0){
                JOptionPane.showMessageDialog(thisPanel, "Tạo PDF đi!!!");
            }
            else JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn hóa đơn cần xuất thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
        btnRefresh.addActionListener(_ -> {
            tfSearch.setText("");
            cbSearch.setSelectedIndex(0);
            loadBill();
        });
        btnOut.addActionListener(_ -> tbBill.ExportExel("Danh sách hóa đơn"));
        cbSearch.addActionListener(_ -> textChange());
        tfSearch.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {textChange();}
        });
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textChange();}
            public void removeUpdate(DocumentEvent e) {textChange();}
            public void changedUpdate(DocumentEvent e) {textChange();}
        });

        btnPDF.addActionListener(_ -> {
            int i = tbBill.getSelectedRow();
            if(i >= 0){
                try {
                    int id = Integer.parseInt(tbBill.getFirstColumn(i));
                    BillDTO bill = BillBUS.getInstance().getBillById(id);
                    List<BillInfoDTO> billInfos = BillInfoBUS.getInstance().loadByIdBill(id);

                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Chọn nơi lưu hóa đơn");
                    fileChooser.setSelectedFile(new java.io.File("HoaDon_" + id + ".pdf"));
                    int userSelection = fileChooser.showSaveDialog(thisPanel);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        java.io.File fileToSave = fileChooser.getSelectedFile();
                        String fileName = fileToSave.getAbsolutePath();
                        if (!fileName.toLowerCase().endsWith(".pdf")) {
                            fileName += ".pdf";
                        }

                        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                        com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(fileName));
                        document.open();
                        // Font Unicode từ Arial
                        String fontPath = "lib/arial.ttf";
                        com.itextpdf.text.pdf.BaseFont baseFont = com.itextpdf.text.pdf.BaseFont.createFont(fontPath,
                                com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
                        // Các loại font
                        com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(baseFont, 18, com.itextpdf.text.Font.BOLD);
                        com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(baseFont, 14, com.itextpdf.text.Font.BOLD);
                        com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(baseFont, 12);
                        com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(baseFont, 12, com.itextpdf.text.Font.BOLD);
                        // Header hóa đơn
                        com.itextpdf.text.Paragraph storeName = new com.itextpdf.text.Paragraph("MINI MART", fontTitle);
                        storeName.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        document.add(storeName);
                        com.itextpdf.text.Paragraph invoiceTitle = new com.itextpdf.text.Paragraph("HÓA ĐƠN BÁN HÀNG", fontHeader);
                        invoiceTitle.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        document.add(invoiceTitle);
                        // Thông tin chung
                        com.itextpdf.text.Paragraph billInfo = new com.itextpdf.text.Paragraph();
                        billInfo.add(new com.itextpdf.text.Chunk("Mã hóa đơn: ", fontBold));
                        billInfo.add(new com.itextpdf.text.Chunk(String.valueOf(bill.getId()), fontNormal));
                        billInfo.add(new com.itextpdf.text.Chunk("\nNgày lập: ", fontBold));
                        billInfo.add(new com.itextpdf.text.Chunk(bill.getDateCreate().toString(), fontNormal));
                        billInfo.add(new com.itextpdf.text.Chunk("\nNhân viên: ", fontBold));
                        billInfo.add(new com.itextpdf.text.Chunk(bill.getStaff().getLastName() + " " + bill.getStaff().getFirstName(), fontNormal));
                        billInfo.add(new com.itextpdf.text.Chunk(" - SĐT: " + bill.getStaff().getPhone(), fontNormal));
                        if(bill.getCustomer().getId() != 0) {
                            billInfo.add(new com.itextpdf.text.Chunk("\nKhách hàng: ", fontBold));
                            billInfo.add(new com.itextpdf.text.Chunk(bill.getCustomer().getLastName() + " " + bill.getCustomer().getFirstName(), fontNormal));
                            billInfo.add(new com.itextpdf.text.Chunk(" - SĐT: " + bill.getCustomer().getPhone(), fontNormal));
                        }
                        document.add(billInfo);
                        // Danh sách sản phẩm
                        com.itextpdf.text.Paragraph productTitle = new com.itextpdf.text.Paragraph("\nDANH SÁCH SẢN PHẨM", fontBold);
                        productTitle.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        document.add(productTitle);
                        com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(5);
                        table.setWidthPercentage(100);
                        table.setWidths(new float[]{3f, 1f, 2f, 1f, 2f});
                        table.setSpacingBefore(10f);
                        table.setSpacingAfter(10f);
                        // Header table
                        com.itextpdf.text.pdf.PdfPCell cell;
                        cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Tên SP", fontBold));
                        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell.setBackgroundColor(new com.itextpdf.text.BaseColor(220, 220, 220));
                        table.addCell(cell);
                        cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("SL", fontBold));
                        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell.setBackgroundColor(new com.itextpdf.text.BaseColor(220, 220, 220));
                        table.addCell(cell);
                        cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Đơn giá", fontBold));
                        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell.setBackgroundColor(new com.itextpdf.text.BaseColor(220, 220, 220));
                        table.addCell(cell);
                        cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Giảm", fontBold));
                        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell.setBackgroundColor(new com.itextpdf.text.BaseColor(220, 220, 220));
                        table.addCell(cell);
                        cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase("Thành tiền", fontBold));
                        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell.setBackgroundColor(new com.itextpdf.text.BaseColor(220, 220, 220));
                        table.addCell(cell);
                        // Content table
                        for(BillInfoDTO info : billInfos){
                            Object[] data = info.getSellObjects();

                            cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(String.valueOf(data[1]), fontNormal));
                            cell.setPadding(5);
                            table.addCell(cell);

                            cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(String.valueOf(data[3]), fontNormal));
                            cell.setPadding(5);
                            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                            table.addCell(cell);

                            cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(String.valueOf(data[2]) , fontNormal));
                            cell.setPadding(5);
                            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                            table.addCell(cell);

                            cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(String.valueOf(data[4]) , fontNormal));
                            cell.setPadding(5);
                            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                            table.addCell(cell);

                            cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(String.format("%,.0f", info.getTotal()) , fontNormal));
                            cell.setPadding(5);
                            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                            table.addCell(cell);
                        }
                        document.add(table);

                        // Tổng thanh toán
                        com.itextpdf.text.Paragraph total = new com.itextpdf.text.Paragraph();

                        // Tính tổng từ cột thành tiền
                        double sum = 0;
                        for(BillInfoDTO info : billInfos) {
                            sum += info.getTotal();
                        }

                        total.add(new com.itextpdf.text.Chunk("\nTổng tiền: ", fontBold));
                        total.add(new com.itextpdf.text.Chunk(String.format("%,.0f VNĐ", sum), fontBold));

                        if(bill.getOfferBill().getOffer().getId()!= 0) {
                            total.add(new com.itextpdf.text.Chunk("\nGiảm giá: ", fontBold));
                            total.add(new com.itextpdf.text.Chunk(bill.getOfferBill().getOffer().getValue() + "%", fontBold));
                        }
                        else {
                            total.add(new com.itextpdf.text.Chunk("\nGiảm giá: ", fontBold));
                            total.add(new com.itextpdf.text.Chunk("Không áp dụng", fontBold));
                        }
                        double thanhtien = sum*(1-((bill.getOfferBill().getOffer().getValue())*1.0/100));
                        total.add(new com.itextpdf.text.Chunk("\nThành tiền: ", fontBold));
                        total.add(new com.itextpdf.text.Chunk(String.format("%,.0f VNĐ",thanhtien), fontBold));
                        total.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                        document.add(total);
                        // Footer
                        com.itextpdf.text.Paragraph thankYou = new com.itextpdf.text.Paragraph("\n\nCẢM ƠN QUÝ KHÁCH ! ", fontHeader);
                        thankYou.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        document.add(thankYou);
                        document.close();
                        JOptionPane.showMessageDialog(thisPanel, "Xuất file PDF thành công:\n" + fileName);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(thisPanel, "Lỗi khi tạo PDF: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(thisPanel, "Vui lòng chọn hóa đơn để in", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        // endregion

        // region add
        add(lbCustomer);
        add(lbStaff);
        add(lbOfferBill);
        add(btnDelete);
        add(btnOut);
//        add(btnDetail);
        add(btnPDF);
        add(pnFunc);
        add(btnRefresh);
        add(cbSearch);
        add(tfSearch);
        add(pnSearch);
        add(pnHeader);
        add(tbBillInfo.scrPn);
        add(tbBill.scrPn);
        add(pnBill);
        add(pnFooter);
        // endregion
    }

    public void loadBill()  {
        BillBUS.getInstance().load();
        textChange();
    }

    public void textChange(){
        tbBill.dftbModel.setRowCount(0);
        int col = cbSearch.getSelectedIndex();
        String txt = tfSearch.getText();
        for(BillDTO bill: BillBUS.getInstance().search(col, txt))
            tbBill.dftbModel.addRow(bill.getRowObjects());
    }
}
