package com.nnk.springboot.service;
import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private DBUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<DBUser> findAll() {
        return userRepository.findAll();
    }

    public Optional<DBUser> findById(Integer id) {
        return userRepository.findById(id);
    }

    public DBUser save(DBUser user) {
        // On encode le mot de passe avant sauvegarde
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public DBUser update(DBUser updatedUser) {
        DBUser existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + updatedUser.getId()));

        existingUser.setFullname(updatedUser.getFullname());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setRole(updatedUser.getRole());

        // Si l’admin a changé le mot de passe, on le réencode
        if (!updatedUser.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
