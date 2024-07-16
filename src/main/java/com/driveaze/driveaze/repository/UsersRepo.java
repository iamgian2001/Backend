package com.driveaze.driveaze.repository;


import com.driveaze.driveaze.entity.OurUsers;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<OurUsers, Integer> {

    Optional<OurUsers> findByEmail(String email);
    Optional<OurUsers> findByName(String name);


    List<OurUsers> findByRoleOrRoleOrRole(String admin, String supervisor, String receptionist);

    List<OurUsers> findByRole(String customer);
}
