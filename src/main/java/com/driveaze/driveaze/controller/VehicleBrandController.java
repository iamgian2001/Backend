package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.VehicleBrandDTO;
import com.driveaze.driveaze.service.interfac.VehicleBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle-brand")
@CrossOrigin
public class VehicleBrandController {
    @Autowired
    private VehicleBrandService vehicleBrandService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewVehicleBrand(@RequestBody VehicleBrandDTO vehicleBrandDTO){
        return ResponseEntity.ok(vehicleBrandService.addNewVehicleBrand(vehicleBrandDTO));
    }

    @PutMapping(path = "/update/{brandId}")
    public ResponseEntity<ResponseDTO> updateVehicleBrand(@PathVariable Integer brandId, @RequestBody VehicleBrandDTO vehicleBrandDTO) {
        ResponseDTO responseDTO = vehicleBrandService.updateVehicleBrand(brandId, vehicleBrandDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{brandId}")
    public ResponseEntity<ResponseDTO> deleteVehicleBrand(@PathVariable Integer brandId) {
        return ResponseEntity.ok(vehicleBrandService.deleteVehicleBrand(brandId));
    }

    @GetMapping("/get-all-vehicle-brands")
    public ResponseEntity<ResponseDTO> getAllVehicleBrands() {
        return ResponseEntity.ok(vehicleBrandService.getAllVehicleBrands());
    }

    @GetMapping("/get-vehicle-brand/{brandId}")
    public ResponseEntity<ResponseDTO> getVehicleBrandById(@PathVariable Integer brandId) {
        return ResponseEntity.ok(vehicleBrandService.getVehicleBrandById(brandId));
    }
}
