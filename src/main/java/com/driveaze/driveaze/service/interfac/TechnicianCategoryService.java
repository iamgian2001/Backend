package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.TechnicianCategoryDTO;
import com.driveaze.driveaze.dto.VehicleModelDTO;
import com.driveaze.driveaze.entity.Supplier;
import com.driveaze.driveaze.entity.TechnicianCategory;
import org.springframework.data.domain.Page;

public interface TechnicianCategoryService {
    ResponseDTO addNewTechnicianCategory(TechnicianCategoryDTO technicianCategoryDTO);

    ResponseDTO getAllTechnicianCategories();

    ResponseDTO updateTechnicianCategory(Integer technicianCategoryId, TechnicianCategoryDTO technicianCategoryDTO);

    ResponseDTO deleteTechnicianCategory(Integer technicianCategoryId);

    ResponseDTO getTechnicianCategoryById(Integer technicianCategoryId);

    Page<TechnicianCategory> findTechnicianCategoriesWithPaginationAndSorting(int offset);
}
