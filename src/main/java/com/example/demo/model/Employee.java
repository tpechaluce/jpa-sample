package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;

    private final String fname;
    private final String lname;
    private String phoneNumber;
    private String department;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> assignedTasks;
}
