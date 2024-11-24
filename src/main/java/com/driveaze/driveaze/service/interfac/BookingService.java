package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.BookingDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

import java.util.List;
public interface BookingService {
    ResponseDTO addBooking(BookingDTO bookingDTO);
    ResponseDTO updateBookiing(BookingDTO bookingDTO);
    ResponseDTO getAllBooking();
}