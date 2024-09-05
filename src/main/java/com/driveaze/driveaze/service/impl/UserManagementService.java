package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.auth.LoginRequest;
import com.driveaze.driveaze.entity.OurUsers;
import com.driveaze.driveaze.repository.UsersRepo;
import com.driveaze.driveaze.service.interfac.IUserManagementService;
import com.driveaze.driveaze.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserManagementService implements IUserManagementService {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseDTO employeeRegister(OurUsers employeeRegistrationRequest) {
        ResponseDTO resp = new ResponseDTO();

        try {
            if (employeeRegistrationRequest.getPassword() == null || employeeRegistrationRequest.getPassword().isEmpty()) {
                resp.setStatusCode(400);
                resp.setMessage("Password cannot be null or empty");
                return resp;
            }

            Optional<OurUsers> existingUserByName = usersRepo.findByName(employeeRegistrationRequest.getName());
            if (existingUserByName.isPresent()) {
                resp.setStatusCode(400);
                resp.setMessage("Username is taken!");
                return resp;
            }

            Optional<OurUsers> existingUserByEmail = usersRepo.findByEmail(employeeRegistrationRequest.getEmail());
            if (existingUserByEmail.isPresent()) {
                resp.setStatusCode(400);
                resp.setMessage("Email is already registered!");
                return resp;
            }

            OurUsers ourUser = new OurUsers();
            ourUser.setEmail(employeeRegistrationRequest.getEmail());
            ourUser.setRole(employeeRegistrationRequest.getRole());
            ourUser.setName(employeeRegistrationRequest.getName());
            ourUser.setContactNumber(employeeRegistrationRequest.getContactNumber());
            ourUser.setPassword(passwordEncoder.encode(employeeRegistrationRequest.getPassword()));
            OurUsers ourUsersResult = usersRepo.save(ourUser);
            if (ourUsersResult.getId()>0) {
                resp.setOurUsers((ourUsersResult));
                resp.setMessage("User registered successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ResponseDTO customerRegister(OurUsers customerRegistrationRequest) {
        ResponseDTO resp = new ResponseDTO();

        try {
            Optional<OurUsers> existingUserByEmail = usersRepo.findByEmail(customerRegistrationRequest.getEmail());
            if (existingUserByEmail.isPresent()) {
                resp.setStatusCode(400);
                resp.setMessage("Email is already registered!");
                return resp;
            }

            OurUsers ourUser = new OurUsers();
            ourUser.setEmail(customerRegistrationRequest.getEmail());
            ourUser.setRole("CUSTOMER");
            ourUser.setName(customerRegistrationRequest.getName());
            ourUser.setContactNumber(customerRegistrationRequest.getContactNumber());
            ourUser.setPassword(passwordEncoder.encode(customerRegistrationRequest.getPassword()));
            OurUsers ourUsersResult = usersRepo.save(ourUser);
            if (ourUsersResult.getId()>0) {
                resp.setOurUsers((ourUsersResult));
                resp.setMessage("User registered successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ResponseDTO login(LoginRequest loginRequest) {
        ResponseDTO response = new ResponseDTO();

        try {
            Optional<OurUsers> existingUserByEmail = usersRepo.findByEmail(loginRequest.getEmail());
            if (!existingUserByEmail.isPresent()) {
                response.setStatusCode(400);
                response.setMessage("Email not exists!");
                return response;
            }

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );
            } catch (BadCredentialsException e) {
                response.setStatusCode(400);
                response.setMessage("Incorrect password!");
                return response;
            }

            var user = usersRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
//            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
//            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully logged in");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAllEmployees() {
        ResponseDTO reqRes = new ResponseDTO();

        try {
            List<OurUsers> result = usersRepo.findByRoleOrRoleOrRoleOrRoleOrRoleOrRole( "ADMIN", "SUPERVISOR", "RECEPTIONIST", "MANAGER", "WAREHOUSE_KEEPER", "TECHNICIAN");
            if (!result.isEmpty()){
                reqRes.setOurUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No Users Found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured: " + e.getMessage());
            return reqRes;
        }
    }

    @Override
    public ResponseDTO getAllCustomers() {
        ResponseDTO reqRes = new ResponseDTO();

        try {
            List<OurUsers> result = usersRepo.findByRole( "CUSTOMER");
            if (!result.isEmpty()){
                reqRes.setOurUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No Users Found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured: " + e.getMessage());
            return reqRes;
        }
    }

    @Override
    public ResponseDTO getUsersById(int userId) {
        ResponseDTO reqRes = new ResponseDTO();

        try {
            OurUsers usersById = usersRepo.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
            reqRes.setOurUsers(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured: " + e.getMessage());
        }
        return reqRes;
    }

    @Override
    public ResponseDTO deleteUser(int userId) {
        ResponseDTO reqRes = new ResponseDTO();

        try {
            Optional<OurUsers> userOptional = usersRepo.findById(userId);
            if (userOptional.isPresent()) {
                usersRepo.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    @Override
    public ResponseDTO updateUser(int userId, OurUsers updatedUser) {
        ResponseDTO reqRes = new ResponseDTO();

        try {
            Optional<OurUsers> userOptional = usersRepo.findById(userId);
            if (userOptional.isPresent()) {
                OurUsers existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                OurUsers savedUser = usersRepo.save(existingUser);
                reqRes.setOurUsers(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }

    @Override
    public ResponseDTO getMyInfo(String email) {
        ResponseDTO reqRes = new ResponseDTO();

        try {
            Optional<OurUsers> userOptional = usersRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                reqRes.setOurUsers(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;
    }
}
