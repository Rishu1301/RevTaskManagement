package com.revtaskmanagement.RevTask.Repository;

import com.revtaskmanagement.RevTask.DTO.TaskDTO;
import com.revtaskmanagement.RevTask.DTO.TaskDetailDTO;
import com.revtaskmanagement.RevTask.Entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TaskDetailDTO> findAllTaskDTOs() {
        String jpql = "SELECT new com.revtaskmanagement.RevTask.DTO.TaskDetailDTO(" +
                "t.id, t.title, t.description, t.status, t.priority, " +
                "t.assignDate, t.dueDate, tm.username, p.id) " +
                "FROM Task t " +
                "JOIN t.assignedTo tm " +
                "JOIN t.project p";
        TypedQuery<TaskDetailDTO> query = entityManager.createQuery(jpql, TaskDetailDTO.class);
        return query.getResultList();
    }

    @Override
    public TaskDetailDTO findTaskDTOById(Long id) {
        String jpql = "SELECT new com.revtaskmanagement.RevTask.DTO.TaskDetailDTO(" +
                "t.id, t.title, t.description, t.status, t.priority, " +
                "t.assignDate, t.dueDate, tm.username, p.id) " +
                "FROM Task t " +
                "JOIN t.assignedTo tm " +
                "JOIN t.project p " +
                "WHERE t.id = :id";
        TypedQuery<TaskDetailDTO> query = entityManager.createQuery(jpql, TaskDetailDTO.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<TaskDetailDTO> findTasksByProjectId(Long projectId)

    {
        String jpql = "SELECT new com.revtaskmanagement.RevTask.DTO.TaskDetailDTO(" +
                "t.id, t.title, t.description, t.status, t.priority, " +
                "t.assignDate, t.dueDate, tm.username, p.id) " +
                "FROM Task t " +
                "JOIN t.assignedTo tm " +
                "JOIN t.project p " +
                "WHERE t.project.id = :projectId";
        TypedQuery<TaskDetailDTO> query = entityManager.createQuery(jpql, TaskDetailDTO.class);
        query.setParameter("projectId", projectId);
        return query.getResultList();


}

}
