package com.driveaze.driveaze.service.impl;
import com.driveaze.driveaze.dto.InventoryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Inventory;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.service.interfac.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.driveaze.driveaze.repository.InventoryRepo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvetoryServiceIMPL implements InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;
    @Override
    public ResponseDTO addInventoryItem(InventoryDTO inventoryDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            Inventory inventory = new Inventory(
                    inventoryDTO.getItemId(),
                    inventoryDTO.getName(),
                    inventoryDTO.getCount(),
                    inventoryDTO.getInitialCount(),
                    inventoryDTO.getSellingPrice()
            );
            if(!inventoryRepo.existsByName(inventory.getName())){
                inventoryRepo.save(inventory);
                response.setStatusCode(200);
                response.setMessage("Successfully added inventory item");
            }else{
                System.out.println("item already exists");
                response.setStatusCode(400);
                response.setMessage("item already exists");
            }
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while adding inventory item: " + e.getMessage());
        }
        return response;
    }
    @Override
    public ResponseDTO updateInventoryItem(InventoryDTO inventoryDTO) {
        ResponseDTO response = new ResponseDTO();
        try{
            if(inventoryRepo.existsById(inventoryDTO.getItemId())){
                Inventory inventory=inventoryRepo.getById(inventoryDTO.getItemId());
                if((inventory.getName().equals(inventoryDTO.getName()) && inventoryRepo.existsByName(inventoryDTO.getName()))
                        || (!inventory.getName().equals(inventoryDTO.getName()) && !inventoryRepo.existsByName(inventoryDTO.getName()))){
    //                Inventory inventory=inventoryRepo.getById(inventoryDTO.getItemId());
                    inventory.setName(inventoryDTO.getName());
                    inventory.setCount(inventoryDTO.getCount());
                    inventory.setInitialCount(inventoryDTO.getInitialCount());
                    inventory.setSellingPrice(inventoryDTO.getSellingPrice());
                    inventoryRepo.save(inventory);
                    response.setStatusCode(200);
                    response.setMessage("Successfully updated inventory item");
                }else{
                    System.out.println("item name already exists");
                    throw new OurException("item name already exists");
                }
            }else{
                System.out.println("No item found");
                throw new OurException("No item found");
            }
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating item: " + e.getMessage());
        }
        return response;
    }
    @Override
    public ResponseDTO getAllItems() {
        ResponseDTO response = new ResponseDTO();
        try{
            List<Inventory> items = inventoryRepo.findAll();
//            for(Inventory inventory : items){
//                InventoryDTO inventoryDTO = new InventoryDTO(
//                        inventory.getItemId(),
//                        inventory.getName(),
//                        inventory.getCount(),
//                        inventory.getInitialCount(),
//                        inventory.getSellingPrice()
//                );
//                inventoryDTOList.add(inventoryDTO);
//            }
            if (!items.isEmpty()){
                response.setInventoryItemList(items);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No inventory items Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving inventory items: " + e.getMessage());
        }

        return response;
    }
    @Override
    public ResponseDTO deleteInventoryItem(int itemId) {
        ResponseDTO response = new ResponseDTO();
        try {
            if(inventoryRepo.existsById(itemId)){
                inventoryRepo.deleteById(itemId);
                response.setStatusCode(200);
                response.setMessage("Successfully deleted vehicle");
            }else{
                System.out.println("No item found under this id");
                throw new OurException("No item found under this id.");
            }
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while deleting inventory item: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO refillItem(int itemId, int quantity) {
        ResponseDTO response = new ResponseDTO();
        try {
            if (inventoryRepo.existsById(itemId)) {
                Inventory inventory = inventoryRepo.getById(itemId);
                inventory.setCount(inventory.getCount() + quantity); // Increase count
                inventoryRepo.save(inventory);
                response.setStatusCode(200);
                response.setMessage("Successfully refilled inventory item.");
            } else {
                throw new OurException("No item found under this id.");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while refilling inventory item: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO reduceItem(int itemId, int quantity) {
        ResponseDTO response = new ResponseDTO();
        try {
            if (inventoryRepo.existsById(itemId)) {
                Inventory inventory = inventoryRepo.getById(itemId);
                if (inventory.getCount() >= quantity) {
                    inventory.setCount(inventory.getCount() - quantity); // Decrease count
                    inventoryRepo.save(inventory);
                    response.setStatusCode(200);
                    response.setMessage("Successfully reduced inventory item.");
                } else {
                    throw new OurException("Insufficient inventory count.");
                }
            } else {
                throw new OurException("No item found under this id.");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while reducing inventory item: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getInventoryStatistics() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<Inventory> allItems = inventoryRepo.findAll();

            int totalItems = allItems.size();
            int overStockItems = 0;
            int belowTenItems = 0;

            for (Inventory item : allItems) {
                // Assuming overstock is when current count exceeds initial count
                if (item.getCount() > item.getInitialCount()) {
                    overStockItems++;
                }

                // Items with count below 10
                if (item.getCount() < 10) {
                    belowTenItems++;
                }
            }

            // Prepare response with statistics
            Map<String, Integer> statistics = new HashMap<>();
            statistics.put("totalItems", totalItems);
            statistics.put("overStockItems", overStockItems);
            statistics.put("belowTenItems", belowTenItems);
            statistics.put("normalStock", totalItems-overStockItems-belowTenItems);

            response.setDetails(statistics);
            response.setStatusCode(200);
            response.setMessage("Inventory statistics retrieved successfully");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving inventory statistics: " + e.getMessage());
        }

        return response;
    }

}