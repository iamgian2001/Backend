package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.BillEntryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Bill;
import com.driveaze.driveaze.entity.BillEntry;
import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.BillEntryRepo;
import com.driveaze.driveaze.repository.BillRepo;
import com.driveaze.driveaze.service.interfac.BillEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillEntryServiceIMPL implements BillEntryService {
    @Autowired
    BillRepo billRepo;

    @Autowired
    BillEntryRepo billEntryRepo;

    @Override
    public ResponseDTO addNewBillEntry(BillEntryDTO billEntryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            // Fetch the JobRegistry to verify if it exists and to get the vehicle ID
            Optional<Bill> bill = billRepo.findById(billEntryDTO.getBill().getBillId());

            if (!bill.isPresent()) {
                response.setStatusCode(404);
                response.setMessage("Bill not found");
                return response;
            }

            // Create and populate the new Bill entity
            BillEntry billEntry = new BillEntry();
            billEntry.setBill(bill.get());
            billEntry.setBillEntryDate(billEntryDTO.getBillEntryDate());
            billEntry.setBillEntryTime(billEntryDTO.getBillEntryTime().toLocalTime());
            billEntry.setServiceOrProduct(billEntryDTO.getServiceOrProduct());
            billEntry.setQuantity(billEntryDTO.getQuantity());
            billEntry.setTotalPrice(billEntryDTO.getTotalPrice());
            billEntry.setUnitPrice(billEntryDTO.getUnitPrice());

            // Save the bill and its entries
            BillEntry result = billEntryRepo.save(billEntry);
            if (result.getBillEntryId() > 0) {
                response.setMessage("Successfully added new Bill Entry");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding Bill Entry: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO getAllBillEntries() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<BillEntry> billEntiess = billEntryRepo.findAll();
            if (!billEntiess.isEmpty()){
                response.setBillEntryList(billEntiess);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Bill Entries Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Bill Entries: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateBillEntry(Integer billEntryId, BillEntryDTO billEntryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            BillEntry billEntry = billEntryRepo.findById(billEntryId)
                    .orElseThrow(() -> new OurException("Bill Entry not found"));

            billEntry.setBill(billEntryDTO.getBill());
            billEntry.setServiceOrProduct(billEntryDTO.getServiceOrProduct());
            billEntry.setQuantity(billEntryDTO.getQuantity());
            billEntry.setTotalPrice(billEntryDTO.getTotalPrice());
            billEntry.setUnitPrice(billEntryDTO.getUnitPrice());


            billEntryRepo.save(billEntry);
            response.setStatusCode(200);
            response.setMessage("Successfully updated Bill Entry");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating Bill Entry: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteBillEntry(Integer billEntryId) {
        ResponseDTO response = new ResponseDTO();

        try {
            billEntryRepo.findById(billEntryId).orElseThrow(()->new OurException("Bill Entry not found"));
            billEntryRepo.deleteById(billEntryId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted Bill Entry");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting Bill Entry: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getBillEntryById(Integer billEntryId) {
        ResponseDTO response = new ResponseDTO();

        try {
            BillEntry billEntry = billEntryRepo.findById(billEntryId)
                    .orElseThrow(() -> new OurException("Bill Entry not found"));

            response.setBillEntry(billEntry);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Bill Entry");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Bill Entry: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAllBillEntriesByBillId(Integer billId) {
        ResponseDTO response = new ResponseDTO();

        try {
            // Fetch the Bill entity
            Bill bill = billRepo.findById(billId)
                    .orElseThrow(() -> new OurException("Bill not found with ID: " + billId));

            // Fetch bill entries for the given Bill
            List<BillEntry> billEntries = billEntryRepo.findAllByBill(bill);

            if (!billEntries.isEmpty()) {
                // Populate response with the retrieved entries
                response.setBillEntryList(billEntries);
                response.setStatusCode(200);
                response.setMessage("Bill entries retrieved successfully.");
            } else {
                throw new OurException("No bill entries found for Bill ID: " + billId);
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("An error occurred while retrieving bill entries: " + e.getMessage());
        }

        return response;
    }

}
