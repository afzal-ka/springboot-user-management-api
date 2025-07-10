package com.usermanagement.api.service;

import com.usermanagement.api.dto.UserRequestDto;
import com.usermanagement.api.dto.UserResponseDto;
import com.usermanagement.api.entity.User;
import com.usermanagement.api.exception.EmailAlreadyExistsException;
import com.usermanagement.api.exception.UserNotFoundException;
import com.usermanagement.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserRequestDto requestDto) {
        if(repository.existsByEmail(requestDto.getEmail())){
            throw new EmailAlreadyExistsException("email already exists");
        }
        String hasPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = new User();
        user.setFullName(requestDto.getFullName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(hasPassword);
        user.setRole("USER");

        User savedUser = repository.save(user);
        return mapToResponseDto(savedUser);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        if(repository.existsByEmail(requestDto.getEmail())){
            throw new EmailAlreadyExistsException("email already exists");
        }

        String hasPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = new User();
        user.setFullName(requestDto.getFullName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(hasPassword);
        user.setRole("USER");

        User savedUser = repository.save(user);
        return mapToResponseDto(savedUser);
    }

    private UserResponseDto mapToResponseDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFullName(user.getFullName());
        responseDto.setEmail(user.getEmail());
        responseDto.setRole(user.getRole());
        return responseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return repository.findAll()
                .stream().map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getById(Long id) {
        User user = repository.findById(id).orElseThrow(()->new UserNotFoundException("user not found with id : "+id));
        return mapToResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = repository.findById(id).orElseThrow(()->new UserNotFoundException("user not found with id : "+id));

        String hashPassword = passwordEncoder.encode(requestDto.getPassword());
        user.setFullName(requestDto.getFullName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(hashPassword);

        return mapToResponseDto(repository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();

        User currentUser = repository.findByEmail(currentEmail)
                .orElseThrow(() -> new UserNotFoundException("Authenticated user not found"));

        User targetUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        boolean isAdmin = currentUser.getRole().equalsIgnoreCase("ADMIN");
        boolean isSelf = currentUser.getId().equals(id);

        if (!isAdmin && !isSelf) {
            throw new AccessDeniedException("You are not authorized to delete this user.");
        }

        repository.delete(targetUser);

    }
}
