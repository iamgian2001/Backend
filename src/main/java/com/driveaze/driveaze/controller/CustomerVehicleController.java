package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.service.CustomerVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-vehicle")
@CrossOrigin
public class CustomerVehicleController {

    @Autowired
    private CustomerVehicleService customerVehicleService;

    @PostMapping(path = "/save" )
    public String saveCustomerVehicle(@RequestBody CustomerVehicleDTO customerVehicleDTO){
        customerVehicleService.addCustomerVehicle(customerVehicleDTO);
        return "Saved";
    }
}
