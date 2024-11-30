package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.JobEntryItemDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

public interface JobEntryItemService {
    ResponseDTO addNewJobEntryItem(JobEntryItemDTO jobEntryItemDTO);

    ResponseDTO updateJobEntryItem(Integer jobEntryItemId, JobEntryItemDTO jobEntryItemDTO);

    ResponseDTO deleteJobEntryItem(Integer jobEntryItemId);

    ResponseDTO getJobEntryItemById(Integer jobEntryItemId);

}
