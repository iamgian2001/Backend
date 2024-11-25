package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.JobEntryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

public interface JobEntryService {
    ResponseDTO addNewJobEntry(JobEntryDTO jobEntryDTO);

    ResponseDTO getAllEntriesOfJob(Integer jobId);

    ResponseDTO updateJobEntry(Integer jobEntryId, JobEntryDTO jobEntryDTO);

    ResponseDTO deleteJobEntry(Integer jobEntryId);

    ResponseDTO getJobEntryById(Integer jobEntryId);
}
