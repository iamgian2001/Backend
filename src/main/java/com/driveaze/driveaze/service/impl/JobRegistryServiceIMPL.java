package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.JobRegistryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.JobRegistryRepo;
import com.driveaze.driveaze.service.interfac.JobRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobRegistryServiceIMPL implements JobRegistryService {

    @Autowired
    private JobRegistryRepo jobRegistryRepo;

    @Override
    public ResponseDTO addNewJob(JobRegistryDTO jobRegistryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            JobRegistry jobRegistry = new JobRegistry(
                    jobRegistryDTO.getId(),
                    jobRegistryDTO.getVehicleId(),
                    jobRegistryDTO.getStartedDate(),
                    jobRegistryDTO.getStartTime().toLocalTime(),
                    jobRegistryDTO.getFinishedDate(),
                    jobRegistryDTO.getCustomerId(),
                    jobRegistryDTO.getSupervisorId(),
                    jobRegistryDTO.getJobStatus(),
                    jobRegistryDTO.getJobDescription()
            );

            // Check if a non-completed job already exists for the vehicle
            if (!jobRegistryRepo.existsByVehicleIdAndJobStatus(jobRegistry.getVehicleId(), 0)) {
                jobRegistryRepo.save(jobRegistry);
                response.setStatusCode(200);
                response.setMessage("Successfully added job registry");
            } else {
                response.setStatusCode(400);
                response.setMessage("A non-completed job already exists for this vehicle");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding job: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO getAllJobs() {
        ResponseDTO response = new ResponseDTO();

        try {
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
    public ResponseDTO updateJob(Integer jobId, JobRegistryDTO jobRegistryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            JobRegistry jobRegistry = jobRegistryRepo.findById(jobId)
                    .orElseThrow(() -> new OurException("Job not found"));

            jobRegistry.setCustomerId(jobRegistryDTO.getCustomerId());
            jobRegistry.setSupervisorId(jobRegistryDTO.getSupervisorId());
            jobRegistry.setJobStatus(jobRegistryDTO.getJobStatus());
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
}
