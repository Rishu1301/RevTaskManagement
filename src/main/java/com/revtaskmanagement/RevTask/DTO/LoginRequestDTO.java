package com.revtaskmanagement.RevTask.DTO;

public class LoginRequestDTO {
        private String email;
        private String password;

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void getEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
