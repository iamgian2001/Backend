package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.SupplierInvoiceEntryDTO;

public interface SupplierInvoiceEntryService {
    ResponseDTO addNewSupplierInvoiceEntry(SupplierInvoiceEntryDTO supplierInvoiceEntryDTO);

    ResponseDTO getAllSupplierInvoiceEntries();

    ResponseDTO updateSupplierInvoiceEntry(Integer invoiceEntryId, SupplierInvoiceEntryDTO supplierInvoiceEntryDTO);

    ResponseDTO deleteSupplierInvoiceEntry(Integer invoiceEntryId);

    ResponseDTO getSupplierInvoiceEntryById(Integer invoiceEntryId);

    ResponseDTO getAllSupplierInvoiceEntriesByInvoiceId(Integer invoiceId);
}
