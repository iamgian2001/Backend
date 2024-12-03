package com.driveaze.driveaze.service.interfac;
import com.driveaze.driveaze.dto.InventoryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

import java.util.List;
public interface InventoryService {
    ResponseDTO addInventoryItem(InventoryDTO inventoryDTO);
    ResponseDTO updateInventoryItem(InventoryDTO inventoryDTO);
    ResponseDTO getAllItems();
    ResponseDTO deleteInventoryItem(int itemId);
    ResponseDTO refillItem(int itemId, int quantity);
    ResponseDTO reduceItem(int itemId, int quantity);
    ResponseDTO getInventoryStatistics();

}