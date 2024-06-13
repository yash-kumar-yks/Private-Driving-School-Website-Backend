package com.drivingschool.RegisterdUsers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DrivingUser, Long> {
    Optional<DrivingUser> findByEmail(String email); 
}
