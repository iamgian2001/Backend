package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.JobEntryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Inventory;
import com.driveaze.driveaze.entity.JobEntry;
import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.entity.OurUsers;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.InventoryRepo;
import com.driveaze.driveaze.repository.JobEntryRepo;
import com.driveaze.driveaze.repository.JobRegistryRepo;
import com.driveaze.driveaze.repository.UsersRepo;
import com.driveaze.driveaze.service.interfac.JobEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobEntryServiceIMPL implements JobEntryService {

    @Autowired
    private JobEntryRepo jobEntryRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private JobRegistryRepo jobRegistryRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Override
    public ResponseDTO addNewJobEntry(JobEntryDTO jobEntryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            // Fetch JobRegistry
            JobRegistry jobRegistry = jobRegistryRepo.findById(jobEntryDTO.getJobRegistry().getJobId())
                    .orElseThrow(() -> new OurException("Job Registry not found"));


            if (jobEntryDTO.getInventoryItemList() != null) {
                List<Map<String, String>> inventoryItemList;

                // Handle potential type casting
                if (jobEntryDTO.getInventoryItemList() instanceof List) {
                    inventoryItemList = (List<Map<String, String>>) jobEntryDTO.getInventoryItemList();
                } else {
                    throw new OurException("Invalid inventory item list format");
                }

                // Reduce inventory for each item
                for (Map<String, String> item : inventoryItemList) {
                    String id = item.get("id");
                    String quantity = item.get("quantity");

                    if (id == null || quantity == null) {
                        throw new OurException("Invalid inventory item details");
                    }

                    ResponseDTO inventoryResponse = reduceItem(
                            Integer.parseInt(id),
                            Integer.parseInt(quantity)
                    );

                    // Check if inventory reduction was successful
                    if (inventoryResponse.getStatusCode() != 200) {
                        throw new OurException("Failed to reduce inventory: " + inventoryResponse.getMessage());
                    }
                }
            }

            // Create JobEntry entity
            JobEntry jobEntry = new JobEntry(
                    0, // jobEntryId will be auto-generated
                    jobRegistry,
                    jobEntryDTO.getEntryDate(),
                    jobEntryDTO.getTime(),
                    jobEntryDTO.getTechnicianId(),
                    jobEntryDTO.getDetails(),
                    jobEntryDTO.getManHours()
            );

            jobEntryRepo.save(jobEntry);

            response.setStatusCode(200);
            response.setMessage("Successfully added job entry");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding job entry: " + e.getMessage());
        }

        return response;
    }


    @Override
    public ResponseDTO getAllEntriesOfJob(Integer jobId) {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Object[]> jobEntryDetails = jobEntryRepo.findEntryWithDetails(jobId);
//            List<JobEntry> jobEntries = jobEntryRepo.findByJobRegistry_JobId(jobId);;
            if (!jobEntryDetails.isEmpty()){
                response.setDetails(jobEntryDetails);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Job entries Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving job entries: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateJobEntry(Integer jobEntryId, JobEntryDTO jobEntryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            JobEntry jobEntry = jobEntryRepo.findById(jobEntryId)
                    .orElseThrow(() -> new OurException("Job Entry not found"));

            jobEntry.setEntryDate(jobEntryDTO.getEntryDate());
            jobEntry.setTime(jobEntryDTO.getTime());
            jobEntry.setDetails(jobEntryDTO.getDetails());
            jobEntry.setManHours(jobEntryDTO.getManHours());
            jobEntry.setTechnicianId(jobEntryDTO.getTechnicianId());

            jobEntryRepo.save(jobEntry);
            response.setStatusCode(200);
            response.setMessage("Successfully updated job entry");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating job entry: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteJobEntry(Integer jobEntryId) {
        ResponseDTO response = new ResponseDTO();

        try {
            jobEntryRepo.findById(jobEntryId).orElseThrow(()->new OurException("Job entry not found"));
            jobEntryRepo.deleteById(jobEntryId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted Job entry");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting Job entry: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getJobEntryById(Integer jobEntryId) {
        ResponseDTO response = new ResponseDTO();

        try {
            JobEntry jobEntry = jobEntryRepo.findById(jobEntryId)
                    .orElseThrow(() -> new OurException("Job entry not found"));

            response.setJobEntry(jobEntry);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Job entry");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Job entry: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getTechnicians(){
        ResponseDTO response = new ResponseDTO();

        try {
            List<OurUsers> result = usersRepo.findByRole( "TECHNICIAN");
            if (!result.isEmpty()){
                response.setOurUsersList(result);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                response.setStatusCode(404);
                response.setMessage("No Staff Found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured: " + e.getMessage());

        }
        return response;
    }

    public ResponseDTO reduceItem(int itemId, int quantity) {

        ResponseDTO response = new ResponseDTO();
        try {
            if (inventoryRepo.existsById(itemId)) {
                Inventory inventory = inventoryRepo.getById(itemId);
                if (inventory.getCount() >= quantity) {
                    inventory.setCount(inventory.getCount() - quantity); // Decrease count
                    inventoryRepo.save(inventory);
                    response.setStatusCode(200);
                    response.setMessage("Successfully reduced inventory item.");
                } else {
                    throw new OurException("Insufficient inventory count.");
                }
            } else {
                throw new OurException("No item found under this id.");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while reducing inventory item: " + e.getMessage());
        }
        return response;
    }
}
