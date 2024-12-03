package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.JobRegistryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.JobRegistry;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobRegistryService {

    ResponseDTO addNewJob(JobRegistryDTO jobRegistryDTO);

    ResponseDTO getAllJobs();
    ResponseDTO getJobs();

    List<JobRegistry> findJobRegistriessWithSorting();

    Page<JobRegistry> findJobRegistriessWithPagination(int offset, int pageSize);

    Page<JobRegistry> findJobRegistriesWithPaginationAndSorting(int offset);

    ResponseDTO updateJob(Integer jobId, JobRegistryDTO jobRegistryDTO);

    ResponseDTO deleteJob(Integer jobId);

    ResponseDTO getJobById(Integer jobId);

    Page<JobRegistry> getAllJobsWithPaginationByVehicleId(int vehicleId, int offset);
}
