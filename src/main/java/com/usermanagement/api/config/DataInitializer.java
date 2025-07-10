package com.usermanagement.api.config;

import com.usermanagement.api.entity.User;
import com.usermanagement.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData(UserRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                User admin = User.builder()
                        .fullName("Admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role("ADMIN")
                        .build();

                User user = User.builder()
                        .fullName("Demo user")
                        .email("user@example.com")
                        .password(passwordEncoder.encode("user123"))
                        .role("USER")
                        .build();

                repository.save(admin);
                repository.save(user);
            }
        };
    }
}
