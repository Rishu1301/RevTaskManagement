package com.revtaskmanagement.RevTask.Repository;

//import com.revtaskmanagement.RevTask.Entity.TeamMember;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
//}

import com.revtaskmanagement.RevTask.DTO.TeamMemberDTO;
import com.revtaskmanagement.RevTask.Entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.SplittableRandom;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    Optional<TeamMember> findByEmail(String email);

    @Query("SELECT tm FROM TeamMember tm JOIN tm.projects p WHERE p.id = :projectId")
    List<TeamMember> findByProjectId(@Param("projectId") Long projectId);

    @Query("select new com.revtaskmanagement.RevTask.DTO.TeamMemberDTO(t.id,t.email,t.role) from TeamMember t where t.active = true")
    List<TeamMemberDTO> findAllTeamMembersDTO();


    @Query("Select new com.revtaskmanagement.RevTask.DTO.TeamMemberDTO(t.id,t.email,t.role) from TeamMember t where t.id = :teamMemberId and t.active = true")
    TeamMemberDTO findTeamMemberDTOById(@Param("teamMemberId") Long id);

    @Query("select t from TeamMember t where t.id = :id and t.active = true")
    Optional<TeamMember> findActiveById(@Param("id") Long id);

    @Modifying
    @Query("update TeamMember t set t.email = :email, t.role = :role where t.id = :id")
    void updateTeamMemberEmailAndRole(@Param("id") Long id, @Param("email") String email, @Param("role") String role);
}

