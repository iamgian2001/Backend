package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.TechnicianCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface TechnicianCategoryRepo extends JpaRepository<TechnicianCategory, Integer> {

    Optional<TechnicianCategory> findByTechnicianCategoryName(String technicianCategoryName);

    boolean existsByTechnicianCategoryName(String technicianCategoryName);

}
