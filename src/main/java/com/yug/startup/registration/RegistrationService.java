package com.yug.startup.registration;

import com.yug.startup.app.user.AppUser;
import static com.yug.startup.app.user.AppUserRole.*;
import com.yug.startup.app.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalArgumentException("Email is not valid.");
        }

        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getEmail(),
                        USER,
                        false,
                        true
                )
        );
    }
}
