package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.ServiceTypeDTO;
import com.driveaze.driveaze.entity.ServiceTypes;
import org.springframework.data.domain.Page;

public interface ServiceTypeService {
    ResponseDTO addNewServiceType(ServiceTypeDTO serviceTypeDTO);

    ResponseDTO getAllServiceTypes();

    ResponseDTO updateServiceType(Integer serviceId, ServiceTypeDTO serviceTypeDTO);

    ResponseDTO deleteServiceType(Integer serviceId);

    ResponseDTO getServiceTypeById(Integer serviceId);

    Page<ServiceTypes> findServiceTypesWithPaginationAndSorting(int offset);
}
