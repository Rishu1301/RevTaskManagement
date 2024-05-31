package com.revtaskmanagement.RevTask.Repository;

import com.revtaskmanagement.RevTask.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
