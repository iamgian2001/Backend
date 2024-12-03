package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.JobEntryItemDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.interfac.JobEntryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-entry-item")
@CrossOrigin
public class JobEntryItemController {

    @Autowired
    private JobEntryItemService jobEntryItemService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewJobEntryItem(@RequestBody JobEntryItemDTO jobEntryItemDTO){
        return ResponseEntity.ok(jobEntryItemService.addNewJobEntryItem(jobEntryItemDTO));
    }

    @PutMapping(path = "/update/{jobEntryItemId}")
    public ResponseEntity<ResponseDTO> updateJobEntryItem(@PathVariable Integer jobEntryItemId, @RequestBody JobEntryItemDTO jobEntryItemDTO) {
        ResponseDTO responseDTO = jobEntryItemService.updateJobEntryItem(jobEntryItemId, jobEntryItemDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{jobEntryItemId}")
    public ResponseEntity<ResponseDTO> deleteJobEntryItem(@PathVariable Integer jobEntryItemId) {
        return ResponseEntity.ok(jobEntryItemService.deleteJobEntryItem(jobEntryItemId));
    }

    @GetMapping("/get-job-entry-item/{jobEntryItemId}")
    public ResponseEntity<ResponseDTO> getJobEntryItemById(@PathVariable Integer jobEntryItemId) {
        return ResponseEntity.ok(jobEntryItemService.getJobEntryItemById(jobEntryItemId));
    }
}
