package com.pedrojvdv.marketplace.service.Auth;

import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final IUserRepository userRepository;

    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         return userRepository.findByUsernameLogin(username)
                 .orElseThrow(() -> new UsernameNotFoundException("usuário não encontado: " + username
                 ));
    }
}
