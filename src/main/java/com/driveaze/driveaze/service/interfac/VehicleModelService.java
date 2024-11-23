package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.VehicleModelDTO;

public interface VehicleModelService {
    ResponseDTO addNewVehicleModel(VehicleModelDTO vehicleModelDTO);

    ResponseDTO getAllVehicleModels();

    ResponseDTO updateVehicleModel(Integer modelId, VehicleModelDTO vehicleModelDTO);

    ResponseDTO deleteVehicleModel(Integer modelId);

    ResponseDTO getVehicleModelById(Integer modelId);
}
