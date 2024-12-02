package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.entity.Bill;

public interface BillPdfService {
    byte[] generateBillPdf(Bill bill);
}
