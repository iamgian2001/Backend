package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.repository.CustomerVehicleRepo;
import com.driveaze.driveaze.service.interfac.CustomerVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerVehicleServiceIMPL implements CustomerVehicleService {

    @Autowired
    private CustomerVehicleRepo customerVehicleRepo;

    @Override
    public void addCustomerVehicle(CustomerVehicleDTO customerVehicleDTO){
        CustomerVehicle customerVehicle = new CustomerVehicle(
                customerVehicleDTO.getVehicleId(),
                customerVehicleDTO.getVehicleNo(),
                customerVehicleDTO.getBrand(),
                customerVehicleDTO.getModel(),
                customerVehicleDTO.getCustomerId()
        );

        if(!customerVehicleRepo.existsById(customerVehicle.getVehicleId())){
            customerVehicleRepo.save(customerVehicle);
        }else{
            System.out.println("Vehicle already exists");
        }
    }
}
