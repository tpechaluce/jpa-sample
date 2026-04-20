package com.example.demo.repository;

import com.example.demo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("""
        SELECT t.title, t.dueAt, t.createdAt, CONCAT(emp.fname, " ", emp.lname)
        FROM Todo t
        RIGHT JOIN t.assignedEmployee as emp
        WHERE UPPER(CONCAT(emp.fname, " ", emp.lname)) = UPPER(:empName)
        """)
    List<Todo> getAllTodoByEmployee(String empName);
}
