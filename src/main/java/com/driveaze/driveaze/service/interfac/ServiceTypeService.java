package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.ServiceTypeDTO;

public interface ServiceTypeService {
    ResponseDTO addNewServiceType(ServiceTypeDTO serviceTypeDTO);

    ResponseDTO getAllServiceTypes();

    ResponseDTO updateServiceType(Integer serviceId, ServiceTypeDTO serviceTypeDTO);

    ResponseDTO deleteServiceType(Integer serviceId);

    ResponseDTO getServiceTypeById(Integer serviceId);
}
