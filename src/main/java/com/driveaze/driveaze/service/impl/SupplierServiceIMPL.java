package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.SupplierDTO;
import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.entity.Supplier;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.SupplierRepo;
import com.driveaze.driveaze.service.interfac.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceIMPL implements SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    @Override
    public ResponseDTO addNewSupplier(SupplierDTO supplierDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            Supplier supplier = new Supplier(
                    supplierDTO.getSupplierId(),
                    supplierDTO.getSupplierEmail(),
                    supplierDTO.getContactNumber(),
                    supplierDTO.getSupplierName(),
                    supplierDTO.getAddress(),
                    supplierDTO.getPartsDescription(),
                    supplierDTO.getRegisteredDate()
            );

            // Check if a supplier available with same email
            if (!supplierRepo.existsBySupplierEmail(supplier.getSupplierEmail())) {
                supplierRepo.save(supplier);
                response.setStatusCode(200);
                response.setMessage("Successfully added supplier");
            } else {
                response.setStatusCode(400);
                response.setMessage("A supplier with this email already exists");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding supplier: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO getAllSuppliers() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Supplier> suppliers = supplierRepo.findAll();
            if (!suppliers.isEmpty()){
                response.setSupplierList(suppliers);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Suppliers Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving suppliers: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateSuppliers(Integer supplierId, SupplierDTO supplierDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            Supplier supplier = supplierRepo.findById(supplierId)
                    .orElseThrow(() -> new OurException("Supplier not found"));

            supplier.setSupplierEmail(supplierDTO.getSupplierEmail());
            supplier.setContactNumber(supplierDTO.getContactNumber());
            supplier.setSupplierName(supplierDTO.getSupplierName());
            supplier.setAddress(supplierDTO.getAddress());
            supplier.setPartsDescription(supplierDTO.getPartsDescription());

            supplierRepo.save(supplier);
            response.setStatusCode(200);
            response.setMessage("Successfully updated supplier");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating supplier: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteSuppliers(Integer supplierId) {
        ResponseDTO response = new ResponseDTO();

        try {
            supplierRepo.findById(supplierId).orElseThrow(()->new OurException("Supplier not found"));
            supplierRepo.deleteById(supplierId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted Supplier");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting Supplier: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getSupplierById(Integer supplierId) {
        ResponseDTO response = new ResponseDTO();

        try {
            Supplier supplier = supplierRepo.findById(supplierId)
                    .orElseThrow(() -> new OurException("Supplier not found"));

            response.setSupplier(supplier);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Supplier");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Supplier: " + e.getMessage());
        }
        return response;
    }
}
