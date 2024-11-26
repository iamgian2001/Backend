package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.JobRegistryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.CustomerVehicleRepo;
import com.driveaze.driveaze.repository.JobRegistryRepo;
import com.driveaze.driveaze.service.interfac.JobRegistryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class JobRegistryServiceIMPL implements JobRegistryService {

    @Autowired
    private JobRegistryRepo jobRegistryRepo;

    @Autowired
    private CustomerVehicleRepo customerVehicleRepo;

    @Override
    public ResponseDTO addNewJob(JobRegistryDTO jobRegistryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            // Step 1: Fetch Customer Vehicle
            CustomerVehicle customerVehicle = customerVehicleRepo.findById(jobRegistryDTO.getVehicleId())
                    .orElseThrow(() -> new OurException("CustomerVehicle not found"));

            // Step 2: Check if a non-completed job already exists for the vehicle
            if (jobRegistryRepo.existsByVehicleIdAndJobStatus(customerVehicle.getVehicleId(), 0)) {
                response.setStatusCode(400);
                response.setMessage("A non-completed job already exists for this vehicle");
                return response;
            }

            // Step 3: Create the JobRegistry entity
            JobRegistry jobRegistry = new JobRegistry(
                    jobRegistryDTO.getJobId(),
                    jobRegistryDTO.getVehicleId(),
                    jobRegistryDTO.getStartedDate(),
                    jobRegistryDTO.getStartTime().toLocalTime(),
                    jobRegistryDTO.getFinishedDate(),
                    jobRegistryDTO.getSupervisorId(), // Correctly map supervisorId here
                    jobRegistryDTO.getServiceTypeId(), // Correctly map serviceTypeId here
                    jobRegistryDTO.getVehicleMilage(),
                    jobRegistryDTO.getJobStatus(),
                    jobRegistryDTO.getJobDescription()
            );

            // Step 4: Update vehicle mileage if the new mileage is greater
            if (jobRegistryDTO.getVehicleMilage() >= customerVehicle.getVehicleMilage()) {
                customerVehicle.setVehicleMilage(jobRegistryDTO.getVehicleMilage());
                customerVehicleRepo.save(customerVehicle); // Save the updated mileage
            } else {
                response.setStatusCode(400);
                response.setMessage("New mileage must be greater than the current mileage");
                return response;
            }

            // Step 5: Save the JobRegistry entity
            jobRegistryRepo.save(jobRegistry);

            // Step 6: Prepare a success response
            response.setStatusCode(200);
            response.setMessage("Successfully added job registry and updated vehicle mileage");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
            throw e; // Rethrow to ensure transaction rollback
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding job: " + e.getMessage());
            throw e; // Rethrow to ensure transaction rollback
        }

        return response;
    }

    @Override
    public ResponseDTO getAllJobs() {
        ResponseDTO response = new ResponseDTO();

        try {
//            List<Object[]> jobDetails = jobRegistryRepo.findJobsWithDetails();

            List<JobRegistry> jobRegistries = jobRegistryRepo.findAll();
            if (!jobRegistries.isEmpty()){
                response.setJobRegistryList(jobRegistries);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Jobs Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving jobs: " + e.getMessage());
        }

        return response;
    }

    @Override
    public List<JobRegistry> findJobRegistriessWithSorting() {
        return jobRegistryRepo.findAll(Sort.by(Sort.Order.desc("startedDate"), Sort.Order.desc("startTime")));
    }

    @Override
    public Page<JobRegistry> findJobRegistriessWithPagination(int offset, int pageSize){
        return jobRegistryRepo.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<JobRegistry> findJobRegistriesWithPaginationAndSorting(int offset){
        return jobRegistryRepo.findAll(PageRequest.of(offset, 10).withSort(Sort.by(Sort.Order.desc("startedDate"), Sort.Order.desc("startTime"))));
    }

    @Override
    public ResponseDTO updateJob(Integer jobId, JobRegistryDTO jobRegistryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            JobRegistry jobRegistry = jobRegistryRepo.findById(jobId)
                    .orElseThrow(() -> new OurException("Job not found"));

            jobRegistry.setVehicleId(jobRegistryDTO.getVehicleId());
            jobRegistry.setJobStatus(jobRegistryDTO.getJobStatus());
            jobRegistry.setSupervisorId(jobRegistryDTO.getSupervisorId());
            jobRegistry.setServiceTypeId(jobRegistryDTO.getServiceTypeId());
            jobRegistry.setVehicleMilage(jobRegistryDTO.getVehicleMilage());
            jobRegistry.setJobDescription(jobRegistryDTO.getJobDescription());

            jobRegistryRepo.save(jobRegistry);
            response.setStatusCode(200);
            response.setMessage("Successfully updated job registry");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating job: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteJob(Integer jobId) {
        ResponseDTO response = new ResponseDTO();

        try {
            jobRegistryRepo.findById(jobId).orElseThrow(()->new OurException("Job registry not found"));
            jobRegistryRepo.deleteById(jobId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted Job Registry");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting Job: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getJobById(Integer jobId) {
        ResponseDTO response = new ResponseDTO();

        try {
            JobRegistry jobRegistry = jobRegistryRepo.findById(jobId)
                    .orElseThrow(() -> new OurException("Job registry not found"));

            response.setJobRegistry(jobRegistry);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Job Registry");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Job: " + e.getMessage());
        }
        return response;
    }


//    @Override
//    public List<JobRegistry> searchByVehicleNoVehicleBrandModelAndAssignedSupervisor(String query) {
//        return jobRegistryRepo.searchByVehicleNoVehicleBrandModelAndAssignedSupervisor(query);
//    }

    @Override
    public ResponseDTO getJobs() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Object[]> jobDetails = jobRegistryRepo.findJobsWithDetails();

//            List<JobRegistry> jobRegistries = jobRegistryRepo.findAll();
            if (!jobDetails.isEmpty()){
                response.setDetails(jobDetails);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Jobs Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving jobs: " + e.getMessage());
        }

        return response;
    }

}
