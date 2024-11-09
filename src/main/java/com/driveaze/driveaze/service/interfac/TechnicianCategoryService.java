package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.TechnicianCategoryDTO;
import com.driveaze.driveaze.dto.VehicleModelDTO;

public interface TechnicianCategoryService {
    ResponseDTO addNewTechnicianCategory(TechnicianCategoryDTO technicianCategoryDTO);

    ResponseDTO getAllTechnicianCategories();

    ResponseDTO updateTechnicianCategory(Integer technicianCategoryId, TechnicianCategoryDTO technicianCategoryDTO);

    ResponseDTO deleteTechnicianCategory(Integer technicianCategoryId);

    ResponseDTO getTechnicianCategoryById(Integer technicianCategoryId);
}
