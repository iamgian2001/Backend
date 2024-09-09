package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
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
    public ResponseDTO addCustomerVehicle(CustomerVehicleDTO customerVehicleDTO){

        ResponseDTO response = new ResponseDTO();

        try {
            CustomerVehicle customerVehicle = new CustomerVehicle(
                    customerVehicleDTO.getVehicleId(),
                    customerVehicleDTO.getVehicleNo(),
                    customerVehicleDTO.getVehicleBrand(),
                    customerVehicleDTO.getVehicleModel(),
                    customerVehicleDTO.getCustomerId()
            );

            if(!customerVehicleRepo.existsByVehicleNo(customerVehicle.getVehicleNo())){
                customerVehicleRepo.save(customerVehicle);
                response.setStatusCode(200);
                response.setMessage("Successfully added customer vehicle");
            }else{
                response.setStatusCode(400);
                response.setMessage("Customer vehicle already exists");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while adding vehicle: " + e.getMessage());
        }
        return response;
    }

    @Override
    public String updateCustomerVehicle(Integer vehicleId, CustomerVehicleDTO customerVehicleDTO) {
        return null;
    }
}
