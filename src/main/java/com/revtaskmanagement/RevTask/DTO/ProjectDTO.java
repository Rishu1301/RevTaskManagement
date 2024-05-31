package com.revtaskmanagement.RevTask.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ProjectDTO {
//        private Long projectId;
//        private String projectName;
//        private String projectDescription;
//        private String clientName;
//        private Long projectManagerId;
//        private String projectManagerName;
//        private List<TaskDTO> taskNames;

        private Long id;
        private String name;
        private String description;
        private String clientName;
        private Long projectManagerId;
        private String projectManagerName;
        private List<TaskDTO> tasks;

//        public ProjectDTO(Long id, String name, String description, String clientName, Long projectManagerId, String projectManagerName, List<TaskDTO> tasks) {
//            this.id = id;
//            this.name = name;
//            this.description = description;
//            this.clientName = clientName;
//            this.projectManagerId = projectManagerId;
//            this.projectManagerName = projectManagerName;
//            this.tasks = tasks;
//        }
    }
