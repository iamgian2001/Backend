package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

public interface CustomerVehicleService {

    ResponseDTO addCustomerVehicle(CustomerVehicleDTO customerVehicleDTO);

    String updateCustomerVehicle(Integer vehicleId, CustomerVehicleDTO customerVehicleDTO);
}
