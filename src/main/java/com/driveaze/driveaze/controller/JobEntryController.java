package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.JobEntryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.interfac.JobEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-entry")
@CrossOrigin
public class JobEntryController {
    @Autowired
    private JobEntryService jobEntryService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewJobEntry(@RequestBody JobEntryDTO jobEntryDTO){
        return ResponseEntity.ok(jobEntryService.addNewJobEntry(jobEntryDTO));
    }

    @PutMapping(path = "/update/{jobEntryId}")
    public ResponseEntity<ResponseDTO> updateJobEntry(@PathVariable Integer jobEntryId, @RequestBody JobEntryDTO jobEntryDTO) {
        ResponseDTO responseDTO = jobEntryService.updateJobEntry(jobEntryId, jobEntryDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{jobEntryId}")
    public ResponseEntity<ResponseDTO> deleteJobEntry(@PathVariable Integer jobEntryId) {
        return ResponseEntity.ok(jobEntryService.deleteJobEntry(jobEntryId));
    }

    @GetMapping("/get-all-job-entries")
    public ResponseEntity<ResponseDTO> getAllJobEntries() {
        return ResponseEntity.ok(jobEntryService.getAllJobEntries());
    }

    @GetMapping("/get-job-entry/{jobEntryId}")
    public ResponseEntity<ResponseDTO> getJobEntryById(@PathVariable Integer jobEntryId) {
        return ResponseEntity.ok(jobEntryService.getJobEntryById(jobEntryId));
    }
}
