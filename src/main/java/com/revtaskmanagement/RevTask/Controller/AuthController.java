package com.revtaskmanagement.RevTask.Controller;

import com.revtaskmanagement.RevTask.DTO.LoginRequestDTO;
import com.revtaskmanagement.RevTask.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api/auth")
public class AuthController {

//    @Autowired
//    private AdminService adminService;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
//        boolean isValid = adminService.validateLogin(loginRequest.getUsername(), loginRequest.getPassword());
//        if (isValid) {
//            return ResponseEntity.ok("Login successful. Redirecting to home page...");
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }
}