package ru.kata.spring.boot_security.demo.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    @Query("SELECT  u FROM User u JOIN FETCH u.roleSet WHERE u.id = :id")
    Optional<User> findById(@NotNull Long id);
}
