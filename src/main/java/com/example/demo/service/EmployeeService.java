package com.example.demo.service;

import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service component responsible for loading employee user details during authentication.
 *
 * <p>This class implements {@link UserDetailsService}, which is a core Spring Security
 * interface used to retrieve user-specific data. During login, Spring Security calls
 * this service to locate a user by username and obtain the corresponding
 * {@link UserDetails} object.
 *
 * <p>In this implementation, the username supplied by Spring Security is treated as
 * the employee's email address.
 *
 * <p><b>Role in the authentication process:</b>
 * <ul>
 *     <li>Receives a username from the security framework</li>
 *     <li>Searches the repository for a matching employee record</li>
 *     <li>Returns the employee details if found</li>
 *     <li>Throws an exception if no matching user exists</li>
 * </ul>
 *
 * <p>This class may also contain additional business methods related to employee
 * management, such as CRUD operations, though those responsibilities are often
 * separated into dedicated service methods in a full application design.
 */
@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

    /**
     * Repository used to access employee data from the persistence layer.
     */
    private final EmployeeRepository empRepo;

    /**
     * Loads a user by username for Spring Security authentication.
     *
     * <p>The provided username is interpreted as the employee's email address.
     * If an employee with the specified email is not found, a
     * {@link UsernameNotFoundException} is thrown.
     *
     * @param username the username presented during authentication
     * @return the matching {@link UserDetails} object
     * @throws UsernameNotFoundException if no employee is found for the given username
     */
    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return empRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    /*
     * In a complete application, methods for creating, reading, updating,
     * and deleting employee records would typically be added here or in
     * separate service classes depending on the application's architecture.
     */
}