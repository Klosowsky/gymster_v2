package com.gymster.backend.repositories;

import com.gymster.backend.models.Role;
import com.gymster.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

}
