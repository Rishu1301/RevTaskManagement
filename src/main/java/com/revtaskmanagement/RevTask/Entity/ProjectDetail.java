package com.revtaskmanagement.RevTask.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String detail;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;


}
