package com.revtaskmanagement.RevTask.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Project {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String description;

        @ManyToOne
        @JoinColumn(name = "project_manager_id")
        private ProjectManager projectManager;

        @ManyToMany
        @JoinTable(
                name = "project_team_member",
                joinColumns = @JoinColumn(name = "project_id"),
                inverseJoinColumns = @JoinColumn(name = "team_member_id")
        )
        private List<TeamMember> teamMembers;

        @OneToMany(mappedBy = "project")
        private List<Task> tasks;

        @ManyToOne
        @JoinColumn(name = "client_id")
        private Client client;

        @OneToOne(mappedBy = "project")
        private ProjectDetail projectDetail;


        // Added one


//        @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//        private List<Task> tasks;
//
//        @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//        private List<TeamMember> teamMembers;
//
//        @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//        private List<ProjectManager> projectManagers;
//
//        @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
//        private List<Client> clients;

}
