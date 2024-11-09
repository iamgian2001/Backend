package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.VehicleBrandDTO;

public interface VehicleBrandService {
    ResponseDTO addNewVehicleBrand(VehicleBrandDTO vehicleBrandDTO);

    ResponseDTO getAllVehicleBrands();

    ResponseDTO updateVehicleBrand(Integer brandId, VehicleBrandDTO vehicleBrandDTO);

    ResponseDTO deleteVehicleBrand(Integer brandId);

    ResponseDTO getVehicleBrandById(Integer brandId);
}
