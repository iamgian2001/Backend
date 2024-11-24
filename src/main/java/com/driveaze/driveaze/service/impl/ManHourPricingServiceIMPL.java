package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ManHourPricingDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.Inventory;
import com.driveaze.driveaze.entity.ManHourPricing;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.CustomerVehicleRepo;
import com.driveaze.driveaze.repository.ManHourPricingRepo;
import com.driveaze.driveaze.service.interfac.ManHourPricingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManHourPricingServiceIMPL implements ManHourPricingService {

    @Autowired
    private ManHourPricingRepo manHourPricingRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ResponseDTO addNewManHourPricing(ManHourPricingDTO manHourPricingDTO) {

        ResponseDTO response = new ResponseDTO();

        try {
            ManHourPricing manHourPricing = new ManHourPricing(
                    manHourPricingDTO.getServiceCategoryId(),
                    manHourPricingDTO.getTechnicianCategory(),
                    manHourPricingDTO.getPricePerHour()
            );

            if(!manHourPricingRepo.existsByTechnicianCategory(manHourPricing.getTechnicianCategory())){
                manHourPricingRepo.save(manHourPricing);
                response.setStatusCode(200);
                response.setMessage("Successfully added man hour price");
            }else{
                response.setStatusCode(400);
                response.setMessage("price for this category already exists");
            }
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while adding man hour price: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAllManHourPricings() {

        ResponseDTO response = new ResponseDTO();

        try {
            List<ManHourPricing> manHourPricings = manHourPricingRepo.findAll();
            if (!manHourPricings.isEmpty()){
                response.setManHourPricingList(manHourPricings);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Man Hour Prices Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Man Hour Prices: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateManHourPricing(Integer serviceCategoryId, ManHourPricingDTO manHourPricingDTO) {

        ResponseDTO response = new ResponseDTO();

        try {
            if(manHourPricingRepo.existsById(serviceCategoryId)){
                ManHourPricing manHourPricing=manHourPricingRepo.getById(serviceCategoryId);
                if(!manHourPricing.getTechnicianCategory().equals(manHourPricingDTO.getTechnicianCategory())){
                    Optional<ManHourPricing> existingPricingByTechnicianCategory = manHourPricingRepo.findByTechnicianCategory(manHourPricingDTO.getTechnicianCategory());
                    if (existingPricingByTechnicianCategory.isPresent()) {
                        response.setStatusCode(400);
                        response.setMessage("Technician Category Already Exists!");
                        return response;
                    }
                }

                manHourPricing.setTechnicianCategory(manHourPricingDTO.getTechnicianCategory());
                manHourPricing.setPricePerHour(manHourPricingDTO.getPricePerHour());

                manHourPricingRepo.save(manHourPricing);
                response.setStatusCode(200);
                response.setMessage("Successfully updated Man Hour Pricings");
            }else{
                System.out.println("Man Hour Pricing not found");
                throw new OurException("Man Hour Pricing not found");
            }
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating Man Hour Pricings: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteManHourPricing(Integer serviceCategoryId) {

        ResponseDTO response = new ResponseDTO();

        try {
            if(manHourPricingRepo.existsById(serviceCategoryId)){
                manHourPricingRepo.deleteById(serviceCategoryId);
                response.setStatusCode(200);
                response.setMessage("Successfully deleted Man Hour Pricing");
            }else{
                System.out.println("No Man Hour Pricing found under this id");
                throw new OurException("No Man Hour Pricing found under this id.");
            }
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting Man Hour Pricing: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getManHourPricingById(Integer serviceCategoryId) {

        ResponseDTO response = new ResponseDTO();

        try {
            ManHourPricing manHourPricing = manHourPricingRepo.findById(serviceCategoryId)
                    .orElseThrow(() -> new OurException("Man Hour Pricing not found"));

            response.setManHourPricing(manHourPricing);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Man Hour Pricing");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Man Hour Pricing: " + e.getMessage());
        }
        return response;
    }
}
