//package com.driveaze.driveaze.service.impl;
//
//import com.driveaze.driveaze.entity.Bill;
//import com.driveaze.driveaze.entity.BillEntry;
//import com.driveaze.driveaze.entity.CustomerVehicle;
//import com.driveaze.driveaze.repository.CustomerVehicleRepo;
//import com.driveaze.driveaze.service.interfac.BillPdfService;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayOutputStream;
//import java.math.BigDecimal;
//import java.sql.Time;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//
//@Service
//public class BillPdfServiceImpl implements BillPdfService {
//    @Autowired
//    private CustomerVehicleRepo customerVehicleRepo;
//
//    @Override
//    public byte[] generateBillPdf(Bill bill) {
//        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//            Document document = new Document();
//            PdfWriter.getInstance(document, out);
//
//            document.open();
//
//            // Add title
//            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
//            Paragraph title = new Paragraph("Vehicle Service Station Invoice", titleFont);
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//
//            // Add customer details
//            document.add(Chunk.NEWLINE);
//            Font detailFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
//            document.add(new Paragraph("Invoice ID: " + bill.getBillId(), detailFont));
//
//            // Fetch Customer Name using the vehicleId from JobRegistry
//            Integer vehicleId = bill.getJobRegistry().getVehicleId(); // assuming vehicleId is stored in JobRegistry
//            CustomerVehicle customerVehicle = customerVehicleRepo.findCustomerVehicleByVehicleId(vehicleId);
//            String customerName = (customerVehicle != null) ? customerVehicle.getOwnerName() : "Unknown Customer";
//            String vehicleNo = (customerVehicle != null) ? customerVehicle.getVehicleNo() : "Unknown Vehicle";
//
//            document.add(new Paragraph("Customer Name: " + customerName));
//            document.add(new Paragraph("Vehicle No: " + vehicleNo));
//            document.add(Chunk.NEWLINE);
//
//            // Add table for bill entries
//            PdfPTable table = new PdfPTable(3); // 3 columns: Service, Quantity, Price
//            table.setWidthPercentage(100);
//            table.setSpacingBefore(10f);
//            table.setSpacingAfter(10f);
//
//            // Table header
//            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
//            PdfPCell header1 = new PdfPCell(new Phrase("Date", headerFont));
//            PdfPCell header2 = new PdfPCell(new Phrase("Service", headerFont));
//            PdfPCell header3 = new PdfPCell(new Phrase("Quantity", headerFont));
//            PdfPCell header4 = new PdfPCell(new Phrase("Unit Cost", headerFont));
//            PdfPCell header5 = new PdfPCell(new Phrase("Total Cost", headerFont));
//            header1.setBackgroundColor(BaseColor.GRAY);
//            header2.setBackgroundColor(BaseColor.GRAY);
//            header3.setBackgroundColor(BaseColor.GRAY);
//
//            table.addCell(header1);
//            table.addCell(header2);
//            table.addCell(header3);
//            table.addCell(header4);
//            table.addCell(header5);
//
////            private int billEntryId;
////            private Bill bill;
////            private LocalDate billEntryDate;
////            private Time billEntryTime;
////            private String serviceOrProduct;
////            private int quantity;
////            private BigDecimal totalPrice;
////            private BigDecimal unitPrice;
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//            // Table rows
//            Font rowFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
//            for (BillEntry entry : bill.getEntries()) {
//                String formattedDate = dateFormat.format(entry.getBillEntryDate());
//                table.addCell(new PdfPCell(new Phrase(formattedDate, rowFont)));
//                table.addCell(new PdfPCell(new Phrase(entry.getServiceOrProduct(), rowFont)));
//                table.addCell(new PdfPCell(new Phrase(String.valueOf(entry.getQuantity()), rowFont)));
//                table.addCell(new PdfPCell(new Phrase(String.valueOf(entry.getUnitPrice()), rowFont)));
//                table.addCell(new PdfPCell(new Phrase(String.valueOf(entry.getTotalPrice()), rowFont)));
//            }
//
//            document.add(table);
//
////            // Add total
////            Font totalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
////            Paragraph total = new Paragraph("Total: " + bill.getTotalAmount(), totalFont);
////            total.setAlignment(Element.ALIGN_RIGHT);
////            document.add(total);
//
//            // Footer
//            document.add(Chunk.NEWLINE);
//            Font footerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.GRAY);
//            Paragraph footer = new Paragraph("Thank you for choosing our services!", footerFont);
//            footer.setAlignment(Element.ALIGN_CENTER);
//            document.add(footer);
//
//            document.close();
//
//            return out.toByteArray();
//        } catch (Exception e) {
//            throw new RuntimeException("Error generating PDF", e);
//        }
//    }
//}
package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.entity.Bill;
import com.driveaze.driveaze.entity.BillEntry;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.repository.CustomerVehicleRepo;
import com.driveaze.driveaze.service.interfac.BillPdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class BillPdfServiceIMPL implements BillPdfService {

    @Autowired
    private CustomerVehicleRepo customerVehicleRepo;

    @Override
    public byte[] generateBillPdf(Bill bill) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();

            // Add title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Paragraph title = new Paragraph("Vehicle Service Station Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add customer details
            document.add(Chunk.NEWLINE);
            Font detailFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            document.add(new Paragraph("Invoice ID: " + bill.getBillId(), detailFont));

            // Fetch Customer Name using the vehicleId from JobRegistry
            Integer vehicleId = bill.getJobRegistry().getVehicleId(); // assuming vehicleId is stored in JobRegistry
            CustomerVehicle customerVehicle = customerVehicleRepo.findCustomerVehicleByVehicleId(vehicleId);
            String customerName = (customerVehicle != null) ? customerVehicle.getOwnerName() : "Unknown Customer";
            String vehicleNo = (customerVehicle != null) ? customerVehicle.getVehicleNo() : "Unknown Vehicle";

            document.add(new Paragraph("Customer Name: " + customerName, detailFont));
            document.add(new Paragraph("Vehicle No: " + vehicleNo, detailFont));
            document.add(Chunk.NEWLINE);

            // Add table for bill entries
            PdfPTable table = new PdfPTable(5); // 5 columns: Date, Service, Quantity, Unit Cost, Total Cost
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Define the column widths (relative to each other)
            float[] columnWidths = { 1.5f, 3f, 1f, 2f, 2f }; // Adjust these values as needed

            // Set the column widths
            table.setWidths(columnWidths);

            // Table header
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            PdfPCell header1 = new PdfPCell(new Phrase("Date", headerFont));
            PdfPCell header2 = new PdfPCell(new Phrase("Service", headerFont));
            PdfPCell header3 = new PdfPCell(new Phrase("Quantity", headerFont));
            PdfPCell header4 = new PdfPCell(new Phrase("Unit Cost(LKR)", headerFont));
            PdfPCell header5 = new PdfPCell(new Phrase("Total Cost(LKR)", headerFont));

            header1.setBackgroundColor(BaseColor.GRAY);
            header2.setBackgroundColor(BaseColor.GRAY);
            header3.setBackgroundColor(BaseColor.GRAY);
            header4.setBackgroundColor(BaseColor.GRAY);
            header5.setBackgroundColor(BaseColor.GRAY);

            table.addCell(header1);
            table.addCell(header2);
            table.addCell(header3);
            table.addCell(header4);
            table.addCell(header5);

            // Variable to accumulate the total amount
            double totalAmount = 0.0;

            // Table rows
            Font rowFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (BillEntry entry : bill.getEntries()) {
                // Format the date once
                String formattedDate = entry.getBillEntryDate().format(dateFormatter);

                // Add the formatted date to the table once
                table.addCell(new PdfPCell(new Phrase(formattedDate, rowFont)));

                // Add other entry details to the table
                table.addCell(new PdfPCell(new Phrase(entry.getServiceOrProduct(), rowFont)));
                table.addCell(createRightAlignedCell(String.valueOf(entry.getQuantity()), rowFont));
                table.addCell(createRightAlignedCell(String.valueOf(entry.getUnitPrice()), rowFont));
                table.addCell(createRightAlignedCell(String.valueOf(entry.getTotalPrice()), rowFont));

                totalAmount += entry.getTotalPrice().doubleValue();
            }


            document.add(table);

            // Add total amount to the document
            Font totalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
            Paragraph totalParagraph = new Paragraph("Total: " + String.format("%.2f", totalAmount)+"LKR", totalFont);
            totalParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalParagraph);

            // Footer
            document.add(Chunk.NEWLINE);
            Font footerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.GRAY);
            Paragraph footer = new Paragraph("Thank you for choosing our services!", footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            // Log the error to help debug
            e.printStackTrace(); // Or use a logger here
            throw new RuntimeException("Error generating PDF", e);
        }
    }

    // Method to create a cell with custom alignment
    private PdfPCell createRightAlignedCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);  // Set the alignment to left
        return cell;
    }

    // Method to create a generic cell
    private PdfPCell createCell(String content, Font font) {
        return new PdfPCell(new Phrase(content, font));
    }
}
