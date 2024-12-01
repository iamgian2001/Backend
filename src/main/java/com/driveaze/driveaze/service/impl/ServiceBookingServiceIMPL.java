package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ServiceBookingDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.OurUsers;
import com.driveaze.driveaze.entity.ServiceBooking;
import com.driveaze.driveaze.repository.CustomerVehicleRepo;
import com.driveaze.driveaze.repository.ServiceBookingRepo;
import com.driveaze.driveaze.repository.UsersRepo;
import com.driveaze.driveaze.service.interfac.ServiceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceBookingServiceIMPL implements ServiceBookingService {

    @Autowired
    private  UsersRepo usersRepo;

    @Autowired
    private CustomerVehicleRepo customerVehicleRepo;

    @Autowired
    private ServiceBookingRepo serviceBookingRepo;


    @Override
    public List<ServiceBookingDTO> getCustomerBookings() {
        List<String> vehicleList = new ArrayList<>();
        List<ServiceBookingDTO> serviceBookingDTOs = new ArrayList<>();
        String user_phone = "";


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        List<OurUsers> users = new usersRepo.findAll();
        for (OurUsers user : users) {
            if(user.getUsername().equals(loggedInUsername)){
                user_phone = user.getContactNumber();
                break;
            }
        }
        List<ServiceBooking> serviceBookings = new serviceBookingRepo.findAll();
        List<CustomerVehicle> customerVehicles = new customerVehicleRepo.findAll();
        for (CustomerVehicle customerVehicle : customerVehicles) {
            if(customerVehicle.getOwnerPhone()==user_phone){
                vehicleList.add(customerVehicle.getVehicleNo());
            }
        }

        for (ServiceBooking serviceBooking : serviceBookings) {
            for(String vehicle : vehicleList){
                if(serviceBooking.getVehicleNo().equals(vehicle)){
                    ServiceBookingDTO serviceBookingDTO = new ServiceBookingDTO(
                            serviceBooking.getBookingId(),
                            serviceBooking.getVehicleNo(),
                            serviceBooking.getVehicleBrand(),
                            serviceBooking.getVehicleModel(),
                            serviceBooking.getStartedDate(),
                            serviceBooking.getStartTime(),
                            serviceBooking.getCustomerId()
                    );
                    serviceBookingDTOs.add(serviceBookingDTO);

                }
            }
        }



        return serviceBookingDTOs;
    }
}
