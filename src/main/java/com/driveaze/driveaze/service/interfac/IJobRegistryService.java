package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.dto.JobRegistryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.JobRegistry;

public interface IJobRegistryService {

    void addNewJob(JobRegistryDTO jobRegistryDTO);
}
