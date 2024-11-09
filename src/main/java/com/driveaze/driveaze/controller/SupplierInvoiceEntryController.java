package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.SupplierInvoiceEntryDTO;
import com.driveaze.driveaze.service.interfac.CustomerVehicleService;
import com.driveaze.driveaze.service.interfac.SupplierInvoiceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier-invoice-entry")
@CrossOrigin
public class SupplierInvoiceEntryController {
    @Autowired
    private SupplierInvoiceEntryService supplierInvoiceEntryService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewSupplierInvoiceEntry(@RequestBody SupplierInvoiceEntryDTO supplierInvoiceEntryDTO){
        return ResponseEntity.ok(supplierInvoiceEntryService.addNewSupplierInvoiceEntry(supplierInvoiceEntryDTO));
    }

    @PutMapping(path = "/update/{invoiceEntryId}")
    public ResponseEntity<ResponseDTO> updateSupplierInvoiceEntry(@PathVariable Integer invoiceEntryId, @RequestBody SupplierInvoiceEntryDTO supplierInvoiceEntryDTO) {
        ResponseDTO responseDTO = supplierInvoiceEntryService.updateSupplierInvoiceEntry(invoiceEntryId, supplierInvoiceEntryDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{invoiceEntryId}")
    public ResponseEntity<ResponseDTO> deleteSupplierInvoiceEntry(@PathVariable Integer invoiceEntryId) {
        return ResponseEntity.ok(supplierInvoiceEntryService.deleteSupplierInvoiceEntry(invoiceEntryId));
    }

    @GetMapping("/get-all-invoice-entries")
    public ResponseEntity<ResponseDTO> getAllSupplierInvoiceEntries() {
        return ResponseEntity.ok(supplierInvoiceEntryService.getAllSupplierInvoiceEntries());
    }

    @GetMapping("/get-invoice-entry/{invoiceEntryId}")
    public ResponseEntity<ResponseDTO> getSupplierInvoiceEntryById(@PathVariable Integer invoiceEntryId) {
        return ResponseEntity.ok(supplierInvoiceEntryService.getSupplierInvoiceEntryById(invoiceEntryId));
    }
}
