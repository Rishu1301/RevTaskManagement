package com.revtaskmanagement.RevTask.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

//    @OneToMany(mappedBy = "client")
//    private List<Project> projects;


    // rishabh modification
    @OneToMany(mappedBy = "client")
    private List<Project> projects;

    public Client() {
    }

    public Client(Long id, String name, String email, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.projects = projects;
    }
}
