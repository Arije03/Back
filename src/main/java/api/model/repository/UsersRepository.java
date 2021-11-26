/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.repository;

import java.util.Optional;
import api.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Makhlouf Helali
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Returns the user Object with the username sent in arguments,
     * which may or may not contain a non-null User object.
     * If the User with username is present,  isPresent() will return true and
     * get() will return the User Object. 
     * @param username
     * @return 
     */
    Optional<Users> findByUsername(String username);
    
    /**
     * Returns the user Object with the phone number sent in arguments,
     * which may or may not contain a non-null User object.
     * If the User with phone number is present,  isPresent() will return true and
     * get() will return the User Object. 
     * @param phoneNumber
     * @return 
     */
    Optional<Users> findByPhoneNumber(String phoneNumber);

    /**
     * Return true if the user with the username sent in arguments exists
     * @param username
     * @return 
     */
    Boolean existsByUsername(String username);

    /**
     * Return true if the user with the phoneNumber sent in arguments exists;
     * @param phoneNumber
     * @return 
     */
    Boolean existsByPhoneNumber(String phoneNumber);

}
