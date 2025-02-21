package com.example.demo.service;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }

        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = new User(userDTO.getUsername(), hashedPassword);
        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }
}
