package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Employee} entities.
 *
 * <p>This interface extends {@link JpaRepository}, which provides a complete
 * set of CRUD (Create, Read, Update, Delete) operations without requiring
 * explicit implementation.
 *
 * <p><b>Key Concepts:</b>
 * <ul>
 *     <li>Spring Data JPA repository abstraction</li>
 *     <li>Automatic query generation and custom query definition</li>
 *     <li>Use of {@link Optional} to handle potentially absent results</li>
 * </ul>
 *
 * <p>The repository is annotated with {@link Repository}, indicating that it is
 * a Spring-managed component responsible for data access.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Retrieves an {@link Employee} entity based on the provided email.
     *
     * <p>This method uses a custom JPQL (Java Persistence Query Language) query
     * to fetch the employee. JPQL operates on entity objects rather than
     * directly on database tables.
     *
     * <p><b>Query Explanation:</b>
     * <ul>
     *     <li>{@code SELECT e} selects the employee entity</li>
     *     <li>{@code FROM Employee e} specifies the entity type</li>
     *     <li>{@code WHERE e.email = :email} filters by email using a named parameter</li>
     * </ul>
     *
     * <p>The use of {@link Optional} helps avoid {@code NullPointerException}
     * by explicitly representing the possibility that no matching employee exists.
     *
     * @param email the email address used to search for the employee
     * @return an {@link Optional} containing the matching employee if found,
     *         or empty if no match exists
     */
    @Query("""
    SELECT e
    FROM Employee e
    WHERE e.email = :email
    """)
    Optional<Employee> findByEmail(String email);
}