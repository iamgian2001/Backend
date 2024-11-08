package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.SupplierDTO;

public interface SupplierService {
    ResponseDTO addNewSupplier(SupplierDTO supplierDTO);

    ResponseDTO getAllSuppliers();

    ResponseDTO updateSuppliers(Integer supplierId, SupplierDTO supplierDTO);

    ResponseDTO deleteSuppliers(Integer supplierId);

    ResponseDTO getSupplierById(Integer supplierId);
}
