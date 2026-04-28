package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Provides security configuration for the application using Spring Security.
 *
 * <p>This class demonstrates a basic role-based access control (RBAC) setup
 * using the modern {@link SecurityFilterChain} bean approach instead of the
 * deprecated {@code WebSecurityConfigurerAdapter}, so if you see an article
 * on the internet or an LLM generated code is not using SecurityFilterChain,
 * then it is wrong.
 *
 * <p>The configuration defines authorization rules for specific endpoints and
 * enables HTTP Basic authentication. It also exposes a password encoder bean
 * for secure password hashing.
 *
 * <p><b>Key Concepts Demonstrated:</b>
 * <ul>
 *     <li>Declarative security configuration using {@link HttpSecurity}</li>
 *     <li>Role-based authorization using request matchers</li>
 *     <li>Bean-based security configuration in Spring</li>
 *     <li>Password hashing using BCrypt</li>
 * </ul>
 */
@Configuration
public class Config {

    /**
     * Defines the security filter chain used to process incoming HTTP requests.
     *
     * <p>This method configures how requests are authorized based on user roles.
     * The rules are evaluated in order:
     *
     * <ul>
     *     <li>Requests to {@code /todos} require either {@code ROLE_USER} or {@code ROLE_ADMIN}</li>
     *     <li>Requests to {@code /users} require {@code ROLE_ADMIN}</li>
     *     <li>All other requests require authentication</li>
     * </ul>
     *
     * <p>Spring Security automatically prefixes roles with {@code ROLE_}, so
     * specifying {@code "USER"} corresponds to {@code ROLE_USER}.
     *
     * <p>HTTP Basic authentication is enabled for simplicity. This is suitable
     * for testing and learning environments but is not recommended for production
     * systems without HTTPS.
     *
     * @param http the {@link HttpSecurity} object used to configure web-based security
     * @return the configured {@link SecurityFilterChain} instance
     * @throws Exception if an error occurs during security configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/todos").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/users").hasRole("ADMIN") // This endpoint is non-existent as I didn't have time to create the implementation
                                .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * Creates a {@link PasswordEncoder} bean for encoding user passwords.
     *
     * <p>This implementation uses {@link BCryptPasswordEncoder}, which applies
     * a strong one-way hashing algorithm with salting. BCrypt is widely recommended
     * for secure password storage due to its resistance to brute-force attacks.
     *
     * <p>In practice, this encoder should be used whenever storing or validating
     * user credentials.
     *
     * @return a {@link PasswordEncoder} implementation based on BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}