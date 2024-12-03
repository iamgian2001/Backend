package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Inventory;
import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.entity.OurUsers;
import com.driveaze.driveaze.repository.InventoryRepo;
import com.driveaze.driveaze.repository.JobRegistryRepo;
import com.driveaze.driveaze.repository.UsersRepo;
import com.driveaze.driveaze.service.interfac.DashboardService;
import com.driveaze.driveaze.service.interfac.JobRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class DashboardServiceIMPL implements DashboardService {

    @Autowired
    private JobRegistryRepo jobRegistryRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public ResponseDTO getSupervisorStatistics() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<JobRegistry> allJobs = jobRegistryRepo.findAll();

            int totalJob = allJobs.size();
            int completed = 0;
            int pending = 0;
            int technicianCount = 0 ;

            for (JobRegistry job : allJobs) {
                if (job.getJobStatus() == 0) {
                    pending++;
                }

                if (job.getJobStatus() == 1) {
                    completed++;
                }
            }

            List<OurUsers> allUsers = usersRepo.findAll();

            for (OurUsers user : allUsers) {
                if (Objects.equals(user.getRole(), "TECHNICIAN")) {
                    technicianCount++;
                }
            }


            // Prepare response with statistics
            Map<String, Integer> statistics = new HashMap<>();
            statistics.put("totalJob", totalJob);
            statistics.put("completedJobs", completed);
            statistics.put("pendingJobs", pending);
            statistics.put("technicianCount", technicianCount);

            response.setDetails(statistics);
            response.setStatusCode(200);
            response.setMessage("Supervisor statistics retrieved successfully");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving supervisor statistics: " + e.getMessage());
        }

        return response;
    }

}
