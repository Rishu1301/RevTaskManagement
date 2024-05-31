package com.revtaskmanagement.RevTask.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Task {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String title;

        private String description;

        @Column(nullable = false)
        private String status;

        @Column(nullable = false)
        private String priority;

        @Temporal(TemporalType.DATE)
        private Date assignDate;

        @Temporal(TemporalType.DATE)
        private Date dueDate;

        @ManyToOne
        @JoinColumn(name = "assigned_to")
        private TeamMember assignedTo;


        @ManyToOne
        @JoinColumn(name = "project_id")
        private Project project;



}
