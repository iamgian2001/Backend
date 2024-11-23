package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.JobRegistryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.interfac.JobRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-registry")
@CrossOrigin
public class JobRegistryController {

    @Autowired
    private JobRegistryService jobRegistryService;

    @PostMapping(path = "/create" )
    public ResponseEntity<ResponseDTO> addNewJob(@RequestBody JobRegistryDTO jobRegistryDTO){
        return ResponseEntity.ok(jobRegistryService.addNewJob(jobRegistryDTO));
    }

    @PutMapping(path = "/update/{jobId}")
    public ResponseEntity<ResponseDTO> updateJob(@PathVariable Integer jobId, @RequestBody JobRegistryDTO jobRegistryDTO) {
        ResponseDTO responseDTO = jobRegistryService.updateJob(jobId, jobRegistryDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<ResponseDTO> deleteJob(@PathVariable Integer jobId) {
        return ResponseEntity.ok(jobRegistryService.deleteJob(jobId));
    }

    @GetMapping("/get-all-jobs")
    public ResponseEntity<ResponseDTO> getAllJobs() {
        return ResponseEntity.ok(jobRegistryService.getAllJobs());
    }

    @GetMapping("/get-job/{jobId}")
    public ResponseEntity<ResponseDTO> getJobById(@PathVariable Integer jobId) {
        return ResponseEntity.ok(jobRegistryService.getJobById(jobId));
    }

}
