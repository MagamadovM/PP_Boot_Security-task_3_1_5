package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Set;

@Service
public class AdminUserInitService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminUserInitService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Transactional
    public void createUsersAndRoles() {
        if (userRepository.findByUserName("admin").isEmpty()) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role(1L, "ROLE_ADMIN")));
            roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> roleRepository.save(new Role(2L, "ROLE_USER")));

            User admin = new User();
            admin.setName("Admin");
            admin.setLastName("User");
            admin.setAge((byte) 30);
            admin.setEmail("admin@example.com");
            admin.setUserName("admin");
            admin.setPassword("admin");
            admin.setRoleSet(Set.of(adminRole));

            userRepository.save(admin);
        }
    }
}
