package com.example.demo.dto;

import java.time.LocalDate;

public record TodoRequestDTO(
        String title,
        String description,
        LocalDate dueAt,
        Long empId
) {
}
