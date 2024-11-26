package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.JobRegistryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

public interface JobRegistryService {

    ResponseDTO addNewJob(JobRegistryDTO jobRegistryDTO);

    ResponseDTO getAllJobs();
    ResponseDTO getJobs();

    ResponseDTO updateJob(Integer jobId, JobRegistryDTO jobRegistryDTO);

    ResponseDTO deleteJob(Integer jobId);

    ResponseDTO getJobById(Integer jobId);
}
