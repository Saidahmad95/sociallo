package com.example.sociallo.utils;

import com.example.sociallo.enums.ERole;
import com.example.sociallo.model.Role;
import com.example.sociallo.repos.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomRunner implements ApplicationRunner {

    private final RoleRepo roleRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepo.findAll().isEmpty()) {
            for (ERole eRole : ERole.values()) {
                roleRepo.save(Role
                        .builder()
                        .roleName(eRole)
                        .build());
            }
        }
    }


}
