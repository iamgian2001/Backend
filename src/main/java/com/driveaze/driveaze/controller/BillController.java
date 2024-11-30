package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.BillDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Bill;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.service.interfac.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
@CrossOrigin
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewBill(@RequestBody BillDTO billDTO){
        return ResponseEntity.ok(billService.addNewBill(billDTO));
    }

    @PutMapping(path = "/update/{billId}")
    public ResponseEntity<ResponseDTO> updateBill(@PathVariable Integer billId, @RequestBody BillDTO billDTO) {
        ResponseDTO responseDTO = billService.updateBill(billId, billDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{billId}")
    public ResponseEntity<ResponseDTO> deleteBill(@PathVariable Integer billId) {
        return ResponseEntity.ok(billService.deleteBill(billId));
    }

    @GetMapping("/get-all-bills")
    public ResponseEntity<ResponseDTO> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/get-bill/{billId}")
    public ResponseEntity<ResponseDTO> getBillById(@PathVariable Integer billId) {
        return ResponseEntity.ok(billService.getBillById(billId));
    }

    @GetMapping("/get-all-bills-with-bill-status/{billStatus}")
    public ResponseEntity<ResponseDTO> getAllBills(@PathVariable Integer billStatus) {
        return ResponseEntity.ok(billService.getAllBillsWithStatus(billStatus));
    }

    @GetMapping("/paginationAndSort/{offset}")
    public Page<Bill> getAllBillsWithPaginationAndStatuses(
            @RequestParam List<Integer> statuses,
            @PathVariable int offset) {
        return billService.findBillsWithPaginationAndSortingAndStatus(statuses, offset);
    }

    @PutMapping(path ="/updatebillstatus/{billId}")
    public ResponseEntity<ResponseDTO> updateBillStatus(@PathVariable Integer billId, @RequestBody int status) {
        ResponseDTO responseDTO = billService.updateBillStatus(billId, status);
        return ResponseEntity.ok(responseDTO);
    }

}
