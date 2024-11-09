package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.TechnicianCategoryDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.TechnicianCategory;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.TechnicianCategoryRepo;
import com.driveaze.driveaze.service.interfac.TechnicianCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianCategoryServiceIMPL implements TechnicianCategoryService {

    @Autowired
    private TechnicianCategoryRepo technicianCategoryRepo;

    @Override
    public ResponseDTO addNewTechnicianCategory(TechnicianCategoryDTO technicianCategoryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            TechnicianCategory technicianCategory = new TechnicianCategory(
                    technicianCategoryDTO.getTechnicianCategoryId(),
                    technicianCategoryDTO.getTechnicianCategoryName()
            );

            if(!technicianCategoryRepo.existsByTechnicianCategoryName(technicianCategory.getTechnicianCategoryName())){
                technicianCategoryRepo.save(technicianCategory);
                response.setStatusCode(200);
                response.setMessage("Successfully added Technician category");
            }else{
                response.setStatusCode(400);
                response.setMessage("Technician category already exists");
            }
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while adding Technician category: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAllTechnicianCategories() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<TechnicianCategory> technicianCategories = technicianCategoryRepo.findAll();
            if (!technicianCategories.isEmpty()){
                response.setTechnicianCategoryList(technicianCategories);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Technician category Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Technician categories: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseDTO updateTechnicianCategory(Integer technicianCategoryId, TechnicianCategoryDTO technicianCategoryDTO) {
        ResponseDTO response = new ResponseDTO();

        try {
            Optional<TechnicianCategory> existingTechnicianCategoryByName = technicianCategoryRepo.findByTechnicianCategoryName(technicianCategoryDTO.getTechnicianCategoryName());
            if (existingTechnicianCategoryByName.isPresent()) {
                response.setStatusCode(400);
                response.setMessage("Technician category Already Exists!");
                return response;
            }

            TechnicianCategory technicianCategory = technicianCategoryRepo.findById(technicianCategoryId)
                    .orElseThrow(() -> new OurException("Technician category not found"));

            technicianCategory.setTechnicianCategoryName(technicianCategoryDTO.getTechnicianCategoryName());

            technicianCategoryRepo.save(technicianCategory);
            response.setStatusCode(200);
            response.setMessage("Successfully updated Technician category");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating Technician category: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteTechnicianCategory(Integer technicianCategoryId) {
        ResponseDTO response = new ResponseDTO();

        try {
            technicianCategoryRepo.findById(technicianCategoryId).orElseThrow(()->new OurException("Technician Category not found"));
            technicianCategoryRepo.deleteById(technicianCategoryId);
            response.setStatusCode(200);
            response.setMessage("Successfully deleted Technician category");
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting Technician category: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getTechnicianCategoryById(Integer technicianCategoryId) {
        ResponseDTO response = new ResponseDTO();

        try {
            TechnicianCategory technicianCategory = technicianCategoryRepo.findById(technicianCategoryId)
                    .orElseThrow(() -> new OurException("Technician Category not found"));

            response.setTechnicianCategory(technicianCategory);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Technician category");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Technician category: " + e.getMessage());
        }
        return response;
    }
}
