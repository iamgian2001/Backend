package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.interfac.CustomerVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-vehicle")
@CrossOrigin
public class CustomerVehicleController {

    @Autowired
    private CustomerVehicleService customerVehicleService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> saveCustomerVehicle(@RequestBody CustomerVehicleDTO customerVehicleDTO){
        return ResponseEntity.ok(customerVehicleService.addCustomerVehicle(customerVehicleDTO));
    }

    @PutMapping(path = "/update/{vehicleId}")
    public ResponseEntity<ResponseDTO> updateCustomerVehicle(@PathVariable Integer vehicleId, @RequestBody CustomerVehicleDTO customerVehicleDTO) {
        ResponseDTO responseDTO = customerVehicleService.updateCustomerVehicle(vehicleId, customerVehicleDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{vehicleId}")
    public ResponseEntity<ResponseDTO> deleteCustomerVehicle(@PathVariable Integer vehicleId) {
        return ResponseEntity.ok(customerVehicleService.deleteCustomerVehicle(vehicleId));
    }

    @GetMapping("/get-all-vehicles")
    public ResponseEntity<ResponseDTO> getAllCustomerVehicles() {
        return ResponseEntity.ok(customerVehicleService.getAllCustomerVehicles());
    }

    @GetMapping("/get-vehicle/{vehicleId}")
    public ResponseEntity<ResponseDTO> getCustomerVehicleById(@PathVariable Integer vehicleId) {
        return ResponseEntity.ok(customerVehicleService.getCustomerVehicleById(vehicleId));
    }
}
