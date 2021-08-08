package com.yug.startup.security.service;

import com.yug.startup.controller.token.ConfirmationTokenService;
import com.yug.startup.model.User;
import com.yug.startup.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found.", username)));
        return UserDetailsImplementation.build(user);
    }

    public Boolean checkUserByUsername(String username) {
        boolean userExistsByUsername = userRepository.findByUsername(username).isPresent();

        if(userExistsByUsername) {
            User existingUserByUsername = userRepository.findByUsername(username).get();
            UserDetailsImplementation existingUserDetailsImplementation = UserDetailsImplementation.build(existingUserByUsername);
            return !existingUserDetailsImplementation.isEnabled();
        }

        return true;
    }

    public Boolean checkUserByEmail(String email) {
        boolean userExistsByEmail = userRepository.findByEmail(email).isPresent();

        if(userExistsByEmail) {
            User existingUserByEmail = userRepository.findByEmail(email).get();
            UserDetailsImplementation existingUserDetailsImplementation = UserDetailsImplementation.build(existingUserByEmail);
            return !existingUserDetailsImplementation.isEnabled();
        }

        return true;
    }

    public int enableUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
