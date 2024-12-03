package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Bill;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.CustomerVehicleRepo;
import com.driveaze.driveaze.repository.JobRegistryRepo;
import com.driveaze.driveaze.service.interfac.CustomerVehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CustomerVehicleServiceIMPL implements CustomerVehicleService {

    @Autowired
    private CustomerVehicleRepo customerVehicleRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JobRegistryRepo jobRegistryRepo;

    @Override
    public ResponseDTO addCustomerVehicle(CustomerVehicleDTO customerVehicleDTO){

        ResponseDTO response = new ResponseDTO();

        try {

            Integer vehicleMilage = customerVehicleDTO.getVehicleMilage() == null ? 0 : customerVehicleDTO.getVehicleMilage();

            CustomerVehicle customerVehicle = new CustomerVehicle(
                    customerVehicleDTO.getVehicleId(),
                    customerVehicleDTO.getVehicleNo(),
                    customerVehicleDTO.getOwnerName(),
                    customerVehicleDTO.getOwnerEmail(),
                    customerVehicleDTO.getOwnerPhone(),
                    vehicleMilage,
                    customerVehicleDTO.getVehicleBrandId(),
                    customerVehicleDTO.getVehicleModelId(),
                    customerVehicleDTO.getRegisteredDate(),
                    customerVehicleDTO.getRegisteredTime().toLocalTime()
            );

            if(!customerVehicleRepo.existsByVehicleNo(customerVehicle.getVehicleNo())){
                customerVehicleRepo.save(customerVehicle);
                response.setStatusCode(200);
                response.setMessage("Successfully added customer vehicle");
            }else{
                response.setStatusCode(400);
                response.setMessage("Customer vehicle already exists");
            }
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while adding vehicle: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAllCustomerVehicles() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<CustomerVehicle> customerVehicles = customerVehicleRepo.findAll();
            if (!customerVehicles.isEmpty()){
                response.setCustomerVehicleList(customerVehicles);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Vehicles Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving vehicles: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateCustomerVehicle(Integer vehicleId, CustomerVehicleDTO customerVehicleDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            Optional<CustomerVehicle> existingVehicleByNumber = customerVehicleRepo.findByVehicleNo(customerVehicleDTO.getVehicleNo());

            CustomerVehicle customerVehicle = customerVehicleRepo.findById(vehicleId)
                    .orElseThrow(() -> new OurException("Customer vehicle not found"));

            // Check if the phone number already exists for another vehicle, excluding the current vehicle
            Optional<CustomerVehicle> existingCustomerVehicleByOwnerPhone = customerVehicleRepo
                    .findByOwnerPhone(customerVehicleDTO.getOwnerPhone());

            if (existingCustomerVehicleByOwnerPhone.isPresent() &&
                    !Objects.equals(existingCustomerVehicleByOwnerPhone.get().getVehicleId(), vehicleId)) {
                response.setStatusCode(400);
                response.setMessage("Contact Number Already Exists for another vehicle!");
                return response;
            }

            if (existingVehicleByNumber.isPresent() &&
                    !Objects.equals(existingVehicleByNumber.get().getVehicleId(), vehicleId)) {
                response.setStatusCode(400);
                response.setMessage("Vehicle Number Already Exists for another vehicle!");
                return response;
            }

            if (customerVehicleDTO.getVehicleMilage() != null &&
                    customerVehicleDTO.getVehicleMilage() >= customerVehicle.getVehicleMilage()) {

                // Update the vehicle details
                customerVehicle.setVehicleNo(customerVehicleDTO.getVehicleNo());
                customerVehicle.setOwnerName(customerVehicleDTO.getOwnerName());
                customerVehicle.setOwnerEmail(customerVehicleDTO.getOwnerEmail());
                customerVehicle.setOwnerPhone(customerVehicleDTO.getOwnerPhone());
                customerVehicle.setVehicleMilage(customerVehicleDTO.getVehicleMilage());
                customerVehicle.setVehicleBrandId(customerVehicleDTO.getVehicleBrandId());
                customerVehicle.setVehicleModelId(customerVehicleDTO.getVehicleModelId());

                // Save the updated vehicle
                customerVehicleRepo.save(customerVehicle);

                response.setStatusCode(200);
                response.setMessage("Successfully updated vehicle");
            } else {
                response.setStatusCode(400);
                response.setMessage("Updated mileage must be greater than the current mileage");
            }
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating vehicle: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteCustomerVehicle(Integer vehicleId) {
        ResponseDTO response = new ResponseDTO();

        try {
            boolean jobsExist = jobRegistryRepo.existsByVehicleId(vehicleId);

            if (jobsExist) {
                response.setStatusCode(400); // Bad Request
                response.setMessage("Cannot Delete vehicle related job registries exist.");
                return response;
            }

            customerVehicleRepo.findById(vehicleId).orElseThrow(()->new OurException("Customer vehicle not found"));
            customerVehicleRepo.deleteById(vehicleId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted vehicle");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting vehicle: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getCustomerVehicleById(Integer vehicleId) {
        ResponseDTO response = new ResponseDTO();

        try {
            CustomerVehicle customerVehicle = customerVehicleRepo.findById(vehicleId)
                    .orElseThrow(() -> new OurException("Customer vehicle not found"));

            response.setCustomerVehicle(customerVehicle);
            response.setStatusCode(200);
            response.setMessage("Successfully Found customer vehicle");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving vehicle: " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<CustomerVehicle> searchByVehicleNo(String query) {
        return customerVehicleRepo.searchByVehicleNo(query);
    }

    @Override
    public Page<CustomerVehicle> findVehiclesWithPaginationAndSorting(int offset) {
        return customerVehicleRepo.findAll(PageRequest.of(offset, 9).withSort(Sort.by(Sort.Order.desc("registeredDate"), Sort.Order.desc("registeredTime"))));
    }


    @Override
    public Page<CustomerVehicle> getAllVehiclesWithPaginationByCustomerPhoneNo(String phoneNo, int offset) {
        return customerVehicleRepo.findVehiclesByCustomerPhone(
                phoneNo,
                PageRequest.of(offset, 6).withSort(Sort.by(Sort.Order.desc("registeredDate"), Sort.Order.desc("registeredTime")))
        );
    }

}
