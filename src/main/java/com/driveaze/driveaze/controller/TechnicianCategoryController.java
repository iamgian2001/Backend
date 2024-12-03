package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.TechnicianCategoryDTO;
import com.driveaze.driveaze.entity.Supplier;
import com.driveaze.driveaze.entity.TechnicianCategory;
import com.driveaze.driveaze.service.interfac.TechnicianCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/technician-category")
@CrossOrigin
public class TechnicianCategoryController {

    @Autowired
    private TechnicianCategoryService technicianCategoryService;

    @PostMapping(path = "/save" )
    public ResponseEntity<ResponseDTO> addNewTechnicianCategory(@RequestBody TechnicianCategoryDTO technicianCategoryDTO){
        return ResponseEntity.ok(technicianCategoryService.addNewTechnicianCategory(technicianCategoryDTO));
    }

    @PutMapping(path = "/update/{technicianCategoryId}")
    public ResponseEntity<ResponseDTO> updateTechnicianCategory(@PathVariable Integer technicianCategoryId, @RequestBody TechnicianCategoryDTO technicianCategoryDTO) {
        ResponseDTO responseDTO = technicianCategoryService.updateTechnicianCategory(technicianCategoryId, technicianCategoryDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{technicianCategoryId}")
    public ResponseEntity<ResponseDTO> deleteTechnicianCategory(@PathVariable Integer technicianCategoryId) {
        return ResponseEntity.ok(technicianCategoryService.deleteTechnicianCategory(technicianCategoryId));
    }

    @GetMapping("/get-all-technician-categories")
    public ResponseEntity<ResponseDTO> getAllTechnicianCategories() {
        return ResponseEntity.ok(technicianCategoryService.getAllTechnicianCategories());
    }

    @GetMapping("/get-technician-category/{technicianCategoryId}")
    public ResponseEntity<ResponseDTO> getTechnicianCategoryById(@PathVariable Integer technicianCategoryId) {
        return ResponseEntity.ok(technicianCategoryService.getTechnicianCategoryById(technicianCategoryId));
    }

    @GetMapping("/paginationAndSort/{offset}")
    public Page<TechnicianCategory> findTechnicianCategoriesWithPaginationAndSorting(@PathVariable int offset) {
        return (Page<TechnicianCategory>) technicianCategoryService.findTechnicianCategoriesWithPaginationAndSorting(offset);
    }
}
