package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.SupplierDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.Supplier;
import com.driveaze.driveaze.service.interfac.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
@CrossOrigin
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewSupplier(@RequestBody SupplierDTO supplierDTO){
        return ResponseEntity.ok(supplierService.addNewSupplier(supplierDTO));
    }

    @PutMapping(path = "/update/{supplierId}")
    public ResponseEntity<ResponseDTO> updateSuppliers(@PathVariable Integer supplierId, @RequestBody SupplierDTO supplierDTO) {
        ResponseDTO responseDTO = supplierService.updateSuppliers(supplierId, supplierDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{supplierId}")
    public ResponseEntity<ResponseDTO> deleteSuppliers(@PathVariable Integer supplierId) {
        return ResponseEntity.ok(supplierService.deleteSuppliers(supplierId));
    }

    @GetMapping("/get-all-suppliers")
    public ResponseEntity<ResponseDTO> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/get-supplier/{supplierId}")
    public ResponseEntity<ResponseDTO> getSupplierById(@PathVariable Integer supplierId) {
        return ResponseEntity.ok(supplierService.getSupplierById(supplierId));
    }

    @GetMapping("/paginationAndSort/{offset}")
    public Page<Supplier> findSuppliersWithPaginationAndSorting(@PathVariable int offset) {
        return supplierService.findSuppliersWithPaginationAndSorting(offset);
    }
}
