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

//    @PutMapping(path = "/update/{vehicleId}" )
//    public ResponseEntity<ResponseDTO> updateCustomerVehicle(@PathVariable Integer vehicleId, @RequestBody CustomerVehicleDTO customerVehicleDTO) {
//        return ResponseEntity.ok(customerVehicleService.updateCustomerVehicle(vehicleId, customerVehicleDTO));
//    }

    @PutMapping(path = "/update/{vehicleId}")
    public ResponseEntity<String> updateCustomerVehicle(@PathVariable Integer vehicleId, @RequestBody CustomerVehicleDTO customerVehicleDTO) {
        String responseDTO = customerVehicleService.updateCustomerVehicle(vehicleId, customerVehicleDTO);
        return ResponseEntity.ok(responseDTO);
    }

//    @PutMapping("/admin/update/{userId}")
//    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres) {
//        return ResponseEntity.ok(userService.updateUser(userId, reqres));
//    }
}
