package com.pedrojvdv.marketplace.service.User;

import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.dto.User.UserAdminInputDto;
import com.pedrojvdv.marketplace.dto.User.UserAdminResponseDto;
import com.pedrojvdv.marketplace.dto.User.UserResponseDto;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import com.pedrojvdv.marketplace.exception.BadRequestException;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void createUser(UserAdminInputDto userDto) throws BadRequestException {
        UserEntity user = userRepository.findByEmail(userDto.getEmail())
                .orElse(null);

        if (user != null) {
            throw new BadRequestException("Já existe um usuário cadastrado com esse email!");
        }

        userRepository.save(UserEntity.builder()
                .name(userDto.getUsername())
                .email(userDto.getEmail())
                .usernameLogin(userDto.getUsernameLogin())
                .age(userDto.getAge())
                .role(UserRole.USER)
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("isAuthenticated() && hasAnyRole('USER', 'SELLER', 'ADMIN')")
    public void updateUser(UserResponseDto userDto) throws NotFoundException {
        userRepository.findByEmail(userDto.getEmail())
                .ifPresentOrElse(user -> {
                            user.setName(userDto.getUsername());
                            user.setEmail(userDto.getEmail());
                            user.setPassword(userDto.getPassword());
                            userRepository.save(user);
                        },
                        () -> {
                            throw new NotFoundException("Usuário com esse email não existe!");
                        });
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser( UserAdminInputDto userDto) throws NotFoundException {
        userRepository.findByEmail(userDto.getEmail())
                .ifPresentOrElse(user -> {
                    if (user.getEmail().equals(userDto.getEmail())) {
                        userRepository.delete(user);
                    } else {
                        throw new NotFoundException("Senha incorreta!");
                    }
                }, () -> {
                    throw new NotFoundException("Usuario com esse email não existe ou email incorreto!");
                });
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserAdminResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDtoResponseUser)
                .toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<UserAdminResponseDto> getUserByUsernameLogin(String username) throws NotFoundException {
        Optional<UserAdminResponseDto> user = userRepository.findByUsernameLogin(username)
                .stream()
                .map(this::toDtoResponseUser)
                .findFirst();
        if (user.isEmpty()) {
            throw new NotFoundException("Não existe um usuário com este login!");
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<UserAdminResponseDto> getUserByEmail(String email) throws NotFoundException {
        Optional<UserAdminResponseDto> user = userRepository.findByEmail(email)
                .stream()
                .map(this::toDtoResponseUser)
                .findFirst();
        if (user.isEmpty()) {
            throw new NotFoundException("Não existe um usuário com este email!");
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<UserAdminResponseDto> getUserById(Long id) throws NotFoundException {
        Optional<UserAdminResponseDto> user = userRepository.findById(id)
                .stream()
                .map(this::toDtoResponseUser)
                .findFirst();
        if (user.isEmpty()) {
            throw new NotFoundException("Nenhum usuário encontrado com este ID!");
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserAdminResponseDto> getUserByRole(UserRole userRole) throws NotFoundException {
        List<UserAdminResponseDto> user = userRepository.findByRole(userRole)
                .stream()
                .map(this::toDtoResponseUser)
                .toList();
        if (user.isEmpty()) {
            throw new NotFoundException("Usuário com a função especificada não existe!");
        }
        return user;
    }

    private UserResponseDto toDtoInputUser(UserEntity user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUsername(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    private UserAdminResponseDto toDtoResponseUser(UserEntity user) {
        UserAdminResponseDto dto = new UserAdminResponseDto();
        dto.setId(user.getId());
        dto.setUsernameLogin(user.getUsername());
        dto.setUsername(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUserRole(user.getRole());
        return dto;
    }
}
