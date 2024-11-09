package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ManHourPricingDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.interfac.ManHourPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/man-hour-pricing")
@CrossOrigin
public class ManHourPricingController {

    @Autowired
    private ManHourPricingService manHourPricingService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewManHourPricing(@RequestBody ManHourPricingDTO manHourPricingDTO){
        return ResponseEntity.ok(manHourPricingService.addNewManHourPricing(manHourPricingDTO));
    }

    @PutMapping(path = "/update/{serviceCategoryId}")
    public ResponseEntity<ResponseDTO> updateManHourPricing(@PathVariable Integer serviceCategoryId, @RequestBody ManHourPricingDTO manHourPricingDTO) {
        ResponseDTO responseDTO = manHourPricingService.updateManHourPricing(serviceCategoryId, manHourPricingDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{serviceCategoryId}")
    public ResponseEntity<ResponseDTO> deleteManHourPricing(@PathVariable Integer serviceCategoryId) {
        return ResponseEntity.ok(manHourPricingService.deleteManHourPricing(serviceCategoryId));
    }

    @GetMapping("/get-all-pricings")
    public ResponseEntity<ResponseDTO> getAllManHourPricings() {
        return ResponseEntity.ok(manHourPricingService.getAllManHourPricings());
    }

    @GetMapping("/get-pricing/{serviceCategoryId}")
    public ResponseEntity<ResponseDTO> getManHourPricingById(@PathVariable Integer serviceCategoryId) {
        return ResponseEntity.ok(manHourPricingService.getManHourPricingById(serviceCategoryId));
    }
}
