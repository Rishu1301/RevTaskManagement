package com.revtaskmanagement.RevTask.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberDTO {
    private Long id;
    private String email;
    private String role;
}
