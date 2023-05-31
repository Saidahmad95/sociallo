package com.example.sociallo.repos;

import com.example.sociallo.enums.ERole;
import com.example.sociallo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {


    Optional<Role> findByRoleName(ERole roleName);
}
