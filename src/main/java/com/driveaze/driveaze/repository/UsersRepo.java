package com.driveaze.driveaze.repository;


import com.driveaze.driveaze.entity.OurUsers;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<OurUsers, Integer> {

    Optional<OurUsers> findByEmail(String email);

    Optional<OurUsers> findByName(String name);

    List<OurUsers> findByRole(String customer);

    List<OurUsers> findByRoleOrRoleOrRoleOrRoleOrRoleOrRole(String admin, String supervisor, String receptionist, String manager, String warehouseKeeper, String technician);

    List<OurUsers> findByRoleOrRoleOrRoleOrRole(String supervisor, String receptionist, String warehouseKeeper, String technician);
}
