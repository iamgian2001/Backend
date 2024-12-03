package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.ServiceTypeDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.ServiceTypes;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.ServiceTypeRepo;
import com.driveaze.driveaze.service.interfac.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ServiceTypeServiceIMPL implements ServiceTypeService {

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    @Override
    public ResponseDTO addNewServiceType(ServiceTypeDTO serviceTypeDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            // Validate serviceName to ensure it's not null or empty
            if (serviceTypeDTO.getServiceName() == null || serviceTypeDTO.getServiceName().trim().isEmpty()) {
                response.setStatusCode(400);
                response.setMessage("Service Name cannot be null or empty");
                return response;
            }

            // Map DTO to Entity
            ServiceTypes serviceTypes = new ServiceTypes();
            serviceTypes.setServiceId(serviceTypeDTO.getServiceId());
            serviceTypes.setServiceName(serviceTypeDTO.getServiceName().trim());
            serviceTypes.setRegisteredDate(serviceTypeDTO.getRegisteredDate() != null
                    ? serviceTypeDTO.getRegisteredDate()
                    : LocalDate.now());
            serviceTypes.setRegisteredTime(serviceTypeDTO.getRegisteredTime() != null
                    ? serviceTypeDTO.getRegisteredTime()
                    : LocalTime.now());

            // Check if Service Name already exists
            if (!serviceTypeRepo.existsByServiceName(serviceTypes.getServiceName())) {
                // Save the Service Type
                serviceTypeRepo.save(serviceTypes);
                response.setStatusCode(200);
                response.setMessage("Successfully added service type");
            } else {
                response.setStatusCode(400);
                response.setMessage("Service Type already exists");
            }

        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding Service Type: " + e.getMessage());
        }

        return response;
    }


    @Override
    public ResponseDTO getAllServiceTypes() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<ServiceTypes> serviceTypes = serviceTypeRepo.findAll();
            if (!serviceTypes.isEmpty()){
                response.setServiceTypesList(serviceTypes);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Service Types Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Service Types: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateServiceType(Integer serviceId, ServiceTypeDTO serviceTypeDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            ServiceTypes serviceTypes = new ServiceTypes(
                    serviceTypeDTO.getServiceId(),
                    serviceTypeDTO.getServiceName(),
                    serviceTypeDTO.getRegisteredDate(),
                    serviceTypeDTO.getRegisteredTime()
            );

            if (serviceTypeRepo.existsByServiceName(serviceTypes.getServiceName())) {
                response.setStatusCode(400);
                response.setMessage("Service type Already Exists!");
                return response;
            }

            ServiceTypes serviceTypes1 = serviceTypeRepo.findById(serviceId)
                    .orElseThrow(() -> new OurException("Service type not found"));

            serviceTypes1.setServiceName(serviceTypeDTO.getServiceName());

            serviceTypeRepo.save(serviceTypes1);
            response.setStatusCode(200);
            response.setMessage("Successfully updated Service Type");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating service type: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteServiceType(Integer serviceId) {
        ResponseDTO response = new ResponseDTO();

        try {
            serviceTypeRepo.findById(serviceId).orElseThrow(()->new OurException("Service Type not found"));
            serviceTypeRepo.deleteById(serviceId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted Service type");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting Service type: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getServiceTypeById(Integer serviceId) {
        ResponseDTO response = new ResponseDTO();

        try {
            ServiceTypes serviceTypes = serviceTypeRepo.findById(serviceId)
                    .orElseThrow(() -> new OurException("Service type not found"));

            response.setServiceTypes(serviceTypes);
            response.setStatusCode(200);
            response.setMessage("Successfully Found service Type");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Service Type: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Page<ServiceTypes> findServiceTypesWithPaginationAndSorting(int offset) {
        return serviceTypeRepo.findAll(PageRequest.of(offset, 10).withSort(Sort.by(Sort.Order.desc("registeredDate"), Sort.Order.desc("registeredTime"))));
    }
}
