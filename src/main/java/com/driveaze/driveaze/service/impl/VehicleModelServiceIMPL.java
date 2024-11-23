package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.VehicleModelDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.VehicleModel;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.VehicleModelRepo;
import com.driveaze.driveaze.service.interfac.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleModelServiceIMPL implements VehicleModelService {

    @Autowired
    private VehicleModelRepo vehicleModelRepo;

    @Override
    public ResponseDTO addNewVehicleModel(VehicleModelDTO vehicleModelDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            VehicleModel vehicleModel = new VehicleModel(
                    vehicleModelDTO.getModelId(),
                    vehicleModelDTO.getVehicleBrand(),
                    vehicleModelDTO.getModelName(),
                    vehicleModelDTO.getFuelType(),
                    vehicleModelDTO.getRegisteredDate()
            );

            if(!vehicleModelRepo.existsByModelName(vehicleModel.getModelName())){
                vehicleModelRepo.save(vehicleModel);
                response.setStatusCode(200);
                response.setMessage("Successfully added vehicle model");
            }else{
                response.setStatusCode(400);
                response.setMessage("vehicle model already exists");
            }
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while adding vehicle model: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAllVehicleModels() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<VehicleModel> vehicleModels = vehicleModelRepo.findAll();
            if (!vehicleModels.isEmpty()){
                response.setVehicleModelList(vehicleModels);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Vehicle models Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving vehicle models: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateVehicleModel(Integer modelId, VehicleModelDTO vehicleModelDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            Optional<VehicleModel> existingVehicleModelByName = vehicleModelRepo.findByModelName(vehicleModelDTO.getModelName());
            if (existingVehicleModelByName.isPresent()) {
                response.setStatusCode(400);
                response.setMessage("Vehicle Model Already Exists!");
                return response;
            }

            VehicleModel vehicleModel = vehicleModelRepo.findById(modelId)
                    .orElseThrow(() -> new OurException("Vehicle model not found"));

            vehicleModel.setVehicleBrand(vehicleModelDTO.getVehicleBrand());
            vehicleModel.setModelName(vehicleModelDTO.getModelName());
            vehicleModel.setFuelType(vehicleModelDTO.getFuelType());
            vehicleModel.setRegisteredDate(vehicleModelDTO.getRegisteredDate());

            vehicleModelRepo.save(vehicleModel);
            response.setStatusCode(200);
            response.setMessage("Successfully updated vehicle model");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating vehicle model: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteVehicleModel(Integer modelId) {
        ResponseDTO response = new ResponseDTO();

        try {
            vehicleModelRepo.findById(modelId).orElseThrow(()->new OurException("vehicle model not found"));
            vehicleModelRepo.deleteById(modelId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted vehicle model");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting vehicle model: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getVehicleModelById(Integer modelId) {
        ResponseDTO response = new ResponseDTO();

        try {
            VehicleModel vehicleModel = vehicleModelRepo.findById(modelId)
                    .orElseThrow(() -> new OurException("Vehicle Model not found"));

            response.setVehicleModel(vehicleModel);
            response.setStatusCode(200);
            response.setMessage("Successfully Found vehicle model");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving vehicle model: " + e.getMessage());
        }
        return response;
    }
}
