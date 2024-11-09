package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.SupplierInvoiceDTO;
import com.driveaze.driveaze.service.interfac.SupplierInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier-invoice")
@CrossOrigin
public class SupplierInvoiceController {

    @Autowired
    private SupplierInvoiceService supplierInvoiceService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewSupplierInvoice(@RequestBody SupplierInvoiceDTO supplierInvoiceDTO){
        return ResponseEntity.ok(supplierInvoiceService.addNewSupplierInvoice(supplierInvoiceDTO));
    }

    @PutMapping(path = "/update/{invoiceId}")
    public ResponseEntity<ResponseDTO> updateSupplierInvoice(@PathVariable Integer invoiceId, @RequestBody SupplierInvoiceDTO supplierInvoiceDTO) {
        ResponseDTO responseDTO = supplierInvoiceService.updateSupplierInvoice(invoiceId, supplierInvoiceDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{invoiceId}")
    public ResponseEntity<ResponseDTO> deleteSupplierInvoice(@PathVariable Integer invoiceId) {
        return ResponseEntity.ok(supplierInvoiceService.deleteSupplierInvoice(invoiceId));
    }

    @GetMapping("/get-all-invoices")
    public ResponseEntity<ResponseDTO> getAllSupplierInvoices() {
        return ResponseEntity.ok(supplierInvoiceService.getAllSupplierInvoices());
    }

    @GetMapping("/get-invoice/{invoiceId}")
    public ResponseEntity<ResponseDTO> getSupplierInvoiceById(@PathVariable Integer invoiceId) {
        return ResponseEntity.ok(supplierInvoiceService.getSupplierInvoiceById(invoiceId));
    }
}
