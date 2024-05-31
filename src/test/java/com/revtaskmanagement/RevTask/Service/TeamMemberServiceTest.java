package com.revtaskmanagement.RevTask.Service;

import com.revtaskmanagement.RevTask.DTO.TeamMemberDTO;
import com.revtaskmanagement.RevTask.Entity.TeamMember;
import com.revtaskmanagement.RevTask.Exception.ResourceNotFoundException;
import com.revtaskmanagement.RevTask.Repository.TeamMemberRepository;
import com.revtaskmanagement.RevTask.Service.TeamMemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamMemberServiceTest {

    @Mock
    private TeamMemberRepository teamMemberRepository;

    @InjectMocks
    private TeamMemberService teamMemberService;

    private TeamMemberDTO teamMemberDTO1;
    private TeamMemberDTO teamMemberDTO2;


    private TeamMember teamMember;

    @BeforeEach
    public void setUp() {
        teamMember = new TeamMember();
        teamMember.setId(1L);
        teamMember.setUsername("user1");
        teamMember.setPassword("password");
        teamMember.setEmail("user1@example.com");
        teamMember.setRole("Admin");
        teamMember.setActive(true);
    }


    @Test
    public void testGetTeamMemberById() {
        TeamMemberDTO teamMemberDTO = new TeamMemberDTO(1L, "test@example.com", "ROLE_USER");
        when(teamMemberRepository.findTeamMemberDTOById(1L)).thenReturn(teamMemberDTO);

        TeamMemberDTO result = teamMemberService.getTeamMemberById(1L);

        verify(teamMemberRepository).findTeamMemberDTOById(1L);
        assertEquals("test@example.com", result.getEmail());
        assertEquals("ROLE_USER", result.getRole());
    }

    @Test
    public void testUpdateTeamMemberEmailAndRole() {
        // Mocking repository behavior
        TeamMember teamMember = new TeamMember();
        teamMember.setId(1L);
        when(teamMemberRepository.findById(1L)).thenReturn(Optional.of(teamMember));

        // Creating updated team member DTO
        TeamMemberDTO updatedTeamMemberDTO = new TeamMemberDTO();
        updatedTeamMemberDTO.setEmail("newEmail@example.com");
        updatedTeamMemberDTO.setRole("newRole");

        // Calling the service method
        teamMemberService.updateTeamMemberEmailAndRole(1L, updatedTeamMemberDTO);

        // Verifying that the repository method was called with the correct parameters
        verify(teamMemberRepository).updateTeamMemberEmailAndRole(1L, "newEmail@example.com", "newRole");
    }

    @Test
    public void testGetAllTeamMembers() {
        // Arrange
        List<TeamMemberDTO> expectedTeamMembers = Arrays.asList(teamMemberDTO1, teamMemberDTO2);
        when(teamMemberRepository.findAllTeamMembersDTO()).thenReturn(expectedTeamMembers);

        // Act
        List<TeamMemberDTO> actualTeamMembers = teamMemberService.getAllTeamMembers();

        // Assert
        assertNotNull(actualTeamMembers);
        assertEquals(2, actualTeamMembers.size());
        assertEquals(expectedTeamMembers, actualTeamMembers);
        verify(teamMemberRepository, times(1)).findAllTeamMembersDTO();
    }

    @Test
    public void testCreateTeamMember() {
        // Arrange
        when(teamMemberRepository.save(teamMember)).thenReturn(teamMember);

        // Act
        TeamMember createdTeamMember = teamMemberService.createTeamMember(teamMember);

        // Assert
        assertNotNull(createdTeamMember);
        assertEquals(teamMember.getId(), createdTeamMember.getId());
        assertEquals(teamMember.getUsername(), createdTeamMember.getUsername());
        assertEquals(teamMember.getPassword(), createdTeamMember.getPassword());
        assertEquals(teamMember.getEmail(), createdTeamMember.getEmail());
        assertEquals(teamMember.getRole(), createdTeamMember.getRole());
        verify(teamMemberRepository, times(1)).save(teamMember);
    }

    @Test
    public void testDeactivateTeamMember() {
        // Arrange
        when(teamMemberRepository.findById(1L)).thenReturn(Optional.of(teamMember));

        // Act
        teamMemberService.deactivateTeamMember(1L);

        // Assert
        assertFalse(teamMember.isActive());
        verify(teamMemberRepository, times(1)).findById(1L);
        verify(teamMemberRepository, times(1)).save(teamMember);
    }

    @Test
    public void testDeactivateTeamMember_NotFound() {
        // Arrange
        when(teamMemberRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            teamMemberService.deactivateTeamMember(1L);
        });
        verify(teamMemberRepository, times(1)).findById(1L);
        verify(teamMemberRepository, never()).save(any());
    }

    @Test
    public void testActivateTeamMember() {
        // Arrange
        teamMember.setActive(false);
        when(teamMemberRepository.findById(1L)).thenReturn(Optional.of(teamMember));

        // Act
        teamMemberService.activateTeamMember(1L);

        // Assert
        assertTrue(teamMember.isActive());
        verify(teamMemberRepository, times(1)).findById(1L);
        verify(teamMemberRepository, times(1)).save(teamMember);
    }

    @Test
    public void testActivateTeamMember_NotFound() {
        // Arrange
        when(teamMemberRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            teamMemberService.activateTeamMember(1L);
        });
        verify(teamMemberRepository, times(1)).findById(1L);
        verify(teamMemberRepository, never()).save(any());
    }
}
