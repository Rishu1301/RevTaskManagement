package com.revtaskmanagement.RevTask.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectManagerDTO {
    private Long id;
    private String username;
    private String email;
}
