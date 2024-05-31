package com.revtaskmanagement.RevTask.Service;

//import com.revtaskmanagement.RevTask.Entity.Admin;
//import com.revtaskmanagement.RevTask.Repository.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AdminService {
//    @Autowired
//    private AdminRepository adminRepository;
//
//    public List<Admin> getAllAdmins() {
//        return adminRepository.findAll();
//    }
//
//    public Admin getAdminById(Long id) {
//        return adminRepository.findById(id).orElseThrow();
//    }
//
//    public Admin createAdmin(Admin admin) {
//        return adminRepository.save(admin);
//    }
//
//    public Admin updateAdmin(Long id, Admin adminDetails) {
//        Admin admin = adminRepository.findById(id).orElseThrow();
//        admin.setUsername(adminDetails.getUsername());
//        admin.setPassword(adminDetails.getPassword());
//        admin.setEmail(adminDetails.getEmail());
//        return adminRepository.save(admin);
//    }
//
//    public void deleteAdmin(Long id) {
//        adminRepository.deleteById(id);
//    }
//}


// Working directory before logging

//import com.revtaskmanagement.RevTask.Entity.Admin;
//import com.revtaskmanagement.RevTask.Repository.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AdminService {
//    @Autowired
//    private AdminRepository adminRepository;
//
//    public List<Admin> getAllAdmins() {
//        return adminRepository.findAll();
//    }
//
//    public Admin getAdminById(Long id) {
//        return adminRepository.findById(id).orElseThrow();
//    }
//
//    public Admin createAdmin(Admin admin) {
//        return adminRepository.save(admin);
//    }
//
////    public Admin updateAdmin(Long id, Admin adminDetails) {
////        Admin admin = adminRepository.findById(id).orElseThrow();
////        admin.setUsername(adminDetails.getUsername());
////        admin.setPassword(adminDetails.getPassword());
////        admin.setEmail(adminDetails.getEmail());
////        return adminRepository.save(admin);
////    }
//
//    public void deleteAdmin(Long id) {
//        adminRepository.deleteById(id);
//    }
//}

import com.revtaskmanagement.RevTask.Entity.Admin;
import com.revtaskmanagement.RevTask.Repository.AdminRepository;
import org.apache.naming.factory.SendMailFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        logger.info("Fetching all admins");
        List<Admin> admins = adminRepository.findAll();
        logger.info("Fetched {} admins", admins.size());
        return admins;
    }

    public Admin getAdminById(Long id) {
        logger.info("Fetching admin with id {}", id);
        return adminRepository.findById(id).orElseThrow(() -> {
            logger.error("Admin not found with id {}", id);
            return new RuntimeException("Admin not found with id " + id);
        });
    }

    public Admin createAdmin(Admin admin) {
        logger.info("Creating new admin with username {}", admin.getUsername());
        Admin createdAdmin = adminRepository.save(admin);
        logger.info("Created admin with id {}", createdAdmin.getId());
        return createdAdmin;
    }

    public void deleteAdmin(Long id) {
        logger.info("Deleting admin with id {}", id);
        adminRepository.deleteById(id);
        logger.info("Deleted admin with id {}", id);
    }

    // for loging

    public boolean validateLogin(String email, String password) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return password.equals(admin.getPassword());
        }
        return false;
    }
}


