package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.BookingDTO;
import com.driveaze.driveaze.dto.ResponseDTO;


public interface BookingService {
    ResponseDTO addBooking(BookingDTO bookingDTO, ResponseDTO userDetails);
    ResponseDTO updateBookiing(BookingDTO bookingDTO);
    ResponseDTO getAllBooking();


    ResponseDTO getCustomerBookings(ResponseDTO userDetails);

}