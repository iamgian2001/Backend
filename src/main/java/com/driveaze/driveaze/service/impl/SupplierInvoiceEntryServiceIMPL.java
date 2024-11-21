package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.SupplierInvoiceEntryDTO;
import com.driveaze.driveaze.entity.Supplier;
import com.driveaze.driveaze.entity.SupplierInvoice;
import com.driveaze.driveaze.entity.SupplierInvoiceEntry;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.SupplierInvoiceEntryRepo;
import com.driveaze.driveaze.repository.SupplierInvoiceRepo;
import com.driveaze.driveaze.service.interfac.SupplierInvoiceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierInvoiceEntryServiceIMPL implements SupplierInvoiceEntryService {
    @Autowired
    private SupplierInvoiceEntryRepo supplierInvoiceEntryRepo;

    @Override
    public ResponseDTO addNewSupplierInvoiceEntry(SupplierInvoiceEntryDTO supplierInvoiceEntryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            SupplierInvoiceEntry supplierInvoiceEntry = new SupplierInvoiceEntry(
                    supplierInvoiceEntryDTO.getInvoiceEntryId(),
                    supplierInvoiceEntryDTO.getSupplierInvoice(),
                    supplierInvoiceEntryDTO.getInvoiceEntryDate(),
                    supplierInvoiceEntryDTO.getInvoiceEntryTime(),
                    supplierInvoiceEntryDTO.getItem(),
                    supplierInvoiceEntryDTO.getQuantity(),
                    supplierInvoiceEntryDTO.getPrice()
            );

            // Save the supplierInvoice and its entries
            SupplierInvoiceEntry result = supplierInvoiceEntryRepo.save(supplierInvoiceEntry);
            if (result.getInvoiceEntryId() > 0) {
                response.setMessage("Successfully added Invoice Entry");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding supplier: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO getAllSupplierInvoiceEntries() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<SupplierInvoiceEntry> supplierInvoiceEntries = supplierInvoiceEntryRepo.findAll();
            if (!supplierInvoiceEntries.isEmpty()){
                response.setSupplierInvoiceEntryList(supplierInvoiceEntries);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Invoice Entries Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Invoice entries: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateSupplierInvoiceEntry(Integer invoiceEntryId, SupplierInvoiceEntryDTO supplierInvoiceEntryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            SupplierInvoiceEntry supplierInvoiceEntry = supplierInvoiceEntryRepo.findById(invoiceEntryId)
                    .orElseThrow(() -> new OurException("Supplier Invoice not found"));

            supplierInvoiceEntry.setSupplierInvoice(supplierInvoiceEntryDTO.getSupplierInvoice());
            supplierInvoiceEntry.setInvoiceEntryDate(supplierInvoiceEntryDTO.getInvoiceEntryDate());
            supplierInvoiceEntry.setInvoiceEntryTime(supplierInvoiceEntryDTO.getInvoiceEntryTime());
            supplierInvoiceEntry.setItem(supplierInvoiceEntryDTO.getItem());
            supplierInvoiceEntry.setQuantity(supplierInvoiceEntryDTO.getQuantity());
            supplierInvoiceEntry.setPrice(supplierInvoiceEntryDTO.getPrice());

            supplierInvoiceEntryRepo.save(supplierInvoiceEntry);
            response.setStatusCode(200);
            response.setMessage("Successfully updated Invoice Entry");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating Invoice Entry: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteSupplierInvoiceEntry(Integer invoiceEntryId) {
        ResponseDTO response = new ResponseDTO();

        try {
            supplierInvoiceEntryRepo.findById(invoiceEntryId).orElseThrow(()->new OurException("Invoice Entry not found"));
            supplierInvoiceEntryRepo.deleteById(invoiceEntryId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted invoice entry");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting invoice entry: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getSupplierInvoiceEntryById(Integer invoiceEntryId) {
        ResponseDTO response = new ResponseDTO();

        try {
            SupplierInvoiceEntry supplierInvoiceEntry = supplierInvoiceEntryRepo.findById(invoiceEntryId)
                    .orElseThrow(() -> new OurException("Invoice entry not found"));

            response.setSupplierInvoiceEntry(supplierInvoiceEntry);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Invoice Entry");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving invoice entry: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAllSupplierInvoiceEntriesByInvoiceId(Integer invoiceId) {
        ResponseDTO response = new ResponseDTO();

        try {
            List<SupplierInvoiceEntry> supplierInvoiceEntries = supplierInvoiceEntryRepo.findBySupplierInvoice_InvoiceId(invoiceId);

            if (!supplierInvoiceEntries.isEmpty()) {
                response.setSupplierInvoiceEntryList(supplierInvoiceEntries);
                response.setStatusCode(200);
                response.setMessage("Successfully retrieved Invoice Entries for Invoice ID: " + invoiceId);
            } else {
                throw new OurException("No Invoice Entries Found for Invoice ID: " + invoiceId);
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Invoice entries for Invoice ID " + invoiceId + ": " + e.getMessage());
        }

        return response;
    }



}
