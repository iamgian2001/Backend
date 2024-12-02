package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.BookingDTO;
import com.driveaze.driveaze.dto.ResponseDTO;


public interface BookingService {
    ResponseDTO addBooking(BookingDTO bookingDTO, ResponseDTO userDetails);
    ResponseDTO updateBooking(BookingDTO bookingDTO);
    ResponseDTO getAllBooking();


    ResponseDTO getCustomerBookings(ResponseDTO userDetails);

    ResponseDTO updateWaitingBooking(BookingDTO bookingDTO);

    ResponseDTO deleteWaitingBooking(BookingDTO bookingDTO);
}