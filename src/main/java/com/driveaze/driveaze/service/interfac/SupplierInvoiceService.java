package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.SupplierInvoiceDTO;

public interface SupplierInvoiceService {
    ResponseDTO addNewSupplierInvoice(SupplierInvoiceDTO supplierInvoiceDTO);

    ResponseDTO getAllSupplierInvoices();

    ResponseDTO updateSupplierInvoice(Integer invoiceId, SupplierInvoiceDTO supplierInvoiceDTO);

    ResponseDTO deleteSupplierInvoice(Integer invoiceId);

    ResponseDTO getSupplierInvoiceById(Integer invoiceId);
}
