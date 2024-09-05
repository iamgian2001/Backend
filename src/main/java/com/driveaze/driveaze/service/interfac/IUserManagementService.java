package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.auth.LoginRequest;
import com.driveaze.driveaze.entity.OurUsers;

public interface IUserManagementService {

    ResponseDTO employeeRegister(OurUsers employeeRegistrationRequest);
    ResponseDTO customerRegister(OurUsers customerRegistrationRequest);
    ResponseDTO login(LoginRequest loginRequest);
    ResponseDTO getAllEmployees();
    ResponseDTO getAllCustomers();
    ResponseDTO getUsersById(int userId);
    ResponseDTO deleteUser(int userId);
    ResponseDTO updateUser(int userId, OurUsers updatedUser);
    ResponseDTO getMyInfo(String email);

}
