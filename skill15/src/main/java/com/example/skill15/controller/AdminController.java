package com.example.skill15.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/add")
    public String addEmployee() {
        return "Employee added successfully (Admin focus)";
    }

    @DeleteMapping("/delete")
    public String deleteEmployee() {
        return "Employee deleted successfully (Admin focus)";
    }
}
