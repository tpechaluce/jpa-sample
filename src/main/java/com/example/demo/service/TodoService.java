package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.Todo;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepo;
    private final EmployeeRepository empRepo;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        List<Employee> empList = List.of(
                new Employee("Jane", "Doe", "j.doe@edu.com", passwordEncoder.encode("password123"),
                        List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"))),
                new Employee("John", "Doe","john.d@edu.com", passwordEncoder.encode("password456"),
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))),
                new Employee("Juan", "Dela Cruz", "jdc@edu.com", passwordEncoder.encode("password789"),
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))),
                new Employee("Maria", "de Pedro", "m.de.pedro@edu.com", passwordEncoder.encode("password098"),
                        List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );

        empRepo.saveAll(empList);

        List<Todo> todos = List.of(
                new Todo("Todo 1", LocalDate.of(2026, 3,12), pickRandomEmployee(empList)),
                new Todo("Todo 1", LocalDate.of(2026, 8,4), pickRandomEmployee(empList)),
                new Todo("Todo 1", LocalDate.of(2026, 3,2), pickRandomEmployee(empList)),
                new Todo("Todo 1", LocalDate.of(2026, 3,9), pickRandomEmployee(empList)),
                new Todo("Todo 1", LocalDate.of(2026, 4,10), pickRandomEmployee(empList)),
                new Todo("Todo 1", LocalDate.of(2026, 12,24),  pickRandomEmployee(empList))
        );

        todoRepo.saveAll(todos);
    }

    private Employee pickRandomEmployee(List<Employee> empList) {
        return empList.get((int)(Math.random() * (empList.size() - 1)));
    }
}
