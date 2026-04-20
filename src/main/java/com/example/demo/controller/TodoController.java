package com.example.demo.controller;

import com.example.demo.dto.TodoRequestDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Todo;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@AllArgsConstructor
public class TodoController {
    private TodoRepository todoRepo;
    private EmployeeRepository empRepo;

    @GetMapping
    public List<Todo> getAllTodo(){
        return todoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable("id") Long todoId) {
        return todoRepo.findById(todoId).orElseThrow();
    }

    @GetMapping("/search")
    public List<Todo> getAllTodoByEmployee(@RequestParam("employeeName") String empName) {
        return todoRepo.getAllTodoByEmployee(empName);
    }

    @PostMapping
    public Todo saveTodo(@RequestBody TodoRequestDTO todo) {
        return todoRepo.save(toEntity(todo));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        todoRepo.deleteById(id);
    }

    Todo toEntity(TodoRequestDTO todo) {
        Employee emp = empRepo.findById(todo.empId()).orElseThrow(); //
        return new Todo(todo.title(), todo.dueAt(), emp);
    }
}
