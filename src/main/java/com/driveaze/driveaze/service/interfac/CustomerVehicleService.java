package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

public interface CustomerVehicleService {

    ResponseDTO addCustomerVehicle(CustomerVehicleDTO customerVehicleDTO);

    ResponseDTO getAllCustomerVehicles();

    ResponseDTO updateCustomerVehicle(Integer vehicleId, CustomerVehicleDTO customerVehicleDTO);

    ResponseDTO deleteCustomerVehicle(Integer vehicleId);

    ResponseDTO getCustomerVehicleById(Integer vehicleId);
}
