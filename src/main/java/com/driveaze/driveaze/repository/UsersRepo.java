package com.driveaze.driveaze.repository;


import com.driveaze.driveaze.entity.OurUsers;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<OurUsers, Integer> {

    Optional<OurUsers> findByEmail(String email);

    Optional<OurUsers> findByContactNumber(String contactNumber);

    Optional<OurUsers> findByName(String name);

    List<OurUsers> findByRole(String customer);

    List<OurUsers> findByRoleOrRoleOrRoleOrRoleOrRoleOrRole(String admin, String supervisor, String receptionist, String manager, String warehouseKeeper, String technician);

    List<OurUsers> findByRoleOrRoleOrRoleOrRole(String supervisor, String receptionist, String warehouseKeeper, String technician);

//    @Query("SELECT u FROM OurUsers u WHERE u.role = 'SUPERVISOR' AND u.name LIKE %:query%")
//    List<OurUsers> searchBySupervisorName(@Param("query") String query);

    List<OurUsers> findByRoleAndNameContainingIgnoreCase(String role, String name);


}
