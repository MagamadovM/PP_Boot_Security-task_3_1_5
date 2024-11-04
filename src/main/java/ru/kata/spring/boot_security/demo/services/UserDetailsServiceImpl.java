package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException(String.format("User not found by email %s ", email));
        }

        User userByEmail = user.get();
        Set<Role> grantedAuthorities = new HashSet<>(userByEmail.getRoleSet());

        return new User(
                userByEmail.getId(),
                userByEmail.getName(),
                userByEmail.getLastName(),
                userByEmail.getAge(),
                userByEmail.getEmail(),
                userByEmail.getUserName(),
                userByEmail.getPassword(),
                grantedAuthorities
        );
    }
}
