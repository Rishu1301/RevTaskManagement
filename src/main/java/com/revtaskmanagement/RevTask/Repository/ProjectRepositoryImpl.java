package com.revtaskmanagement.RevTask.Repository;

import com.revtaskmanagement.RevTask.DTO.ProjectDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProjectDTO> findAllProjectDTOs() {
        String jpql = "SELECT new com.revtaskmanagement.RevTask.DTO.ProjectDTO(" +
                "p.id, p.name, p.description, c.name, pm.id, pm.username, " +
                "p.tasks.stream().map(t -> new com.revtaskmanagement.RevTask.DTO.TaskDTO(t.id, t.title)).collect(Collectors.toList())) " +
                "FROM Project p " +
                "JOIN p.client c " +
                "JOIN p.projectManager pm";
        TypedQuery<ProjectDTO> query = entityManager.createQuery(jpql, ProjectDTO.class);
        return query.getResultList();
    }

    @Override
    public ProjectDTO findProjectDTOById(Long id) {
        String jpql = "SELECT new com.revtaskmanagement.RevTask.DTO.ProjectDTO(" +
                "p.id, p.name, p.description, c.name, pm.id, pm.username, " +
                "p.tasks.stream().map(t -> new com.revtaskmanagement.RevTask.DTO.TaskDTO(t.id, t.title)).collect(Collectors.toList())) " +
                "FROM Project p " +
                "JOIN p.client c " +
                "JOIN p.projectManager pm " +
                "WHERE p.id = :id";
        TypedQuery<ProjectDTO> query = entityManager.createQuery(jpql, ProjectDTO.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
