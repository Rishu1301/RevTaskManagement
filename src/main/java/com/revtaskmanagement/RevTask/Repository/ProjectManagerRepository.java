package com.revtaskmanagement.RevTask.Repository;

//import com.revtaskmanagement.RevTask.Entity.ProjectManager;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface ProjectManagerRepository extends JpaRepository<ProjectManager,Long> {
//}


import com.revtaskmanagement.RevTask.DTO.ProjectManagerDTO;
import com.revtaskmanagement.RevTask.DTO.ProjectManagerDTO;
import com.revtaskmanagement.RevTask.Entity.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectManagerRepository extends JpaRepository<ProjectManager,Long> {
    Optional<ProjectManager> findByEmail(String email);

    @Query("select new com.revtaskmanagement.RevTask.DTO.ProjectManagerDTO(p.id,p.username,p.email) from ProjectManager p")
    List<ProjectManagerDTO> findAllProjectManagersDTO();

    @Query("Select new com.revtaskmanagement.RevTask.DTO.ProjectManagerDTO(p.id,p.username,p.email) from ProjectManager p where p.id=:projectmanagerId")
    ProjectManagerDTO findProjectManagerByIdDTO(@Param("projectmanagerId") Long id);

}