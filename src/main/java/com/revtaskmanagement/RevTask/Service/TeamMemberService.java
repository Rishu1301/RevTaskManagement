package com.revtaskmanagement.RevTask.Service;

import com.revtaskmanagement.RevTask.DTO.ProjectDTO;
import com.revtaskmanagement.RevTask.DTO.TeamMemberDTO;
import com.revtaskmanagement.RevTask.Entity.Project;
import com.revtaskmanagement.RevTask.Entity.TeamMember;
import com.revtaskmanagement.RevTask.Exception.ResourceNotFoundException;
import com.revtaskmanagement.RevTask.Repository.ProjectRepository;
import com.revtaskmanagement.RevTask.Repository.TeamMemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamMemberService {

    private static final Logger logger = LoggerFactory.getLogger(TeamMemberService.class);

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
  private ProjectRepository projectRepository;
    @Autowired
    private ProjectService projectService;

    public List<TeamMemberDTO> getAllTeamMembers() {
        logger.info("Fetching all team members");
        List<TeamMemberDTO> teamMembers = teamMemberRepository.findAllTeamMembersDTO();
        logger.info("Fetched {} team members", teamMembers.size());
        return teamMembers;
    }

    public TeamMemberDTO getTeamMemberById(Long id) {
        logger.info("Fetching team member with id {}", id);
        TeamMemberDTO teamMember = teamMemberRepository.findTeamMemberDTOById(id);
        if (teamMember == null) {
            logger.error("Team member not found with id {}", id);
            throw new ResourceNotFoundException("TeamMember not found with id: " + id);
        }
        logger.info("Fetched team member with id {}", id);
        return teamMember;
    }

    public TeamMember createTeamMember(TeamMember teamMember) {
        logger.info("Creating new team member with email {}", teamMember.getEmail());
        TeamMember createdTeamMember = teamMemberRepository.save(teamMember);
        logger.info("Created team member with id {}", createdTeamMember.getId());
        return createdTeamMember;
    }

    @Transactional
    public TeamMemberDTO updateTeamMemberEmailAndRole(Long id, TeamMemberDTO teamMemberDTO) {
        logger.info("Updating team member with id {}", id);
        teamMemberRepository.findById(id).orElseThrow(() -> {
            logger.error("TeamMember not found with id {}", id);
            return new EntityNotFoundException("TeamMember not found with id " + id);
        });

        teamMemberRepository.updateTeamMemberEmailAndRole(id, teamMemberDTO.getEmail(), teamMemberDTO.getRole());
        logger.info("Updated email and role for team member with id {}", id);

        TeamMemberDTO updatedTeamMember = teamMemberRepository.findTeamMemberDTOById(id);
        logger.info("Fetched updated team member with id {}", id);
        return updatedTeamMember;
    }

    @Transactional
    public void deactivateTeamMember(Long id) {
        logger.info("Deactivating team member with id {}", id);
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("TeamMember not found with id {}", id);
                    return new ResourceNotFoundException("TeamMember not found with id: " + id);
                });
        teamMember.setActive(false);
        teamMemberRepository.save(teamMember);
        logger.info("Deactivated team member with id {}", id);
    }

    @Transactional
    public void activateTeamMember(Long id) {
        logger.info("Activating team member with id {}", id);
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("TeamMember not found with id {}", id);
                    return new ResourceNotFoundException("TeamMember not found with id: " + id);
                });
        teamMember.setActive(true);
        teamMemberRepository.save(teamMember);
        logger.info("Activated team member with id {}", id);
    }

    // loging

    public boolean validateLogin(String email, String password) {
        return teamMemberRepository.findByEmail(email)
                .map(teamMember -> teamMember.getPassword().equals(password))
                .orElse(false);
    }


    public List<TeamMemberDTO> getTeamMembersByProjectId(Long projectId) {
        List<TeamMember> teamMembers = teamMemberRepository.findByProjectId(projectId);
        return teamMembers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private TeamMemberDTO mapToDTO(TeamMember teamMember) {
        return new TeamMemberDTO(
                teamMember.getId(),
                teamMember.getEmail(),
                //teamMember.getName(),
                teamMember.getRole()
                );
}
    public ProjectDTO addMemberToProject(Long projectId, Long memberId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        TeamMember member = teamMemberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        project.getTeamMembers().add(member);
        projectRepository.save(project);
        return projectService.mapToDTO(project);
    }
}


// working fine

//import com.revtaskmanagement.RevTask.DTO.TeamMemberDTO;
//import com.revtaskmanagement.RevTask.Entity.TeamMember;
//import com.revtaskmanagement.RevTask.Exception.ResourceNotFoundException;
//import com.revtaskmanagement.RevTask.Repository.TeamMemberRepository;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TeamMemberService {
//
//    @Autowired
//    private TeamMemberRepository teamMemberRepository;
//
//
//    public List<TeamMemberDTO> getAllTeamMembers() {
//        return teamMemberRepository.findAllTeamMembersDTO();
//    }
//
//    public TeamMemberDTO getTeamMemberById(Long id) {
//
//        return teamMemberRepository.findTeamMemberDTOById(id);
//    }
//
//    public TeamMember createTeamMember(TeamMember teamMember) {
//
//        return teamMemberRepository.save(teamMember);
//    }
//
//
//
//    @Transactional
//    public TeamMemberDTO updateTeamMemberEmailAndRole(Long id, TeamMemberDTO teamMemberDTO) {
//        // Check if the team member exists
//        teamMemberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("TeamMember not found with id " + id));
//
//        teamMemberRepository.updateTeamMemberEmailAndRole(id, teamMemberDTO.getEmail(), teamMemberDTO.getRole());
//
//        return teamMemberRepository.findTeamMemberDTOById(id);
//    }
//
////    public void deleteTeamMember(Long id) {
////
////        teamMemberRepository.deleteById(id);
////    }
//
////    public void deleteTeamMember(Long id) {
////
////        teamMemberRepository.deleteById(id);
////    }
//
//
//
//
//
//    @Transactional
//    public void deactivateTeamMember(Long id) {
//        TeamMember teamMember = teamMemberRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("TeamMember not found with id: " + id));
//        teamMember.setActive(false);
//        teamMemberRepository.save(teamMember);
//    }
//
//    @Transactional
//    public void activateTeamMember(Long id) {
//        TeamMember teamMember = teamMemberRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("TeamMember not found with id: " + id));
//        teamMember.setActive(true);
//        teamMemberRepository.save(teamMember);
//    }
//}
