package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.SupplierInvoiceDTO;
import com.driveaze.driveaze.dto.SupplierInvoiceEntryDTO;
import com.driveaze.driveaze.entity.SupplierInvoice;
import com.driveaze.driveaze.entity.SupplierInvoiceEntry;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.SupplierInvoiceRepo;
import com.driveaze.driveaze.service.interfac.SupplierInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierInvoiceServiceIMPL implements SupplierInvoiceService {

    @Autowired
    private SupplierInvoiceRepo supplierInvoiceRepo;

//    @Override
//    public ResponseDTO addNewSupplierInvoice(SupplierInvoiceDTO supplierInvoiceDTO) {
//        ResponseDTO response = new ResponseDTO();
//
//        try {
//            SupplierInvoice supplierInvoice = new SupplierInvoice(
//                    supplierInvoiceDTO.getInvoiceId(),
//                    supplierInvoiceDTO.getSupplier(),
//                    supplierInvoiceDTO.getInvoiceDate(),
//                    supplierInvoiceDTO.getInvoiceTime(),
//                    supplierInvoiceDTO.getInvoiceStatus(),
//                    supplierInvoiceDTO.getEntries()
//            );
//
//            SupplierInvoice result = supplierInvoiceRepo.save(supplierInvoice);
//            if (result.getInvoiceId()>0) {
//                response.setMessage("Successfully added new supplier invoice");
//                response.setStatusCode(200);
//            }
//        }catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error occured while adding supplier Invoice: " + e.getMessage());
//        }
//        return response;
//    }

    @Override
    public ResponseDTO addNewSupplierInvoice(SupplierInvoiceDTO supplierInvoiceDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            // Create a new SupplierInvoice from the DTO
            SupplierInvoice supplierInvoice = new SupplierInvoice();
            supplierInvoice.setSupplier(supplierInvoiceDTO.getSupplier());
            supplierInvoice.setInvoiceDate(supplierInvoiceDTO.getInvoiceDate());
            supplierInvoice.setInvoiceTime(supplierInvoiceDTO.getInvoiceTime());
            supplierInvoice.setInvoiceStatus(supplierInvoiceDTO.getInvoiceStatus());

            //Meka through supplierInvoiceEntry danakota request body eken "supplierInvoice": "string", me peliya ain karanna
            // Set entries and ensure each entry references this supplierInvoice
            List<SupplierInvoiceEntry> entries = supplierInvoiceDTO.getEntries();
            for (SupplierInvoiceEntry entry : entries) {
                entry.setSupplierInvoice(supplierInvoice);  // Set parent reference
            }
            supplierInvoice.setEntries(entries);

            // Save the supplierInvoice and its entries
            SupplierInvoice result = supplierInvoiceRepo.save(supplierInvoice);
            if (result.getInvoiceId() > 0) {
                response.setMessage("Successfully added new supplier invoice");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding supplier Invoice: " + e.getMessage());
        }

        return response;
    }


    @Override
    public ResponseDTO getAllSupplierInvoices() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<SupplierInvoice> supplierInvoices = supplierInvoiceRepo.findAll();
            if (!supplierInvoices.isEmpty()){
                response.setSupplierInvoiceList(supplierInvoices);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Invoices Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Invoices: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateSupplierInvoice(Integer invoiceId, SupplierInvoiceDTO supplierInvoiceDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            SupplierInvoice supplierInvoice = supplierInvoiceRepo.findById(invoiceId)
                    .orElseThrow(() -> new OurException("Supplier Invoice not found"));

            supplierInvoice.setSupplier(supplierInvoiceDTO.getSupplier());
            supplierInvoice.setInvoiceDate(supplierInvoiceDTO.getInvoiceDate());
            supplierInvoice.setInvoiceTime(supplierInvoiceDTO.getInvoiceTime());
            supplierInvoice.setInvoiceStatus(supplierInvoiceDTO.getInvoiceStatus());

            supplierInvoiceRepo.save(supplierInvoice);
            response.setStatusCode(200);
            response.setMessage("Successfully updated Invoice");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating Invoice: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteSupplierInvoice(Integer invoiceId) {
        ResponseDTO response = new ResponseDTO();

        try {
            supplierInvoiceRepo.findById(invoiceId).orElseThrow(()->new OurException("Invoice not found"));
            supplierInvoiceRepo.deleteById(invoiceId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted invoice");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting invoice: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getSupplierInvoiceById(Integer invoiceId) {
        ResponseDTO response = new ResponseDTO();

        try {
            SupplierInvoice supplierInvoice = supplierInvoiceRepo.findById(invoiceId)
                    .orElseThrow(() -> new OurException("Invoice not found"));

            response.setSupplierInvoice(supplierInvoice);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Invoice");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving invoice: " + e.getMessage());
        }
        return response;
    }
}
