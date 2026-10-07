package com.pedrojvdv.marketplace.service.User;

import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.dto.User.UserResponseDto;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

//    public void createUser(UserDto userDto) throws BadRequestException {
//        UserEntity user = userRepository.findByEmail(userDto.getEmail())
//                .orElse(null);
//
//        if (user != null) {
//            throw new BadRequestException("Já existe um usuário cadastrado com esse email!");
//        }
//
//        userRepository.save(UserEntity.builder()
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .role(UserRole.USER)
//                .build());
//    }

//    public void updateUser(UserDto userDto, String email) throws NotFoundException {
//        userRepository.findByEmail(email)
//                .ifPresentOrElse(user -> {
//                            user.setName(userDto.getName());
//                            user.setEmail(userDto.getEmail());
//                            user.setPassword(userDto.getPassword());
//                            user.setRole(userDto.getUserRole());
//                            userRepository.save(user);
//                        },
//                        () -> {
//                            throw new NotFoundException("Usuário com esse email não existe!");
//                        });
//    }

//    public void deleteUser(String email, String password, UserDto userDto) throws NotFoundException {
//        userRepository.findByEmail(email)
//                .ifPresentOrElse(user -> {
//                    if (userDto.getPassword().equals(password) && user.getEmail().equals(email)) {
//                        userRepository.delete(user);
//                    } else {
//                        throw new NotFoundException("Senha incorreta!");
//                    }
//                }, () -> {
//                    throw new NotFoundException("Usuario com esse email não existe ou email incorreto!");
//                });
//    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<UserResponseDto> getUserByUsernameLogin(String username) throws NotFoundException {
        Optional<UserResponseDto> user = userRepository.findByUsernameLogin(username)
                .stream()
                .map(this::toDto)
                .findFirst();
        if (user.isEmpty()) {
            throw new NotFoundException("Não existe um usuário com este login!");
        }
        return user;
    }

    public Optional<UserResponseDto> getUserByEmail(String email) throws NotFoundException {
        Optional<UserResponseDto> user = userRepository.findByEmail(email)
                .stream()
                .map(this::toDto)
                .findFirst();
        if (user.isEmpty()) {
            throw new NotFoundException("Não existe um usuário com este email!");
        }
        return user;
    }

    public Optional<UserResponseDto> getUserById(Long id) throws NotFoundException {
        Optional<UserResponseDto> user = userRepository.findById(id)
                .stream()
                .map(this::toDto)
                .findFirst();
        if (user.isEmpty()) {
            throw new NotFoundException("Nenhum usuário encontrado com este ID!");
        }
        return user;
    }

    public List<UserResponseDto> getUserByRole(UserRole userRole) throws NotFoundException {
        List<UserResponseDto> user = userRepository.findByRole(userRole)
                .stream()
                .map(this::toDto)
                .toList();
        if (user.isEmpty()) {
            throw new NotFoundException("Usuário com a função especificada não existe!");
        }
        return user;
    }

    private UserResponseDto toDto(UserEntity p) {
        UserResponseDto dto = new UserResponseDto();
        dto.setName(p.getName());
        dto.setEmail(p.getEmail());
        dto.setUserRole(p.getRole());
        return dto;
    }
}
