package com.ecloud.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecloud.userservice.entity.User;

/**
 * 
 * @author Musni
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
