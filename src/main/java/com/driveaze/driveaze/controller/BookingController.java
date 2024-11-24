package com.driveaze.driveaze.controller;
import com.driveaze.driveaze.dto.BookingDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.interfac.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping(path = "/add" )
    public ResponseEntity<ResponseDTO> addItem(@RequestBody BookingDTO bookingDTO){
        ResponseDTO responseDTO = bookingService.addBooking(bookingDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @PutMapping(path = "/update")
    public  ResponseEntity<ResponseDTO> updateItem(@RequestBody BookingDTO bookingDTO) {
        ResponseDTO responseDTO = bookingService.updateBookiing(bookingDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping(path = "/getAll")
    public ResponseEntity<ResponseDTO> getAllItems(){
        ResponseDTO responseDTO = bookingService.getAllBooking();
        return ResponseEntity.ok(responseDTO);
    }
}