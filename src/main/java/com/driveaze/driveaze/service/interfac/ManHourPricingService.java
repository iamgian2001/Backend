package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ManHourPricingDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

public interface ManHourPricingService {
    ResponseDTO addNewManHourPricing(ManHourPricingDTO manHourPricingDTO);

    ResponseDTO getAllManHourPricings();

    ResponseDTO updateManHourPricing(Integer serviceCategoryId, ManHourPricingDTO manHourPricingDTO);

    ResponseDTO deleteManHourPricing(Integer serviceCategoryId);

    ResponseDTO getManHourPricingById(Integer serviceCategoryId);
}
