package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ServiceBookingDTO;

import java.util.List;

public interface ServiceBookingService {
    List<ServiceBookingDTO> getCustomerBookings();
}
