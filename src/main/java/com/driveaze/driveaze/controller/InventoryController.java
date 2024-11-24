package com.driveaze.driveaze.controller;
import com.driveaze.driveaze.dto.InventoryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.interfac.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @PostMapping(path = "/add" )
    public ResponseEntity<ResponseDTO> addItem(@RequestBody InventoryDTO inventoryDTO){
        return ResponseEntity.ok(inventoryService.addInventoryItem(inventoryDTO));
    }
    @PutMapping(path = "/update")
    public  ResponseEntity<ResponseDTO> updateItem(@RequestBody InventoryDTO inventoryDTO) {
        return ResponseEntity.ok(inventoryService.updateInventoryItem(inventoryDTO));
    }
    @GetMapping(path = "/getAll")
    public ResponseEntity<ResponseDTO> getAllItems(){
        return ResponseEntity.ok(inventoryService.getAllItems());
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteItem(@PathVariable(value = "id") int itemId){
        return ResponseEntity.ok(inventoryService.deleteInventoryItem(itemId));
    }

    @PostMapping(path = "/refill")
    public ResponseEntity<ResponseDTO> refillItem(@RequestParam int itemId, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.refillItem(itemId, quantity));
    }

    @PostMapping(path = "/reduce")
    public ResponseEntity<ResponseDTO> reduceItem(@RequestParam int itemId, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.reduceItem(itemId, quantity));
    }
}