package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Bill;
import com.driveaze.driveaze.entity.CustomerVehicle;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerVehicleService {

    ResponseDTO addCustomerVehicle(CustomerVehicleDTO customerVehicleDTO);

    ResponseDTO getAllCustomerVehicles();

    ResponseDTO updateCustomerVehicle(Integer vehicleId, CustomerVehicleDTO customerVehicleDTO);

    ResponseDTO deleteCustomerVehicle(Integer vehicleId);

    ResponseDTO getCustomerVehicleById(Integer vehicleId);

    List<CustomerVehicle> searchByVehicleNo(String query);

    Page<CustomerVehicle> findVehiclesWithPaginationAndSorting(int offset);

    Page<CustomerVehicle> getAllVehiclesWithPaginationByCustomerPhoneNo(String phoneNo, int offset);
}
