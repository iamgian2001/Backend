package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.OurUsers;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String expirationTime;
    private String name;
    private String role;
    private String email;
    private String contactNumber;
    private OurUsers ourUsers;
    private List<OurUsers> ourUsersList;
    private CustomerVehicle customerVehicle;
    private List<CustomerVehicle> customerVehicleList;

}
