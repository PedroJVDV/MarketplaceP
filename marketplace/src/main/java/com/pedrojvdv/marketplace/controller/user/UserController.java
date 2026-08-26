package com.pedrojvdv.marketplace.controller.user;

import com.pedrojvdv.marketplace.dto.User.UserDto;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import com.pedrojvdv.marketplace.exception.NotFoundException;
import com.pedrojvdv.marketplace.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    //NOW, THIS CLASS IS MANAGED ONLY BY ADMINS!

    @GetMapping("/{email}/admin")
    @ResponseStatus(HttpStatus.OK)
    public void findByEmail(@PathVariable("email") String email)throws NotFoundException {
        userService.getUserByEmail(email);
    }

    @GetMapping("/role/admin")
    @ResponseStatus(HttpStatus.OK)
    public void findByRole(@RequestParam UserRole role)throws NotFoundException {
        userService.getUserByRole(role);
    }

    @GetMapping("/name/admin")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findByName(@RequestParam String name)throws NotFoundException {
        return userService.getUserByName(name);
    }

}
