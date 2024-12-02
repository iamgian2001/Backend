package com.driveaze.driveaze.controller;
import com.driveaze.driveaze.dto.BookingDTO;
import com.driveaze.driveaze.dto.OurUserDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.impl.UserManagementService;
import com.driveaze.driveaze.service.interfac.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserManagementService userManagementService;

    @PostMapping(path = "/add" )
    public ResponseEntity<ResponseDTO> addItem(@RequestBody BookingDTO bookingDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ResponseDTO userDetails = userManagementService.getMyInfo(email);
        ResponseDTO responseDTO = bookingService.addBooking(bookingDTO,userDetails);
        return ResponseEntity.ok(responseDTO);
    }
    @PutMapping(path = "/update")
    public  ResponseEntity<ResponseDTO> updateItem(@RequestBody BookingDTO bookingDTO) {
        ResponseDTO responseDTO = bookingService.updateBooking(bookingDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping(path = "/getAll")
    public ResponseEntity<ResponseDTO> getAllItems(){
        ResponseDTO responseDTO = bookingService.getAllBooking();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path="/getCustomerBookings")
    public ResponseEntity<ResponseDTO> getCustomerBookings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ResponseDTO userDetails = userManagementService.getMyInfo(email);
        ResponseDTO responseDTO = bookingService.getCustomerBookings(userDetails);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping(path="/updateWaitingBooking")
    public ResponseEntity<ResponseDTO> updateWaitingBooking(@RequestBody BookingDTO bookingDTO){
        ResponseDTO responseDTO = bookingService.updateWaitingBooking(bookingDTO);
        return ResponseEntity.ok(responseDTO);
    }
}