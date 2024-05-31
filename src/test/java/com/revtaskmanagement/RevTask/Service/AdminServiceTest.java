package com.revtaskmanagement.RevTask.Service;

import com.revtaskmanagement.RevTask.Entity.Admin;
import com.revtaskmanagement.RevTask.Repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetAllAdmins() {
        // Arrange
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin(1L, "admin1", "admin1@example.com", "password1"));
        admins.add(new Admin(2L, "admin2", "admin2@example.com", "password2"));
        when(adminRepository.findAll()).thenReturn(admins);

        // Act
        List<Admin> result = adminService.getAllAdmins();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void testGetAdminById() {
        // Arrange
        Admin admin = new Admin(1L, "admin1", "admin1@example.com", "password1");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        // Act
        Admin result = adminService.getAdminById(1L);

        // Assert
        assertEquals(admin, result);
    }

    @Test
    void testCreateAdmin() {
        // Arrange
        Admin admin = new Admin(1L, "admin1", "admin1@example.com", "password1");
        when(adminRepository.save(admin)).thenReturn(admin);

        // Act
        Admin result = adminService.createAdmin(admin);

        // Assert
        assertEquals(admin, result);
    }
}
