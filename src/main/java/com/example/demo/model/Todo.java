package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long todoId;

    private final String title;
    private String description;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
    private final LocalDate dueAt;



    @ManyToOne(optional = false)
    private final Employee assignedEmployee;

    public enum Status {
        PENDING, COMPLETED,
    }
}
