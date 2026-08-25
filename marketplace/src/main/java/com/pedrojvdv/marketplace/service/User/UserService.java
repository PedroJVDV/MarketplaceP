package com.pedrojvdv.marketplace.service.User;

import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.dto.User.UserDto;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import com.pedrojvdv.marketplace.exception.BadRequestException;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

    public void createUser(UserDto userDto) throws BadRequestException {
        UserEntity user = userRepository.findByEmail(userDto.getEmail())
                .orElse(null);

        if (user != null) {
            throw new BadRequestException("Já existe um usuário cadastrado com esse email!");
        }

        userRepository.save(UserEntity.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(UserRole.USER)
                .build());
    }

    public void updateUser(UserDto userDto, String email) throws NotFoundException {
        userRepository.findByEmail(email)
                .ifPresentOrElse(user -> {
                            user.setName(userDto.getName());
                            user.setEmail(userDto.getEmail());
                            user.setPassword(userDto.getPassword());
                            user.setRole(userDto.getUserRole());
                            userRepository.save(user);
                        },
                        () -> {
                            throw new NotFoundException("Usuário com esse email não existe!");
                        });
    }

    public void deleteUser(String email, String password, UserDto userDto) throws NotFoundException {
        userRepository.findByEmail(email)
                .ifPresentOrElse(user -> {
                    if (userDto.getPassword().equals(password) && user.getEmail().equals(email)) {
                        userRepository.delete(user);
                    } else {
                        throw new NotFoundException("Senha incorreta!");
                    }
                }, () -> {
                    throw new NotFoundException("Usuario com esse email não existe ou email incorreto!");
                });
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<UserDto> getUserByName(String username) throws NotFoundException {
        List<UserDto> user = userRepository.findByName(username)
                .stream()
                .map(this::toDto)
                .toList();
        if (user.isEmpty()) {
            throw new NotFoundException("Não existe um usuário com este nome!");
        }
        return user;
    }

    public Optional<UserDto> getUserByEmail(String email) throws NotFoundException {
        Optional<UserDto> user = userRepository.findByEmail(email)
                .stream()
                .map(this::toDto)
                .findFirst();
        if (user.isEmpty()) {
            throw new NotFoundException("Não existe um usuário com este email!");
        }
        return user;
    }

    public Optional<UserDto> getUserById(Long id) throws NotFoundException {
        Optional<UserDto> user = userRepository.findById(id)
                .stream()
                .map(this::toDto)
                .findFirst();
        if (user.isEmpty()) {
            throw new NotFoundException("Nenhum usuário encontrado com este ID!");
        }
        return user;
    }

    //TODO: STATIC ROLES... (just thinking)
    public Optional<UserDto> getUserByRole(UserRole userRole) throws NotFoundException {
        Optional<UserDto> user = userRepository.findByRole(userRole)
                .stream()
                .map(this::toDto)
                .findFirst();
        if (user.isEmpty()) {
            throw new NotFoundException("Usuário com a função especificada não existe!");
        }
        return user;
    }

    private UserDto toDto(UserEntity p) {
        UserDto dto = new UserDto();
        dto.setName(p.getName());
        dto.setEmail(p.getEmail());
        dto.setPassword(p.getPassword());
        dto.setUserRole(p.getRole());
        return dto;
    }
}
