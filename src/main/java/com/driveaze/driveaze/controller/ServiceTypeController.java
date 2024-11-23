package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.ServiceTypeDTO;
import com.driveaze.driveaze.service.interfac.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-type")
@CrossOrigin
public class ServiceTypeController {
    @Autowired
    private ServiceTypeService serviceTypeService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewServiceType(@RequestBody ServiceTypeDTO serviceTypeDTO){
        return ResponseEntity.ok(serviceTypeService.addNewServiceType(serviceTypeDTO));
    }

    @PutMapping(path = "/update/{serviceId}")
    public ResponseEntity<ResponseDTO> updateServiceType(@PathVariable Integer serviceId, @RequestBody ServiceTypeDTO serviceTypeDTO) {
        ResponseDTO responseDTO = serviceTypeService.updateServiceType(serviceId, serviceTypeDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{serviceId}")
    public ResponseEntity<ResponseDTO> deleteServiceType(@PathVariable Integer serviceId) {
        return ResponseEntity.ok(serviceTypeService.deleteServiceType(serviceId));
    }

    @GetMapping("/get-all-service-types")
    public ResponseEntity<ResponseDTO> getAllServiceTypes() {
        return ResponseEntity.ok(serviceTypeService.getAllServiceTypes());
    }

    @GetMapping("/get-service-type/{serviceId}")
    public ResponseEntity<ResponseDTO> getServiceTypeById(@PathVariable Integer serviceId) {
        return ResponseEntity.ok(serviceTypeService.getServiceTypeById(serviceId));
    }
}
