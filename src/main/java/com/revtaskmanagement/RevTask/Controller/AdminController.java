package com.revtaskmanagement.RevTask.Controller;

//import com.revtaskmanagement.RevTask.Entity.Admin;
//import com.revtaskmanagement.RevTask.Service.AdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admins")
//public class AdminController {
//
//    @Autowired
//    private AdminService adminService;
//
//    @GetMapping
//    public List<Admin> getAllAdmins() {
//        return adminService.getAllAdmins();
//    }
//
//    @GetMapping("/{id}")
//    public Admin getAdminById(@PathVariable Long id) {
//        return adminService.getAdminById(id);
//    }
//
//    @PostMapping
//    public Admin createAdmin(@RequestBody Admin admin) {
//        return adminService.createAdmin(admin);
//    }
//
//    @PutMapping("/{id}")
//    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
//        return adminService.updateAdmin(id, adminDetails);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteAdmin(@PathVariable Long id) {
//        adminService.deleteAdmin(id);
//    }
//}


import com.revtaskmanagement.RevTask.DTO.LoginRequestDTO;
import com.revtaskmanagement.RevTask.DTO.UserDetails;
import com.revtaskmanagement.RevTask.Entity.Admin;
import com.revtaskmanagement.RevTask.Repository.AdminRepository;
import com.revtaskmanagement.RevTask.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {

//        return adminService.getAllAdmins();
        List<Admin> admin = adminService.getAllAdmins();
        if(admin==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(admin,HttpStatus.OK);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        if(admin==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO loginRequest) {
        boolean isValid = adminService.validateLogin(loginRequest.getEmail(), loginRequest.getPassword());
        Map<String, String> response = new HashMap<>();
        if (isValid) {
            response.put("message", "Login successful. Redirecting to home page...");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
}
}





}