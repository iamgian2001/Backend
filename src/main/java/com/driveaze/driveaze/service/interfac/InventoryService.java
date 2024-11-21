package com.driveaze.driveaze.service.interfac;
import com.driveaze.driveaze.dto.InventoryDTO;
import java.util.List;
public interface InventoryService {
    String addInventoryItem(InventoryDTO inventoryDTO);
    String updateInventoryItem(InventoryDTO inventoryDTO);
    List<InventoryDTO> getAllItems();
    String deleteInventoryItem(int itemId);
}