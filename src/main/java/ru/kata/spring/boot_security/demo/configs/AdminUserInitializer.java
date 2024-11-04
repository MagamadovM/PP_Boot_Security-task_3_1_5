package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.services.AdminUserInitService;

@Configuration
public class AdminUserInitializer {

    private final AdminUserInitService adminUserInitService;

    public AdminUserInitializer(AdminUserInitService adminUserInitService) {
        this.adminUserInitService = adminUserInitService;
    }

    @Bean
    public ApplicationRunner initializeAdminUser() {
        return args -> adminUserInitService.createUsersAndRoles();
    }

}
