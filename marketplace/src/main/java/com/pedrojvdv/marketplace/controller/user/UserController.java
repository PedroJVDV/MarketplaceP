package com.pedrojvdv.marketplace.controller.user;

import com.pedrojvdv.marketplace.dto.User.UserDto;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    //NOW, THIS CLASS IS MANAGED ONLY BY ADMINS!

    @GetMapping("/filter/email/admin")
    @ResponseStatus(HttpStatus.OK)
    public Optional<UserDto> findByEmail(@RequestParam String email)throws NotFoundException {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/filter/role/admin")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findByRole(@RequestParam UserRole role)throws NotFoundException {
        return userService.getUserByRole(role);
    }

    @GetMapping("/filter/name/admin")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDetails> findByName(@RequestParam String name)throws NotFoundException {
        return userService.getUserByUsernameLogin(name);
    }

}
