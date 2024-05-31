package com.revtaskmanagement.RevTask.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private List<ProjectCustomDTO> Projects;
}
