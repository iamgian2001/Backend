package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.BookingDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Booking;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.BookingRepo;
import com.driveaze.driveaze.service.interfac.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class BookingServiceIMPL implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Override
    public ResponseDTO addBooking(BookingDTO bookingDTO){

        ResponseDTO response = new ResponseDTO();

        try {

            Booking booking = new Booking(
                    bookingDTO.getBookingId(),
                    bookingDTO.getVehicleNo(),
                    bookingDTO.getBrand(),
                    bookingDTO.getModel(),
                    bookingDTO.getStatus(),
                    bookingDTO.getPreferredDate(),
                    bookingDTO.getPreferredTime()
            );
            bookingRepo.save(booking);
            response.setStatusCode(200);
            response.setMessage("Successfully added booking");

        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while adding booking: " + e.getMessage());
        }
        return response;
    }
    @Override
    public ResponseDTO updateBookiing(BookingDTO bookingDTO){
        ResponseDTO response = new ResponseDTO();

        try {
            if(bookingRepo.existsById(bookingDTO.getBookingId())){
                Booking booking = bookingRepo.getById(bookingDTO.getBookingId());
                booking.setStatus(bookingDTO.getStatus());
                bookingRepo.save(booking);
                response.setStatusCode(200);
                response.setMessage("Successfully updated booking");
            }else{
                throw new OurException("No booking Found");
            }
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating booking: " + e.getMessage());
        }
        return response;
    }
    @Override
    public ResponseDTO getAllBooking(){
        ResponseDTO response = new ResponseDTO();

        try {

            List<Booking> bookings = bookingRepo.findAll();
//            List<BookingDTO> bookingDTOList = new ArrayList<>();
//            for (Booking booking: bookings){
//                BookingDTO bookingDTO = new BookingDTO(
//                        booking.getBookingId(),
//                        booking.getVehicleNo(),
//                        booking.getBrand(),
//                        booking.getModel(),
//                        booking.getStatus(),
//                        booking.getPreferredDate(),
//                        booking.getPreferredTime()
//                );
//                bookingDTOList.add(bookingDTO);
//            }
            if (!bookings.isEmpty()){
                response.setBookingList(bookings);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No booking Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while bookings: " + e.getMessage());
        }

        return response;
    }
}