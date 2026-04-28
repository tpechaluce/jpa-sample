package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Represents an employee entity in the system.
 *
 * <p>This class serves a dual purpose:
 * <ul>
 *     <li>It is a JPA entity mapped to a database table</li>
 *     <li>It implements {@link UserDetails}, allowing it to be used directly
 *     by Spring Security for authentication and authorization</li>
 * </ul>
 *
 * <p><b>About {@link UserDetails}:</b>
 * {@code UserDetails} is a core interface in Spring Security that defines
 * the necessary information about a user required during the authentication process.
 * It acts as an adapter between the application's user model and Spring Security.
 *
 * <p>Key responsibilities of {@code UserDetails} include:
 * <ul>
 *     <li>Providing the username (used for login)</li>
 *     <li>Providing the password (used for credential verification)</li>
 *     <li>Providing granted authorities (roles/permissions)</li>
 *     <li>Indicating account status (e.g., expired, locked, enabled)</li>
 * </ul>
 *
 * <p>By implementing this interface, the {@code Employee} class can be used
 * directly by Spring Security without requiring a separate adapter or wrapper class.
 *
 * <p><b>Annotations Used:</b>
 * <ul>
 *     <li>{@link Entity} marks this class as a JPA entity</li>
 *     <li>Lombok annotations generate boilerplate code such as getters, setters, and constructors</li>
 * </ul>
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;
    private final String fname;
    private final String lname;
    /**
     * Employee's email address.
     * <p>
     * Used as the username for authentication.
     */
    private final String email;
    /**
     * Encrypted password used for authentication.
     */
    private final String password;
    private String phoneNumber;
    private String department;

    /**
     * Collection of roles or permissions granted to the employee.
     *
     * <p>Each authority represents a role (e.g., ROLE_USER, ROLE_ADMIN).
     * The use of {@code ? extends GrantedAuthority} allows flexibility
     * while maintaining type safety, this specifies that the authorities
     * list can accept any object that extends {@code GrantedAuthority}.
     */
    private final List<? extends GrantedAuthority> authorities;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> assignedTasks;

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection of granted authorities
     */
    @Override
    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    /**
     * Returns the password used to authenticate the user.
     *
     * @return the encrypted password (may be null depending on configuration)
     */
    @Override
    public @Nullable String getPassword() {
        return password;
    }
    /**
     * Returns the username, or in this case, the email used to authenticate the user.
     *
     * <p>In this implementation, the email is used as the username.
     *
     * @return the username (email)
     */
    @Override
    @NullMarked
    public String getUsername() {
        return email;
    }
}