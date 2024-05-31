package com.revtaskmanagement.RevTask.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class TaskDetailDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Date assignDate;
    private Date dueDate;
    private String assignedTo;
    private Long projectId;


    public TaskDetailDTO(Long id, String title, String description, String status, String priority,
                   Date assignDate, Date dueDate, String assignedTo, Long projectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.assignDate = assignDate;
        this.dueDate = dueDate;
        this.assignedTo = assignedTo;
        this.projectId = projectId;
    }

}
