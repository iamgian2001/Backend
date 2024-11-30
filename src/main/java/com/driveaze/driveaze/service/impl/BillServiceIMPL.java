package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.BillDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.*;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.BillRepo;
import com.driveaze.driveaze.repository.CustomerVehicleRepo;
import com.driveaze.driveaze.repository.JobRegistryRepo;
import com.driveaze.driveaze.service.interfac.BillService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceIMPL implements BillService {
    @Autowired
    private BillRepo billRepo;

    @Autowired
    private JobRegistryRepo jobRegistryRepo;

    @Autowired
    private CustomerVehicleRepo customerVehicleRepo;

    @Override
    public ResponseDTO addNewBill(BillDTO billDTO) {
        System.out.println(billDTO);
        ResponseDTO response = new ResponseDTO();

        try {
            // Fetch the JobRegistry to verify if it exists and to get the vehicle ID
            Optional<JobRegistry> jobRegistry = jobRegistryRepo.findById(billDTO.getJobRegistry().getJobId());

            if (!jobRegistry.isPresent()) {
                response.setStatusCode(404);
                response.setMessage("Job Registry not found");
                return response;
            }

            // Create and populate the new Bill entity
            Bill bill = new Bill();
            bill.setJobRegistry(billDTO.getJobRegistry());
            bill.setBillDate(billDTO.getBillDate());
            bill.setBillTime(billDTO.getBillTime());
            bill.setBillStatus(billDTO.getBillStatus());

            // Set entries and ensure each entry references this Bill
            List<BillEntry> entries = billDTO.getEntries();
            for (BillEntry entry : entries) {
                entry.setBill(bill);  // Set parent reference to avoid detached entity issues
            }
            bill.setEntries(entries);

            // Save the bill and its entries
            Bill result = billRepo.save(bill);
            if (result.getBillId() > 0) {
                response.setMessage("Successfully added new Bill");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding Bill: " + e.getMessage());
        }

        return response;
    }


    @Override
    public ResponseDTO getAllBills() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Bill> bills = billRepo.findAll();
            if (!bills.isEmpty()){
                response.setBillList(bills);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Bills Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Bills: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateBill(Integer billId, BillDTO billDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            Bill bill = billRepo.findById(billId)
                    .orElseThrow(() -> new OurException("Bill not found"));

            bill.setJobRegistry(billDTO.getJobRegistry());
            bill.setBillDate(billDTO.getBillDate());
            bill.setBillTime(billDTO.getBillTime());
            bill.setBillStatus(billDTO.getBillStatus());

            billRepo.save(bill);
            response.setStatusCode(200);
            response.setMessage("Successfully updated Bill");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating Bill: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteBill(Integer billId) {
        ResponseDTO response = new ResponseDTO();

        try {
            billRepo.findById(billId).orElseThrow(()->new OurException("Bill not found"));
            billRepo.deleteById(billId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted Bill");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting Bill: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getBillById(Integer billId) {
        ResponseDTO response = new ResponseDTO();

        try {
            Bill bill = billRepo.findById(billId)
                    .orElseThrow(() -> new OurException("Bill not found"));

            response.setBill(bill);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Bill");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Bill: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAllBillsWithStatus(Integer billStatus) {
        ResponseDTO response = new ResponseDTO();

        try {
            // Fetch bills with the specified status
            List<Bill> bills = billRepo.findByBillStatus(billStatus); // Assuming this method exists in the BillRepo

            if (!bills.isEmpty()) {
                response.setBillList(bills);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Bills Found with status " + billStatus);
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Bills: " + e.getMessage());
        }

        return response;
    }

    @Override
    public Page<Bill> findBillsWithPaginationAndSortingAndStatus(List<Integer> statuses, int offset) {
        return billRepo.findByBillStatusIn(
                statuses,
                PageRequest.of(offset, 6).withSort(Sort.by(Sort.Order.desc("billDate"), Sort.Order.desc("billTime")))
        );
    }

    @Override
    public ResponseDTO updateBillStatus(Integer billId, int status) {
        ResponseDTO response = new ResponseDTO();

        try {
            Bill bill = billRepo.findById(billId)
                    .orElseThrow(() -> new EntityNotFoundException("Bill not found with ID: " + billId));

            bill.setBillStatus(status);

            billRepo.save(bill);
            response.setStatusCode(200);
            response.setMessage("Successfully updated Status");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating Bill: " + e.getMessage());
        }
        return response;
    }
}
