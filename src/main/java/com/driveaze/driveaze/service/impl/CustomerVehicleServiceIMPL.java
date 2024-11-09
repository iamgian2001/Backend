package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.CustomerVehicleRepo;
import com.driveaze.driveaze.service.interfac.CustomerVehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerVehicleServiceIMPL implements CustomerVehicleService {

    @Autowired
    private CustomerVehicleRepo customerVehicleRepo;

    @Autowired
    private ModelMapper modelMapper;

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
            if (existingVehicleByNumber.isPresent()) {
                response.setStatusCode(400);
                response.setMessage("Vehicle Number Already Exists!");
                return response;
            }

            CustomerVehicle customerVehicle = customerVehicleRepo.findById(vehicleId)
                    .orElseThrow(() -> new OurException("Customer vehicle not found"));

            customerVehicle.setVehicleNo(customerVehicleDTO.getVehicleNo());
            customerVehicle.setVehicleBrand(customerVehicleDTO.getVehicleBrand());
            customerVehicle.setVehicleModel(customerVehicleDTO.getVehicleModel());
            customerVehicle.setCustomerId(customerVehicleDTO.getCustomerId());

            customerVehicleRepo.save(customerVehicle);
            response.setStatusCode(200);
            response.setMessage("Successfully updated vehicle");
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
}
