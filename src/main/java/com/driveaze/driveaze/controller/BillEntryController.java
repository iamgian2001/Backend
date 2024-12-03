package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.BillEntryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.interfac.BillEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill-entry")
@CrossOrigin
public class BillEntryController {

    @Autowired
    private BillEntryService billEntryService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewBillEntry(@RequestBody BillEntryDTO billEntryDTO){
        return ResponseEntity.ok(billEntryService.addNewBillEntry(billEntryDTO));
    }

    @PutMapping(path = "/update/{billEntryId}")
    public ResponseEntity<ResponseDTO> updateBillEntry(@PathVariable Integer billEntryId, @RequestBody BillEntryDTO billEntryDTO) {
        ResponseDTO responseDTO = billEntryService.updateBillEntry(billEntryId, billEntryDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{billEntryId}")
    public ResponseEntity<ResponseDTO> deleteBillEntry(@PathVariable Integer billEntryId) {
        return ResponseEntity.ok(billEntryService.deleteBillEntry(billEntryId));
    }

    @GetMapping("/get-all-bill-entries")
    public ResponseEntity<ResponseDTO> getAllBillEntries() {
        return ResponseEntity.ok(billEntryService.getAllBillEntries());
    }

    @GetMapping("/get-all-bill-entries-by-bill-id/{billId}")
    public ResponseEntity<ResponseDTO> getAllBillEntriesByBillId(@PathVariable Integer billId) {
        return ResponseEntity.ok(billEntryService.getAllBillEntriesByBillId(billId));
    }

    @GetMapping("/get-bill-entry/{billEntryId}")
    public ResponseEntity<ResponseDTO> getBillEntryById(@PathVariable Integer billEntryId) {
        return ResponseEntity.ok(billEntryService.getBillEntryById(billEntryId));
    }
}
